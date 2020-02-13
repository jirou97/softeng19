package gr.ntua.ece.softeng19b.data;


public class AggregatedGenerationPerTypeForMonth {

    private String areaName;
    private String resolutionCodeText;
    private String mapCodeText;
    private String areaTypeCodeText;
    private String productionTypeCodeText;
    private int year;
    private int month;
    private int day;
    public String source = new String("entso-e");
    public String dataset = new String("AggregatedGenerationPerType");
    private Float ActualGenerationOutputByDayValue ;

    public String getSource( ){
        return source;
    }
    public String getDataset( ){
        return dataset;
    }
    
    public String getAreaTypeCodeText() {
        return areaTypeCodeText;
    }

    public void setAreaTypeCodeText(String areaTypeCodeText) {
        this.areaTypeCodeText = areaTypeCodeText;
    }

    public String getproductionTypeCodeText() {
        return productionTypeCodeText;
    }

    public void setproductionTypeCodeText(String productionTypeCodeText) {
        this.productionTypeCodeText = productionTypeCodeText;
    }

    public Float getActualGenerationOutputByDayValue() {
        return ActualGenerationOutputByDayValue;
    }

    public void setActualGenerationOutputByDayValue(Float ActualGenerationOutputByDayValue) {
        this.ActualGenerationOutputByDayValue = ActualGenerationOutputByDayValue;
    }
    
    public String getMapCode() {
        return mapCodeText;
    }

    public void setMapCodeText(String mapCode) {
        this.mapCodeText = mapCode;
    }

    public AggregatedGenerationPerTypeForMonth() {

    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getResolutionText() {
        return resolutionCodeText;
    }

    public void setResolutionText(String resolution) {
        this.resolutionCodeText = resolution;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}