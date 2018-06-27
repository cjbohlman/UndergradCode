package view;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.FancyTreat;
import model.ICritterMemoryEvent;
import model.Owner;
import model.Treat;

/**
 * @author Chris Bohlman
 * View component of project. Handles GUI work by presenting Owner data on GUI.
 */

public class ICritterView extends BorderPane implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private Owner owner;
	private Image cat;
	private Slider slider;
	private ObservableList<String> treatMemories;
	private ListView<String> memoryListView;
	private VBox vbox2;
	private VBox vbox3;
	private GridPane panel;
	private ObservableList<String> names;
	private ListView<String> listView;
	private Text credits;
	private ImageView iv2;
	private Button giveTreat;
	private Button buyCheapTreat;
	private Button buyFancyTreat;

	public ICritterView(Owner owner, int width) {
		this.owner = owner;
		this.width = width;
		initializePane(); 
	}

	/**
	 * Sets up rest of initial GUI on border pane
	 */
	private void initializePane() {
		// Set up button views
		giveTreat = new Button();
		giveTreat.setText("Give Treat");
		giveTreat.setMinSize(width/2, 50);
		buyCheapTreat = new Button();
		buyCheapTreat.setText("Buy Cheap Treat");
		buyCheapTreat.setMinSize(width/2, 50);
		buyFancyTreat = new Button();
		buyFancyTreat.setText("Buy Fancy Treat");
		buyFancyTreat.setMinSize(width/2, 50);

		setupLeftElement();
		setLeft(iv2);

		setupCenterElement();
		setCenter(vbox2);

		setupBottomElement();
		setBottom(vbox3);
	}

	/**
	 * Sets up bottom element of border pane: list of treats and 4 buttons
	 */
	private void setupBottomElement() {
		// Will hold elements in GridPane 
		panel = new GridPane();

		// Put list of treats in ListView and put in grid pane
		names = FXCollections.observableArrayList();
		
		for (Treat treat: owner.listTreats()) {
			names.add(treat.getDescription());
		}
		listView = new ListView<String>(names);
		listView.setPrefWidth(width/2);
		listView.setPrefHeight(50);
		GridPane.setConstraints(listView, 0, 0);
		
		// Put buttons in grid pane
		GridPane.setConstraints(giveTreat,0,1);
		GridPane.setConstraints(buyCheapTreat,1,1);
		GridPane.setConstraints(buyFancyTreat,1,0);
		panel.getChildren().addAll(listView, giveTreat, buyCheapTreat, buyFancyTreat);
		panel.setAlignment(Pos.CENTER);

		// Puts number of credits and grid pane in vertical box
		vbox3 = new VBox(10);
		credits = new Text("Credits: "+owner.getCredits());
		vbox3.getChildren().addAll(credits,panel);
	}

	/**
	 * Sets up center element of border pane: text, slider, and list of memories
	 */
	private void setupCenterElement() {
		// Makes an iCritter happiness slider
		slider = new Slider(-4, 4, owner.getCritter().getHappiness());
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);

		// Makes a text label for slider
		Text sliderLabel = new Text("Happiness and Memories");

		// Makes a list of iCritter memories
		treatMemories = FXCollections.observableArrayList();
		int size = owner.getCritter().getMemories().size();
		if (size <= 8) {
			for (ICritterMemoryEvent icme: owner.getCritter().getMemories()) {
				if (icme.getRememberedTreat() instanceof FancyTreat) {
					treatMemories.add("Fancy Treat: Reaction was "+icme.getRememberedReaction().getMoodModifier());
				} else {
					treatMemories.add("Cheap Treat: Reaction was "+icme.getRememberedReaction().getMoodModifier());
				}
			}
		} else {
			for (int i = size - 8; i < size; i++) {
				ICritterMemoryEvent icme = owner.getCritter().getMemories().get(i);
				if (icme.getRememberedTreat() instanceof FancyTreat) {
					treatMemories.add("Fancy Treat: Reaction was "+icme.getRememberedReaction().getMoodModifier());
				} else {
					treatMemories.add("Cheap Treat: Reaction was "+icme.getRememberedReaction().getMoodModifier());
				}
			}
		}
		memoryListView = new ListView<String>(treatMemories);
		memoryListView.setPrefWidth(110);
		memoryListView.setPrefHeight(187);

		// Puts all elements into a vertical box
		vbox2 = new VBox(30);
		vbox2.setPadding(new Insets(5));
		vbox2.getChildren().addAll(sliderLabel, slider, memoryListView);
		vbox2.setAlignment(Pos.TOP_CENTER);
	}

	/**
	 * Sets up left element of border pane, which is a picture of my cat my girlfriend took
	 */
	private void setupLeftElement() {
		//left element of border pane
		cat = new Image("images/patches_crop.jpg",true);
		iv2 = new ImageView();
		iv2.setImage(cat);
		iv2.setFitWidth(600);
		iv2.setPreserveRatio(true);
		iv2.setSmooth(true);
		iv2.setCache(true);
	}

	/**
	 * Adds event handler to Give Treat button
	 * @param EventHandler for button
	 */
	public void addGiveTreatListener(EventHandler<ActionEvent> mal) {
		giveTreat.setOnAction(mal);
	}

	/**
	 * Adds event handler to Buy Cheap Treat button
	 * @param EventHandler for button
	 */
	public void addBuyCheapTreatListener(EventHandler<ActionEvent> mal) {
		buyCheapTreat.setOnAction(mal);
	}

	/**
	 * Adds event handler to Buy Fancy Treat button
	 * @param EventHandler for button
	 */
	public void addBuyFancyTreatListener(EventHandler<ActionEvent> mal) {
		buyFancyTreat.setOnAction(mal);
	}

	/**
	 * @return ListView<String> of iCritter memories
	 */
	public ListView<String> getTreatsListView() {
		return listView;
	}

	/**
	 * Updates only two elements that need to update: Center element and Bottom element
	 */
	@Override
	public void update(Observable observable, Object arg) {
		setupCenterElement();
		setCenter(vbox2);

		setupBottomElement();
		setBottom(vbox3);
	}
	
	public void refresh(Owner newOwner) {
		this.owner = newOwner;
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	setupCenterElement();
        		setCenter(vbox2);

        		setupBottomElement();
        		setBottom(vbox3);
            }
        });
		
	}
}