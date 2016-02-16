package Client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Server.ExamServer;

public class Client {

	public static void main (String [] args){

		String name = "ExamServer";
		
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {
			
			ExamServer client = (ExamServer) Naming.lookup(name);
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your User ID(123): ");
			int userID = scanner.nextInt();
			System.out.println("Enter your password(abc): ");
			String password = scanner.next();
			
			System.out.println("Attempting to login...");
			if (client.login(userID, password) == 1){
				System.out.println("\nYou are now logged in :)");
			} else {
				System.out.println("\nWrong login :(");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
