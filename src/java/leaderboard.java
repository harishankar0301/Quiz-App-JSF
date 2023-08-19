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

import java.sql.*;
import java.util.ArrayList;




@ManagedBean(name="leaderboard")
public class leaderboard {
    
    ArrayList<user>users;
    public leaderboard(){
        users=new ArrayList<user>();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quizscores", "root", "");
            Statement stmt = con.createStatement();
            
            ResultSet rs= stmt.executeQuery("select * from scores order by score desc");
            
            while(rs.next()){
                user u=new user();
                u.name=rs.getString(1);
                u.email=rs.getString(2);
                u.score=Integer.parseInt(rs.getString(3));
                users.add(u);
                System.out.println(u.email);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<user> getUsers() {
        return users;
    }


}
