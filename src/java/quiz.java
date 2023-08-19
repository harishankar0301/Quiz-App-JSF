/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hari Shankar
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;
import java.util.ArrayList;
import org.unbescape.html.HtmlEscape;

import java.net.http.HttpClient;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.sql.*;

import java.util.*;



@ManagedBean(name = "quiz")
@SessionScoped
public class quiz {
    

    List<mcq> selection;

    Integer score = 0;

    public void onLoad() {
        score = 0;
    }

    HashMap<Integer, String> qMap = new HashMap<Integer, String>();

    public quiz() {
        selection = new ArrayList<mcq>();
        try {
            String response = getQuiz.getreq();
            try {
                Object obj = new JSONParser().parse(response);
                JSONObject jo = (JSONObject) obj;

                JSONArray ja = (JSONArray) jo.get("results");

                Iterator itr2 = ja.iterator();
                Iterator itr1, itr3;
                JSONParser parser = new JSONParser();
                int k = 100;
                while (itr2.hasNext()) {
                    jo = (JSONObject) parser.parse(itr2.next().toString());
                    k++;
                    mcq m1 = new mcq();
                    String unescapedText = HtmlEscape.unescapeHtml(jo.get("question").toString());
                    m1.question = unescapedText;
                    m1.qId = k;
                    ArrayList<String> opt = new ArrayList<String>();
                    ja = (JSONArray) jo.get("incorrect_answers");
                    for (int i = 0; i < ja.size(); i++) {
                        unescapedText = HtmlEscape.unescapeHtml(ja.get(i).toString());

                        opt.add(unescapedText);
                    }
                    unescapedText = HtmlEscape.unescapeHtml(jo.get("correct_answer").toString());
                    qMap.put(k, unescapedText);
                    opt.add(unescapedText);
                    Collections.shuffle(opt);
                    m1.options = opt;
                    selection.add(m1);

                }
            } catch (Exception pe) {
                System.out.println(pe);
            }
        } catch (Exception ex) {
            System.out.println("ex");
        }

    }

    public void setResp(int qid, String ans) {
        if (ans != null) {
            if (qMap.get(qid) == ans) {
                score++;
            }
            else{
                System.out.println(ans);
            }
        }
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<mcq> getSelection() {
        return selection;
    }

    public String submit() {



        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quizscores", "root", "");
            Statement stmt = con.createStatement();
            
        stmt.executeUpdate   ("insert into scores values ('" + startQuiz.tmpName + "','" + startQuiz.tmpEmail + "','" + score + "') ON DUPLICATE KEY UPDATE score='" + score + "'");

            
        } catch (Exception e) {
            System.out.println(e);
        }

        return "result";
    }
    
    public String home(){
        return "index";
    }

}
