package com.lind.common;

import org.jboss.logging.Logger;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTask {
    private static final Logger logger = Logger.getLogger(AsyncUserRoleExpireTask.class);

    @Test
    public void timerTask() throws InterruptedException {

        logger.info("timetask");
        Timer timer = new Timer();
        AsyncUserRoleExpireTask myTimerTask = new AsyncUserRoleExpireTask("user-role-expire-task");
        //每1秒执行一次
        timer.schedule(myTimerTask, 2000L,1000L);
        Thread.sleep(10000);
    }
    public static class AsyncUserRoleExpireTask extends TimerTask {
        private static final Logger logger = Logger.getLogger(AsyncUserRoleExpireTask.class);
        private String taskName;

        public AsyncUserRoleExpireTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                logger.info(taskName + ":task doing");
            } catch (Throwable t) {
            }
        }
    }

}
