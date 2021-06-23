package automation.vaccinationSlot.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import automation.vaccinationSlot.helper.generic.ResourceHelper;

public class LoggerHelper 
{

	private static boolean root=false;
	
	/**
	 * This will implement logger file.
	 * @param clsss
	 * @return Logger class
	 */
	public static Logger getLogger(Class<?> cls)
	{
		if(root)
		{
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\log4j.properties"));
		return Logger.getLogger(cls);
	}	
	
}
