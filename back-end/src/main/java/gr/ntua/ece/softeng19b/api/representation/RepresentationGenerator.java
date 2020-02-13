package gr.ntua.ece.softeng19b.api.representation;


import gr.ntua.ece.softeng19b.data.ActualTotalLoadForMonth;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForYear;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForDay;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForMonth;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForYear;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForDay;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForMonth;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForYear;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForDay;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForMonth;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForYear;
import gr.ntua.ece.softeng19b.data.User;

import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result);
    public Representation generateRepresentation1(List<ActualTotalLoadForMonth> result);
    public Representation generateRepresentation2(List<ActualTotalLoadForYear> result);
    public Representation generateRepresentation3(List<DayAheadTotalLoadForecastForDay> result);
    public Representation generateRepresentation4(List<DayAheadTotalLoadForecastForMonth> result);
    public Representation generateRepresentation5(List<DayAheadTotalLoadForecastForYear> result);
    public Representation generateRepresentation6(List<AggregatedGenerationPerTypeForDay> result);
    public Representation generateRepresentation7(List<AggregatedGenerationPerTypeForMonth> result);
    public Representation generateRepresentation8(List<AggregatedGenerationPerTypeForYear> result);
    public Representation generateRepresentation9(List<ActualvsForecastForDay> result);
    public Representation generateRepresentation10(List<ActualvsForecastForMonth> result);
    public Representation generateRepresentation11(List<ActualvsForecastForYear> result);
    public Representation generateRepresentationUser(List<User> result);

}
