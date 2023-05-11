package model;

import java.util.Arrays;

public class TestManual extends DataStock{
	public static final String ALL_CORRECT_ANS = "All Answers are correct";
	public static final String ALL_WRONG_ANS = "All Answers are Wrong";

	
	
	
	@Override
	public boolean equals(Object other) {
		TestManual test = (TestManual)other;
		for (int i = 0; i < questions.size(); i++) {
			if(!test.getQuestions().get(i).equals(questions.get(i)))
			 return false;
		}
		return true;
	}
	
	public String toString() {
		questions.sort(new CompareQuestioinByAnswerLegnthText());
		String back="Manual Test: \n";
		back+=super.toString();
		return back;
	}


	public void addTwoAnswersToAmericanQuests() throws QuestionAlreadyExistException {

		for (int i = 0; i < this.getNumOfQuestion(); i++) {
			
			if(this.questions.get(i) instanceof AmericanQuestion) {
				if(((AmericanQuestion)this.questions.get(i)).isAllAnswersAreTrue()) {
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_CORRECT_ANS,true));
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_WRONG_ANS,false));
				}
				else if(((AmericanQuestion)this.questions.get(i)).isAllAnswersAreFalse()) {
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_CORRECT_ANS,false));
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_WRONG_ANS,true));
				}
				else {
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_CORRECT_ANS,false));
					((AmericanQuestion)this.questions.get(i)).addAnswer(new AmericanAnswer(ALL_WRONG_ANS,false));
				}
			}
			
		}
		
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
}
