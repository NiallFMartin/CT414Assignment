package Server;

import java.util.Date;
import java.util.List;

public class AssessmentImplementation implements Assessment {

	private int courseCode;
	private String assessmentInfo;
	private Date closingDate;
	private List<Question> questionList;
	private Question question;
	private int questionNumber;
	private int selectedAnswer;
	private int studentId;
	private boolean completionStatus;
	
	//constructor
	public AssessmentImplementation(int courseCode, String assessmentInfo,Date closingDate, List<Question> questionList, int studentId, boolean completionStatus)
	{
		this.courseCode = courseCode;
		this.assessmentInfo = assessmentInfo;
		this.closingDate = closingDate;
		this.questionList = questionList;
		this.studentId = studentId;
		this.completionStatus = completionStatus;
	}
	
	public int getCourseCode(){
		return courseCode;
	}
	
	public String getInformation() {
		//course code and assessment name
		return this.assessmentInfo;
	}

	public Date getClosingDate() {
		//assessment closing date
		return this.closingDate;
	}
	
	public List<Question> getQuestions() {
		//returns list of question objects
		return this.questionList;
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
	
	public int getStudentID() { //Changed from getAssociatedID to StudentID.	
		//get student id
		return this.studentId;
	}
	
	public boolean getCompletionStatus(){
		return this.completionStatus;
	}
	
	public void setCompletionStatus(boolean completionStatus){
		this.completionStatus = completionStatus;
	}

}
