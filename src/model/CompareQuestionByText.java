package model;

import java.util.Comparator;

public class CompareQuestionByText implements Comparator<Question> {
	@Override
	public int compare(Question q1, Question q2) {
		return q1.getQuestionText().compareTo(q2.getQuestionText());
		
	}

}
