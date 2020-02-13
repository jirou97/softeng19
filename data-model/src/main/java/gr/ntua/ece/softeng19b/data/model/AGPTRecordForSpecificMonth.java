package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double ActualGenerationOutputByDayValue ;
    private String productionTypeCodeText;

    public AGPTRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public String getproductionTypeCodeText() {
        return productionTypeCodeText;
    }

    public void setproductionTypeCodeText(String productionTypeCodeText) {
        this.productionTypeCodeText = productionTypeCodeText;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualGenerationOutputByDayValue() {
        return ActualGenerationOutputByDayValue;
    }

    public void setActualGenerationOutputByDayValue(double ActualGenerationOutputByDayValue) {
        this.ActualGenerationOutputByDayValue = ActualGenerationOutputByDayValue;
    }
}
