
package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


@Command(
    name="Logout"
)
public class Logout extends BasicCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }
        RestAPI Z = new RestAPI();

        try { 
            String auth = Z.retrieveTokenFromFile();
            Z.setToken(auth);
            
               Z.logout();
               removeLineFromFile("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\cli-client\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\cli\\softeng19bAPI.token",auth);
               System.out.println("    {") ;
               System.out.print("        Status") ;
               System.out.println(" : 200");  
               System.out.println("    }") ;
                return 0;
            
        }
    catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }
    
	public void removeLineFromFile(String file, String lineToRemove) {
		
		try {
	
		  File inFile = new File(file);
	
		  if (!inFile.isFile()) {
			System.out.println("This is not a file to delete from");
			return;
		  }
	
		  //Construct the new file that will later be renamed to the original filename.
		  File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
	
		  BufferedReader br = new BufferedReader(new FileReader(file));
		  PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	
		  String line = null;
	
		  //Read from the original file and write to the new
		  //unless content matches data to be removed.
		  while ((line = br.readLine()) != null) {
	
			if (!line.trim().equals(lineToRemove)) {
	
			  pw.println(line);
			  pw.flush();
			}
		  }
		  pw.close();
		  br.close();
	
		  //Delete the original file
		  if (!inFile.delete()) {
			System.out.println("Could not delete file");
			return;
		  }
	
		  //Rename the new file to the filename the original file had.
		  if (!tempFile.renameTo(inFile))
			System.out.println("Could not rename file");
	
		}
		catch (FileNotFoundException ex) {
		  ex.printStackTrace();
		}
		catch (IOException ex) {
		  ex.printStackTrace();
		}
	}
}