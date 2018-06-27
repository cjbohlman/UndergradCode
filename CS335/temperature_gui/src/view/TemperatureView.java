package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.TemperatureModel;

public class TemperatureView extends BorderPane implements Observer {
	
	TemperatureModel tm;

	public TemperatureView(TemperatureModel tm) {
		this.tm = tm;
		initializePane();
	}

	private void initializePane() {
		VBox vbox = new VBox(30);
		Label farenText = new Label("Farenheit:");
		Label celsText = new Label("Celcius:");
		TextField farenTF = new TextField();
		TextField celsTF = new TextField();
		Button convert = new Button("Convert");
		convert.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (farenTF.getText() != null && !farenTF.getText().isEmpty() && celsTF.getText() != null && !celsTF.getText().isEmpty()) {
					System.err.println("One line should be blank");
				} else if ((farenTF.getText() != null && !farenTF.getText().isEmpty())) {
					tm.setT((Double.parseDouble(farenTF.getText())), 0);
					celsTF.setText(" "+tm.convertFtoC());
				} else if((celsTF.getText() != null && !celsTF.getText().isEmpty())) {
					tm.setT(0, Double.parseDouble(celsTF.getText()));
					farenTF.setText(" "+tm.convertCtoF());
				}
			}
		});
		
		vbox.getChildren().addAll(farenText, farenTF, celsText, celsTF,convert);
		Insets pad = new Insets(20);
		vbox.setPadding(pad);
		setCenter(vbox);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}


}
