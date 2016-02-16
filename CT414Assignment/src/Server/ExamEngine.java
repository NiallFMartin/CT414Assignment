
package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import Server.NoMatchingAssessment;
import Server.UnauthorizedAccess;
import Server.Assessment;

public class ExamEngine implements ExamServer {

	private int [] studentIDs = {123, 456, 789, 012};
	private String [] passwords = {"abc", "def", "ghi", "jkl"};
	
    // Constructor is required
    public ExamEngine() {
        super();
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {

	// TBD: You need to implement this method!
	// For the moment method just returns an empty or null value to allow it to compile
    	int studIDCheck = 0, passwordCheck = 0;
    	
    	for (int i = 0; i < studentIDs.length; i++) {
    		if (studentIDs[i] == studentid) {
    			studIDCheck = 1;
    		}
    	}
    	
    	for (int i = 0; i < password.length(); i++ ){
    		if (passwords[i].equals(password)){
    			passwordCheck = 1;
    		}
    	}
    	
    	if ((studIDCheck == 1) && (passwordCheck == 1)){
    		System.out.println("User is now logged in...");
    	} else {
    		System.out.println("Incorrect user login...");
    	}

	return studIDCheck;	
    }

    // Return a summary list of Assessments currently available for this studentid
    public List<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile

        return null;
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile

        return null;
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ExamServer";
            ExamServer engine = new ExamEngine();
            ExamServer stub =
                (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ExamEngine Started...");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
        
        Assessment assessment = new AssessmentImplementation();
        
        
    }
}
