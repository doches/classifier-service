package de.daslaboratorium.machinelearning.classifier;

import java.io.Serializable;
import java.util.Collection;

/**
 * A basic wrapper reflecting a classification.  It will store both featureset
 * and resulting classification.
 *
 * @author Philipp Nolte
 *
 * @param <T> The feature class.
 * @param <K> The category class.
 */
public class Classification<T, K> implements Serializable {

    private static final long serialVersionUID = 2020292L;

    /**
     * The classified featureset.
     * @serial
     */
    private Collection<T> featureset;

    /**
     * The category as which the featureset was classified.
     * @serial
     */
    private K category;

    /**
     * The probability that the featureset belongs to the given category.
     * @serial
     */
    private float probability;

    /**
     * Constructs a new Classification with the parameters given and a default
     * probability of 1.
     *
     * @param featureset The featureset.
     * @param category The category.
     */
    public Classification(Collection<T> featureset, K category) {
        this(featureset, category, 1.0f);
    }

    /**
     * Constructs a new Classification with the parameters given.
     *
     * @param featureset The featureset.
     * @param category The category.
     * @param probability The probability.
     */
    public Classification(Collection<T> featureset, K category,
            float probability) {
        this.featureset = featureset;
        this.category = category;
        this.probability = probability;
    }

    /**
     * Retrieves the featureset classified.
     *
     * @return The featureset.
     */
    public Collection<T> getFeatureset() {
        return featureset;
    }

    /**
     * Retrieves the classification's probability.
     * @return
     */
    public float getProbability() {
        return this.probability;
    }

    /**
     * Retrieves the category the featureset was classified as.
     *
     * @return The category.
     */
    public K getCategory() {
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Classification [category=" + this.category
                + ", probability=" + this.probability
                + ", featureset=" + this.featureset
                + "]";
    }

}
