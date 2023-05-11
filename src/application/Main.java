package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



import model.Management;
import Controller.Controller;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView view = new MainView(primaryStage);
		Management model = new Management();
		Controller controller = new Controller(model, view);
	}
}
