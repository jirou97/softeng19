
package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;

import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
    name="ActualTotalLoad"
)
public class ActualTotalLoad extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if (dateArgs.date != null ) {
                List<ATLRecordForSpecificDay> records = new RestAPI().
                        getActualTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    ATLRecordForSpecificDay X = new ATLRecordForSpecificDay();
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
                    
                    System.out.print("      UpdateTime");
                    System.out.println(" : " + X.getUpdateTime() + ",");      
                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
            else if (dateArgs.month!=null) {
                List<ATLRecordForSpecificMonth> records = new RestAPI().
                                getActualTotalLoadForMonth(areaName, timeres.name(), LocalDate.parse(dateArgs.month+"-01"), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    ATLRecordForSpecificMonth X = new ATLRecordForSpecificMonth();
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
                    System.out.print("      ActualTotalLoadByDayValue");
                    System.out.println(" : " + X.getActualTotalLoadByDayValue() + ",");     
                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
        else if (dateArgs.year!=null) {
            List<ATLRecordForSpecificYear> records = new RestAPI().
                            getActualTotalLoadForYear(areaName, timeres.name(), LocalDate.parse(dateArgs.year+"-01-01"), format);
            // Do something with the records :)
            System.out.println("Fetched " + records.size() + " record(s)");
            System.out.println("[");
            Integer i ;
            for (i = 0 ; i < records.size() ; i ++){    
                ATLRecordForSpecificYear X = new ATLRecordForSpecificYear();
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
                System.out.print("      ActualDataLoadByMonthValue");
                System.out.println(" : " + X.getActualTotalLoadByMonthValue() + ",");     
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