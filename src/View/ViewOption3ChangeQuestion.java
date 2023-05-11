package View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Management.eChangesUserNeedToKnow;

public class ViewOption3ChangeQuestion extends ViewOption {


	private TextField newQuestField;
	private ArrayList<Label> questionsLBL;
	private Label userPickQuest = null;
	private int userPickQuestIndex = -1;

	public ViewOption3ChangeQuestion(Pane pane, MainView mainViewListener) {
		super(pane, "Change Question", mainViewListener);

		mainViewListener.uiAskForAllQuestions();
		questionsLBL =  (ArrayList<Label>) mainViewListener.getAllQuestView();
		if(questionsLBL.size() == 0) {
			proprtay.add(new Label("there is no Questions"), 0, 0);
		}
		else {
			for (int i = 0; i < questionsLBL.size(); i++) {
				proprtay.add(questionsLBL.get(i), 0, i);
				eventQuest(questionsLBL.get(i) , i);  
			}
			newQuestField = new TextField();
			proprtay.add(new Label("new Quest details:"), 10, 0);
			proprtay.add(newQuestField, 11, 0);
			btnAdd.setText("Update");
			proprtay.add(btnAdd, 10, 1);
			proprtay.add(msgUser, 10, 2,15,3);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(proprtay);
			scrollPane.setPrefViewportHeight(MainView.HEIGHT - 150);
			scrollPane.setPrefViewportWidth(MainView.WIDTH - 350);
			scrollPane.pannableProperty().set(true);

			borderpane.setBottom(scrollPane);

			EventHandler<ActionEvent> pressAdd = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(newQuestField.getText().isEmpty()) {
						msgUser.setText("please enter the question update");
						msgUser.setTextFill(Color.RED);
						return;
					}
					if(userPickQuest == null) {
						msgUser.setText("please press on the question to update");
						msgUser.setTextFill(Color.RED);
						return;
					}
					mainViewListener.uiUpdateModelQuestionChangedFullShow(newQuestField.getText(),userPickQuestIndex);

			
				}

			};
			btnAdd.setOnAction(pressAdd);
		}
	}


	private void eventQuest(Label label, int index) {
			EventHandler<Event> pressQuest = new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					msgUser.setText("");
					if(userPickQuest == null) {
						userPickQuest = label;
						userPickQuest.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userPickQuestIndex = index;
					}
					else {
						userPickQuest.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
						userPickQuest = label;
						userPickQuest.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userPickQuestIndex = index;
					}
					

				}

			};
			
			label.setOnMouseClicked(pressQuest);
	}


	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {

		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
			msgUser.setText("Succeeded");
		} else if(ans == eChangesUserNeedToKnow.QuestionAlreadyExists){
			msgUser.setTextFill(Color.RED);
			msgUser.setText("Question Already Exists...");
		}
	}
	@Override
	public void clearPane() {
		if(userPickQuest != null)
			userPickQuest.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
		super.clearPane();
	}


	public void mainViewUpdateQuest(String question, int index) {
		questionsLBL.get(index).setText(question);
		
	}

}
