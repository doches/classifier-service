package com.quailhq.classifier.api.classifier;

import de.daslaboratorium.machinelearning.classifier.Classification;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/classifier")
public class ClassifierResource {
    public static final String UNKNOWN = "";

    private BayesClassifier<String, String> bayes;
    private String modelPath;

    public ClassifierResource(String modelPath, int capacity) {
        this.modelPath = modelPath;

        File existingModel = new File(modelPath);
        bayes = null;
        if (existingModel.exists()) {
            try {
                ObjectInputStream oin = new ObjectInputStream(new FileInputStream(existingModel));
                bayes = (BayesClassifier<String, String>)oin.readObject();
            } catch (Exception e) {
                Logger.getLogger(getClass().getCanonicalName()).log(Level.WARNING, "model path specified, but could not load model from file");
                bayes = null;
            }
        }

        if (bayes == null) {
            bayes = new BayesClassifier<String, String>();
        }

        bayes.setMemoryCapacity(capacity);
    }

    @Path("/learn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response learn(LearnRequest request) {
        Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "Learning '" + request.getExample() + "' as '" + request.getCategory() + "'");
        bayes.learn(request.getCategory(), Arrays.asList(request.getExample().split("\\s")));

        if (Math.random() < 0.1) {
            try {
                save();
            } catch (IOException e) {
                Logger.getLogger(getClass().getCanonicalName()).log(Level.WARNING, "Could not save model during learning");
                e.printStackTrace();
            }
        }

        return Response.ok().build();
    }

    @Path("/classify")
    @GET
    public Response classify(@QueryParam("q") String query, @QueryParam("t") @DefaultValue("0.1") Double threshold) {
        Classification<String, String> classification = bayes.classify(Arrays.asList(query.split("\\s")));
        String response = UNKNOWN;

        if (classification == null) {
            return Response.ok().entity(response).build();
        }

        Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, query + ": " + classification.getCategory() + "(" + classification.getProbability() + ")");

        if (classification.getProbability() > threshold) {
            response = classification.getCategory();
        }
        return Response.ok().entity(response).build();
    }

    @Path("/save")
    @POST
    public void save() throws IOException {
        FileOutputStream fout = new FileOutputStream(modelPath);
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(bayes);
        oout.close();
        fout.close();
    }
}
