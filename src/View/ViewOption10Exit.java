package View;

import javafx.scene.layout.Pane;
import model.Management.eChangesUserNeedToKnow;

public class ViewOption10Exit extends ViewOption {

	public ViewOption10Exit(Pane pane, MainView mainViewListener) {
		super(pane, "Saving... BYE BYE", mainViewListener);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {	
	}

}
