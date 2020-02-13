package gr.ntua.ece.softeng19b.api.resource;
import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Arrays; 

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class ActualTotalLoadForSpecificDate extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        List<String> resolutionValues = Arrays.asList("PT15M","PT60M","PT30M","P7D","P1M","P1Y","P1D","CONTRACT");
                
        if (!resolutionValues.contains(resolution)){
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN,"Bad ResolutionCode value",null);
        }
        
        //Read the optional date attribute
        String dateParam = getAttributeDecoded("date");
        if (dateParam.length() == 0 ){
            dateParam = LocalDate.now().toString();
        }
        String[] params = dateParam.split("-");
        
        String Year = params[0];
        if (Year.length() != 4){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Year must have exactly 4 digits.",null);
        }

        String Month = params[1];
        if (Month.length() > 2 || Month.length() ==0 ){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Month must have 1 or 2 digits.",null);
        }
        String Day = params[2];
        if (Day.length() > 2 || Day.length() ==0 ){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Day must have 1 or 2 digits.",null);
        }

        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        Integer Y = EnergyResource.parseYear(Year);
        Integer M = EnergyResource.parseYear(Month);
        Integer D = EnergyResource.parseYear(Day);
        if ( Y > LocalDate.now().getYear() || Y < LocalDate.now().getYear() - 30){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Bad request of Year.",null);
        }
        if ( M>31 || M <1 ){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Bad request of Month.",null);
        }
        if ( D >31 || D<1){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Bad request of Day.",null);
        }
        
        LocalDate date = LocalDate.of(Y,M,D);

        //Read the format query parameter
        String ft = new String();
        if (getQueryValue("format") != null ){
            if (!getQueryValue("format").equals("CSV") && !getQueryValue("format").equals("json") && !getQueryValue("format").equals("JSON") && !getQueryValue("format").equals("Json")&& !getQueryValue("format").equals("csv"))
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Format is either CSV(or csv) or json(or JSON or Json)",null);
            else {
                ft = getQueryValue("format");
            }
        }
        else if ( getQueryValue("format") == null)
            ft = "json";


        Format format = parseFormat(ft);
        try {

            List<ActualTotalLoadForSpecificDay> result = dataAccess.fetchActualDataLoadForSpecificDate(
                    areaName,
                    resolution,
                    date
            );
            if ( result.size() == 0){
                    //return new JsonMapRepresentation( Map.of("NoData","403"));
                    throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN,"No data fetched in this call.",null );
            }
            return format.generateRepresentation(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
