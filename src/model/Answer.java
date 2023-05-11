package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

public abstract class Answer implements Cloneable, Serializable{
	protected String answerText;
	
	public Answer(String answerText) {
		this.answerText=answerText;
	}
	public Answer(Answer other) {
		this.answerText=other.answerText;
	}
	
	public String getAnswerText() {
		return answerText;
	}
	
	public void changeAnswerText(String newAnswer) {
		this.answerText=newAnswer;
	}
	 @Override
	public String toString() {
		return answerText;
	}
	 @Override
		public Answer clone() throws CloneNotSupportedException{
			return (Answer)super.clone();
		}
	 
	 public void save(PrintWriter pw) throws FileNotFoundException {
			pw.println(answerText);
		}

}
