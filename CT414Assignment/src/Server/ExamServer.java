//Shane O' Rourke - 12361351
//Niall Martin - 12301341

//ExamServer.java

package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;
import Server.NoMatchingAssessment;
import Server.UnauthorizedAccess;

public interface ExamServer extends Remote {

	//Creates and returns a list of assessment and 
	public List<Assessment> createObjects() throws ParseException, RemoteException;
	
	// Return an access token that allows access to the server for some time period
	public int login(int studentid, String password) throws 
		UnauthorizedAccess, RemoteException;

	// Return a summary list of Assessments currently available for this studentid
	public List<String> getAvailableSummary(int token, int studentid) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Return an Assessment object associated with a particular course code
	public Assessment getAssessment(int token, int studentid, int courseCode) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Submit a completed assessment
	public boolean submitAssessment(int token, int studentid, Assessment completed) throws 
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

}

