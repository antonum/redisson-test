FROM java
ADD my-app-1.0-SNAPSHOT-jar-with-dependencies.jar .
ADD loop.sh .
#CMD ["java","-cp", "my-app-1.0-SNAPSHOT-jar-with-dependencies.jar", "com.mycompany.app.TEST"]
CMD ["bash","loop.sh"]