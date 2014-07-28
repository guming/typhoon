package org.jinn.typhoon.utils;
import org.apache.log4j.Logger;

/**
 * common logger
 * @author guming
 */
public class CommonLogger {
	    public static long MC_FIRE_TIME = 200L;
	    public static long DB_FIRE_TIME = 500L;
	    public static long REDIS_FIRE_TIME = 300L;
	    private static Logger log = Logger.getLogger("typhoon");
	    private static Logger infoLog = Logger.getLogger("info");
	    private static Logger warnLog = Logger.getLogger("warn");
	    private static Logger errorLog = Logger.getLogger("error");
	    private static Logger joblog = Logger.getLogger("joblog");
	    private static Logger slowLog = Logger.getLogger("slowLog");
	    private static Logger fireLog = Logger.getLogger("fire");
	    private static Logger scribeLog = Logger.getLogger("scribe");
	    
	    public static boolean isTraceEnabled()
	    {
	        return log.isTraceEnabled();
	    }

	    public static boolean isDebugEnabled()
	    {
	        return log.isDebugEnabled();
	    }

	    public static void trace(Object msg)
	    {
	        log.trace(msg);
	    }

	    public static void debug(Object msg)
	    {
	        if(log.isDebugEnabled())
	            log.debug(msg);
	    }

	    public static void fire(Object msg)
	    {
	        if(fireLog.isInfoEnabled())
	            fireLog.info(msg);
	    }

	    public static void slowLog(Object msg)
	    {
	    	slowLog.warn(msg);
	    }

	    public static void scribe(Object msg)
	    {
	        if(scribeLog.isDebugEnabled())
	            scribeLog.debug(msg);
	    }

	    public static void info(Object msg)
	    {
	        if(infoLog.isInfoEnabled())
	            infoLog.info(msg);
	    }

	    public static void warn(Object msg)
	    {
	        warnLog.warn(msg);
	    }

	    public static void warn(Object msg, Throwable e)
	    {
	        warnLog.warn(msg, e);
	    }

	    public static void error(Object msg)
	    {
	        errorLog.error(msg);
	    }

	    public static void error(Object msg, Throwable e)
	    {
	        errorLog.error(msg, e);
	    }

	    public static void logForJob(Object msg)
	    {
	    	joblog.info(msg);
	    }

	    public static void logForJob(Object msg, Throwable e)
	    {
	    	joblog.info(msg, e);
	    }

}
