/*
 * User feedbek poi
 */
package org.userfeedback.userfeedbackservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User feedback poi
 * @author hfnukal
 */
@XmlRootElement(name = "User")
public class User {
    
    private String name;
    private String content;

    /**
     * User feedback poi class
     */
    public User() {
    }

    /**
     * User name field setter
     * @param name User name as String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * User name field getter
     * @return User name as String
     */
    public String getName() {
        return name;
    }

    /**
     * Content field setter
     * @param content Content String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Content field getter
     * @return Content String
     */
    public String getContent() {
        return content;
    }
    
    
}
