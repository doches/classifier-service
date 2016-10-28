# Classifier Service

This is a tiny standalone microservice that provides naive Bayes classification through a simple API.

## Api

### Learning

`POST` a JSON object containing a training `example` and a `category` to apply to `/api/classifier/learn`:

    curl -XPOST http://localhost:6000/api/classifier/learn -d '{"example": "sunshine and roses", "category": "positive"}' -H "Content-Type: application/json"
    curl -XPOST http://localhost:6000/api/classifier/learn -d '{"example": "fire and brimstone", "category": "negative"}' -H "Content-Type: application/json"
    
### Classifying

    curl -XPOST http://localhost:GET http://localhost:6000/api/classifier/classify?q=fire
    > negative
    
    curl -XPOST http://localhost:GET http://localhost:6000/api/classifier/classify?q=roses
    > positive
    
You can append an optional threshold parameter (`t`) to the url; if this is present, `/classify` will only return the best
matching category if the probability of that category exceeds `t`:

    curl -XGET "http://localhost:6000/api/classifier/classify?q=unicorns\&t=0.5"
    > 

## Dependencies

The only external dependency is Java 8. 

## Deploying

1. Build a distributable zip with `./gradlew distZip`.
2. Schlep this file over to your servers.
3. Unzip.
4. Start the server with `./bin/distribution server var/conf/server.yml`

## Development

1. Run `./gradlew idea` to generate an IntelliJ project.
2. Import the gradle project as well, for easily-refreshed dependencies.
3. Edit the generated run configuration, changing the module classpath to `classifier-service-server_main`. 