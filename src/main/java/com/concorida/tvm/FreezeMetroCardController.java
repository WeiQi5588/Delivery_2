package com.concorida.tvm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FreezeMetroCardController {
    @FXML
    private TextField metroCardId;
    @FXML
    private TextField verificationCode;
    @FXML
    private Label confirm_msg;

    @FXML
    private void freezeCard(){
        confirm_msg.setText("");
        if ("123456".equalsIgnoreCase(verificationCode.getText())){
            confirm_msg.setText(metroCardId.getText() + " has been frozen successfully!");
        }else {
            confirm_msg.setText("The verification code is not correct!");
        }
    }
}
