package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AmericanQuestion extends Question {
	public static final int MAX_ANSWERS=10;
	private mySet<AmericanAnswer> answers; 
	


	public AmericanQuestion(String quesText){
		super(quesText);
		answers = new mySet<>();
		
	}

	public void cleanAnswers() {
		answers.clear();
	} 
	public int getNumOfAnswers() {
		return answers.size();
	}
	@Override
	public int getLengthOfCharAnswers() {
		int lengthOfCharAnswers=0;
		for (int i = 0; i < answers.size(); i++) {
			lengthOfCharAnswers+=answers.get(i).getAnswerText().length();
		}
		return lengthOfCharAnswers;
	}


	@Override
	public boolean addAnswer(Answer answer) {
		if(!(answer instanceof AmericanAnswer))
			return false;
		if(answers.size() >= MAX_ANSWERS)
			throw new IndexOutOfBoundsException("num of Answers must to be less than "+MAX_ANSWERS);
		AmericanAnswer americanAnswer= (AmericanAnswer) answer;
		answers.add(americanAnswer);

		return true;

	}
	public void addAnswers(mySet<AmericanAnswer> americanAnswer) throws QuestionAlreadyExistException{
		for (int i = 0; i < americanAnswer.size(); i++) 
			addAnswer(americanAnswer.get(i));
		
	}
	public AmericanQuestion addAnswers(ArrayList<AmericanAnswer> americanAnswer) throws QuestionAlreadyExistException{
		for (int i = 0; i < americanAnswer.size(); i++) 
			addAnswer(americanAnswer.get(i));
		return this;
	}

	public mySet<AmericanAnswer> getAnswers() {
		return this.answers;
	}


	public void deleteAnswer(int index) {
		answers.remove(index);
	}



	@Override
	public boolean equals(Object other) {
		if(!(other instanceof AmericanQuestion))
			return false;

		return super.equals(other);

	}

	@Override
	public String toString(){
		String back="";

		for (int i = 0; i < answers.size(); i++) {
			if(answers!=null) {
				back+="\t"+ (i+1)+ ". "+ answers.get(i)+ "\n";
			}
		}
		return super.toString()+"\tanswers: \n"+ back + "\n";
	}

	public boolean isAllAnswersAreTrue() {
		for (int i = 0; i < answers.size(); i++) {
			if(answers.get(i).isAnswerCorrect()==false)
				return false;

		}
		return true;
	}
	public boolean isAllAnswersAreFalse() {
		for (int i = 0; i < answers.size(); i++) {
			if(answers.get(i).isAnswerCorrect()== true)
				return false;

		}

		return true;
	}
	@Override
	public AmericanQuestion clone() throws CloneNotSupportedException{
		AmericanQuestion tmp =(AmericanQuestion)super.clone();
		tmp.answers= this.answers.clone();
		return tmp;
	
	}

}
