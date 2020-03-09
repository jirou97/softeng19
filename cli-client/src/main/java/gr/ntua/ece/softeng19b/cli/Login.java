
package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

import java.io.IOException;
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
                throw new IOException("File not found, fix this");
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
			Files.write(Paths.get("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\cli-client\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\cli\\softeng19bAPI.token"), (token+"\n").getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			throw new IOException("File not found, fix this");
		}
	}
}