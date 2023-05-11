package model;

import java.util.Comparator;

public class CompareQuestioinByAnswerLegnthText implements Comparator<Question>{
	
	public int compare(Question q1, Question q2) {
		return (q1.getLengthOfCharAnswers()-q2.getLengthOfCharAnswers());
		
	}
}
