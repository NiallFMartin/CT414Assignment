package Server;

import java.util.Date;
import java.util.List;

public class AssessmentImplementation implements Assessment {

	private String courseInfo;
	private Date closingDate;
	private List<Question> questionList;
	private Question question;
	private int questionNumber;
	private int selectedAnswer;
	private int studentId;
	
	//constructor
	public AssessmentImplementation(String courseInfo,Date closingDate, List<Question> questionList, int studentId)
	{
		courseInfo = this.courseInfo;
		closingDate = this.closingDate;
		questionList = this.questionList;
		studentId = this.studentId;
	}
	
	public String getInformation() {
		//course code and assessment name
		return courseInfo;
	}

	
	public Date getClosingDate() {
		//assessment closing date
		return closingDate;
	}

	
	public List<Question> getQuestions() {
		//returns list of question objects
		return questionList;
	}

	
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		//get specific question using question numbers
		Question q = new QuestionImplementation(0, null, null, 0, 0);
		
		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber)
		    {
		    	q = obj;
		    	return q;
		    }
		}
		return null;
	}

	
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {

		//select answer of question
		//get specific question using question number
		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber)
		    {
		    	obj.setStudentAnswer(optionNumber);
		    }
		}
	}

	
	public int getSelectedAnswer(int questionNumber) {
		//return answer selected by student

		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber)
		    {
		    	return obj.getStudentAnswer();
		    }
		}
		return 0;
	}

	
	public int getAssociatedID() {
		//get student id
		return studentId;
	}

}
