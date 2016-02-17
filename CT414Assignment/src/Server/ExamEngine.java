//Shane O' Rourke - 12361351
//Niall Martin - 12301341

package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Server.NoMatchingAssessment;
import Server.UnauthorizedAccess;
import Server.Assessment;
import java.util.*;

public class ExamEngine implements ExamServer {

	//Available logins.
	private int [] studentIDs = {123, 456, 789, 012};
	private String [] passwords = {"abc", "def", "ghi", "jkl"};
	private int token = 0;
	
	public List<Assessment> assessments = new ArrayList<Assessment>();
	public List<Assessment> completedAssessments = new ArrayList<Assessment>();
	
    // Constructor is required
    public ExamEngine() {
        super();
    }

    //Create assessment and question objects
    public List<Assessment> createObjects() throws ParseException, RemoteException {
    	//Initiates 2 questions and adds them to question list. 
    	
    	//Create questions
    	List<Question> qList1 = new ArrayList<Question>();
    	String[] mountains = {"1: Everest", "2: K2", "3: Mount Blanc"};
    	Question q1 = new QuestionImplementation(1, "What is the tallest mountain?", mountains , 1, 0);
    	Question q2 = new QuestionImplementation(2, "What is the smallest mountain?", mountains , 3, 0);
    	qList1.add(q1);
    	qList1.add(q2);
    	
    	List<Question> qList2 = new ArrayList<Question>();
    	String[] rivers = {"1: Nile", "2: Amazon", "3: Shannon"};
    	Question q3 = new QuestionImplementation(1, "What is the longest river?", rivers , 1, 0);
    	Question q4 = new QuestionImplementation(2, "What is the shortest river?", rivers , 3, 0);
    	qList2.add(q3);
    	qList2.add(q4);
    	
    	List<Question> qList3 = new ArrayList<Question>();
    	String[] countries = {"1: Russia", "2: Germany", "3: Ireland"};
    	Question q5 = new QuestionImplementation(1, "What is the largest country?", countries , 1, 0);
    	Question q6 = new QuestionImplementation(2, "What is the smallest country?", countries , 3, 0);
    	qList3.add(q5);
    	qList3.add(q6);
    	
    	//Closing dates for assessment1, 2, 3.
    	SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");    	
    	Date d1 = fmt.parse("10/03/2016");
    	Date d2 = fmt.parse("10/01/2016");//Set to the past.
    	Date d3 = fmt.parse("25/03/2016");
    	
    	//Create assessment objects and adds them to assessments list.		
    	Assessment assessment1 = new AssessmentImplementation(1, "CT414 - Mountains Assignment",d1 , qList1, 123, false, null);
    	Assessment assessment2 = new AssessmentImplementation(2, "CT450 - Rivers Assignment",d2 , qList2, 456, false, null);
    	Assessment assessment3 = new AssessmentImplementation(3, "CT425 - Countries Assignment",d3 , qList3, 789, false, null);
    	
    	assessments.add(assessment1);
    	assessments.add(assessment2);
    	assessments.add(assessment3);
    	
    	return assessments;
    }
    
    //Prompts for studentID and password.
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {

    	int studIDCheck = 0, passwordCheck = 0;
    	
    	//If studentID is contained in the server's studentID array.
    	for (int i = 0; i < studentIDs.length; i++) {
    		if (studentIDs[i] == studentid) {
    			studIDCheck = 1;
    		}
    	}
    	//If password is contained in the server's passwords array.
    	for (int i = 0; i < passwords.length; i++ ){
    		if (passwords[i].equals(password)){
    			passwordCheck = 1;
    		}
    	}
    	
    	//If both are correct then return 1 representing true.
    	if ((studIDCheck == 1) && (passwordCheck == 1)){
    		System.out.println("User is now logged in...");
    		token = 1;//Token set to 1 when login is approved.
    		return 1;// Successful login.
    	} else {
    		System.out.println("Incorrect user login...");
    		return 0; //Incorrect login.
    	}
	
    }

    // Return a summary list of Assessments currently available for this studentID.
    public List<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

    	List<String> summaries = new ArrayList<String>();
    	
    	//Get current date.
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	
    	//Access token set to 1 when login is approved.
    	if (token == 1){
    		//Search for assessments containing studentID and that are not within the submission deadlines.
    		for (Assessment a : assessments){
    			if (a.getStudentID() == studentid && (date.before(a.getClosingDate()) || date.equals(a.getClosingDate()))){
    				summaries.add("Assessment ID: "+a.getAssessmentCode()+"\n"+a.getInformation());
    			}
    			//Else tell user assessment has closed.
    			else if(a.getStudentID() == studentid && date.after(a.getClosingDate())){
    				summaries.add("Assessment ID: "+a.getAssessmentCode()+"\n"+a.getInformation() + 
    						"\nCompleted: " + a.getCompletionStatus() + "\nASSESSMENT HAS CLOSED");
    			}
    		}
    		return summaries;
    	} else {
    		return null;
    	}
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, int assessmentCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

    	//get current date
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	
    	if (token == 1){
    		//Search for assessments containing studentID and within the submission deadlines.
    		for (Assessment a : assessments){
    			if (a.getStudentID() == studentid && (date.before(a.getClosingDate()) || date.equals(a.getClosingDate()))){
    				return a;
    			}
    			else if(a.getStudentID() == studentid && date.after(a.getClosingDate())){
    				return null;
    			}
    			
    		}
    	}
        return null;
    }

    // Submit a completed assessment
    public boolean submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    		
    	if (token == 1) {
    		for (Assessment a : assessments) {
    			//If assessment codes and studentID's match then update assessment info in assessement's list.  
    			if ((a.getAssessmentCode() == completed.getAssessmentCode()) && (a.getStudentID() == completed.getStudentID())){
    				int index = assessments.indexOf(a);
    				assessments.set(index, completed);
    				completedAssessments.add(completed);//Also add this assessment to list of completed assessments.
    				return true;
    			} else {
    				System.out.println("Unknown Assignment.\n\n");
    		    	return false;
    			}
    		}
    	}
		return false;    
    }

    //Starts the server and binds an instance of it to the registry.
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
    }
}
