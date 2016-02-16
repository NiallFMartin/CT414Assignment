package Server;

public class QuestionImplementation implements Question{

	private int qNum; //Question Number
	private String qDetail; //Question Detail
	private String [] qAnsOptions; //Question Answer Options
	
	public QuestionImplementation(int qNum, String qDetail, String [] qAnsOptions){
		qNum = this.qNum;
		qDetail = this.qDetail;
		qAnsOptions = this.qAnsOptions;
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

}
