package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForDay;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class AggregatedGenerationPerTypeForSpecificDay extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String production = getMandatoryAttribute("Production", "ProductionType is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String dateParam = getAttributeDecoded("date");
        if (dateParam.length() == 0 ){
            dateParam = LocalDate.now().toString();
        }
        String[] params = dateParam.split("-");
        String Year = params[0];
        String Month = params[1];
        String Day = params[2];
        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        LocalDate date = LocalDate.of(EnergyResource.parseYear(Year), EnergyResource.parseYear(Month), EnergyResource.parseYear(Day));

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<AggregatedGenerationPerTypeForDay> result = dataAccess.fetchAggregatedGenerationPerTypeForSpecificDay(
                    areaName,
                    production,
                    resolution,
                    date
            );
            return format.generateRepresentation6(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
