package gr.ntua.ece.softeng19b.api.resource; 
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import gr.ntua.ece.softeng19b.conf.Configuration;


import org.restlet.resource.ResourceException;
import java.util.Map;
//import org.springframework.jdbc.core.JdbcTemplate;
import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;



public class Reset extends EnergyResource {
    private DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    
    
    //protected Representation put(Representation entity) throws ResourceException {
    @Override
    protected Representation post(Representation entity) throws ResourceException {    
        //reset the database
       try {
           dataAccess.resetDB();
           return new JsonMapRepresentation(Map.of("status","OK"));
       }
       catch (Exception e){
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
       }

       //return new JsonMapRepresentation(Map.of("200","OK"));
    }
}