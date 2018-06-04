package com.performance.service.handler;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static Object lock = new Object();

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static ApplicationContext getApplicationContextNotNull() throws RuntimeException{
        if(null == applicationContext){
            try {
                synchronized (lock) {
                    lock.wait(60000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("获取spring context失败", e);
            }

        }
        return applicationContext;
    }
}
