package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificYear extends AbstractEntsoeRecord {

    private double ActualGenerationOutputByMonthValue ;
    private String productionTypeCodeText;

    public AGPTRecordForSpecificYear() {
        super(DataSet.AggregatedGenerationPerType);
    }
    
    public String getproductionTypeCodeText() {
        return productionTypeCodeText;
    }

    public void setproductionTypeCodeText(String productionTypeCodeText) {
        this.productionTypeCodeText = productionTypeCodeText;
    }

    public double getActualGenerationOutputByMonthValue() {
        return ActualGenerationOutputByMonthValue;
    }

    public void setActualGenerationOutputByMonthValue(double ActualGenerationOutputByMonthValue) {
        this.ActualGenerationOutputByMonthValue = ActualGenerationOutputByMonthValue;
    }
}
