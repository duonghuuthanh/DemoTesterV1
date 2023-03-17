/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.JdbcUtils;
import com.dht.services.QuestionService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import java.sql.*;


/**
 *
 * @author admin
 */
public class QuestionTester {
    private static Connection conn;
    private static QuestionService s;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new QuestionService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void tesAddSuccessful() {
        Question q = new Question("ABC", 1);
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("A", true, q.getId()));
        choices.add(new Choice("B", false, q.getId()));
        choices.add(new Choice("C", false, q.getId()));
        choices.add(new Choice("D", false, q.getId()));
        
        try {
            boolean actual = s.addQuestion(q, choices);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM question WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, q.getId());
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals("ABC", rs.getString("name"));
            Assertions.assertEquals(1, rs.getInt("category_id"));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSearch() {
        try {
            List<Question> ques = s.getQuestions("is");
            
            Assertions.assertEquals(3, ques.size());
            for (Question q: ques)
                Assertions.assertTrue(q.getContent().contains("is"));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDelete() {
        String id = "45224c30-7f68-4696-b1c5-11ddb534e056";
        boolean actual;
        try {
            actual = s.deleteQuestion(id);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM question WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
            
            sql = "SELECT * FROM choice WHERE question_id=?";
            stm = conn.prepareCall(sql);
            rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
