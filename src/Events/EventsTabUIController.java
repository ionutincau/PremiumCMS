package Events;

import Utils.UtilFunctions;
import domain.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */
public class EventsTabUIController implements Initializable,Observer{

    private EventsController controller;

    @FXML private Button deleteEventButton;
    @FXML private Button editEventButton;
    @FXML private Button addEventButton;
    @FXML private ListView eventsListView;

    public EventsTabUIController() {
        this.controller = new EventsController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventsListView.setFixedCellSize(48);
        eventsListView.getItems().addAll(0,controller.getAllEvents());
        EventADD();
        EventEdit();
        EventDelete();
    }

    private void loadWindow(String name, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_event.fxml"));
            AnchorPane root = loader.load();
            AddEventUIController editController = loader.<AddEventUIController>getController();
            editController.initData(name,event,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EventADD()
    {
        addEventButton.setOnAction(event -> loadWindow("Adauga",null));
    }

    public void EventEdit() {
        editEventButton.setOnAction(event -> {
            eventsListView.getSelectionModel().getSelectedIndex();
            Event e = (Event) eventsListView.getSelectionModel().getSelectedItem();
            if (e != null) {
                loadWindow("Editare", e);
            }
            else UtilFunctions.showInfo("Selectati un eveniment");
        });
    }

    public void EventDelete() {
        deleteEventButton.setOnAction(e->{
            eventsListView.getSelectionModel().getSelectedIndex();
            Event event=(Event) eventsListView.getSelectionModel().getSelectedItem();
            if (event != null) {
                controller.delete(event);
            }
            else UtilFunctions.showInfo("Selectati un eveniment");
        });
    }

    public void update(Observable o, Object arg) {
        eventsListView.getItems().clear();
        eventsListView.getItems().addAll(0,controller.getAllEvents());
    }
}
