package model;

public class AmericanAnswer extends Answer {
	public static final String SPLITER = " ------>";
	private boolean isAnswerCorrect;


	public AmericanAnswer() {
		super("");
	}
	public AmericanAnswer(String answerText, boolean isAnswerCorrect) {
		super(answerText);
		this.isAnswerCorrect = isAnswerCorrect;
	}
	public AmericanAnswer(AmericanAnswer other) {
		super(other);
		this.isAnswerCorrect = other.isAnswerCorrect;
	}
	public void changeIsAnswerCorrectValue() {
		isAnswerCorrect=!isAnswerCorrect;
	}

	public boolean isAnswerCorrect() {
		return isAnswerCorrect;
	}

	@Override
	public String toString() {
		return super.toString()+ SPLITER+ isAnswerCorrect;
	}
	@Override
	public AmericanAnswer clone() throws CloneNotSupportedException{
		return (AmericanAnswer)super.clone();
	}

}
