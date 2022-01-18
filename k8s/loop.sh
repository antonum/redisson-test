while true; do 
   echo "Sleeping for $REDIS_SLEEP seconds"
   sleep $REDIS_SLEEP; 
   date
   java -cp my-app-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.app.TEST; 
done