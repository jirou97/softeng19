package gr.ntua.ece.softeng19b.data.model;

public class AvFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double ActualTotalLoadValue;
    private double DayAheadTotalLoadForecastValue;

    public AvFRecordForSpecificYear() {
        super(DataSet.ActualVSForecastedTotalLoad);
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
