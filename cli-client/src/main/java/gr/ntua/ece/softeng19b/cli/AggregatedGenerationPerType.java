package gr.ntua.ece.softeng19b.cli;
import gr.ntua.ece.softeng19b.client.RestAPI;

import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;

import picocli.CommandLine;
import java.util.concurrent.Callable;
import static picocli.CommandLine.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


import static picocli.CommandLine.Command;

@Command(
    name="AggregatedGenerationPerType"
)


public class AggregatedGenerationPerType extends EnergyCliArgs implements Callable<Integer> {
/*
    public enum productionType{
        Fossil Gas,
        Hydro Run-of-river and poundage,
        Hydro Pumped Storage,
        Hydro Water Reservoir,
        Fossil Hard coal,
        Nuclear,
        Fossil Brown coal/Lignite,
        Fossil Oil,
        Fossil Oil shale,
        Biomass,
        Fossil Peat,
        Wind Onshore,
        Other,
        Wind Offshore,
        Fossil Coal-derived gas,
        Waste,
        Solar,
        Geothermal,
        Other renewable,
        Marine,
        AC Link,
        Transformer,
        DC Link,
        Substation    

    }
    */
    static final private List<String> prodtypes = new ArrayList<>( 
        List.of(
                "Fossil"+"\u0020"+"Gas",
                "Hydro Run-of-river and poundage",
                "Hydro Pumped Storage",
                "Hydro Water Reservoir",
                "Fossil Hard coal",
                "Nuclear",
                "Fossil Brown coal/Lignite",
                "Fossil Oil",
                "Fossil Oil shale",
                "Biomass",
                "Fossil Peat",
                "Wind Onshore",
                "Other",
                "Wind Offshore",
                "Fossil Coal-derived gas",
                "Waste",
                "Solar",
                "Geothermal",
                "Other renewable",
                "Marine",
                "AC Link",
                "Transformer",
                "DC Link",
                "Substation",
                "AllTypes"
        ));
    @Option(
        names = "--prodtype",
        required = true,
        description = "the production type."
    )
    protected String productionType;

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if (!prodtypes.contains(productionType)){
                cli.getOut().println("Bad Production Type value.");
                return -1;                
            }
            /*
            if (dateArgs.date == null && dateArgs.month == null && dateArgs.year == null){
                dateArgs.date = LocalDate.now().toString();
            }
            */
            if (dateArgs.date != null ) {

                List<AGPTRecordForSpecificDay> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, timeres.name(),LocalDate.parse(dateArgs.date),productionType, format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    AGPTRecordForSpecificDay X = new AGPTRecordForSpecificDay();
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
                    System.out.print("      ProductionType");
                    System.out.println(" : " + X.getproductionTypeCodeText() + ",");  
                    System.out.print("      ActualGenerationOutputValue");
                    System.out.println(" : " + X.getActualGenerationOutputValue() + ","); 
                    System.out.print("      UpdateTime");
                    System.out.println(" : " + X.getUpDateTime() + ","); 

                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
            else if (dateArgs.month != null ) {
                List<AGPTRecordForSpecificMonth> records = new RestAPI().
                        getAggregatedGenerationPerTypeForMonth(areaName, timeres.name(),LocalDate.parse(dateArgs.month+"-01"),productionType, format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    AGPTRecordForSpecificMonth X = new AGPTRecordForSpecificMonth();
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
                    System.out.print("      ProductionType");
                    System.out.println(" : " + X.getproductionTypeCodeText() + ",");  
                    System.out.print("      ActualGenerationOutputByDayValue");
                    System.out.println(" : " + X.getActualGenerationOutputByDayValue() + ","); 

                    if (i != records.size()-1)
                        System.out.println("    }, ") ;
                    else 
                        System.out.println("    }");
                }
                System.out.println("]");
                return 0;
            }
            else if (dateArgs.year != null ) {
                List<AGPTRecordForSpecificYear> records = new RestAPI().
                        getAggregatedGenerationPerTypeForYear(areaName, timeres.name(),LocalDate.parse(dateArgs.year+"-01-01"),productionType, format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                System.out.println("[");
                Integer i ;
                for (i = 0 ; i < records.size() ; i ++){    
                    AGPTRecordForSpecificYear X = new AGPTRecordForSpecificYear();
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
                    System.out.print("      ProductionType");
                    System.out.println(" : " + X.getproductionTypeCodeText() + ",");   
                    System.out.print("      ActualGenerationOutputByMonthValue");
                    System.out.println(" : " + X.getActualGenerationOutputByMonthValue() + ","); 

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