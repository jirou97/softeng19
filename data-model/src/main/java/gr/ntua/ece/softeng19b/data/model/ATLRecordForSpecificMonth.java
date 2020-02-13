package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificMonth extends AbstractEntsoeRecord {

    private double actualTotalLoadByDayValue;
    private int day;

    public ATLRecordForSpecificMonth() {
        super(DataSet.ActualTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setday(int day) {
        this.day = day;
    }
    public double getActualTotalLoadByDayValue() {
        return actualTotalLoadByDayValue;
    }

    public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
        this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
    }
}
