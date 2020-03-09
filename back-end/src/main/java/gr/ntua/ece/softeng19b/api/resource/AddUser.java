package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.data.Header;
import org.restlet.util.Series;

import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.api.representation.JsonUserRepresentation;
import gr.ntua.ece.softeng19b.data.model.User;


//import org.restlet.data.Header;
//import org.restlet.util.Series;

public class AddUser extends EnergyResource {

    private DataAccess dataAccess = Configuration.getInstance().getDataAccess();


    @Override
    protected Representation post(Representation entity) throws ResourceException {
        
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
        Form form = new Form(entity);  
        String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
        String email = form.getFirstValue("email");
        Integer quotas = Integer.parseInt(form.getFirstValue("requestsPerDayQuota"));
        
        if (username == null || username.isEmpty()){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing username");
        }
        if (password == null || password.isEmpty()){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing password");
        }
        Form queryParams = getQuery();
    	String format = queryParams.getFirstValue("format");
    	if (format == null || format.isEmpty()) {
    		format = "json";
    	}
        
        String optional = dataAccess.createUser(username,email,password,quotas);
        if (optional == null)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"User already exists.");

        return new JsonUserRepresentation(new User(username,email,quotas));
        
    }

}
