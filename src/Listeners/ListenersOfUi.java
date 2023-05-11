package Listeners;

import model.Question;

import java.util.ArrayList;

import model.Management.eChangesUserNeedToKnow;

public interface ListenersOfUi {
	
	void uiUpdateModelAddQuestion(Question question);
	
	void uiAskFromModelAllQuestions();
	
	void uiUpdateModelQuestionChangedFullShow(String newQuestion, int index);
	
	void uiUpdateModelAnswerChangedFullShow(String newText, int questIndex, int ansIndex);
	
	void uiUpdateModelAnswerDeleteFullShow(int questIndex, int ansIndex);
	
	void uiUpdateModelQuestDeleteFullShow(int questIndex);

	void uiUpdateModelDataToManualTest(ArrayList<Question> dataForTest);

	void uiUpdateModelCreateAutomaticTest(int numOFQuests);

	void uiAskFromModelAllTests();

	void uiUpdateModelCopyTest(int userTestIndex);

	void uiUpdateModelExit();
	
}
