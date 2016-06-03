/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.properties;

import com.oncore.chhs.ejb.exception.ResourceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Kerry O'Brien
 */
public class PropertyFileResourceLoader {

    private static Map<String, Properties> properties = new HashMap<String, Properties>();

    private static PropertyFileResourceLoader instance = new PropertyFileResourceLoader();
    
    /**
     * 
     */
    private PropertyFileResourceLoader() {
        
    }  
    
    /**
     * 
     * @param resourceName The resource file name.
     * @param key The property key.
     * @return The property value.
     */
    public static String getProperty( String resourceName, String key ) {
        return getProperty( resourceName, key, String.class );
    }
   
    /**
     * 
     * @param <T> The Return type.
     * @param resourceName The resource file name.
     * @param key The property key.
     * @param clazz THe return type.
     * @return The return value.
     */
    public static <T> T getProperty( String resourceName, String key, Class<T> clazz ) {
        Object value = null;
        
        Properties props = instance.getProperties( resourceName );

        value = props.get( key );
        
        return clazz.cast( value );
    }
    
    /**
     * 
     * @param resourceName The resource name.
     * @return  The Properties file.
     */
    public static Properties getProperties( String resourceName ) {
        Properties props = properties.get( resourceName );
        
        if ( props == null ) {
            try {
                props = instance.loadProperties(resourceName);
                
                properties.put( resourceName, props );
            } catch ( Throwable t ) {
                throw new ResourceException( "Error opening resource " + 
                            resourceName, t );
            }
        }
        
        return props;
    }
    
    /**
     * 
     * @param resourceName A resource name.
     * @return A Properties instance.
     * 
     * @throws IOException 
     */
    private Properties loadProperties( String resourceName ) throws IOException {
        Properties properties;
        
        try ( InputStream is = this.getClass().getClassLoader().getResourceAsStream( resourceName ); ) {
            properties = new Properties( );
            
            properties.load(is);
        }

        return properties;
    }
}
