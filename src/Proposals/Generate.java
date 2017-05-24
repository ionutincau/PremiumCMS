package Proposals;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * Created by Incau Ionut on 24-May-17.
 * Contact: ionut.incau@gmail.com
 */

public class Generate {
    @FXML private Tab generate;
    @FXML private TextField nrText;
    private ProposalsController controller;

    public Generate() {
        controller = new ProposalsController();
    }

    public void generate() {
        try {
            int nrRev = Integer.parseInt(nrText.getText());
            controller.splitProposals(nrRev);
        }
        catch (Exception e) {
            UtilFunctions.showInfo("Introduceti numarul de evaluatori");
        }
    }
}
