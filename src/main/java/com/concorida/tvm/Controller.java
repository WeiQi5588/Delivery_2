package com.concorida.tvm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class Controller {


    @FXML
    private AnchorPane main_pane_under_scroll;
    @FXML
    private AnchorPane main;

    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/image/home.png"));

        ImageView imageView = new ImageView(image);

        main_pane_under_scroll.getChildren().addAll(imageView);

        setImageViewTopAnchor(imageView, main_pane_under_scroll.getHeight());

        main_pane_under_scroll.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setImageViewTopAnchor(imageView, newValue.doubleValue());
            }
        });
    }
    @FXML
    private void handleRecharge() throws IOException {
        skipView("recharge.fxml");
    }

    @FXML
    private void showMap() throws IOException {
        skipView("map.fxml");
    }

    @FXML
    private void showHome() throws IOException {
        skipView("home.fxml");
    }

    @FXML
    private void handlePurchase() throws IOException {
        skipView("purchaseTicket.fxml");
    }

    @FXML
    private void reportLost() throws IOException{
        skipView("freezeMetroCard.fxml");
    }

    private void setImageViewTopAnchor(ImageView imageView, double anchorPaneHeight) {
        double topAnchor = (anchorPaneHeight - imageView.getFitHeight()) / 3;
        AnchorPane.setTopAnchor(imageView, topAnchor);
    }

    private void skipView(String pagePath) throws IOException {
        if ("home.fxml".equalsIgnoreCase(pagePath)){
            ObservableList<Node> root = main.getChildren();
            root.clear();
            root.add(FXMLLoader.load(App.class.getResource(pagePath)));
        }else {
            ObservableList<Node> scrolChildren = main_pane_under_scroll.getChildren();
            scrolChildren.clear();
            scrolChildren.add(FXMLLoader.load(App.class.getResource(pagePath)));
        }
//        ObservableList<Node> scrolChildren = main_pane_under_scroll.getChildren();
//        scrolChildren.clear();
//        scrolChildren.add(FXMLLoader.load(App.class.getResource(pagePath)));
    }

}