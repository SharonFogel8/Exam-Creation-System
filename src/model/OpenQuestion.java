package model;

import java.util.ArrayList;

public class OpenQuestion extends Question{
	private OpenAnswer answer;
	
	
	public OpenQuestion(String quesText,String answerText) {
		super(quesText);
		answer = new OpenAnswer(answerText);
	}
	public OpenQuestion(OpenQuestion other) { //copy cons't
		super(other);
		this.answer = other.answer;
	}
	
	public OpenQuestion(String quesText) {
		super(quesText);
	}

	public Answer getAnswer() {
		return answer;
	}
	
	public void deleteAnswer() {
		this.answer=new OpenAnswer("-Empty-");
	}
	@Override
	public boolean addAnswer(Answer answer) {
		if(!(answer instanceof OpenAnswer))
			return false;
		OpenAnswer openAnswer= (OpenAnswer) answer;
		this.answer=openAnswer;
		return true;
	}
	@Override
	public int getLengthOfCharAnswers() {
		return this.answer.getAnswerText().length();
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof OpenQuestion))
			return false;
		return super.equals(other);
	}
	
	@Override
	public String toString() {
		return super.toString()+ "\tanswer: "+ (answer != null ? answer : "Empty answer...")+"\n";
	}
	@Override
	public Question clone() throws CloneNotSupportedException{
		OpenQuestion tmp=(OpenQuestion)super.clone();
		tmp.answer = (OpenAnswer) this.answer.clone();
		return tmp;
	}

	

}
