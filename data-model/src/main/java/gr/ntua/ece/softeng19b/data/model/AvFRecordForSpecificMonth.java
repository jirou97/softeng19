package gr.ntua.ece.softeng19b.data.model;

public class AvFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double ActualTotalLoadValue;
    private double DayAheadTotalLoadForecastValue;

    public AvFRecordForSpecificMonth() {
        super(DataSet.ActualVSForecastedTotalLoad);
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
