package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Header;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.fileupload.*;

import org.restlet.representation.Representation;
import gr.ntua.ece.softeng19b.api.representation.JsonImportResultRepresentation;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.model.ImportResult;

import org.restlet.resource.ResourceException;
import org.restlet.util.Series;
import org.restlet.ext.fileupload.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.util.Base64;

public class ImportAggregatedGenerationPerType extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    @Override
    public Representation post(Representation entity) throws ResourceException {
        //Representation result = null;
        @SuppressWarnings("unchecked")
        Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
        //System.out.println(headers.getNames());
        String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
        if (auth == null || auth == "-1"){
            throw new ResourceException(new Status(401),"You are not authorized for this call",null);
        }
        if (!dataAccess.isAdmin(auth) && !dataAccess.isUser(auth)){
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "You are not authorized for this information");
        }
        try{
            if (entity != null) {
                if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true)) {
                    // 1/ Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setSizeThreshold(300000000);
        
                    // 2/ Create a new file upload handler based on the Restlet
                    // FileUpload extension that will parse Restlet requests and
                    // generates FileItems.
                    RestletFileUpload upload = new RestletFileUpload(factory);
        
                    // 3/ Request is parsed by the handler which generates a
                    // list of FileItems
                    FileItemIterator fileIterator = upload.getItemIterator(entity);

                    // Process only the uploaded item called "fileToUpload"
                    // and return back
                    boolean found = false;
                    while (fileIterator.hasNext() && !found) {
                        
                        FileItemStream fi = fileIterator.next();

                        /*
                        BufferedReader br = new BufferedReader(new InputStreamReader(fi.openStream()));
                        if (fi.getFieldName().equals("Id")){
                            long Id = br
                        }
                        */
                        if (fi.getFieldName().equals("file")) {
                            found = true;
                            // consume the stream immediately, otherwise the stream
                            // will be closed.
                            /*
                            StringBuilder sb = new StringBuilder("media type: ");
                            sb.append(fi.getContentType()).append("\n");
                            sb.append("file name : ");
                            sb.append(fi.getName()).append("\n");
                            */
                            StringBuilder sb = new StringBuilder();
                            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(fi.openStream()));
                            //String line = br.readLine();
                            String line = new String();
                            //String actualTotalLoadRows = new String (Base64.getDecoder().decode(line.getBytes("utf-8")));
                            while ((line = br.readLine()) != null) {
                                //decode from Base64 to byte and make String.
                                String toAdd = new String(Base64.getDecoder().decode(line.getBytes("utf-8")));
                                //System.out.println(toAdd);                             
                                
                                sb.append(toAdd);
                            }
                            //System.out.println(sb.toString());
                            String[] toPasss = sb.toString().split("\n");
                            //String toPass = toPasss[0];
                            File file = new File("C:" + File.separator + "Users" + File.separator + "user" + File.separator + "Desktop" + File.separator + "2.0TL19-25-our-master" + File.separator + "back-end" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "gr" + File.separator + "ntua" + File.separator + "ece" + File.separator + "softeng19b" + File.separator + "api" + File.separator + "resource" + File.separator + "temp.csv");
                            file.getParentFile().mkdirs(); 
                            if(!file.exists()) file.createNewFile();
                            for (int i = 1 ; i < toPasss.length ;i++)
                                    appendToFile(toPasss[i]);
                            //System.out.println(System.getProperty("." + File.separator + "temp.csv"));
                            //Paths.get("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\back-end\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\api\\resource\\temp.csv"),toPass);
                            //System.out.println("OK");
                            long changedLines = dataAccess.insertAGPTIntoDatabaseUsingLD("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\back-end\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\api\\resource\\temp.csv");
                            file.delete();
                            //System.out.println(sb.toString());
                            //String[] toPass = sb.toString().split("\n");
                            //long changedLines = dataAccess.insertATLIntoDatabase(toPass);
                            long i = toPasss.length;
                            ImportResult X = new ImportResult(i,changedLines,dataAccess.countAGPTLines());  
                            //System.out.println(X.getTotalRecordsInFile()+" "+X.getTotalRecordsImported()+" "+X.getTotalRecordsInDatabase());
                            
                            return new JsonImportResultRepresentation(X);                     
                        }
                        
                    }
                } 
                else {
                    throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"No csv file found.",null);
                }   
            }    
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"Null Representation",null);
        }
        catch (Exception e){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,"File not found ",null);
        }
    }
    
    public void appendToFile(String token) 
    throws IOException {
		/*
        FileWriter fileWriter = new FileWriter("C:\\Users\\user\\Desktop\\TL19-25-master\\back-end\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\api\\resource\\softeng19bAPI.token");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.append(token);
		printWriter.close();
		*/
		try {
			Files.write(Paths.get("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\back-end\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\api\\resource\\temp.csv"), (token+"\n").getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			throw new IOException("File not found, fix this");
		}
	}
}
  