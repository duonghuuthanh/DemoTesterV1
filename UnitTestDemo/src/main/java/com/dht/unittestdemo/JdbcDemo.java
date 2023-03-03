/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.unittestdemo;

import com.dht.pojo.Question;
import com.dht.services.QuestionService;
import java.sql.SQLException;



/**
 *
 * @author admin
 */
public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        Question q = new Question("This is ... engineer", 1);
        QuestionService s = new QuestionService();
        s.addQuestion(q, null);
    }
}
