/*
 * Copyright (c) ITivityKids, Inc. All Rights Reserved.
 */
package org.cfg.services.rs.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.cfg.services.rs.BookRSService;
import org.cfg.services.rs.util.GenericJsonReaderProvider;

/**
 * Adds resources in order to make related RESTs able to consume.
 * 
 * @author Adriana Mendoza
 */
@javax.ws.rs.ApplicationPath("api/v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return getRestResourceClasses();
    }
    
    /**
     * Adds REST classes in order to make them able to consume.
     * @return Set<Class<?>>
     */
    private Set<Class<?>> getRestResourceClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(BookRSService.class);
        resources.add(GenericJsonReaderProvider.class);
        return resources;
    }
    
}
