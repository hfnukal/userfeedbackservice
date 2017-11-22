package org.userfeedback.userfeedbackservice;

import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test for UserFeedbackResource class
 * @author hfnukal
 */
public class UserFeedbackResourceTest {

    private HttpServer server;
    private WebTarget target;

    /**
     * Setup testing environment. Starts server.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        //c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    /**
     * Cleanup after test. Stops server.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Testing user
     */
    public static final String TEST_USER = "Test User";

    /**
     * Testing content
     */
    public static final String TEST_CONTENT = "Test Content";
    
    /**
     * Test to see that the feedback is added and then appears in list and detail.
     */
    //@Test
    public void testFeedbackJson() {
        
        // Get list of entries, should contain one entry
        Response responseMap;
        responseMap = target.path("/rest/feedback").request().get(Response.class);
        Map<String, UserEntry> m1 = responseMap.readEntity(Map.class);
        assertEquals(0, m1.size());
        
        String[] users = {"Honza", "Vojta", "Michal", "Petr"};
        for (int i = 0; i < users.length; i++) {
            String user = users[i];
            addEntry(user, user+".s coment"); 
        }
        addEntry(users[0], users[0]+".s 2nd coment");

        // Get list of entries, should contain entries from userl list plus one extra
        responseMap = target.path("/rest/feedback").request().get(Response.class);
        Map<String, UserEntry> m2 = responseMap.readEntity(Map.class);
        assertEquals(users.length+1, m2.size());
        
        // Get list of entries for first user, should contain two entries
        responseMap = target.path("/rest/feedback/"+users[0]).request().get(Response.class);
        Map m3 = responseMap.readEntity(Map.class);
        assertEquals(2, m3.size());
        
        // Get list of entries for second user, should contain one entry
        responseMap = target.path("/rest/feedback/"+users[0]).request().get(Response.class);
        Map m4 = responseMap.readEntity(Map.class);
        assertEquals(1, m4.size());
        
    }
    
    private void addEntry(String name, String content) {
        // Create testing UserEntry entry
        UserEntry u = new UserEntry();
        u.setName(TEST_USER);
        u.setContent(TEST_CONTENT);
        
        // Add entry on server
        Response response = target.path("/rest/feedback").request(MediaType.APPLICATION_JSON)
                .post(Entity.json(u));
    }
}
