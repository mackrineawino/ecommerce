package com.ecom.events;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.ecom.utils.EnumConverter;


@WebListener
public class EnumConverterListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EnumConverter.registerEnumConverters();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
