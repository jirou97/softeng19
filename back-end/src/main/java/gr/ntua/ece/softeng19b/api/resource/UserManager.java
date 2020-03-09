package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.User;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

import gr.ntua.ece.softeng19b.api.representation.JsonUserRepresentation;
//import gr.ntua.ece.softeng19b.data.DataAccessException;

//import org.springframework.jdbc.core.JdbcTemplate;

import org.restlet.data.Form;
import org.restlet.data.Header;


import java.util.Optional;

public class UserManager extends EnergyResource {

    private DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    //private JdbcTemplate jdbcTemplate;

    @Override
    protected Representation get() throws ResourceException {
        
         //Read the mandatory URI attributes
         @SuppressWarnings("unchecked")
         Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
         
         String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
         if (auth == null || auth == "-1"){
             throw new ResourceException(new Status(401),"You are not authorized for this call",null);
         }
         if (!dataAccess.isAdmin(auth)) {
             throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "You are not authorized for this information");
         }
         
        String username = getMandatoryAttribute("username","Missing username");
        
        if (username == null || username.isEmpty()){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing username");
        }
        Form queryParams = getQuery();
    	String format = queryParams.getFirstValue("format");
    	if (format == null || format.isEmpty()) {
    		format = "json";
    	}
        
        Optional<User> optional = dataAccess.getUserByUsername(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "User not found - username: " + username));
        /*
         * Return message.
         */
        	return new JsonUserRepresentation(user);
        
    }

    @Override
    protected Representation put(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
        //System.out.println("AAAAAAA");
        @SuppressWarnings("unchecked")
        Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
        //System.out.println(headers.getNames());
        String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
        if (auth == null || auth == "-1"){
            throw new ResourceException(new Status(401),"You are not authorized for this call",null);
        }
        if (!dataAccess.isAdmin(auth)) {
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "You are not authorized for this information");
        }
    	/*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the MandatoryAttribute
         */
        String username = getMandatoryAttribute("username", "Missing username");
        //System.out.println("AAAAAAA");
        
        /*
         * Read the parameters
         */
        Form queryParams = getQuery();
    	String format = queryParams.getFirstValue("format");
    	if (format == null || format.isEmpty()) {
    		format = "json";
        }
        
        //User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "User not found - username: " + username));
        
        String email = form.getFirstValue("email");
        int quotas = Integer.parseInt(form.getFirstValue("requestsPerDayQuota"));
        if (quotas == 0)
            quotas = -100;
            
        if (email.equals("-1"))
            email = null;

        User optionals = dataAccess.updateUserByUsername(username,email,quotas);
        //System.out.println("AAAAAAA");
        if (optionals.equals(null)){
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Internal Server error!");
        }
        //System.out.println("AAAAAAA");
        //System.out.println(quotas);
        return new JsonUserRepresentation(optionals);        
    }

    
}

