//Shane O' Rourke - 12361351
//Niall Martin - 12301341

//Client.java

package Client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Server.Assessment;
import Server.ExamServer;
import Server.NoMatchingAssessment;
import Server.Question;
import Server.UnauthorizedAccess;

public class Client {

	private static String name = "ExamServer"; 
	private static int num = 0;
	private static int token = 0;
	private ExamServer server;
	
	//Constructor that will start the server and sign in process.
	public Client(ExamServer server) {
		this.server = server;
		this.signin();
	}
	
	//Set up and run client.
	public static void main (String [] args){		
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		//Implement registry
		String name = "ExamServer";
		Registry registry;
		
		try{
			//Looks up the object that is binded to the registry. e.g. "ExamServer"
			registry = LocateRegistry.getRegistry();
			ExamServer server = (ExamServer)registry.lookup(name);
			new Client(server);//Starts the client and sign in.
		}
		catch(Exception e) {
			System.err.println("Exception in ExamServer");
			e.printStackTrace();
		}
	}
	
	//Client main menu containing options for 
	// 1 - View Summary, 2 - Complete Assessments, 3 - Exit System.
	private void mainMenu(int studentID) throws RemoteException, UnauthorizedAccess, NoMatchingAssessment {
		
		System.out.println("\nPlease choose an action: \n1 - View your assessments summary\n2 "
				+ "- Complete an assessment\n3 - Exit System");
		System.out.print("Choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		if(choice == 1) {
			this.summary(studentID);
		}
		else if (choice == 2) {
			completeAssessment(studentID);
		}
		else if(choice == 3) {
			System.exit(0);
		}
		else {
			System.out.println("Invalid input, try again.");
			mainMenu(studentID);
		}
	}
	
	//Allows client to sign in to the server.
	private void signin(){
		while(num == 0){
			try {
				//Prompts user for Student ID and password.
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter your Student ID (Hint: Try 123 OR 456 OR 789): ");
				int studentID = scanner.nextInt();
				System.out.print("Enter your password (Hint: Try abc OR def OR ghi): ");
				String password = scanner.next();
				
				System.out.println("Attempting to login...");
				//Pass studentID and password to server to verify login.
				if (this.server.login(studentID, password) == 1){
					token = 1;
					System.out.println("\nYou are now logged in :)");
					
					//Creates the assessments and questions lists. 
					this.server.createObjects();
					//Allow user to view main menu.
					mainMenu(studentID);
					
				} 
				else {
					System.out.println("\nWrong login :(");
					System.out.println("Enter 0 to try again: \nOR \n1 to exit: ");
					num = scanner.nextInt();
					if (num != 0){
						System.exit(0);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Summary of given assessments.
	public void summary(int studentID) throws RemoteException, UnauthorizedAccess, NoMatchingAssessment
	{
		List<String> summaries = new ArrayList<String>();
		System.out.println("Here is the list of course that are available to you: \n");
		summaries = this.server.getAvailableSummary(1, studentID);
		
		System.out.println("ASSESSMENTS\n");
		for (String s : summaries){
			System.out.println(s);//Prints assessment ID, assessment name, completion status.
		}
		
		mainMenu(studentID);
	}
	
	//Allows the user to complete the chosen asessment.
	public void completeAssessment(int studentID) throws RemoteException, UnauthorizedAccess, NoMatchingAssessment
	{
		System.out.println("\nTry and complete assessment test\n");
		
		//View assessments.
		List<String> summaries = new ArrayList<String>();
		System.out.println("Here is the list of course that are available to you: \n");
		//Returns the assessments that are available to the student.
		summaries = this.server.getAvailableSummary(1, studentID);
		
		System.out.println("ASSESSMENTS\n");
		for (String s : summaries){
			System.out.println(s);
		}
		
		Scanner in = new Scanner(System.in);		
		System.out.print("Enter Course Code for Assessment you wish to complete: ");
		int cc = in.nextInt();
		
		//Returns the assessment that the student has chosen.
		Assessment assessmentToComplete = this.server.getAssessment(1, studentID, cc);
		if(assessmentToComplete == null)
		{
			System.out.println("Assessment is closed, sorry");
			mainMenu(studentID);
		}
		List<Question> questions = new ArrayList<Question>();
		questions = assessmentToComplete.getQuestions();
		
		//Iterates through all the questions for the assessment.
		for(Question q : questions){
			System.out.println("Q" + q.getQuestionNumber() + " - " + q.getQuestionDetail());
			for(String s : q.getAnswerOptions()){
				System.out.println(s);
			}
			
			//Prompts the user for the answer.
			System.out.print("Your answer: ");
			int studentAnswer = in.nextInt();
			
			//Then sets this answer as studentAnswer in the assessment object.
			q.setStudentAnswer(studentAnswer);
		}
		
		//Sets status and the current date that it was completed.
		assessmentToComplete.setCompletionStatus(true);
		assessmentToComplete.setDateCompleted();
		//Submits the assesssment to the server.
		boolean submit = this.server.submitAssessment(1, studentID, assessmentToComplete);
		
		//If true is returned then output assessment completed.
		if(submit){
			System.out.print("Assessment completed.\n\n");
		}
		
		//Return student to main menu.
		mainMenu(studentID);
	}
	
}
