package Payments;

import domain.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Aurelian on 5/18/2017.
 */
public class PaymentTabUIController {
    @FXML
    private Label paymentDueDate;

    @FXML
    private TextField paymentCardNumber;

    @FXML
    private TextField paymentCardCVV;

    @FXML
    private Button paymentPayButton;

    @FXML
    private TextField paymentCardNumberExpirationDate;

    @FXML
    private Label paymentCurrency;

    @FXML
    private Label paymentAmmount;

    public PaymentTabUIController()
    {

    }

    public void initialize()
    {

    }
}
