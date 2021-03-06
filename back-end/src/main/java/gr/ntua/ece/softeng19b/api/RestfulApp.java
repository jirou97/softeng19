package gr.ntua.ece.softeng19b.api;
import gr.ntua.ece.softeng19b.api.resource.*;

import org.restlet.engine.application.CorsFilter;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.routing.Router;
import java.util.Set;
//import org.restlet.routing.Template;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());
        


        router.attach("/HealthCheck", HealthCheck.class);

        /*
         All routes accept a `format` query param (optional, either json or csv, default is json)
         All year, month and date attributes in routes are in the ISO-8601 format
           (YYYY, YYYY-MM, YYYY-MM-DD respectively)
         */
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/date/{date}",
            ActualTotalLoadForSpecificDate.class
        );

        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/month/{date}",
            ActualTotalLoadForSpecificMonth.class
        );

        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/year/{date}",
            ActualTotalLoadForSpecificYear.class
        );
//////////////////////////////////////////////////////////////////////////////////////
        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/date/{date}",
            DayAheadTotalLoadForecastForSpecificDate.class
        );

        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/month/{date}",
            DayAheadTotalLoadForecastForSpecificMonth.class
        );

        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/year/{date}",
            DayAheadTotalLoadForecastForSpecificYear.class
        );
///////////////////////////////////////////////////////////////////////////////////////
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{Production}/{Resolution}/date/{date}",
            AggregatedGenerationPerTypeForSpecificDay.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{Production}/{Resolution}/month/{date}",
            AggregatedGenerationPerTypeForSpecificMonth.class
        );
        
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{Production}/{Resolution}/year/{date}",
            AggregatedGenerationPerTypeForSpecificYear.class
        );
///////////////////////////////////////////////////////////////////////////////////////
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/date/{date}",
            ActualvsForecastForSpecificDay.class
        );
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/month/{date}",
            ActualvsForecastForSpecificMonth.class
        );
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/year/{date}",
            ActualvsForecastForSpecificYear.class
        );

//////////////////////////////////////////////////////////////////////////////////////
        //Init a new (empty) database with the default Admin user (username: admin, password: 321nimda)
        router.attach("/Reset", Reset.class);

        //Authenticate the user
        router.attach("/Login", Login.class);

        //You should make these REST endpoints available to Admin users only (Users with the Admin role)
        router.attach("/Admin/users", AddUser.class);
        router.attach("/Admin/users/{username}", UserManager.class);
        router.attach("/Admin/ActualTotalLoad", ImportActualTotalLoadDataSet.class);
        router.attach("/Admin/AggregatedGenerationPerType", ImportAggregatedGenerationPerType.class);
        router.attach("/Admin/DayAheadTotalLoadForecast", ImportDayAheadTotalLoad.class);
        //Add more datasets

        //You should make these REST endpoints available to all authenticated users
        router.attach("/Logout", Logout.class);
		
		
        //Enable CORS for all origins (don't use this in a production service)
        CorsFilter corsFilter = new CorsFilter(getContext(), router);
        corsFilter.setAllowedOrigins(Set.of("*"));
        corsFilter.setAllowedCredentials(true);
        corsFilter.setAllowedHeaders(Set.of("X-OBSERVATORY-AUTH"));
        corsFilter.setDefaultAllowedMethods(Set.of(Method.GET, Method.PUT, Method.POST, Method.DELETE));
        corsFilter.setAllowingAllRequestedHeaders(true);
        corsFilter.setSkippingResourceForCorsOptions(true);
        corsFilter.setMaxAge(10);
		
        return router;
    }

}
