
package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Command(
    name="Login"
)
public class Login extends UserCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
                
            String token = new RestAPI().login(username,passw);
            try {
                appendToFile(token);
            }
            catch(Exception e){
                throw new IOException("Existing user");
            }
            System.out.println("    {") ;
            System.out.print("        token") ;
            System.out.println(" : " + token);  
            System.out.println("    }") ;
                return 0;
            
        }
    catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
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
            countLinesNew("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\cli-client\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\cli\\softeng19bAPI.token");
			Files.write(Paths.get("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\cli-client\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\cli\\softeng19bAPI.token"), (token+"\n").getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			throw new IOException("File not found, fix this");
		}
    }
    
    public static int countLinesNew(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
    
            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }
    
            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }
    
            // count remaining characters
            while (readChars != -1) {
                System.out.println(readChars);
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }
            if (count == 0 ){
                return count;
            }
            else {
                throw new IOException("Existing User");
            }
        } finally {
            is.close();
        }
    }
}