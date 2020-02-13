package gr.ntua.ece.softeng19b.data;


public class ActualvsForecastForYear {

    private String areaName;
    private String resolutionCodeText;
    private int year;
    private int month;
    private String areaTypeCodeText;
    public String source = new String("entso-e");
    public String dataset = new String("ActualVSForecastedTotalLoad");
    private String mapCodeText;
    private Float DayAheadTotalLoadForecastByMonthValue ;
    private Float ActualTotalLoadByMonthValue;

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

    public Float getDayAheadTotalLoadForecastByMonthValue() {
        return DayAheadTotalLoadForecastByMonthValue;
    }

    public void setDayAheadTotalLoadForecastByMonthValue(Float DayAheadTotalLoadForecastByMonthValue) {
        this.DayAheadTotalLoadForecastByMonthValue = DayAheadTotalLoadForecastByMonthValue;
    }

    
    public Float getActualTotalLoadByMonthValue() {
        return ActualTotalLoadByMonthValue;
    }

    public void setActualTotalLoadByMonthValue(Float ActualTotalLoadByMonthValue) {
        this.ActualTotalLoadByMonthValue = ActualTotalLoadByMonthValue;
    }
    public String getMapCode() {
        return mapCodeText;
    }

    public void setMapCodeText(String mapCode) {
        this.mapCodeText = mapCode;
    }

    public ActualvsForecastForYear() {

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