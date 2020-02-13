package gr.ntua.ece.softeng19b.data.model;
import java.sql.Date;

public class AGPTRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double ActualGenerationOutputValue ;
    private String productionTypeCodeText;
    private String DateTime;
    private String UpdateTime;

    public AGPTRecordForSpecificDay() {
        super(DataSet.AggregatedGenerationPerType);
    }
    
    public String getproductionTypeCodeText() {
        return productionTypeCodeText;
    }

    public void setproductionTypeCodeText(String productionTypeCodeText) {
        this.productionTypeCodeText = productionTypeCodeText;
    }
    
    public String getDateTime() {
        return DateTime;
    }
    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }
    
    public String getUpDateTime() {
        return UpdateTime;
    }
    public void setUpDateTime(String UpDateTime) {
        this.UpdateTime = UpDateTime;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualGenerationOutputValue() {
        return ActualGenerationOutputValue;
    }

    public void setActualGenerationOutputValue(double ActualGenerationOutputValue) {
        this.ActualGenerationOutputValue = ActualGenerationOutputValue;
    }
}
