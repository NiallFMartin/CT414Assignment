package Client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Server.ExamServer;

public class Client {

	public static void main (String [] args){
		String name = "ExamServer";
		
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {
//			Registry registry = LocateRegistry.getRegistry(args[0]);
			ExamServer client = (ExamServer) Naming.lookup(name);
			System.out.println("Attempting to login...");
			if (client.login(123, "abc") == 1){
				System.out.println("\nYou are now logged in :)");
			} else {
				System.out.println("Wrong login :(");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
