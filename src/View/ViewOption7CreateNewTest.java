package View;

import model.AmericanAnswer;
import model.AmericanQuestion;
import model.Management.eChangesUserNeedToKnow;
import model.OpenQuestion;
import model.Question;

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
import javafx.scene.paint.Paint;

public class ViewOption7CreateNewTest extends ViewOption {
	private static final int QUEST_WITHOUT_ANSWERS = 1;
	private final Paint PICKED_COLOR = Color.LIGHTGREEN;
	private final Paint NOT_PICKED_COLOR = null;
	private final int QUEST_INDEX = 0;
	private ArrayList<ArrayList<Label>> questionsLBL;
	private ArrayList<Question> dataForTest = new ArrayList<Question>();
	private AmericanQuestion activeQuest = null;

	public ViewOption7CreateNewTest(Pane pane, MainView mainViewListener) {
		super(pane, "Create Manual Test", mainViewListener);

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
					if(ans != QUEST_INDEX)
						eventAns(questionsLBL.get(quest).get(ans), quest, ans);
					else {
						eventQuest(questionsLBL.get(quest).get(ans), quest, ans);
					}
				}
			}

			btnAdd.setText("Create");
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
					msgUser.setText("");
					int QuestsToAddCounter = 0;
					for (int quest = 0; quest < questionsLBL.size(); quest++) {
						for (int ans = 0; ans < questionsLBL.get(quest).size(); ans++) {
							if(ans == QUEST_INDEX) {
								createQuest(quest);
							}
							else {
								addAnsToQuest(quest , ans);

							}
						}
					}
					if(dataForTest.size() != 0) {
						mainViewListener.uiUpdateModelDataToManualTest(dataForTest);
						dataForTest.clear();
						activeQuest = null;
					}
					else {
						msgUser.setText("please press on the Quests & Answers to add...");
					}

					
				}

				private void addAnsToQuest(int quest, int ans) {
					if(activeQuest == null)
						return;
					Label label = questionsLBL.get(quest).get(ans);
					if(label.getBackground()  != null && label.getBackground().getFills().get(0).getFill() == PICKED_COLOR) {
						String[] detailsQuest = label.getText().split(AmericanAnswer.SPLITER);
						String ansText;
						boolean isTrueAns;
						if(detailsQuest.length == 2) {
							try{
								ansText = detailsQuest[0];
								isTrueAns =  Boolean.valueOf(detailsQuest[1]);
							}
							catch (Exception e) {
								return;
							}
							activeQuest.addAnswer(new AmericanAnswer(ansText, isTrueAns));
						}
					}

				}

				private void createQuest(int questIndex) {

					if(questionsLBL.get(questIndex).get(QUEST_INDEX).getBackground() != null && questionsLBL.get(questIndex).get(QUEST_INDEX).getBackground().getFills().get(0).getFill() == PICKED_COLOR) {
						if(questionsLBL.get(questIndex).size() == QUEST_WITHOUT_ANSWERS || questionsLBL.get(questIndex).get(QUEST_INDEX+1).getBorder().getStrokes().get(0).getTopStroke() == MainView.AMERICAN_PAINT) {
							activeQuest = new AmericanQuestion(questionsLBL.get(questIndex).get(QUEST_INDEX).getText());
							dataForTest.add(activeQuest);
						}
						else if(questionsLBL.get(questIndex).get(QUEST_INDEX+1).getBorder().getStrokes().get(0).getTopStroke() == MainView.OPEN_PAINT) {
							Question quest = null;
							quest = new OpenQuestion(
									questionsLBL.get(questIndex).get(QUEST_INDEX).getText(),
									questionsLBL.get(questIndex).get(QUEST_INDEX+1).getText());
							dataForTest.add(quest);
							activeQuest = null;
						}
					}

				}

			};
			btnAdd.setOnAction(pressAdd);
		}
	}


	private void eventQuest(Label label, int quest, int ans) {
		EventHandler<Event> pressQuest = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				msgUser.setText("");
				if(label.getBackground()  != null && label.getBackground().getFills().get(0).getFill() == PICKED_COLOR) {
					questionsLBL.get(quest).forEach(ans -> 
					ans.setBackground(new Background(new BackgroundFill(NOT_PICKED_COLOR,new CornerRadii(50), Insets.EMPTY))));
				}
				else {
					label.setBackground(new Background(new BackgroundFill(PICKED_COLOR,new CornerRadii(50), Insets.EMPTY)));
				}
			}

		};
		label.setOnMouseClicked(pressQuest);
	}

	private void eventAns(Label label, int questIndex, int ansIndex) {
		EventHandler<Event> pressAns = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				msgUser.setText("");
				if(label.getBackground()  != null && label.getBackground().getFills().get(0).getFill() == PICKED_COLOR) {
					label.setBackground(new Background(new BackgroundFill(NOT_PICKED_COLOR,new CornerRadii(50), Insets.EMPTY)));
				}
				else {
					label.setBackground(new Background(new BackgroundFill(PICKED_COLOR,new CornerRadii(50), Insets.EMPTY)));
					if(!(questionsLBL.get(questIndex).get(QUEST_INDEX).getBackground() != null && questionsLBL.get(questIndex).get(QUEST_INDEX).getBackground().getFills().get(0).getFill() == PICKED_COLOR))
						questionsLBL.get(questIndex).get(QUEST_INDEX).setBackground(new Background(new BackgroundFill(PICKED_COLOR,new CornerRadii(50), Insets.EMPTY)));
				}
			}

		};

		label.setOnMouseClicked(pressAns);
	}


	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
			msgUser.setText("Succeeded,\n Open answers Saved with Questions automatic");
		}
	}

	@Override
	public void clearPane() {
		super.clearPane();
	}

}
