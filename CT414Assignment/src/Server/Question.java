// Question.java

package Server;

import java.io.Serializable;

public interface Question extends Serializable {

	// Return the question number
	public int getQuestionNumber();

	// Return the question text
	public String getQuestionDetail();

	// Return the possible answers to select from
	public String[] getAnswerOptions();
	
	// Returns the correct answer to the question.
	public int getCorrectAnswer();
	
	// Returns the student's answer to the question.
	public int getStudentAnswer();
	
	//sets what the student's answer to the question is
	public void setStudentAnswer(int answer);

}
