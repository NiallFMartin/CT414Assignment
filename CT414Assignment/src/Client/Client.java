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

		String name = "ExamServer"; int num = 0;
		
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
		while (num == 0){
			try {
				
				ExamServer client = (ExamServer) Naming.lookup(name);
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter your User ID(123): ");
				int userID = scanner.nextInt();
				System.out.println("Enter your password(abc): ");
				String password = scanner.next();
				
				System.out.println("Attempting to login...");
				System.out.println("Login: " + client.login(userID, password));
				if (client.login(userID, password) == 1){
					System.out.println("\nYou are now logged in :)");
					System.exit(0);
				} else {
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
	
}
