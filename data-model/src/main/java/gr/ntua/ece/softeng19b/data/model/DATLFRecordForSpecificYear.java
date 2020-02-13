package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecast;

    public DATLFRecordForSpecificYear() {
        super(DataSet.DayAheadTotalLoadForecast);
    }


    public double getdayAheadTotalLoadForecast() {
        return dayAheadTotalLoadForecast;
    }

    public void setdayAheadTotalLoadForecast(double dayAheadTotalLoadForecast) {
        this.dayAheadTotalLoadForecast = dayAheadTotalLoadForecast;
    }
}
