package View;


import java.util.ArrayList;
import java.util.List;

import Listeners.ListenersOfUi;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AmericanAnswer;
import model.AmericanQuestion;
import model.DataStock;
import model.Management.eChangesUserNeedToKnow;
import model.OpenQuestion;
import model.Question;
import model.TestManual;
import model.mySet;

public class MainView {
	public static final double HEIGHT = 800;
	public static final double WIDTH = 1200;
	public static final Paint OPEN_PAINT = Color.AQUA;
	public static final Paint AMERICAN_PAINT = Color.AQUAMARINE;
	private ListenersOfUi listener;
	private HBox hboxMain;
	private Label isSuccededMsg;
	private GridPane gridPane;
	private Pane placeOption;
	private RadioButton option1;
	private RadioButton option2;
	private RadioButton option3;
	private RadioButton option4;
	private RadioButton option5;
	private RadioButton option6;
	private RadioButton option7;
	private RadioButton option8;
	private RadioButton option9;
	private RadioButton option10;
	private ViewOption optionView;
	private List<Label> listQuestions;
	private List<Label> listTests;
	private ArrayList<ArrayList<Label>> listQuestionsFullShow;
	private String allQuestionsShow;
	private int counterIfFirstTimeMenu; // we clear menu in changes, first time we cant
	private Stage stage;

	public MainView(Stage stage) {
		stage.setTitle("Tests System");
		counterIfFirstTimeMenu = 0;
		listQuestions = new ArrayList<Label>();
		listTests = new ArrayList<Label>();
		listQuestionsFullShow = new ArrayList<ArrayList<Label>>();
		allQuestionsShow = new String();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		// inside gridePane
		isSuccededMsg = new Label();
		gridPane.add(isSuccededMsg, 0, 3);
		hboxMain = new HBox();
		hboxMain.getChildren().add(gridPane);
		
		stage.setScene(new Scene(hboxMain, WIDTH, HEIGHT));
		stage.show();
		this.stage = stage;
		
	}

	public void addListener(ListenersOfUi listener) {
		this.listener = listener;
		showMenu(this.stage); // verify some on listen
	}

	private void showMenu(Stage stage) {
		stage.setTitle("Welcome To Tests System");
		hboxMain.getChildren().remove(gridPane);
		GridPane menu = new GridPane();
		menu.setAlignment(Pos.TOP_LEFT);
		menu.setPadding(new Insets(10));
		menu.setHgap(10);
		menu.setVgap(10);
		ToggleGroup groupMenu = new ToggleGroup();
		option1 = new RadioButton("Show all questions");
		option2 = new RadioButton("Add Questions");
		option3 = new RadioButton("Change question");
		option4 = new RadioButton("Change Answer");
		option5 = new RadioButton("Delete Answer");
		option6 = new RadioButton("Delete question");
		option7 = new RadioButton("Create New Test");
		option8 = new RadioButton("Create Automatic Test");
		option9 = new RadioButton("Create Test Copy");
		option10 = new RadioButton("Exit");
		option1.setToggleGroup(groupMenu);
		option2.setToggleGroup(groupMenu);
		option3.setToggleGroup(groupMenu);
		option4.setToggleGroup(groupMenu);
		option5.setToggleGroup(groupMenu);
		option6.setToggleGroup(groupMenu);
		option7.setToggleGroup(groupMenu);
		option8.setToggleGroup(groupMenu);
		option9.setToggleGroup(groupMenu);
		option10.setToggleGroup(groupMenu);
		Label menuTitle = new Label("Menu");
		menuTitle.setFont(new Font(25));
		menu.add(menuTitle, 0, 0, 10, 1);
		menu.add(option1, 0, 1);
		menu.add(option2, 0, 2);
		menu.add(option3, 0, 3);
		menu.add(option4, 0, 4);
		menu.add(option5, 0, 5);
		menu.add(option6, 0, 6);
		menu.add(option7, 0, 7);
		menu.add(option8, 0, 8);
		menu.add(option9, 0, 9);
		menu.add(option10, 0, 10);
		placeOption = new Pane();
		hboxMain.getChildren().addAll(menu, placeOption);
		events();
	}

