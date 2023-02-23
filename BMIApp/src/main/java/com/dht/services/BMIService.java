/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

/**
 *
 * @author admin
 */
public class BMIService {
    public static int tinhBmi(double canNang, double chieuCao) {
        double bmi = canNang/Math.pow(chieuCao, 2);
        
        if (bmi < 18.5)
            return 0;
        return 1;
    }
}
