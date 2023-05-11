package View;


import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import model.Management.eChangesUserNeedToKnow; 

public class ViewOption1ShowAllQuestions extends ViewOption {

	public ViewOption1ShowAllQuestions(Pane pane, MainView mainViewListener) {
		super(pane, "Show All Questions", mainViewListener);
		Label questions = new Label(mainViewListener.getShowAllQuestions());
		if(questions.getText().isEmpty())
			questions.setText("there is no Questions yet");
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(questions);
		scrollPane.setPrefViewportHeight(MainView.HEIGHT - 150);
		scrollPane.setPrefViewportWidth(MainView.WIDTH - 350);
		scrollPane.pannableProperty().set(true);

		borderpane.setBottom(scrollPane);


	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {

	}
}
