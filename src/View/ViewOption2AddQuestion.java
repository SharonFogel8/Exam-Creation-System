package View;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.AmericanAnswer;
import model.AmericanQuestion;
import model.Management.eChangesUserNeedToKnow;
import model.OpenQuestion;
import model.Question;

public class ViewOption2AddQuestion extends ViewOption {
	private RadioButton americanTypeQuest;
	private RadioButton openTypeQuest;
	private TextField questionField;
	private TextField openAnswerField;
	private ArrayList<TextField> americanAnswerField = new ArrayList<TextField>();
	private ArrayList<Label> americanAnswerLBL = new ArrayList<Label>();
	private ArrayList<RadioButton> americanAnswerIsTrue = new ArrayList<RadioButton>();
	private Button moreAnswer;
	private Label QuestLBL;
	private Label openAnswerLBL;
	private int plusVertical;
	private int openAnsVertical;
	private Label exceptionMsg;



	public ViewOption2AddQuestion(Pane pane, MainView mainViewListener) {
		super(pane, "Add Question", mainViewListener);

		Label questType = new Label("Question Type:");
		americanTypeQuest = new RadioButton("American Question");
		openTypeQuest = new RadioButton("Open Question");
		ToggleGroup radioGroup = new ToggleGroup();
		americanTypeQuest.setToggleGroup(radioGroup);
		openTypeQuest.setToggleGroup(radioGroup);
		HBox radioShow = new HBox();
		radioShow.setSpacing(15);
		radioShow.getChildren().addAll(questType, americanTypeQuest, openTypeQuest);

		QuestLBL = new Label("Question:");
		QuestLBL.setVisible(false);
		questionField = new TextField();
		questionField.setVisible(false);


		openAnswerLBL = new Label("Answer:");
		openAnswerLBL.setVisible(false);
		openAnswerField = new TextField();
		openAnswerField.setVisible(false);


		americanAnswerField.add(new TextField());
		americanAnswerLBL.add(new Label("Answer "+americanAnswerField.size()+":"));
		americanAnswerIsTrue.add(new RadioButton("it's a correct answer"));

		moreAnswer = new Button("+");
		moreAnswer.setBackground(new Background(new BackgroundFill(Color.AQUA,new CornerRadii(50), Insets.EMPTY)));


		americanAnswerLBL.get(0).setVisible(false);
		americanAnswerField.get(0).setVisible(false);
		americanAnswerIsTrue.get(0).setVisible(false);
		moreAnswer.setVisible(false);
		
		exceptionMsg = new Label();
		exceptionMsg.setTextFill(Color.RED);
		btnAdd.setVisible(false);

		proprtay.add(radioShow, 0, 0, 15, 1);
		proprtay.add(QuestLBL, 0, 1);
		proprtay.add(questionField, 1, 1);
		proprtay.add(openAnswerLBL, 0, 2);
		proprtay.add(openAnswerField, 1, 2);
		proprtay.add(americanAnswerLBL.get(0), 0, 2);
		proprtay.add(americanAnswerField.get(0), 1, 2);
		proprtay.add(americanAnswerIsTrue.get(0), 2, 2);
		proprtay.add(moreAnswer, 0, 3);
		proprtay.add(btnAdd, 0, 4);
		proprtay.add(msgUser, 0, 5, 15, 1);
		proprtay.add(exceptionMsg, 0, 6, 15, 1);
		plusVertical = 3;
		openAnsVertical = 2;

		events();
	}

