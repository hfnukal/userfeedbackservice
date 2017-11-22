package org.userfeedback.userfeedbackservice;

import java.util.ArrayList;
import java.util.List;
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
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    /**
     * Method returning list of feedback IDs as JSON array
     * @return List of Feedback IDs
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/feedback")
    public List<String> getFeedbackListJson() {
        return (List<String>) new ArrayList<String>(UserData.getModel().keySet());
    }

    /**
     * Method for entry detail as JSON object
     * @param id ID of entry
     * @return User object with entry data
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/feedback/{id}")
    public User getFeedbackJson(@PathParam("id") String id) {
        User u = UserData.getModel().get(id);
        return u;
    }

    // Testing from cmd line
    // echo "Name:" ; read n; echo "Text"; read c ; curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"$n\",\"content\":\"$c\"}"  http://localhost:8080/userfeedback/rest/addfeedback

    /**
     * Method for adding user feedback
     * @param u User object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/feedback")
    public void postFeedbackJson(final User u) {
        String id = UserData.getNewId();
        UserData.getModel().put(id, u);
    }
}
