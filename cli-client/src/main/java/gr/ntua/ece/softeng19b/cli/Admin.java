package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.client.ImportResult;

import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
    name="Admin"
)
public class Admin extends AdminCliArgsMult implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }
        
        RestAPI Z = new RestAPI();
        try {   
            Z.setToken(Z.retrieveTokenFromFile());
                if (adminArgs.newuser != null  ){
                    Z.addUser(adminArgs.newuser.toString(), email, passw, quotas);
                    return 0;
                }
                else if (adminArgs.moduser != null ){
                    if (email == null){
                        email = "-1";
                    }
                    User user = Z.updateUser(new User((adminArgs.moduser).toString(), email, quotas));
                    //System.out.println(adminArgs.moduser + " " + email + " " + quotas);
                    System.out.println("    {") ;
                    System.out.print("        username") ;
                    System.out.println(" : " +user.getUsername());  
                    System.out.print("        email") ;
                    System.out.println(" : " +user.getEmail());  
                    System.out.print("        quotasLeft") ;
                    System.out.println(" : " +user.getRequestsPerDayQuota());  
                    System.out.println("    }") ;
                    return 0;
                }
                else if (adminArgs.userstatus != null ){
                    User user = Z.getUser(adminArgs.userstatus.toString());
                    System.out.println("    {") ;
                    System.out.print("        quotasLeft") ;
                    System.out.println(" : " +user.getRequestsPerDayQuota());  
                    System.out.println("    }") ;
                    return 0;
                }
                else if (adminArgs.newData != null){
                    ImportResult importedResult = Z.importFile((adminArgs.newData).toString(),source);
                    System.out.println("    {") ;
                    System.out.print("        totalRecordsInFile") ;
                    System.out.println(" : " +importedResult.getTotalRecordsInFile() + ",");  
                    System.out.print("        totalRecordsImported") ;
                    System.out.println(" : " +importedResult.getTotalRecordsImported() + ","); 
                    System.out.print("        totalRecordsInDatabase") ;
                    System.out.println(" : " +importedResult.getTotalRecordsInDatabase() ); 
                    System.out.println("    }") ;
                    return 0;
                }
                else {
                    return -1;
                }            
        }
    catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }
}