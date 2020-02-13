package gr.ntua.ece.softeng19b.data.model;
import java.sql.Date;

public class DATLFRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecast;
    private String DateTime; 
    private String UpdateTime;

    public DATLFRecordForSpecificDay() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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


    public double getdayAheadTotalLoadForecast() {
        return dayAheadTotalLoadForecast;
    }

    public void setdayAheadTotalLoadForecast(double dayAheadTotalLoadForecast) {
        this.dayAheadTotalLoadForecast = dayAheadTotalLoadForecast;
    }
}