	private void events() {
		EventHandler<Event> eventop1 = new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				optionMenu(1);

			}
		};
		option1.setOnMouseClicked(eventop1);
		EventHandler<Event> eventop2 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(2);
			}
		};
		option2.setOnMouseClicked(eventop2);
		EventHandler<Event> eventop3 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(3);
			}
		};
		option3.setOnMouseClicked(eventop3);
		EventHandler<Event> eventop4 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(4);
			}
		};
		option4.setOnMouseClicked(eventop4);
		EventHandler<Event> eventop5 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(5);
			}
		};
		option5.setOnMouseClicked(eventop5);
		EventHandler<Event> eventop6 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(6);
			}
		};
		option6.setOnMouseClicked(eventop6);
		EventHandler<Event> eventop7 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(7);
			}
		};
		option7.setOnMouseClicked(eventop7);
		EventHandler<Event> eventop8 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(8);
			}
		};
		option8.setOnMouseClicked(eventop8);
		EventHandler<Event> eventop9 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(9);
			}
		};
		option9.setOnMouseClicked(eventop9);
		EventHandler<Event> eventop10 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(10);
			}
		};
		option10.setOnMouseClicked(eventop10);
	}

	private void optionMenu(int optionIndex) {
		if (counterIfFirstTimeMenu != 0) {
			optionView.clearPane();
		}
		switch (optionIndex) {
		case 1:
			optionView = new ViewOption1ShowAllQuestions(placeOption, this);
			break;
		case 2:
			optionView = new ViewOption2AddQuestion(placeOption, this);
			break;
		case 3:
			optionView = new ViewOption3ChangeQuestion(placeOption, this);
			break;
		case 4:
			optionView = new ViewOption4AddChangeAnswer(placeOption, this);
			break;
		case 5:
			optionView = new ViewOption5DeleteAnswer(placeOption, this);
			break;
		case 6:
			optionView = new ViewOption6DeleteQuestion(placeOption, this);
			break;
		case 7:
			optionView = new ViewOption7CreateNewTest(placeOption, this);
			break;
		case 8:
			optionView = new ViewOption8CreateAutomaticTest(placeOption, this);
			break;
		case 9:
			optionView = new ViewOption9CreateTestCopy(placeOption, this);
			break;
		case 10:
			listener.uiUpdateModelExit();
			optionView =new ViewOption10Exit(placeOption, this);
			hboxMain.setDisable(true);
			break;
		}
		counterIfFirstTimeMenu++;
	}

	public void addQuest(String quest) {
		Label questLBL = new Label("Quest: " + quest);
		questLBL.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(1))));
		questLBL.setPadding(new Insets(5));
		listQuestions.add(questLBL);
	}
	
	public void addFullQuest(ArrayList<Question> questions) {
		for (int i = 0; i < questions.size(); i++) {
			listQuestionsFullShow.add(new ArrayList<Label>());
			Label questLBL = new Label(questions.get(i).getQuestionText());
			questLBL.setBorder(
					new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(1))));
			questLBL.setPadding(new Insets(5));
			listQuestionsFullShow.get(i).add(questLBL);
			Paint questTypeColor = null;
			ArrayList<String> answers = new ArrayList<String>();
			if(questions.get(i) instanceof AmericanQuestion) {
				questTypeColor = MainView.AMERICAN_PAINT;
				int size = ((AmericanQuestion)questions.get(i)).getAnswers().size();
				mySet<AmericanAnswer> answersToAdd = ((AmericanQuestion)questions.get(i)).getAnswers();
				for (int j = 0; j < size; j++) {
					answers.add(answersToAdd.get(j).toString());
				}
				
			}
			else if(questions.get(i) instanceof OpenQuestion) {
				answers.add(((OpenQuestion)questions.get(i)).getAnswer().toString());
				questTypeColor = MainView.OPEN_PAINT;
			}
			for (String str : answers) {
				Label answerLBL = new Label(str);
				answerLBL.setBorder(
						new Border(new BorderStroke(questTypeColor, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(1))));
				answerLBL.setPadding(new Insets(3));
				listQuestionsFullShow.get(i).add(answerLBL);
			}
		}
		
	}

	public List<Label> getAllQuestView() {
		return listQuestions;
	}
	public List<Label> getAllTestsView() {
		return listTests;
	}
	public ArrayList<ArrayList<Label>> getAllQuestFullView() {
		return listQuestionsFullShow;
	}

	public void updateResultMsgFromModel(eChangesUserNeedToKnow ans) {
		optionView.mainViewUpdateOption(ans);
	}

	public void addQuestionOption2SendDetailsToMainView(Question question) {
		listener.uiUpdateModelAddQuestion(question);
	}
	public void uiAskForAllQuestions() {
		listener.uiAskFromModelAllQuestions();
	}
	void uiUpdateModelQuestionChangedFullShow(String newQuestion, int index) {
		listener.uiUpdateModelQuestionChangedFullShow(newQuestion, index);
	}

	public void questionsFullShow(String questionToString) {
		allQuestionsShow = questionToString;
	}

	public String getShowAllQuestions() {
		return allQuestionsShow;
	}

	public void getFromModelQuests(ArrayList<Question> questions) {
		listQuestions.clear();
		questions.forEach(q -> addQuest(q.getQuestionText()));
		listQuestionsFullShow.clear();
		addFullQuest(questions);
	}

	public void updatingQuestChanges(String question, int index) {
		if(optionView instanceof ViewOption3ChangeQuestion)
			((ViewOption3ChangeQuestion)optionView).mainViewUpdateQuest(question, index);
	}

	public void uiUpdateModelAnwserChangedFullShow(String newText, int questIndex, int ansIndex) {
		listener.uiUpdateModelAnswerChangedFullShow(newText, questIndex, ansIndex);
	}

	public void uiUpdateModelAnwserDeleteFullShow(int questIndex, int ansIndex) {
		listener.uiUpdateModelAnswerDeleteFullShow(questIndex, ansIndex);
	}

	public void uiUpdateModelQuestionDeleteFullShow(int questIndex) {
		listener.uiUpdateModelQuestDeleteFullShow(questIndex);
	}

	public void uiUpdateModelDataToManualTest(ArrayList<Question> dataForTest) {
		listener.uiUpdateModelDataToManualTest(dataForTest);
		
	}

	public void uiUpdateModelCreateAutomaticTest(int numOFQuests) {
		listener.uiUpdateModelCreateAutomaticTest(numOFQuests);
	}

	public void uiAskForAllTests() {
		listener.uiAskFromModelAllTests();
		
	}

	public void getFromModelTests(ArrayList<DataStock> tests) {
		listTests.clear();
		tests.forEach(t -> addTest(t.toString()));
		
	}

	private void addTest(String test) {
		Label testLBL = new Label("\t\t\t"+test);
		testLBL.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(1))));
		testLBL.setPadding(new Insets(5));
		listTests.add(testLBL);
	}

	public void uiUpdateModelCopyTest(int userTestIndex) {
		listener.uiUpdateModelCopyTest(userTestIndex);
		
	}

}
