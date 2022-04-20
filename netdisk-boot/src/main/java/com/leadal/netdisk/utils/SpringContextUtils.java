package com.leadal.netdisk.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtils.applicationContext == null){
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static Object getBean(String name, Class requiredType){
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name){
        return applicationContext.isSingleton(name);
    }

    public static Class getType(String name){
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name){
        return applicationContext.getAliases(name);
    }
}
