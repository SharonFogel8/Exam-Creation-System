package model;

public class QuestionAlreadyExistException extends Exception {
	
	public QuestionAlreadyExistException() {
		super("this question already added");
	}

}
