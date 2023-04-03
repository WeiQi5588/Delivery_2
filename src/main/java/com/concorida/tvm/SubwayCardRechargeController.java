package com.concorida.tvm;

import com.concorida.tvm.backend.QRCodeGenerator;
import com.google.zxing.WriterException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URISyntaxException;

public class SubwayCardRechargeController {

    @FXML
    private ToggleGroup rechargeAmountToggleGroup;

    @FXML
    private ToggleGroup paymentMethodToggleGroup;
    @FXML
    private ToggleGroup invoiceToggleGroup;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private HBox emailAddressHBox;
    @FXML
    private Label Invoice_Msg;

    @FXML
    private Button rechargeButton;
    @FXML
    private Label QRCode;
    @FXML
    private Label Message;

    @FXML
    public void initialize() {
        updateEmailAddressVisibility();

        invoiceToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                updateEmailAddressVisibility();
            }
        });
        rechargeButton.setOnAction(event -> {
            try {
                handleRecharge();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void handleRecharge() throws IOException, WriterException, URISyntaxException {
        Invoice_Msg.setText("");
        RadioButton selectedRechargeAmount = (RadioButton) rechargeAmountToggleGroup.getSelectedToggle();
        String rechargeAmount = (String) selectedRechargeAmount.getUserData();

        RadioButton selectedPaymentMethod = (RadioButton) paymentMethodToggleGroup.getSelectedToggle();
        String paymentMethod = (String) selectedPaymentMethod.getUserData();

        RadioButton selectedInvoiceMethod = (RadioButton) invoiceToggleGroup.getSelectedToggle();
        String invoiceMethod = (String) selectedInvoiceMethod.getUserData();

        rechargeSubwayCard(rechargeAmount, paymentMethod);
        printInvoice(invoiceMethod);
    }



    private void printInvoice(String Invoice_Message){
        if ("PRINT_INVOICE".equalsIgnoreCase(Invoice_Message)){
            Invoice_Msg.setText("The invoice printed");
        }if ("EMAIL_INVOICE".equalsIgnoreCase(Invoice_Message)){
            Invoice_Msg.setText("The invoice has been sent to your email box!");
        }
    }

    private void rechargeSubwayCard(String rechargeAmount, String paymentMethod) throws IOException, WriterException, URISyntaxException {

        if ("CREDIT_CARD".equalsIgnoreCase(paymentMethod)){
            Message.setText("Recharge successfully!");
        }else if("DEBIT_CARD".equalsIgnoreCase(paymentMethod)){
            Message.setText("Recharge successfully!");
        }else if("CASH".equalsIgnoreCase(paymentMethod)){
            Message.setText("Recharge successfully!");
        }else {
            Message.setText("Please scan QR code to pay.");
            QRCodeGenerator.getInstance().generateQRCode();
            Image image;
            image = new Image(getClass().getResourceAsStream("/QR/payment_code.png"),250,250,false,false);
            QRCode.setGraphic(new ImageView(image));
        }


    }
    private void updateEmailAddressVisibility() {
        RadioButton selectedInvoiceOption = (RadioButton) invoiceToggleGroup.getSelectedToggle();
        String userData = selectedInvoiceOption.getUserData().toString();
        emailAddressHBox.setVisible(userData.equals("EMAIL_INVOICE"));
    }


}
