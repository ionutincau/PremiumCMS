package Utils;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class UtilFunctions {

    public static void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
    }

    public static String ChooseFile(){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\ionut\\IdeaProjects\\PremiumCMS\\src\\documents"));
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File","*.pdf"));
        String name = "";
        if(selectedFile != null) {
            name = selectedFile.getName();
        }
        else {
            System.out.print("File not Valid!");
        }
        return name;
    }
}
