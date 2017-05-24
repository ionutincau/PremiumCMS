package Presentations;

import domain.Presentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by ASUS on 23.May.2017.
 */
public class PresentationAddDocUI implements Initializable {
    private PresentationsController controller;
    private Presentation presentation;

    @FXML
    private Button demoChooseFileButton;

    @FXML
    private Label demoFileLabel;

    @FXML
    private Button addDemoButton;

    public String DemoChoseFileButtonAction(ActionEvent event){
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\ASUS\\Desktop\\PremiumISS\\PremiumCMS\\src\\"));
        File selectedFile=fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File","*.pdf"));
        String name="";
        if(selectedFile!=null)
        {
            name=selectedFile.getName();
        }
        else {
            System.out.print("File not Valid!");
        }
        return name;
    }
    public void initData(String name, Presentation presentation, PresentationsController controller) {
        this.controller = controller;
        this.presentation = presentation;

        demoChooseFileButton.setVisible(true);
        demoFileLabel.setVisible(true);
        addDemoButton.setVisible(true);


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
