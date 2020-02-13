package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import  gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.conf.Configuration;
import java.util.Map;


public class Reset extends EnergyResource {
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    @Override
    protected Representation get() throws ResourceException {
        //reset the database
        //throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
            
        try {
            dataAccess.resetDB(); 
            return new JsonMapRepresentation(Map.of("200","OK"));
        }
        catch (Exception e){
             throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }
     }
 }