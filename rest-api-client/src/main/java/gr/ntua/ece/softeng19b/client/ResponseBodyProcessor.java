package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.*;

import java.io.Reader;
import java.util.List;

public interface ResponseBodyProcessor {

    List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader);
    List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader);
    List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader);
    
    List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader);
    List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader);
    List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader);

    List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadRecordsForSpecificDay(Reader reader);
    List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadRecordsForSpecificMonth(Reader reader);
    List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadRecordsForSpecificYear(Reader reader);

    List<AvFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader);
    List<AvFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader);
    List<AvFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader);


}
