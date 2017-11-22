/*
 * UserEntry feedbek poi
 */
package org.userfeedback.userfeedbackservice;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * UserEntry feedback poi
 * @author hfnukal
 */
@XmlRootElement(name = "User")
public class UserEntry {
    
    private String name;
    private String content;
    private Date created;

    /**
     * User feedback poi class
     */
    public UserEntry() {
        this.created = new Date();
    }

    /**
     * UserEntry name field setter
     * @param name UserEntry name as String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * UserEntry name field getter
     * @return UserEntry name as String
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

    public Date getCreated() {
        return created;
    }
    
}
