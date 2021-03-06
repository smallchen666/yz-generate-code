package com.yz.code.config;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.CollectionUtils;

public final class ConfigManager extends PropertyPlaceholderConfigurer {
	static final ConcurrentHashMap<String, String> ALL_PROPERTIES = new ConcurrentHashMap<String, String>();
	
	public static String getProperty(String propertyName) {
		return ALL_PROPERTIES.get(propertyName);
	}

	public static void setProperty(String key, String value){
		ALL_PROPERTIES.put(key, value);
	}

	@Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
            Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        CollectionUtils.mergePropertiesIntoMap(props, ConfigManager.ALL_PROPERTIES);
//        System.out.println("Properties=" + JSON.toJSONString(ALL_PROPERTIES));
    }
}
