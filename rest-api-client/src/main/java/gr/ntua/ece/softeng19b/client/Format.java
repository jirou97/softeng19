package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public enum Format implements ResponseBodyProcessor {

    JSON {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
///////////////////////////////////////
        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        @Override
        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        @Override
        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
/////////////////////////////////////////////////////
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /////////////////////////////////////////////////////////////////////////////

        @Override
        public List<AvFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        @Override
        public List<AvFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        @Override
        public List<AvFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    },
    CSV {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        @Override
        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        
        @Override
        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        @Override
        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }


        @Override
        public List<AvFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        @Override
        public List<AvFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        @Override
        public List<AvFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
    };

    private static List<ATLRecordForSpecificDay> readActualDataLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificDay readActualDataLoadRecordForSpecificDay(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificDay rec = new ATLRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTime(reader.nextString());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTime(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }


    private static List<ATLRecordForSpecificMonth> readActualDataLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificMonth readActualDataLoadRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificMonth rec = new ATLRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setday(reader.nextInt());
                    break;
                case "ActualTotalLoadValuePerDay":
                    rec.setActualTotalLoadByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<ATLRecordForSpecificYear> readActualDataLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificYear readActualDataLoadRecordForSpecificYear(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificYear rec = new ATLRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualTotalLoadValuePerMonth":
                    rec.setActualTotalLoadByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<AGPTRecordForSpecificDay> readAggregatedGenerationPerTypeRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static AGPTRecordForSpecificDay readAggregatedGenerationPerTypeRecordForSpecificDay(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificDay rec = new AGPTRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTime(reader.nextString());
                    break;
                case "ProductionType":
                    rec.setproductionTypeCodeText(reader.nextString());
                    break;
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpDateTime(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }


    private static List<AGPTRecordForSpecificMonth> readAggregatedGenerationPerTypeRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static AGPTRecordForSpecificMonth readAggregatedGenerationPerTypeRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificMonth rec = new AGPTRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ProductionType":
                    rec.setproductionTypeCodeText(reader.nextString());
                    break;
                case "ActualGenerationOutputValueByDay":
                    rec.setActualGenerationOutputByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<AGPTRecordForSpecificYear> readAggregatedGenerationPerTypeRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static AGPTRecordForSpecificYear readAggregatedGenerationPerTypeRecordForSpecificYear(JsonReader reader)
            throws IOException {
                AGPTRecordForSpecificYear rec = new AGPTRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ProductionType":
                    rec.setproductionTypeCodeText(reader.nextString());
                    break;
                case "ActualGenerationOutputValueByMonth":
                    rec.setActualGenerationOutputByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }


    private static List<DATLFRecordForSpecificDay> readDayAheadTotalLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    private static DATLFRecordForSpecificDay readDayAheadTotalLoadRecordForSpecificDay(JsonReader reader)
            throws IOException {
                DATLFRecordForSpecificDay rec = new DATLFRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTime(reader.nextString());
                    break;
                case "DayAheadTotalLoadForecastValue":
                    rec.setdayAheadTotalLoadForecast(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpDateTime(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<DATLFRecordForSpecificMonth> readDayAheadTotalLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    
    private static DATLFRecordForSpecificMonth readDayAheadTotalLoadRecordForSpecificMonth(JsonReader reader)
            throws IOException {
                DATLFRecordForSpecificMonth rec = new DATLFRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastValuePerDay":
                    rec.setdayAheadTotalLoadForecast(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<DATLFRecordForSpecificYear> readDayAheadTotalLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    private static DATLFRecordForSpecificYear readDayAheadTotalLoadRecordForSpecificYear(JsonReader reader)
            throws IOException {
                DATLFRecordForSpecificYear rec = new DATLFRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastValuePerMonth":
                    rec.setdayAheadTotalLoadForecast(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    /////////////////////////////////////////////////////////////////////////////

    private static List<AvFRecordForSpecificDay> readActualvsForecastRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AvFRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    private static AvFRecordForSpecificDay readActualvsForecastRecordForSpecificDay(JsonReader reader)
            throws IOException {
                AvFRecordForSpecificDay rec = new AvFRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;                
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;      
                case "DateTimeUTC":
                    rec.setDateTime(reader.nextString());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;                
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<AvFRecordForSpecificMonth> readActualvsForecastRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AvFRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    private static AvFRecordForSpecificMonth readActualvsForecastRecordForSpecificMonth(JsonReader reader)
            throws IOException {
                AvFRecordForSpecificMonth rec = new AvFRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadByDayValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;             
                case "DayAheadTotalLoadForecastByDayValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static List<AvFRecordForSpecificYear> readActualvsForecastRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AvFRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        if (!reader.hasNext()){
            return result;
        }
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    
    private static AvFRecordForSpecificYear readActualvsForecastRecordForSpecificYear(JsonReader reader)
            throws IOException {
                AvFRecordForSpecificYear rec = new AvFRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualTotalLoadByMonthValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;             
                case "DayAheadTotalLoadForecastByMonthValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    

}