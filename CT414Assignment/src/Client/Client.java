package Client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Server.ExamServer;

public class Client {

	private static String name = "ExamServer"; 
	private static int num = 0;
	
	
	public static void main (String [] args){		
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
		while (num == 0){
			signin();
		}
	}
	
	private static void mainMenu()
	{
		System.out.println("\nPlease choose an action: \n1 - View your assessments summary\n2 - Complete an assessment\n3 - Exit System");
		System.out.print("Choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		if(choice == 1)
		{
			summary();
		}
		else if (choice == 2)
		{
			completeAssessment();
		}
		else if(choice == 3)
		{
			System.exit(0);
		}
		else
		{
			System.out.println("Invalid input, try again.");
			mainMenu();
		}
	}
	
	private static void signin(){
		try {
			ExamServer client = (ExamServer) Naming.lookup(name);
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your User ID(123): ");
			int userID = scanner.nextInt();
			System.out.print("Enter your password(abc): ");
			String password = scanner.next();
			
			System.out.println("Attempting to login...");
			if (client.login(userID, password) == 1){
				System.out.println("\nYou are now logged in :)");
				mainMenu();
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
	
	public static void summary()
	{
		System.out.println("\nSummary test\n");
		mainMenu();
	}
	
	public static void completeAssessment()
	{
		System.out.println("\ncomplete assessment test\n");
		mainMenu();
	}
}
