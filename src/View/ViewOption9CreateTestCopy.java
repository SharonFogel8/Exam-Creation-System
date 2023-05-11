package View;

import java.util.ArrayList;
import java.util.List;

import model.Management.eChangesUserNeedToKnow;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewOption9CreateTestCopy extends ViewOption {


	private ArrayList<Label> testsLBL;
	private Label userPickTest = null;
	private int userTestQuestIndex = -1;

	public ViewOption9CreateTestCopy(Pane pane, MainView mainViewListener) {
		super(pane, "Copy Test", mainViewListener);

		mainViewListener.uiAskForAllTests();
		testsLBL =  (ArrayList<Label>) mainViewListener.getAllTestsView();
		if(testsLBL.size() == 0) {
			proprtay.add(new Label("there is no tests"), 0, 0);
		}
		else {
			for (int i = 0; i < testsLBL.size(); i++) {
				proprtay.add(testsLBL.get(i), 0, i+3);
				eventQuest(testsLBL.get(i) , i);  
			}
			
			proprtay.add(new Label("Press the test to  copy"), 0, 0);
			btnAdd.setText("Copy");
			proprtay.add(btnAdd, 0, 1);
			proprtay.add(msgUser, 0, 2);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(proprtay);
			scrollPane.setPrefViewportHeight(MainView.HEIGHT - 150);
			scrollPane.setPrefViewportWidth(MainView.WIDTH - 350);
			scrollPane.pannableProperty().set(true);

			borderpane.setBottom(scrollPane);

			EventHandler<ActionEvent> pressAdd = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(userPickTest == null) {
						msgUser.setText("please press on the test to create");
						msgUser.setTextFill(Color.RED);
						return;
					}
					mainViewListener.uiUpdateModelCopyTest(userTestQuestIndex);

			
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
					if(userPickTest == null) {
						userPickTest = label;
						userPickTest.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userTestQuestIndex = index;
					}
					else {
						userPickTest.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
						userPickTest = label;
						userPickTest.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,new CornerRadii(50), Insets.EMPTY)));
						userTestQuestIndex = index;
					}
					

				}

			};
			
			label.setOnMouseClicked(pressQuest);
	}


	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {

		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
			msgUser.setText("Succeeded,\n Copy tests won't be write to File");
			updateView();
		}
	}
	
	private void updateView() {
		proprtay.getChildren().clear();
		userPickTest = null;
		
		mainViewListener.uiAskForAllTests();
		testsLBL =  (ArrayList<Label>) mainViewListener.getAllTestsView();
		if(testsLBL.size() == 0) {
			proprtay.add(new Label("there is no tests"), 0, 0);
		}
		else {
			for (int i = 0; i < testsLBL.size(); i++) {
				proprtay.add(testsLBL.get(i), 0, i+3);
				eventQuest(testsLBL.get(i) , i);  
			}
			
			proprtay.add(new Label("Press the test to  copy"), 0, 0);
			proprtay.add(btnAdd, 0, 1);
			proprtay.add(msgUser, 0, 2);
		}
	}


	@Override
	public void clearPane() {
		if(userPickTest != null)
			userPickTest.setBackground(new Background(new BackgroundFill(null,CornerRadii.EMPTY, Insets.EMPTY)));
		super.clearPane();
	}

}
