package gr.ntua.ece.softeng19b.api;
//import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForMonth;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForYear;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForDay;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForMonth;
import gr.ntua.ece.softeng19b.data.DayAheadTotalLoadForecastForYear;
import gr.ntua.ece.softeng19b.data.User;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForDay;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForMonth;
import gr.ntua.ece.softeng19b.data.AggregatedGenerationPerTypeForYear;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForDay;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForMonth;
import gr.ntua.ece.softeng19b.data.ActualvsForecastForYear;

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
        public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ActualTotalLoadForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation1(List<ActualTotalLoadForMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ActualTotalLoadForMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("ActualTotalLoadValuePerDay").value(rec.getActualTotalLoadValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation2(List<ActualTotalLoadForYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ActualTotalLoadForYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ActualTotalLoadValuePerMonth").value(rec.getActualTotalLoadValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation3(List<DayAheadTotalLoadForecastForDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DayAheadTotalLoadForecastForDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecast());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation4(List<DayAheadTotalLoadForecastForMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DayAheadTotalLoadForecastForMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DayAheadTotalLoadForecastValuePerDay").value(rec.getDayAheadTotalLoadForecast());

                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentation5(List<DayAheadTotalLoadForecastForYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DayAheadTotalLoadForecastForYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastValuePerMonth").value(rec.getDayAheadTotalLoadForecast());

                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation6(List<AggregatedGenerationPerTypeForDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AggregatedGenerationPerTypeForDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC());
                        w.name("ProductionType").value(rec.getproductionTypeCodeText());
                        w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        
        public Representation generateRepresentation7(List<AggregatedGenerationPerTypeForMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AggregatedGenerationPerTypeForMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
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

        public Representation generateRepresentation8(List<AggregatedGenerationPerTypeForYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AggregatedGenerationPerTypeForYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
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
            public Representation generateRepresentation9(List<ActualvsForecastForDay> result) {
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginArray(); // [
                        for(ActualvsForecastForDay rec: result) {
                            w.beginObject(); // {
                            w.name("Source").value(rec.getSource());
                            w.name("DataSet").value(rec.getDataset());
                            w.name("AreaName").value(rec.getAreaName());
                            w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                            w.name("MapCode").value(rec.getMapCode());
                            w.name("ResolutionCode").value(rec.getResolutionText());
                            w.name("Year").value(rec.getYear());
                            w.name("Month").value(rec.getMonth());
                            w.name("Day").value(rec.getDay());
                            w.name("DateTimeUTC").value(rec.getDateTimeUTC());
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
            public Representation generateRepresentation10(List<ActualvsForecastForMonth> result) {
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginArray(); // [
                        for(ActualvsForecastForMonth rec: result) {
                            w.beginObject(); // {
                            w.name("Source").value(rec.getSource());
                            w.name("DataSet").value(rec.getDataset());
                            w.name("AreaName").value(rec.getAreaName());
                            w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                            w.name("MapCode").value(rec.getMapCode());
                            w.name("ResolutionCode").value(rec.getResolutionText());
                            w.name("Year").value(rec.getYear());
                            w.name("Month").value(rec.getMonth());
                            w.name("Day").value(rec.getDay());
                            w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastByDayValue());
                            w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue());
                            
                            w.endObject(); // }
                            w.flush();
                        }
                        w.endArray(); // ]
                    } catch (IOException e) {
                        throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                    }
                });
            }
            public Representation generateRepresentation11(List<ActualvsForecastForYear> result) {return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ActualvsForecastForYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataset());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCodeText());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionText());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastByMonthValue());
                        w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadByMonthValue());
                        
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
            }

            public Representation generateRepresentationUser(List<User> result) {
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginArray(); // [
                        for(User rec: result) {
                            w.beginObject(); // {
                            w.name("Username").value(rec.getUsername());
                            w.name("Email").value(rec.getEmail());
                            w.name("Quotas").value(rec.getRequestsPerDayQuota());
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
        public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation1(List<ActualTotalLoadForMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation2(List<ActualTotalLoadForYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }

        
        public Representation generateRepresentation3(List<DayAheadTotalLoadForecastForDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation4(List<DayAheadTotalLoadForecastForMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation5(List<DayAheadTotalLoadForecastForYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }


        public Representation generateRepresentation6(List<AggregatedGenerationPerTypeForDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation7(List<AggregatedGenerationPerTypeForMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation8(List<AggregatedGenerationPerTypeForYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }

        public Representation generateRepresentation9(List<ActualvsForecastForDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation10(List<ActualvsForecastForMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentation11(List<ActualvsForecastForYear> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentationUser(List<User> result) {
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
