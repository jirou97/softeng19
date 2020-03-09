package gr.ntua.ece.softeng19b.cli;

import static picocli.CommandLine.*;

public class AdminCliArgs  {

    @Option(
        names = "--newuser",
        description = "to add new user"
    )
    protected String newuser;

    @Option(
        names = "--moduser",
        description = "to upldate an existing user"
    )
    protected String moduser;

    @Option(
        names = "--userstatus",
        description = "to get an existing user"
    )
    protected String userstatus;

    public enum NewData {
        ActualTotalLoad,
        AggregatedGenerationPerType,
        DayAheadTotalLoadForecast
    }

    @Option(
        names = "--newdata",
        description = "the name of the area."
    )
    protected NewData newData;
}