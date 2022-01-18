# Transaction Test for Redisson client

## Build

Build is done with Maven, which need to be installed locally
```bash
bash build.sh 
```
builds a single "megajar" with all the dependencies `my-app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar` as well as docker container.

## Parameters

all parameters are passed as the environment variables
```bash
export REDIS_MASTER="redis://35.196.252.193:12000"
export REDIS_SLAVE="redis://34.73.135.35:13000"
export REDIS_SLEEP=5 #time to wait between the runs
export REDIS_PASSWORD="super-secret-password"
```
For docker use `-e REDIS_MASTER="redis://35.196.252.193:12000"` and for k8s `env` field in the container spec. See `k8s/redisson-test.yaml`

## Test locally
```bash
export REDIS_MASTER="redis://35.196.252.193:12000"
export REDIS_SLAVE="redis://34.73.135.35:13000"
export REDIS_SLEEP=5
#export REDIS_PASSWORD="super-secret-password"
java -cp my-app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.app.TEST
```
## Test docker image
```bash
docker run -e REDIS_MASTER="redis://35.196.252.193:12000" \
           -e REDIS_SLAVE="redis://34.73.135.35:13000" \
           -e REDIS_SLEEP=5 \
           jtest
```
## Pushing docker image 

Change `antonum` to your docker id
```bash
docker login #enter dockerhub credentials
docker image tag jtest:latest antonum/jtest
docker push antonum/jtest
```
## Running as a deployment in k8s
```bash
kubectl apply -f redisson-test.yaml 
```
All the parameters are in redisson-test.yaml as environment variables

## Accessing the logs in k8s
```bash
# identify the rediss-n-test pod id
kubectl get pods
# access logs for the pod
kubectl logs redisson-test-577d88bc8b-xzrlx
```