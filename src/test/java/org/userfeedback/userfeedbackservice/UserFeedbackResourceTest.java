package org.userfeedback.userfeedbackservice;

import java.util.List;
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
    @Test
    public void testFeedbackJson() {
        // Create testing User entry
        User u = new User();
        u.setName(TEST_USER);
        u.setContent(TEST_CONTENT);
        
        // Add entry on server
        Response response = target.path("/rest/feedback").request(MediaType.APPLICATION_JSON)
                .post(Entity.json(u));
        
        // Get list of entries, should contain one entry
        Response responseList = target.path("/rest/feedback").request().get(Response.class);
        List l = responseList.readEntity(List.class);
        assertEquals("0", l.get(0));
        
        // Check User feedback detail
        User responseUser = target.path("/rest/feedback/0").request().get(User.class);
        assertEquals(TEST_USER, responseUser.getName());
        assertEquals(TEST_CONTENT, responseUser.getContent());
    }
}
