package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.User;

public class UserDao {
    Connection conn = null;
    ArrayList<User> list = new ArrayList<User>();


    public boolean findUser(User user){
        boolean ishave = false;
        String sql = "select * from users where username = "+ "\"" +user.getUsername() + "\"" +";";
        try {
            conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs!=null) ishave = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return ishave;
    }
    public User findUser(String username){
        String sql = "select * from users where username = "+ "\"" +username + "\"" +";";
        User user = new User();
        {
            try {
                conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setIdentity(rs.getString("identity"));
                    System.out.println(user.getUsername()+user.getPassword()+user.getIdentity());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    return user;
    }
    public boolean addUser(User user){
        String sql = "insert into users values" + "(" + "\"" + user.getUsername() + "\""+","+ "\""+user.getPassword()+ "\""+","+ "\""+user.getIdentity()+ "\""+");";
        //System.out.println(sql);
        {
            try {
                conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
		return false;
    }



}
