package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.api.representation.*;

import org.restlet.data.Form;
import org.restlet.data.Header;
//import org.restlet.data.Header;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
//import org.restlet.util.Series;
import org.restlet.util.Series;
import java.util.Map;

public class Logout extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    
    @Override
	protected Representation post(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
		String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
		//System.out.println(auth);
    	/*
    	 * Get parameters of the url.
    	 */
        Form queryParams = getQuery();
		String format = queryParams.getFirstValue("format");  
		if (format == null || format.isEmpty()) {
			format = "json";
		}
    	/*
    	 * Check if it is valid and disable it.
    	 */
		//remove existing token and make user's token unavalable
		try {
			//
			String message = dataAccess.tokenIsValid(auth);
			/*
			* Return 'OK' message.
			*/
			return new JsonMapRepresentation(Map.of("status",message));
		}
		catch (Exception e){
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL,e.getMessage());
		}
	}
	
}
    
