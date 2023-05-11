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

public class ViewOption4AddChangeAnswer extends ViewOption {
	private TextField newAnsField;
	private ArrayList<ArrayList<Label>> questionsLBL;
	private Label userPickAns = null;
	private int userPickQuestIndex = -1;
	private int userPickAnsIndex = -1;

	public ViewOption4AddChangeAnswer(Pane pane, MainView mainViewListener) {
		super(pane, "Change Answer", mainViewListener);

		mainViewListener.uiAskForAllQuestions();
		questionsLBL =   mainViewListener.getAllQuestFullView();
		if(questionsLBL.size() == 0) {
			proprtay.add(new Label("there is no Questions"), 0, 0);
		}
		else {
			int place = 0;
			for (int quest = 0; quest < questionsLBL.size(); quest++) {
				for (int ans = 0; ans < questionsLBL.get(quest).size(); ans++) {
					proprtay.add(questionsLBL.get(quest).get(ans), 0, place++);
					if(ans != 0)
						eventQuest(questionsLBL.get(quest).get(ans), quest, ans); 
				}
				
				 
			}
			newAnsField = new TextField();
			proprtay.add(new Label("new Answer details:"), 10, 0);
			proprtay.add(newAnsField, 11, 0);
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
					if(newAnsField.getText().isEmpty()) {
						msgUser.setText("please enter the Answer update");
						msgUser.setTextFill(Color.RED);
						return;
					}
					if(userPickAns == null) {
						msgUser.setText("please press on the Answer to update");
						msgUser.setTextFill(Color.RED);
						return;
					}
					mainViewListener.uiUpdateModelAnwserChangedFullShow(newAnsField.getText(),userPickQuestIndex, (userPickAnsIndex-1));
				}

			};
			btnAdd.setOnAction(pressAdd);
		}
	}


	private void eventQuest(Label label, int questIndex, int ansIndex) {
			EventHandler<Event> pressAns = new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					msgUser.setText("");
					if(userPickAns == null) {
						userPickAns = label;
						userPickAns.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userPickQuestIndex = questIndex;
						userPickAnsIndex = ansIndex;
					}
					else {
						userPickAns.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
						userPickAns = label;
						userPickAns.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userPickQuestIndex = questIndex;
						userPickAnsIndex = ansIndex;
					}
				}

			};
			
			label.setOnMouseClicked(pressAns);
	}


	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {

		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
			msgUser.setText("Succeeded");
			updateView();
		}
	}
	private void updateView() {
		proprtay.getChildren().clear();
		userPickAns = null;
		
		mainViewListener.uiAskForAllQuestions();
		questionsLBL =   mainViewListener.getAllQuestFullView();

			int place = 0;
			for (int quest = 0; quest < questionsLBL.size(); quest++) {
				for (int ans = 0; ans < questionsLBL.get(quest).size(); ans++) {
					proprtay.add(questionsLBL.get(quest).get(ans), 0, place++);
					if(ans != 0)
						eventQuest(questionsLBL.get(quest).get(ans), quest, ans); 
				}
				
				 
			}
			proprtay.add(new Label("new Answer details:"), 10, 0);
			proprtay.add(newAnsField, 11, 0);
			proprtay.add(btnAdd, 10, 1);
			proprtay.add(msgUser, 10, 2,15,3);
	}
	@Override
	public void clearPane() {
		if(userPickAns != null)
			userPickAns.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
		super.clearPane();
	}

}
