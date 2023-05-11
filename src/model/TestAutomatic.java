package model;

import java.util.ArrayList;
import java.util.Random;

public class TestAutomatic extends TestManual{
	public static final int MIN_ANS_IN_QUEST = 4;
	ArrayList<Question> rndQuestions;


	public TestAutomatic() {
		super();

	}


	public void createTest(int userPickNumQuestions) {

		rndQuestions = new ArrayList<Question>();
		Random rnd=new Random();
		int questionIndex,counterQuestionAdded=0;
		while(counterQuestionAdded < userPickNumQuestions) {
			questionIndex = rnd.nextInt(userPickNumQuestions);
			if(!contains(rndQuestions , questions.get(questionIndex))) {
				rndQuestions.add(questions.get(questionIndex));
				counterQuestionAdded++;

			}
		}

	}

	private boolean contains(ArrayList<Question> quetsArr,Question question) {
		for (int i = 0; i < quetsArr.size(); i++) {
			if(quetsArr.get(i) != null && quetsArr.get(i).equals(question))
				return true;
		}
		return false;
	}

	@Override
	public void addQuestion(Question question) throws QuestionAlreadyExistException, CloneNotSupportedException {
		if(question instanceof AmericanQuestion) {
			removeDuplicateOfTrueAnswers((AmericanQuestion)question);
			if(((AmericanQuestion)question).getNumOfAnswers() < MIN_ANS_IN_QUEST) {
				return;
			}
			else if(((AmericanQuestion)question).getNumOfAnswers() > MIN_ANS_IN_QUEST) 
				takeTheNumOfAnswerWeNeed((AmericanQuestion)question);
		}
		super.addQuestion(question);
	}


	private void takeTheNumOfAnswerWeNeed(AmericanQuestion question) {
		for (int i = MIN_ANS_IN_QUEST; i < question.getNumOfAnswers(); i++) {
			question.deleteAnswer(i);
		}

	}

	private void removeDuplicateOfTrueAnswers(AmericanQuestion question) throws ArrayIndexOutOfBoundsException, CloneNotSupportedException {
		int counterOfTrueAns = 0;
		for (int i = 0; i < question.getNumOfAnswers(); i++) {
			if(question.getAnswers().get(i).isAnswerCorrect() && counterOfTrueAns!=0) {
				question.deleteAnswer(i);
			}
			else if(counterOfTrueAns==0 && question.getAnswers().get(i).isAnswerCorrect()) {
				counterOfTrueAns++;
			}
		}

	}

	@Override
	public String toString(){

		String back="AutoMation Test:\n ========= all data ========== \nAll Questions:";
		if(rndQuestions == null) {
			back +="there is no data";
			return back;
		}
		rndQuestions.sort(new CompareQuestioinByAnswerLegnthText());
		for (int i = 0; i < rndQuestions.size(); i++) {
			back+= rndQuestions.get(i);
		}

		back+="AutoMation Test :\n ========= Solution ========== \nAll Questions:\n";
		for (int i = 0; i < rndQuestions.size(); i++) {
			back+= rndQuestions.get(i) instanceof AmericanQuestion ? getCorrectAns((AmericanQuestion)rndQuestions.get(i)) : rndQuestions.get(i);


		}
		
		return back;
	}


	public String getCorrectAns(AmericanQuestion americanQuestion) {
		String back = americanQuestion.getSerialNum()+". AmericanQuestion :  "+americanQuestion.getQuestionText()+"\n";
		for (int i = 0; i < americanQuestion.getNumOfAnswers(); i++) {
			if(americanQuestion.getAnswers().get(i).isAnswerCorrect()) {
				back+= "\t"+americanQuestion.getAnswers().get(i)+"\n";
				return back;
			}	
		}
		return back;

	}
	
	@Override
	public DataStock clone() throws CloneNotSupportedException{
		TestAutomatic tmp= (TestAutomatic)super.clone();
		tmp.rndQuestions=new ArrayList<Question>();

		for (int i = 0; i < this.rndQuestions.size(); i++) {
			tmp.rndQuestions.add(this.rndQuestions.get(i).clone());
		}
	
		return tmp;
	}
	
}




