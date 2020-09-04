package com.revature;

import java.util.Timer; 
import java.util.TimerTask; 
import java.util.logging.FileHandler;

//For logging and concurrency

public class Time 
{   java.util.logging.Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());

    public Time()
    {   TimerTask task = new ScheduleTask();
        Timer timer = new Timer();
        try
        {   logger.setUseParentHandlers(false);
            FileHandler fileHandler = new FileHandler("status.log");
            logger.addHandler(fileHandler);
        }   catch (Exception e)
        {   logger.fine("Unable to build logger file"); }
        timer.scheduleAtFixedRate(task, 10000, 10000);
    }

    class ScheduleTask extends TimerTask
    {   public void run()
        {   logger.info(java.time.LocalTime.now() + " Program Running");
        }
    }
}