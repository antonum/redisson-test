export REDIS_MASTER="redis://35.196.252.193:12000"
export REDIS_SLAVE="redis://34.73.135.35:13000"
export REDIS_SLEEP=5
java -cp my-app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.app.TEST
docker run -e REDIS_MASTER="redis://35.196.252.193:12000" \
           -e REDIS_SLAVE="redis://34.73.135.35:13000" \
           -e REDIS_SLEEP=5 \
           jtest