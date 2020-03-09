package gr.ntua.ece.softeng19b.api.resource;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
//import gr.ntua.ece.softeng19b.client.ClientHelper;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Login extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
	protected Representation post(Representation entity) throws ResourceException {
    	/*
    	 * Get parameters of the url.
    	 */
        Form queryParams = getQuery();
		String format = queryParams.getFirstValue("format"); 
    	/*
		 * Create a new restlet form
		 */	
		Form form = new Form(entity);       
		/*
		 * Read the parameters from the body of the form.
		 */
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		/*
		Map <String, String> data = new HashMap<String, String>();
		data.put ("username", username);
		data.put("password" , password);
		System.out.println(ClientHelper.encode(data));
		*/
		/*
		 *  Validate the values (in the general case)
		 */
		if (username == null || username.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Username is required");
		}
		if (password == null || password.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Password is required");
		}
		if (format == null || format.isEmpty()) {
			format = "json";
		}
		
		/*
		 * Add requested product in the database if user exists.
		 */
		
		String token = dataAccess.getToken(username, password);
		if (token == null)
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Existing user");
		
		return new JsonMapRepresentation(Map.of("token",token));
    }


	
}