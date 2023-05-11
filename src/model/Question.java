package model;

import java.io.Serializable;
import java.util.Comparator;

public abstract class Question implements Cloneable,Serializable   {
	private static int counter=0;
	private int serialNum=0;
	protected String quesText;
	
	public Question(String quesText) {
		this.quesText= quesText;
		serialNum=++counter;
	}
	public Question(Question other) { //copy cons't
		this.serialNum = other.serialNum;
		this.quesText = other.quesText;
	}
	
	public abstract boolean addAnswer(Answer answer);
	
	public int getSerialNum() {
		return serialNum;
	}
	
	public String getQuestionText() {
		return quesText;
	}
	
	public void changeQues(String quesText) {
		this.quesText=quesText;
	}
	public abstract int getLengthOfCharAnswers();
	
	@Override
	public Question clone() throws CloneNotSupportedException{
		return (Question)super.clone();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Question) {
			Question q = (Question)other;
			return q.quesText.equals(this.quesText);
		}
		return false;
	}
	
	 @Override
	public String toString() {
		return this.getClass().getSimpleName()+": "+ quesText+"\n";
	}

	

}
