package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.AvFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AvFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AvFRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
    name="ActualvsForecast"
)
public class ActualvsForecast extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }
        RestAPI Z = new RestAPI();
        try {
            Z.setToken(Z.retrieveTokenFromFile());
            if (dateArgs.date != null ) {
                List<AvFRecordForSpecificDay> records = Z.
                        getActualvsForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    AvFRecordForSpecificDay X = new AvFRecordForSpecificDay();
                    X = records.get(i);
                    System.out.println("    {") ;
                    
                    System.out.print("      Source");
                    System.out.println(" : " + X.getSource() + ",");  
                    System.out.print("      Dataset");
                    System.out.println(" : " + X.getDataSet() + ",");   
                    System.out.print("      AreaName");
                    System.out.println(" : " + X.getAreaName() + ",");  
                    System.out.print("      AreaTypeCode");
                    System.out.println(" : " + X.getAreaTypeCode() + ","); 
                    System.out.print("      MapCode");
                    System.out.println(" : " + X.getMapCode() + ","); 
                    System.out.print("      ResolutionCodeText");
                    System.out.println(" : " + X.getResolutionCode() + ",");  
                    System.out.print("      Year");
                    System.out.println(" : " + X.getYear() + ",");  
                    System.out.print("      Month");
                    System.out.println(" : " + X.getMonth() + ",");   
                    System.out.print("      Day");
                    System.out.println(" : " + X.getDay() + ","); 
                    System.out.print("      DateTime");
                    System.out.println(" : " + X.getDateTime() + ","); 
                    System.out.print("      ActualTotalLoadValue");
                    System.out.println(" : " + X.getActualTotalLoadValue() + ","); 
                    System.out.print("      DayAheadTotalLoadForecastValue");
                    System.out.println(" : " + X.getDayAheadTotalLoadForecastValue() + ","); 
                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
            else if (dateArgs.month!=null) {
                List<AvFRecordForSpecificMonth> records = Z.
                                getActualvsForecastForMonth(areaName, timeres.name(), LocalDate.parse(dateArgs.month+"-01"), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    AvFRecordForSpecificMonth X = new AvFRecordForSpecificMonth();
                    X = records.get(i);
                    System.out.println("    {") ;
                    
                    System.out.print("      Source");
                    System.out.println(" : " + X.getSource() + ",");  
                    System.out.print("      Dataset");
                    System.out.println(" : " + X.getDataSet() + ",");   
                    System.out.print("      AreaName");
                    System.out.println(" : " + X.getAreaName() + ",");  
                    System.out.print("      AreaTypeCode");
                    System.out.println(" : " + X.getAreaTypeCode() + ","); 
                    System.out.print("      MapCode");
                    System.out.println(" : " + X.getMapCode() + ","); 
                    System.out.print("      ResolutionCodeText");
                    System.out.println(" : " + X.getResolutionCode() + ",");  
                    System.out.print("      Year");
                    System.out.println(" : " + X.getYear() + ",");  
                    System.out.print("      Month");
                    System.out.println(" : " + X.getMonth() + ",");   
                    System.out.print("      Day");
                    System.out.println(" : " + X.getDay() + ","); 
                    System.out.print("      ActualTotalLoadValueByDayValue");
                    System.out.println(" : " + X.getActualTotalLoadValue() + ","); 
                    System.out.print("      DayAheadTotalLoadForecastValueByDayValue");
                    System.out.println(" : " + X.getDayAheadTotalLoadForecastValue() + ",");    
                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
        else if (dateArgs.year!=null) {
            List<AvFRecordForSpecificYear> records = Z.
                            getActualvsForecastForYear(areaName, timeres.name(), LocalDate.parse(dateArgs.year+"-01-01"), format);
            // Do something with the records :)
            System.out.println("Fetched " + records.size() + " record(s)");
            System.out.println("[");
            Integer i ;
            for (i = 0 ; i < records.size() ; i ++){    
                AvFRecordForSpecificYear X = new AvFRecordForSpecificYear();
                X = records.get(i);
                System.out.println("    {") ;
                
                System.out.print("      Source");
                System.out.println(" : " + X.getSource() + ",");  
                System.out.print("      Dataset");
                System.out.println(" : " + X.getDataSet() + ",");   
                System.out.print("      AreaName");
                System.out.println(" : " + X.getAreaName() + ",");  
                System.out.print("      AreaTypeCode");
                System.out.println(" : " + X.getAreaTypeCode() + ","); 
                System.out.print("      MapCode");
                System.out.println(" : " + X.getMapCode() + ","); 
                System.out.print("      ResolutionCodeText");
                System.out.println(" : " + X.getResolutionCode() + ",");  
                System.out.print("      Year");
                System.out.println(" : " + X.getYear() + ",");  
                System.out.print("      Month");
                System.out.println(" : " + X.getMonth() + ",");   
                System.out.print("      ActualTotalLoadValueByMonthValue");
                System.out.println(" : " + X.getActualTotalLoadValue() + ","); 
                System.out.print("      DayAheadTotalLoadForecastValueByMonthValue");
                System.out.println(" : " + X.getDayAheadTotalLoadForecastValue() + ",");  
                if (i != records.size()-1)
                    System.out.println("    }, ") ;
                else 
                    System.out.println("    }");
            }
            System.out.println("]");
            return 0;        
        }
        else {
            cli.getOut().println("Bad input");
            return -1;
        }
    }
    catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }
}