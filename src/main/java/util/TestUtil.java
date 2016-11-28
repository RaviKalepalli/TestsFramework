package util;

import org.apache.log4j.Logger;

public class TestUtil {

	public final static Logger LOGGER = Logger.getLogger(TestUtil.class);
	
	public static void sleep(long milliseconds){
		try{
			Thread.sleep(milliseconds);
		}catch (InterruptedException e){
			LOGGER.debug("The thread sleep has been interupted");
		}
	}
}
