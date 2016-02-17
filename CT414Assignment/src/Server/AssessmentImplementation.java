//Shane O' Rourke - 12361351
//Niall Martin - 12301341

//AssessmentImplementation.java

package Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AssessmentImplementation implements Assessment {

	private int assessmentCode;
	private String assessmentInfo;
	private Date closingDate;
	private List<Question> questionList;
	private Question question;
	private int questionNumber;
	private int selectedAnswer;
	private int studentId;
	private boolean completionStatus;
	private String dateCompleted;
	
	//Constructor that contains all information regarding an assessment.
	public AssessmentImplementation(int assessmentCode, String assessmentInfo,Date closingDate, 
			List<Question> questionList, int studentId, boolean completionStatus, String dateCompleted) {
		this.assessmentCode = assessmentCode;
		this.assessmentInfo = assessmentInfo;//Name of assessment.
		this.closingDate = closingDate;//Submission date.
		this.questionList = questionList;//List of questions contained in the assessment.
		this.studentId = studentId;
		this.completionStatus = completionStatus;
		this.dateCompleted = dateCompleted;//Date assessment was last completed.
	}
	
	//Return assessment code.
	public int getAssessmentCode(){
		return this.assessmentCode;
	}
	
	public String getInformation() {
		//Course code and assessment name.
		return this.assessmentInfo + "\nCompleted: " + this.completionStatus;
	}

	public Date getClosingDate() {
		//Assessment closing date.
		return this.closingDate;
	}
	
	public List<Question> getQuestions() {
		//Returns list of question objects.
		return this.questionList;
	}
	
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		//Get specific question using question numbers.
		Question q = new QuestionImplementation(0, null, null, 0, 0);
		
		//Iterates through the question list to find specific question.
		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber){
		    	q = obj;
		    	return q;
		    }
		}
		return null;
	}
	
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		//Select answer of question.
		//Get specific question using question number.
		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber) {
		    	obj.setStudentAnswer(optionNumber);
		    }
		}
	}
	
	public int getSelectedAnswer(int questionNumber) {
		//Return answer selected by student.

		for(int i = 0; i < questionList.size(); i++) {
		    Question obj = questionList.get(i);
		    int qNumber = obj.getQuestionNumber();
		    if (qNumber == questionNumber) {
		    	return obj.getStudentAnswer();
		    }
		}
		return 0;
	}
	
	public int getStudentID() { //Changed from getAssociatedID to getStudentID.	
		//Gets student id.
		return this.studentId;
	}
	
	//Returns true/false depending on completion.
	public boolean getCompletionStatus(){
		return this.completionStatus;
	}
	
	//Sets assessment to true/false depending on completion.
	public void setCompletionStatus(boolean completionStatus){
		this.completionStatus = completionStatus;
	}
	
	//Returns the date completed.
	public String getDateCompleted(){
		return this.dateCompleted;
	}
	
	//Sets the date assessment was last completed.
	public void setDateCompleted(){
    	SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
		this.dateCompleted = fmt.format(date);
	}

}
