package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecast;

    public DATLFRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getdayAheadTotalLoadForecast() {
        return dayAheadTotalLoadForecast;
    }

    public void setdayAheadTotalLoadForecast(double dayAheadTotalLoadForecast) {
        this.dayAheadTotalLoadForecast = dayAheadTotalLoadForecast;
    }
}
