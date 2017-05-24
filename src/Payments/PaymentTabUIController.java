package Payments;

import Events.EventsController;
import Login.LoginController;
import Utils.UtilFunctions;
import domain.Event;
import domain.Payment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/18/2017.
 */
public class PaymentTabUIController implements Initializable, Observer {
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

    PaymentController paymentController = new PaymentController();

    public PaymentTabUIController()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        boolean paid = paymentController.userPaid();
        // if user already paid show no ammound and disable pay button
        EventsController eventsController = new EventsController();
        Event event = (Event) eventsController.getAllEvents().get(0);
        if (paid)
        {
            setPaymentAmmount(00.00);
            setPaymentCurrency("$");
            setPaymentDueDate(event.getD_taxes());
            paymentPayButton.setDisable(true);
        }
        else
        {
            setPaymentAmmount(57.90);
            setPaymentCurrency("$");
            setPaymentDueDate(event.getD_taxes());
            setPaymentPayButtonAction();
        }
    }

    public void setPaymentAmmount(Double ammount)
    {
        paymentAmmount.setText(ammount.toString());
    }

    public void setPaymentCurrency(String currency)
    {
        paymentCurrency.setText(currency);
    }

    public void setPaymentDueDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        paymentDueDate.setText(dateFormat.format(date));
    }

    public boolean checkDate(String date)
    {
        return date.trim().matches("(0[1-9]|1[0-2])\\/(20(1[7-9]|2[0-9]))");
    }

    public boolean checkCVV(String CVV)
    {
        return CVV.trim().matches("^\\d{3}$");
    }

    public boolean checkCardNumber(String cardNumber)
    {
        return cardNumber.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
    }

    public void setPaymentPayButtonAction()
    {
        paymentPayButton.setOnAction(event ->
        {
            String cardNumber = paymentCardNumber.getText();
            String cardCVV = paymentCardCVV.getText();
            String expirationDate = paymentCardNumberExpirationDate.getText();
            if (checkCardNumber(cardNumber) && checkCVV(cardCVV) && checkDate(expirationDate))
            {
                // make payment
                paymentController.addPayment();
                clearForm();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("transaction_approved.fxml"));
                    AnchorPane root = loader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Payment approved");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            stage.close();
                        }
                    });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                UtilFunctions.showInfo("Date card invalide!");
            }
        });
    }

    public void clearForm()
    {
        paymentCardNumber.setText("");
        paymentDueDate.setText("");
        paymentAmmount.setText("00.00");
        paymentCardCVV.setText("");
        paymentCardNumberExpirationDate.setText("");
    }

    @Override
    public void update(Observable o, Object arg) {
        clearForm();
    }
}
