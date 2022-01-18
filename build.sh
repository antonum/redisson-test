cd my-app
mvn clean compile assembly:single
cd ../k8s
cp ../my-app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar .
docker build -t jtest .