/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cfg.services.rs.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Cesar Flores <Cesar.Flores@jalasoft.com>
 */
@Provider
public class GenericJsonReaderProvider implements MessageBodyReader<Object>{

    private static final Logger logger = Logger.getLogger(GenericJsonReaderProvider.class.getName());
    
    private JacksonJsonProvider delegate;
    
    private JacksonJaxbJsonProvider jaxbDelegate;
    
    public GenericJsonReaderProvider(){
        delegate = new JacksonJsonProvider();
        jaxbDelegate = new JacksonJaxbJsonProvider();
    }
    
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return delegate.isReadable(type, genericType, annotations, mediaType) ||
                jaxbDelegate.isReadable(type, genericType, annotations, mediaType);
    }

    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        logger.log(Level.INFO, "trying to read using delegate(JacksonJsonProvider)");
        
        String jsonBody = IOUtils.toString(entityStream, "UTF-8");
        InputStream jsonIn = IOUtils.toInputStream(jsonBody);
        ObjectMapper mapper = jaxbDelegate.locateMapper(type, mediaType);
        
        JsonNode jNode = mapper.readTree(jsonBody);
        Iterator<Map.Entry<String, JsonNode>> map = jNode.getFields();
        while(map.hasNext()){
            Map.Entry<String, JsonNode> tup = map.next();
            logger.log(Level.INFO, "tup:{0}", tup.getKey() + "=" + tup.getValue());
        }
        JsonParser jparser = mapper.getJsonFactory().createJsonParser(jsonBody);
        logger.log(Level.INFO, "has current token:{0}", jparser.hasCurrentToken());
        logger.log(Level.INFO, "next token:{0}", jparser.nextToken());
        logger.log(Level.INFO, "next value:{0}", jparser.nextValue());
        
        return //delegate.isReadable(type, genericType, annotations, mediaType) ? 
                //delegate.readFrom(type, genericType, annotations, mediaType, httpHeaders, jsonIn):
                jaxbDelegate.readFrom(type, genericType, annotations, mediaType, httpHeaders, jsonIn);
    }

}
