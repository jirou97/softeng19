package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.Format;

import java.nio.file.Path;
import static picocli.CommandLine.*;

@Command
public class AdminCliArgsMult extends BasicCliArgs {

    @ArgGroup(exclusive = true, multiplicity = "1")
    protected AdminCliArgs adminArgs;
    
    @Option(
        names = "--format",
        defaultValue = "JSON",
        fallbackValue = "JSON",
        description = "the format of the response; supported values: ${COMPLETION-CANDIDATES} (default is ${DEFAULT-VALUE})."
    )
    protected Format format;

    @Option(
        names = "--source",
        description = "filename",
        required = false
    )
    protected Path source;
    
    @Option(
        names = "--passw",
        description = "password (not empty)",
        required = false
    )
    protected String passw;

    @Option(
        names = "--email",
        description = "email (unique)",
        required = false
    )
    protected String email;

    @Option(
        names = "--quota",
        required = false
    )
    protected int quotas;

}