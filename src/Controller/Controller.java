package Controller;

import java.util.ArrayList;
import Listeners.ListenersOfModel;
import Listeners.ListenersOfUi;
import View.MainView;
import model.DataStock;
import model.Management;
import model.Management.eChangesUserNeedToKnow;
import model.Question;
import model.TestManual;

public class Controller implements ListenersOfModel, ListenersOfUi {
	private MainView view;
	private Management model;

	public Controller(Management model, MainView view) {
		this.model = model;
		this.view = view;
		this.view.addListener(this);
		this.model.addListener(this);
	}

	
	// Ui update Model
	@Override
	public void uiAskFromModelAllQuestions() {
		this.model.allQuestions();
	}

	@Override
	public void uiUpdateModelAddQuestion(Question question) {
			this.model.addQuestionToData(question);
	}


	@Override
	public void uiUpdateModelQuestionChangedFullShow(String newQuestion, int index) {
		model.updateQuestionTxt(index, newQuestion);
	}


	@Override
	public void uiUpdateModelAnswerChangedFullShow(String newText, int questIndex, int ansIndex) {
		model.updateAnsTxt(newText, questIndex, ansIndex);
	}


	@Override
	public void uiUpdateModelAnswerDeleteFullShow(int questIndex, int ansIndex) {
		model.updateAnsDelete(questIndex, ansIndex);
	}


	@Override
	public void uiUpdateModelQuestDeleteFullShow(int questIndex) {
		model.deleteQuestion(questIndex);
	}
	
	@Override
	public void uiUpdateModelDataToManualTest(ArrayList<Question> dataForTest) {
		model.createManualTest(dataForTest);	
	}
	
	@Override
	public void uiUpdateModelCreateAutomaticTest(int numOFQuests) {
		model.createAutomaticTest(numOFQuests);
		
	}
	@Override
	public void uiAskFromModelAllTests() {
		this.model.allTests();
	}

	@Override
	public void uiUpdateModelCopyTest(int userTestIndex) {
		this.model.CopyTest(userTestIndex);
	}
	
	@Override
	public void uiUpdateModelExit() {
		this.model.saveBeforeExit();
	}

	// model update Ui Msg Action
	@Override
	public void modelUpdateUiQuestionsFullShow(String questions) {
		this.view.questionsFullShow(questions);
	}


	@Override
	public void modelUpdateUiQuestionActionStatus(eChangesUserNeedToKnow ans) {
		this.view.updateResultMsgFromModel(ans);
	}

	@Override
	public void modelUpdateUiQuestionsList(ArrayList<Question> questions) {
		view.getFromModelQuests(questions);
	}
	
	@Override
	public void modelUpdateUiQuestionChangedFullShow(String question,int index) {
		view.updatingQuestChanges(question, index);
	}


	@Override
	public void modelUpdateUiTestsList(ArrayList<DataStock> tests) {
		view.getFromModelTests(tests);
		
	}

}
