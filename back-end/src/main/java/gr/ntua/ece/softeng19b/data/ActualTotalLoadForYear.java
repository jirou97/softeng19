package gr.ntua.ece.softeng19b.data;


public class ActualTotalLoadForYear {

    private String areaName;
    private String resolutionCodeText;
    private int year;
    private int month;
    private String areaTypeCodeText;
    public String source = new String("entso-e");
    public String dataset = new String("ActualTotalLoad");
    private String mapCodeText;
    private Float actualTotalLoadValue ;


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

    public Float getActualTotalLoadValue() {
        return actualTotalLoadValue;
    }

    public void setActualTotalLoadValue(Float actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
    }
    public String getMapCode() {
        return mapCodeText;
    }

    public void setMapCodeText(String mapCode) {
        this.mapCodeText = mapCode;
    }

    public ActualTotalLoadForYear() {

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

}