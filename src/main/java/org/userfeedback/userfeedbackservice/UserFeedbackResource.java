package org.userfeedback.userfeedbackservice;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "rest" path)
 */
@Path("rest")
public class UserFeedbackResource {

    /**
     * Method returning Map of feedbacks wit id as key
     * @return List of Feedback IDs
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/feedback")
    public Map getFeedbackListJson() {
        return EntryData.getModel();
    }

    /**
     * Method for entry search by name.
     * @param search Search String
     * @return Map with entry data and id
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/feedback/{search}")
    public Map getFeedbackJson(@PathParam("search") String search) {
        return EntryData.searchUser(search);
   }
    
/* Testing from cmd line
echo "Name:" ; read n; echo "Text"; read c ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/addfeedback
    
n="aaa"; c="aaaa" ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/feedback
n="bbb"; c="bbbb" ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/feedback
n="ccc"; c="cccc" ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/feedback
n="abc"; c="abcd" ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/feedback
*/
    /**
     * Method for adding user feedback
     * @param u UserEntry object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/feedback")
    public void postFeedbackJson(final UserEntry u) {
        String id = EntryData.getNewId();
        EntryData.getModel().put(id, u);
    }
}
