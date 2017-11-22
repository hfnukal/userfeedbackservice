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
public enum EntryData {

    /**
     * 
     */
    instance;
    
    private static final Map<String, UserEntry> content = new HashMap<>();
    private static int counter = 0;
    
    /**
     * Return list of entries
     * @return Map of entry ID and UserEntry object
     */
    public static Map<String, UserEntry> getModel() {
        return content;
    }

    /**
     * ID sequence. Each call return new ID
     * @return ID as String
     */
    public static String getNewId() {
        return ""+counter++;
    }
    
    public static Map<String, UserEntry> searchUser(String search) {
        Map<String, UserEntry> r = new HashMap();
        for(String id : EntryData.getModel().keySet()) {
            UserEntry u = EntryData.getModel().get(id);
            String name = u!=null?u.getName():"";
            if(name.contains(search)) {
                r.put(id, u);
            }
        }
        return r;
    }
}
