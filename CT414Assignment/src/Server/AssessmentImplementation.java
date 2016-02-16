package Server;

import java.util.Date;
import java.util.List;

public class AssessmentImplementation implements Assessment {

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getClosingDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAssociatedID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
