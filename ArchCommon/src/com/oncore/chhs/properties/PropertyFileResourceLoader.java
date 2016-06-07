/*
 * The MIT License
 *
 * Copyright 2016 oncore.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
 * @author OnCore LLC
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
