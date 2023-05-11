package Listeners;


import java.util.ArrayList;

import model.DataStock;
import model.Management.eChangesUserNeedToKnow;
import model.Question;
import model.TestManual;



public interface ListenersOfModel {

	void modelUpdateUiQuestionsFullShow(String questions);
	
	void modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow ans);
	
	void modelUpdateUiQuestionsList(ArrayList<Question> questions);
	
	void modelUpdateUiQuestionChangedFullShow(String questions,int index);

	void modelUpdateUiTestsList(ArrayList<DataStock> tests);
	
	
}
