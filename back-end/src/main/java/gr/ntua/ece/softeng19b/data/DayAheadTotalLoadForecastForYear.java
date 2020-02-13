package gr.ntua.ece.softeng19b.data;


public class DayAheadTotalLoadForecastForYear {

    private String areaName;
    private String resolutionCodeText;
    private int year;
    private int month;
    private int day;
    private String areaTypeCodeText;
    public String source = new String("entso-e");
    public String dataset = new String("DayAheadTotalLoadForecast");
    private String mapCodeText;
    private Float dayAheadTotalLoadForecast ;

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

    public Float getDayAheadTotalLoadForecast() {
        return dayAheadTotalLoadForecast;
    }

    public void setDayAheadTotalLoadForecast(Float dayAheadTotalLoadForecast) {
        this.dayAheadTotalLoadForecast = dayAheadTotalLoadForecast;
    }
    
    public String getMapCode() {
        return mapCodeText;
    }

    public void setMapCodeText(String mapCode) {
        this.mapCodeText = mapCode;
    }

    public DayAheadTotalLoadForecastForYear() {

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