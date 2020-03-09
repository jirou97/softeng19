package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;

import static picocli.CommandLine.*;

@Command
public class UserCliArgs extends BasicCliArgs {

    

    @Option(
        names = "--username",
        required = true
    )
    protected String username;
    

    @Option(
        names = "--passw",
        required = true
    )
    protected String passw;

    

    @Option(
        names = "--format",
        defaultValue = "JSON",
        fallbackValue = "JSON",
        description = "the format of the response; supported values: ${COMPLETION-CANDIDATES} (default is ${DEFAULT-VALUE})."
    )
    protected Format format;

}