	private void events() {
		EventHandler<Event> addMoreAnswer = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(americanAnswerField.size() < AmericanQuestion.MAX_ANSWERS) {
					proprtay.getChildren().remove(moreAnswer);
					proprtay.getChildren().remove(btnAdd);
					proprtay.getChildren().remove(exceptionMsg);
					proprtay.getChildren().remove(msgUser);

					americanAnswerField.add(new TextField());
					americanAnswerLBL.add(new Label("Answer "+americanAnswerField.size()+":"));
					americanAnswerIsTrue.add(new RadioButton("it's a correct answer"));

					proprtay.add(americanAnswerLBL.get(americanAnswerLBL.size()-1), 0, plusVertical);
					proprtay.add(americanAnswerField.get(americanAnswerField.size()-1), 1, plusVertical);
					proprtay.add(americanAnswerIsTrue.get(americanAnswerIsTrue.size()-1), 2, plusVertical);
					plusVertical++;
					proprtay.add(moreAnswer, 0, plusVertical);
					proprtay.add(btnAdd, 0, plusVertical+1);
					proprtay.add(msgUser, 0, plusVertical+2);
					proprtay.add(exceptionMsg, 0, plusVertical+3);

				}
				else {
					exceptionMsg.setText("no more than "+AmericanQuestion.MAX_ANSWERS+" answers");
				}



			}
		};

		EventHandler<Event> pressAmericanType = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				msgUser.setText("");
				exceptionMsg.setText("");
				proprtay.getChildren().remove(btnAdd);
				proprtay.getChildren().remove(exceptionMsg);
				proprtay.getChildren().remove(msgUser);
				proprtay.add(btnAdd, 0, plusVertical+1);
				proprtay.add(msgUser, 0, plusVertical+2);
				proprtay.add(exceptionMsg, 0, plusVertical+3);
				QuestLBL.setVisible(true);
				questionField.setVisible(true);
				btnAdd.setVisible(true);

				americanAnswerLBL.forEach(lbl -> lbl.setVisible(true)); 
				americanAnswerField.forEach(field -> field.setVisible(true)); 
				americanAnswerIsTrue.forEach(radioBTN -> radioBTN.setVisible(true)); 
				moreAnswer.setVisible(true);

				openAnswerField.setVisible(false);
				openAnswerLBL.setVisible(false);

			}

		};
		EventHandler<Event> pressOpenType = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				msgUser.setText("");
				exceptionMsg.setText("");
				proprtay.getChildren().remove(btnAdd);
				proprtay.getChildren().remove(exceptionMsg);
				proprtay.getChildren().remove(msgUser);
				proprtay.add(btnAdd, 0, openAnsVertical+1);
				proprtay.add(msgUser, 0, openAnsVertical+2);
				proprtay.add(exceptionMsg, 0, openAnsVertical+3);
				QuestLBL.setVisible(true);
				questionField.setVisible(true);
				btnAdd.setVisible(true);

				americanAnswerLBL.forEach(lbl -> lbl.setVisible(false)); 
				americanAnswerField.forEach(field -> field.setVisible(false)); 
				americanAnswerIsTrue.forEach(radioBTN -> radioBTN.setVisible(false)); 
				moreAnswer.setVisible(false);


				openAnswerField.setVisible(true);
				openAnswerLBL.setVisible(true);
			}
		};


		EventHandler<ActionEvent> pressAddBtn = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				msgUser.setText("");
				exceptionMsg.setText("");
				
				
				if(questionField.getText().isEmpty()) {
					exceptionMsg.setText("please fill the Question field");
					return;
				}
				Question myQuestion;
				if(americanTypeQuest.isSelected()) {
					for (int i = 0; i < americanAnswerField.size(); i++) {
						if(americanAnswerField.get(i).getText().isEmpty()) {
							exceptionMsg.setText("please fill all the field of answers");
							return;
						}
					}
					
					myQuestion = new AmericanQuestion(questionField.getText());
					for (int i = 0; i < americanAnswerField.size(); i++) {
						myQuestion.addAnswer(new AmericanAnswer(
								americanAnswerField.get(i).getText(),
								americanAnswerIsTrue.get(i).isSelected()));
					}
				}
				else {
					if(openAnswerField.getText().isEmpty()) {
						exceptionMsg.setText("please fill the field of answer");
						return;
					}
					myQuestion = new OpenQuestion(questionField.getText(), openAnswerField.getText());
				}
				mainViewListener.addQuestionOption2SendDetailsToMainView(myQuestion);

			}

		};
		americanTypeQuest.setOnMouseClicked(pressAmericanType);
		openTypeQuest.setOnMouseClicked(pressOpenType);
		moreAnswer.setOnMouseClicked(addMoreAnswer);
		btnAdd.setOnAction(pressAddBtn);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
			msgUser.setText("Succeeded to Add");
			exceptionMsg.setText("");
		} else {
			msgUser.setText("");
			exceptionMsg.setText("Question Already exists");
		}
	}
}
