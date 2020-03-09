package gr.ntua.ece.softeng19b.api;
//import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
//import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.model.*;
//import gr.ntua.ece.softeng19b.data.model.User;

import com.google.gson.stream.JsonWriter;
//import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;
//import java.io.*; 
//import java.util.Map;
//import java.util.LinkedHashMap;

public enum Format implements RepresentationGenerator {
    JSON {
        //TODO ADD EXCEPTIONS
        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTime());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTime());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation1(List<ATLRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("ActualTotalLoadValuePerDay").value(rec.getActualTotalLoadByDayValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation2(List<ATLRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ActualTotalLoadValuePerMonth").value(rec.getActualTotalLoadByMonthValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation3(List<DATLFRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTime());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getdayAheadTotalLoadForecast());
                        w.name("UpdateTimeUTC").value(rec.getUpDateTime());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation4(List<DATLFRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DayAheadTotalLoadForecastValuePerDay").value(rec.getdayAheadTotalLoadForecast());

                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation5(List<DATLFRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastValuePerMonth").value(rec.getdayAheadTotalLoadForecast());

                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation6(List<AGPTRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTime());
                        w.name("ProductionType").value(rec.getproductionTypeCodeText());
                        w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                        w.name("UpdateTimeUTC").value(rec.getUpDateTime());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        
        public Representation generateRepresentation7(List<AGPTRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("ProductionType").value(rec.getproductionTypeCodeText());
                        w.name("ActualGenerationOutputValueByDay").value(rec.getActualGenerationOutputByDayValue());
                        
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation8(List<AGPTRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ProductionType").value(rec.getproductionTypeCodeText());
                        w.name("ActualGenerationOutputValueByMonth").value(rec.getActualGenerationOutputByMonthValue());
                        
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
            public Representation generateRepresentation9(List<AvFRecordForSpecificDay> result) {
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginArray(); // [
                        for(AvFRecordForSpecificDay rec: result) {
                            w.beginObject(); // {
                            w.name("Source").value(rec.getSource());
                            w.name("DataSet").value(rec.getDataSet());
                            w.name("AreaName").value(rec.getAreaName());
                            w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                            w.name("MapCode").value(rec.getMapCode());
                            w.name("ResolutionCode").value(rec.getResolutionCode());
                            w.name("Year").value(rec.getYear());
                            w.name("Month").value(rec.getMonth());
                            w.name("Day").value(rec.getDay());
                            w.name("DateTimeUTC").value(rec.getDateTime());
                            w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                            w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                            
                            
                            w.endObject(); // }
                            w.flush();
                        }
                        w.endArray(); // ]
                    } catch (IOException e) {
                        throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                    }
                });
            }
            public Representation generateRepresentation10(List<AvFRecordForSpecificMonth> result) {
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginArray(); // [
                        for(AvFRecordForSpecificMonth rec: result) {
                            w.beginObject(); // {
                            w.name("Source").value(rec.getSource());
                            w.name("DataSet").value(rec.getDataSet());
                            w.name("AreaName").value(rec.getAreaName());
                            w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                            w.name("MapCode").value(rec.getMapCode());
                            w.name("ResolutionCode").value(rec.getResolutionCode());
                            w.name("Year").value(rec.getYear());
                            w.name("Month").value(rec.getMonth());
                            w.name("Day").value(rec.getDay());
                            w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastValue());
                            w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadValue());
                            
                            w.endObject(); // }
                            w.flush();
                        }
                        w.endArray(); // ]
                    } catch (IOException e) {
                        throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                    }
                });
            }
            public Representation generateRepresentation11(List<AvFRecordForSpecificYear> result) {return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AvFRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadValue());
                        
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });

            }        

    },
    CSV {
        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation1(List<ATLRecordForSpecificMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation2(List<ATLRecordForSpecificYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }

        
        public Representation generateRepresentation3(List<DATLFRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation4(List<DATLFRecordForSpecificMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation5(List<DATLFRecordForSpecificYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }


        public Representation generateRepresentation6(List<AGPTRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation7(List<AGPTRecordForSpecificMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation8(List<AGPTRecordForSpecificYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }

        public Representation generateRepresentation9(List<AvFRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation10(List<AvFRecordForSpecificMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation11(List<AvFRecordForSpecificYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }

    };



    private static final class CustomJsonRepresentation extends WriterRepresentation {

        private final Consumer<JsonWriter> consumer;

        CustomJsonRepresentation(Consumer<JsonWriter> consumer) {
            super(MediaType.APPLICATION_JSON);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            JsonWriter jsonWriter = new JsonWriter(writer);
            consumer.accept(jsonWriter);
        }
    }
}
