package gr.ntua.ece.softeng19b.data.model;
//import java.sql.Date;

public class AvFRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double ActualTotalLoadValue;
    private double DayAheadTotalLoadForecastValue;
    private String DateTime; 

    public AvFRecordForSpecificDay() {
        super(DataSet.ActualVSForecastedTotalLoad);
    }

    
    public String getDateTime() {
        return DateTime;
    }
    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }
    

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getActualTotalLoadValue() {
        return ActualTotalLoadValue;
    }

    public void setActualTotalLoadValue(double ActualTotalLoadValue) {
        this.ActualTotalLoadValue = ActualTotalLoadValue;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return DayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double DayAheadTotalLoadForecastValue) {
        this.DayAheadTotalLoadForecastValue = DayAheadTotalLoadForecastValue;
    }
}
