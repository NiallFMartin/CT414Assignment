
package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Server.NoMatchingAssessment;
import Server.UnauthorizedAccess;
import Server.Assessment;
import java.util.*;

public class ExamEngine implements ExamServer {

	private int [] studentIDs = {123, 456, 789, 012};
	private String [] passwords = {"abc", "def", "ghi", "jkl"};
	private int token = 0;
	
//	public static List<Assessment> assessments = new ArrayList<Assessment>();
	public List<Assessment> completedAssessments = new ArrayList<Assessment>();
	
    // Constructor is required
    public ExamEngine() {
        super();
    }

    //Create assessment and question objects
    public List<Assessment> createObjects() throws ParseException, RemoteException
    {
    	List<Assessment> assessments = new ArrayList<Assessment>();
    	
    	//Create questions
    	List<Question> qList1 = new ArrayList<Question>();
    	String[] mountains = {"1: Everest", "2: K2", "3: Mount Blanc"};
    	Question q1 = new QuestionImplementation(1, "What is the tallest mountain?", mountains , 1, 0);
    	Question q2 = new QuestionImplementation(1, "What is the smallest mountain?", mountains , 3, 0);
    	qList1.add(q1);
    	qList1.add(q2);
    	
    	List<Question> qList2 = new ArrayList<Question>();
    	String[] rivers = {"1: Nile", "2: Amazon", "3: Shannon"};
    	Question q3 = new QuestionImplementation(1, "What is the longest river?", rivers , 1, 0);
    	Question q4 = new QuestionImplementation(1, "What is the shortest river?", rivers , 3, 0);
    	qList2.add(q3);
    	qList2.add(q4);
    	
    	List<Question> qList3 = new ArrayList<Question>();
    	String[] countries = {"1: Russia", "2: Germany", "3: Ireland"};
    	Question q5 = new QuestionImplementation(1, "What is the largest country?", countries , 1, 0);
    	Question q6 = new QuestionImplementation(1, "What is the smallest country?", countries , 3, 0);
    	qList3.add(q5);
    	qList3.add(q6);
    	
    	//create dates
    	SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");    	
    	Date d1 = fmt.parse("10/03/2016");
    	Date d2 = fmt.parse("20/02/2016");
    	Date d3 = fmt.parse("25/02/2016");
    	
    	//create assessments		
    	Assessment assessment1 = new AssessmentImplementation(1, "CT414 - Mountains Assignment",d1 , qList1, 123, false);
    	Assessment assessment2 = new AssessmentImplementation(2, "CT450 - Rivers Assignment",d2 , qList2, 456, false);
    	Assessment assessment3 = new AssessmentImplementation(3, "CT425 - Countries Assignment",d3 , qList3, 789, false);
    	
    	assessments.add(assessment1);
    	assessments.add(assessment2);
    	assessments.add(assessment3);
    	
    	return assessments;
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
    	for (int i = 0; i < passwords.length; i++ ){
    		if (passwords[i].equals(password)){
    			passwordCheck = 1;
    		}
    	}
    	
    	if ((studIDCheck == 1) && (passwordCheck == 1)){
    		System.out.println("User is now logged in...");
    		token = 1;
    		return 1;// Successful login.
    	} else {
    		System.out.println("Incorrect user login...");
    		return 0; //Incorrect login.
    	}
	
    }

    // Return a summary list of Assessments currently available for this studentid
//    public List<String> getAvailableSummary(int token, int studentid) throws
//                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
//
//    	List<String> summaries = null;
//    	
//    	if (token == 1){
//    		for (Assessment a : assessments){
//    			if (a.getStudentID() == studentid){
//    				summaries.add(a.getInformation());
//    			}
//    		}
//    		return summaries;
//    	}
//        return null;
//    }
//
//    // Return an Assessment object associated with a particular course code
//    public Assessment getAssessment(int token, int studentid, int courseCode) throws
//                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
//
//    	if (token == 1){
//    		for (Assessment a : assessments){
//    			if ((a.getStudentID() == studentid) && (a.getCourseCode() == courseCode)){
//    				return a;
//    			}
//    		}
//    	}
//        return null;
//    }
//
//    // Submit a completed assessment
//    public void submitAssessment(int token, int studentid, Assessment completed) throws 
//                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
//    		
//    	if (token == 1) {
//    		for (Assessment a : completedAssessments) {
//    			if ((a.getCourseCode() == completed.getCourseCode()) && (a.getStudentID() == completed.getStudentID())){
//    				a = completed;
//    			} else {
//    				completedAssessments.add(completed);
//    			}
//    		}
//    	}
//    
//    }

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
        
//        Assessment assessment = new AssessmentImplementation();
        
        
    }
}
