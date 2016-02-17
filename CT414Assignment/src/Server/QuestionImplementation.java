//Shane O' Rourke - 12361351
//Niall Martin - 12301341

//QuestionImplementation.java

package Server;

public class QuestionImplementation implements Question {

	private int qNum; //Question Number
	private String qDetail; //Question Detail
	private String [] qAnsOptions; //Question Answer Options
	private int correctAns; //Question Correct Answer
	private int studentAns; //Question Student Answer
	
	//Constructor that can hold all the info regarding a question.
	public QuestionImplementation(int qNum, String qDetail, String [] qAnsOptions, int correctAns, int studentAns){
		this.qNum = qNum;
		this.qDetail = qDetail;
		this.qAnsOptions = qAnsOptions;
		this.correctAns = correctAns;
		this.studentAns = studentAns;
	}
	
	// Returns question number.
	public int getQuestionNumber() {
		return this.qNum;
	}

	// Returns the details of the question. e.g. the actual question.
	public String getQuestionDetail() {
		return this.qDetail;
	}

	// Returns the different answers that are available.
	public String[] getAnswerOptions() {
		return this.qAnsOptions;
	}
	
	// Returns the correct answer.
	public int getCorrectAnswer() {
		return this.correctAns;
	}
	
	// Returns the Student's answer.
	public int getStudentAnswer() {
		return this.studentAns;
	}
	
	// Sets the student's answer to the question.
	public void setStudentAnswer(int answer) {
		this.studentAns = answer;
	}

}
