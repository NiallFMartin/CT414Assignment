package Server;

public class QuestionImplementation implements Question {

	private int qNum; //Question Number
	private String qDetail; //Question Detail
	private String [] qAnsOptions; //Question Answer Options
	private int correctAns; //Question Correct Answer
	private int studentAns; //Question Student Answer
	
	public QuestionImplementation(int qNum, String qDetail, String [] qAnsOptions, int correctAns, int studentAns){
		this.qNum = qNum;
		this.qDetail = qDetail;
		this.qAnsOptions = qAnsOptions;//0 = Yes, 1 = No, 2 = None.
		this.correctAns = correctAns;
		this.studentAns = studentAns;
	}
	
	public int getQuestionNumber() {
		return this.qNum;
	}

	public String getQuestionDetail() {
		return this.qDetail;
	}

	public String[] getAnswerOptions() {
		return this.qAnsOptions;
	}
	
	public int getCorrectAnswer() {
		return this.correctAns;
	}
	
	public int getStudentAnswer() {
		return this.studentAns;
	}
	
	public void setStudentAnswer(int answer) {
		this.studentAns = answer;
	}

}
