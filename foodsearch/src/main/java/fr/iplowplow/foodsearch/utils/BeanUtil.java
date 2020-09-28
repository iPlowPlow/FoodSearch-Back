package fr.iplowplow.foodsearch.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BeanUtil.setStaticApplicationContext(applicationContext);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public static String getProperty(String key) {
        return context.getEnvironment().getProperty(key);
    }

    private static void setStaticApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
}
