/*
 * 
 */
package org.userfeedback.userfeedbackservice;

import java.util.HashMap;
import java.util.Map;

/**
 * Storing user feedback data in memory
 * @author hfnukal
 */
public enum UserData {

    /**
     * 
     */
    instance;
    
    private static final Map<String, User> content = new HashMap<>();
    private static int counter = 0;
    
    /**
     * Return list of entries
     * @return Map of entry ID and User object
     */
    public static Map<String, User> getModel() {
        return content;
    }

    /**
     * ID sequence. Each call return new ID
     * @return ID as String
     */
    public static String getNewId() {
        return ""+counter++;
    }
}
