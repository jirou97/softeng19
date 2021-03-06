package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.DataAccess;
//import gr.ntua.ece.softeng19b.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;
import org.restlet.data.Header;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays; 

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class DayAheadTotalLoadForecastForSpecificMonth extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        @SuppressWarnings("unchecked")
        Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
        //System.out.println(headers.getNames());
        String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
        if (auth == null || auth == "-1"){
            throw new ResourceException(new Status(401),"You are not authorized for this call",null);
        }
        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String dateParam = getAttributeDecoded("date");

        List<String> resolutionValues = Arrays.asList("PT15M","PT60M","PT30M","P7D","P1M","P1Y","P1D","CONTRACT");
        //setted in Listener, which is invoked only once.
        List<String> areaNames = dataAccess.getAreaNamesDATL();
        
        if (!resolutionValues.contains(resolution)){
            throw new ResourceException(new Status(403),"Bad ResolutionCode value",null);
        }
        if (!areaNames.contains(areaName)){
            throw new ResourceException(new Status(403),"Bad AreaName value",null);
        }
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

        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        Integer Y = EnergyResource.parseYear(Year);
        Integer M = EnergyResource.parseYear(Month);
        if ( Y > LocalDate.now().getYear() || Y < LocalDate.now().getYear() - 30){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Bad request of Year.",null);
        }
        if ( M>31 || M <1 ){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Bad request of Month.",null);
        }
        
        LocalDate date = LocalDate.of(Y,M,1);

        //Read the format query parameter
        String ft = new String();
        if (getQueryValue("format") == null){
            ft = "json";
        }
        else {
            ft = getQueryValue("format").toLowerCase();
            if (!ft.equals("csv") && !ft.equals("json") ){
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Format is either csv or json",null);
            }
        }
        Format format = parseFormat(ft);
        int quotas = dataAccess.getQuotasFromToken(auth);
        if (quotas <= 1){
            throw new ResourceException(new Status(402),"Out of quota.",null);
        }
        dataAccess.updateQuotasForUser(quotas-1 ,auth);

        try {

            List<DATLFRecordForSpecificMonth> result = dataAccess.fetchDayAheadTotalLoadForecastForSpecificMonth(
                    areaName,
                    resolution,
                    date
            );
            if ( result.size() == 0){
                throw new ResourceException(new Status(403),"No data fetched in this call.",null );
        }
            return format.generateRepresentation4(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }

}
