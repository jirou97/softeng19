package gr.ntua.ece.softeng19b.api.representation;

import com.google.gson.Gson;
import gr.ntua.ece.softeng19b.data.model.ImportResult;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonImportResultRepresentation extends WriterRepresentation {

    private final ImportResult res;

    public JsonImportResultRepresentation(ImportResult res) {
        super(MediaType.APPLICATION_JSON);
        this.res = res;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(res));
    }
}
