package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Observer;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CheapTreat;
import model.FancyTreat;
import model.Owner;
import model.Treat;
import server.ICritterServer;
import view.ICritterView;

/**
 * @author Chris Bohlman
 * Controller component of project. Handles user interactions of program.
 */

public class ICritterController extends Application implements Serializable {

  private int width = 800;
  private int height = 675;
  private Owner owner;
  private MenuBar menuBar;
  private VBox vbox1;
  private BorderPane componentLayout;
  private Observer icview;

  private Socket socket;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;

  private static final long serialVersionUID = 1L;
  private static final String ADDRESS = "localhost";
  private int SERVER_PORT;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage arg0) throws Exception {
  //Get desired port #
    TextInputDialog dialog = new TextInputDialog("4000");
    dialog.setTitle("ICritter Client");
    dialog.setContentText("Please enter port number:");
    dialog.setHeaderText("ICritter session");
 
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      try {
        SERVER_PORT = Integer.parseInt(result.get());
      } catch (NumberFormatException exc) {
        exc.printStackTrace();
      }    	
    }

  //Set up connections
  try {
    socket = new Socket(ADDRESS, SERVER_PORT);
    outputStream = new ObjectOutputStream(socket.getOutputStream());
    outputStream.flush();
    inputStream = new ObjectInputStream(socket.getInputStream());
    owner = (Owner) inputStream.readObject();
    System.out.println("Connected to server at " + ADDRESS + ":"
	                 + SERVER_PORT);
  } catch (IOException e) {
    e.printStackTrace();
    this.cleanUpAndQuit("Couldn't connect to the server");
  }
  ServerListener serverListener = new ServerListener();
  serverListener.start();

  arg0.setTitle("CSc 335 Assignment 7");

  // Set up objects for GUI

  //owner = new Owner("Chris Bohlman","Patches");
  componentLayout = new BorderPane();
  Scene scene = new Scene(componentLayout, width, height);

  // Set up the top element of the GUI
  setupTopElement();
  componentLayout.setTop(vbox1);

  // Set up new view for rest of GUI, puts it in center of Border Pane
  icview = new ICritterView(owner, width);

  // Give handlers to 3 gui buttons
  ButtonListener handler = new ButtonListener();
  ((ICritterView) icview).addGiveTreatListener(handler);
  ((ICritterView) icview).addBuyCheapTreatListener(handler);
  ((ICritterView) icview).addBuyFancyTreatListener(handler);
  owner.addObserver(icview);
  componentLayout.setCenter((Node) icview); 

  // Display
  arg0.setScene(scene);
  arg0.show();
  }

  /**
   * Sets up top element (menu and text) for border pane
   */
  private void setupTopElement() {
  // Menu with option to exit
    menuBar = new MenuBar();
    MenuItem quit = new MenuItem("Exit");
    Menu file = new Menu("File");
    file.getItems().add(quit);
    menuBar.getMenus().add(file);
    MenuItemListener menuListener = new MenuItemListener();
    quit.setOnAction(menuListener);

  // Text with Owner and iCritter name
    Text top = new Text("iCritter: "+owner.getCritter().getName()+'\n'+"Owner: "+owner.getName());
    top.setFont(new Font("serif",16));

  // Vertical box to store both
    vbox1 = new VBox();
    vbox1.getChildren().addAll(menuBar, top);

  }

  /**
   * @author Chris Bohlman
   * Nested class that adds EventHandler to menu bar
   */
  private class MenuItemListener implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
    // Find out the text of the JMenuItem that was just clicked
      String text = ((MenuItem) e.getSource()).getText();
    // If exit button was pressed
      if (text.equals("Exit")) {
        Platform.exit();
        System.exit(0);
      }
    }
  }

  /**
   * @author Chris Bohlman
   * Nested class that adds EventHandler to button that was just pressed
   */
  private class ButtonListener implements EventHandler<ActionEvent> {	
    @Override
    public void handle(ActionEvent arg0) {
    // There was a button pressed. What was it?
      Button buttonClicked = (Button) arg0.getSource();

    // If it was "Give Treat" button, "Buy Cheap Treat", or "Buy Fancy Treat" button
      if (buttonClicked.getText().equals("Give Treat")) {
        String treat = ((ICritterView) icview).getTreatsListView().getSelectionModel().getSelectedItem();
        if (treat == null) return;
		// Give selected fancy treat or cheap treat
        if (treat.equals("Fancy Treat")) {
          List<Treat> treatList = owner.listTreats();
          for (Treat icme: treatList) {
            if (icme instanceof FancyTreat) {
              owner.giveTreat(icme);
              treatList.remove(icme);
              try {
                outputStream.writeObject(owner);

                FileOutputStream fos = new FileOutputStream("icritter.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(owner);
                oos.close();
								
              } catch (IOException e1) {
                e1.printStackTrace();
              }
              try {
                outputStream.reset();
              } catch (IOException e1) {
              e1.printStackTrace();
              }
              break;
            }
          }
        } else if (treat.equals("Cheap Treat")) {
          List<Treat> treatList = owner.listTreats();
          for (Treat icme: treatList) {
            if (icme instanceof CheapTreat) {
              owner.giveTreat(icme);
              treatList.remove(icme);
              try {
                outputStream.writeObject(owner);
                FileOutputStream fos = new FileOutputStream("icritter.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(owner);
                oos.close();
              } catch (IOException e1) {
                e1.printStackTrace();
              }
              try {
                outputStream.reset();
              } catch (IOException e1) {
                e1.printStackTrace();
              }
              break;
            }
          }
        }
      } else if (buttonClicked.getText().equals("Buy Cheap Treat")) {
        owner.buyCheapTreat("Cheap Treat");
        try {
          outputStream.writeObject(owner);

          FileOutputStream fos = new FileOutputStream("icritter.ser");
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(owner);
          oos.close();

        } catch (IOException e1) {
          e1.printStackTrace();
        }
        try {
          outputStream.reset();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      } else if (buttonClicked.getText().equals("Buy Fancy Treat")) {
        owner.buyFancyTreat("Fancy Treat");
        try {
          outputStream.writeObject(owner);

          FileOutputStream fos = new FileOutputStream("icritter.ser");
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(owner);
          oos.close();
					
        //System.out.println("written");
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        try {
          outputStream.reset();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  private void cleanUpAndQuit(String message) {
    try {
      if (socket != null)
        socket.close();
    } catch (IOException e) {
    // Couldn't close the socket, we are in deep trouble. Abandon ship.
      e.printStackTrace();
    }
  }

  private class ServerListener extends Thread {
    public void run() {
      try {
        while (true) {
          owner = (Owner) inputStream.readObject();
          ((ICritterView) icview).refresh(owner);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}