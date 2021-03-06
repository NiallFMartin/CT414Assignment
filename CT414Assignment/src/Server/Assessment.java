//Shane O' Rourke - 12361351
//Niall Martin - 12301341

//Assessment.java 

package Server;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

public interface Assessment extends Serializable {

	//Returns the code for the assessment.
	public int getAssessmentCode();
	
	// Return information about the assessment.	
	public String getInformation();

	// Return the final date / time for submission of completed assessment.
	public Date getClosingDate();

	// Return a list of all questions and answer options.
	public List<Question> getQuestions();

	// Return one question only with answer options.
	public Question getQuestion(int questionNumber) throws 
		InvalidQuestionNumber;

	// Answer a particular question.
	public void selectAnswer(int questionNumber, int optionNumber) throws
		InvalidQuestionNumber, InvalidOptionNumber;

	// Return selected answer or zero if none selected yet.
	public int getSelectedAnswer(int questionNumber);

	// Return studentid associated with this assessment object
	// This will be preset on the server before object is downloaded
	public int getStudentID();//Changed from getAssociatedID to StudentID.
	
	//Returns true or false depending whether the assessments has been completed.
	public boolean getCompletionStatus();
	
	//Can set the assessment to true or false depending on completion.
	public void setCompletionStatus(boolean completionStatus);
	
	//Return the date the assessment has been completed.
	public String getDateCompleted();
	
	//Set the date that the assessment was completed.
	public void setDateCompleted();

}



