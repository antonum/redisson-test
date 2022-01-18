//Original java file from Allen for the reference
//DO NOT EDIT
//All edits should go to:
//     my-app/src/main/java/com/mycompany/app/TEST.java
package org.redisson.allen;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RStream;
import org.redisson.api.RTransaction;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.transaction.TransactionException;

/**
 * Hello world!
 *
 */
public class TEST 
{
    public static void main( String[] args )
    {
    	Config config = new Config();
		config.useMasterSlaveServers().setMasterAddress("redis://35.196.252.193:12000")
		.setReadMode(ReadMode.SLAVE)
		.addSlaveAddress("redis://34.73.135.35:13000");
		//.addSlaveAddress("redis://35.231.189.246:14000");
		
		RedissonClient redisson = Redisson.create(config);

		try {
						
			TransactionOptions options = TransactionOptions.defaults()
			
			.syncSlavesTimeout(5, TimeUnit.SECONDS)

			// Response timeout
			// Default is 3000 milliseconds.
			.responseTimeout(3 * 60, TimeUnit.SECONDS)

			// Defines time interval for each attempt to send transaction if it hasn't been sent already.
			// Default is 1500 milliseconds.
			.retryInterval(2, TimeUnit.SECONDS)

			// Defines attempts amount to send transaction if it hasn't been sent already.
			// Default is 3 attempts.
			.retryAttempts(3)

			// If transaction hasn't committed within <code>timeout</code> it will rollback automatically.
			// Default is 5000 milliseconds.
			.timeout(5 * 60, TimeUnit.SECONDS);
			
			RTransaction transaction = redisson.createTransaction(options);

			RMap<String, String> map = transaction.getMap("{my}Map");
			map.delete();
			map.put("1", "2");
			
			System.out.println("Got here");
			
			String value = map.get("3");
			RSet<String> set = transaction.getSet("{my}Set");
			set.delete();
			set.add(value);

			System.out.println("Got here too");

			RStream<String, String> stream = redisson.getStream("{my}test");
			stream.delete();
			stream.add("0", "0");
			
			System.out.println("Got here too YAY");
			
			try {
			   transaction.commit();
			} catch(TransactionException e) {
			   transaction.rollback();
			}
			
			
						
			/*RList<Integer> numbers = redisson.getList("numbers");

			numbers.delete(); //Cleanup just in case
			
	        for(int i = 0; i < 100; i++)
	        	numbers.add(i);
			
	        System.out.println(numbers.readAll()); //Confirm numbers were written to Redis
	        
	        */
		}catch(Exception e) {
	    	System.out.println(e);
		}finally {
			if(redisson != null)
				redisson.shutdown();
			redisson = null;
		}
    }
}
