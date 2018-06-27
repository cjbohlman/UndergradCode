package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Owner;

public class ICritterServer extends Application implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // static fields
  private int LISTENER_PORT;

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // instance variables
  private static List<ObjectOutputStream> clientList = Collections
                 .synchronizedList(new ArrayList<ObjectOutputStream>());
  private ServerSocket server;
  public Owner owner;

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // methods

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * <code>main</code> method
   *
   * @param args a <code>String[]</code> value
   +~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  public static void main(String[] args) {
	launch(args);
  }

  /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * <code>start</code> method
   *
   * Runs the main loop of the ChatServer, waiting for new
   * connections and dispatching to newly created ChatServer.Client
   * objects upon connection.
   +~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  public void start (Stage arg0) throws Exception  {

    TextInputDialog dialog = new TextInputDialog("4000");

    dialog.getDialogPane().setPrefSize(320, 160);
    
    dialog.setTitle("ICritter Server");
    dialog.setContentText("Please enter port number:");

// remove "confirmation" text
    dialog.setHeaderText(" ");
		
    HBox box = new HBox();
    CheckBox cb = new CheckBox();
    Label text = new Label("    Load Previous ICritter State?");
    
    text.setTranslateY(22);
    text.setGraphic(cb);
    text.setContentDisplay(ContentDisplay.RIGHT);
    box.getChildren().add(text);
    box.setSpacing(400);
		
    text.setStyle(
      "-fx-min-width: 300;"
    + "-fx-min-height: 20;"
    + "-fx-height: -100px;"
    );

    box.setStyle(
      "-fx-border-color: lightblue; "
    + "-fx-font-size: 16;"
    + "-fx-border-insets: -55; "
    + "-fx-border-radius: 12;"
    + "-fx-border-style: dotted;"
    + "-fx-border-width: 2;"
    );
        
    dialog.getDialogPane().getChildren().add(box);

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
      try {
        LISTENER_PORT = Integer.parseInt(result.get());
      } catch (NumberFormatException exc) {
        exc.printStackTrace();
      }    	
    }

// if: user selected to load previous icritter state
// load serialized owner object from file
    if (cb.isSelected()) {
      try {
      // read object from file
        FileInputStream fis = new FileInputStream("icritter.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        owner = (Owner) ois.readObject();
        ois.close();
        
        System.out.println("Previous ICritter state was loaded.");
        
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    
// else: create new owner 
    else {
      owner = new Owner("Chris Bohlman","Patches");
    }
    
//    
    try {
      server = new ServerSocket(LISTENER_PORT);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  
//
    System.out.println("iCritter server started on port num " + LISTENER_PORT);
    while (true) {
      try {
        Socket client = server.accept();

// Make both connection steams available
        ObjectOutputStream outputToClient = new ObjectOutputStream(
        client.getOutputStream());
        ObjectInputStream inputFromClient = new ObjectInputStream(
        client.getInputStream());
        clientList.add(outputToClient);
        ClientHandler handler = new ClientHandler(inputFromClient,
        clientList);
        handler.start();
        outputToClient.writeObject(owner);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void setOwner(Owner newOwner) {
    this.owner = newOwner;
  }

  class ClientHandler extends Thread {
    private ObjectInputStream inputStream;
    private List<ObjectOutputStream> clients;

    public ClientHandler(ObjectInputStream inputStream,
    List<ObjectOutputStream> clientList) {
      this.clients = clientList;
      this.inputStream = inputStream;
    }

    @Override
    // Start a new thread
    // Read from the client update the server and write back out
    public void run() {
      Owner newOwner;
      while (true) {
        try {
          newOwner = (Owner) inputStream.readObject();
        } catch (IOException e) {
        this.cleanUp();
        return;
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        this.cleanUp();
        return;
        }
        setOwner(newOwner);
        this.writeOwnerToClients(newOwner);
      }
    } //end run()

// Write out to every client
// if a client is done remove the client from the list and don't write to it
// anymore.
    private void writeOwnerToClients(Owner owner) {
      synchronized (clients) {
        ObjectOutputStream toRemove = null;
        for (ObjectOutputStream client : clients) {
          try {
            client.writeObject(owner);
          } catch (IOException e) {
            toRemove = client;
          }
        }
        clients.remove(toRemove);
      }
    } //end writeOwnerToClients

    // Close the input stream
    private void cleanUp() {
      try {
        this.inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } //end cleanUp()
  }
}