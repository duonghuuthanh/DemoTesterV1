package com.dht.bmiapp;

import com.dht.services.BMIService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class PrimaryController {
    @FXML private TextField txtWeight;
    @FXML private TextField txtChieuCao;
    @FXML private Label lblResult;
    
    public void tinhBmiHandler(ActionEvent evt) {
        double w = Double.parseDouble(this.txtWeight.getText());
        double h = Double.parseDouble(this.txtChieuCao.getText());
        double bmi = BMIService.tinhBmi(w, h);
        
        if (bmi == 0) {
            this.lblResult.setTextFill(Color.RED);
            this.lblResult.setText("Gầy");
        } else {
            this.lblResult.setTextFill(Color.BLUE);
            this.lblResult.setText("Bình thường"); 
        }
    }
}
