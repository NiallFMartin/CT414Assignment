package Server;

import java.util.Date;
import java.util.List;

public class AssessmentImplementation implements Assessment {

	private String courseInfo;
	private Date closingDate;
	private List<Question> questionList;
	private Question question;
	private int selectedAnswer;
	private int studentId;
	
	//constructor
	public AssessmentImplementation()
	{
		super();
	}
	
	public String getInformation() {
		//course code and assessment name
		return null;
	}

	
	public Date getClosingDate() {
		//assessment closing date
		return null;
	}

	
	public List<Question> getQuestions() {
		//returns list of question objects
		return null;
	}

	
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		//get specific question using question numbers
		return null;
	}

	
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {

		//select answer of question
		
	}

	
	public int getSelectedAnswer(int questionNumber) {
		//return answer selected by student
		return 0;
	}

	
	public int getAssociatedID() {
		//get student id
		return 0;
	}

}
