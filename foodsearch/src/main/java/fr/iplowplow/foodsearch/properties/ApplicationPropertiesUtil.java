package fr.iplowplow.foodsearch.properties;

import fr.iplowplow.foodsearch.utils.BeanUtil;

public class ApplicationPropertiesUtil {

    private static ApplicationProperties applicationProperties;

    private ApplicationPropertiesUtil() {
    }

    private static ApplicationProperties getApplicationProperties() {
        if (applicationProperties == null) {
            applicationProperties = BeanUtil.getBean(ApplicationProperties.class);
        }
        return applicationProperties;
    }

    public static String getSecretKey() {
        return getApplicationProperties().getSecretKey();
    }

}
