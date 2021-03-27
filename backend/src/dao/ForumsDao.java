package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Forums;

public class ForumsDao {
    Connection conn = null;

    public Forums findForums(String forumname){
        String sql = "select * from forums where forumname = "+ "\"" +forumname + "\"" +";";
        Forums forums = new Forums();
        {
            try {
                conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    forums.setForumnama(rs.getString("forumname"));
                    forums.setChairman(rs.getString("chairman"));
                    forums.setTheme(rs.getString("theme"));
                    forums.setTime(rs.getString("time"));
                    //System.out.println(forums.getForumnama()+forums.getChairman()+forums.getTheme()+forums.getTime());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return forums;
    }
    public ArrayList<Forums> findAllForums(String forumname){
        ArrayList<Forums> list = new ArrayList<Forums>();
        String sql = "select * from forums;";
        {
            try {
                conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                Forums forums = new Forums();
                while(rs.next()){
                    forums.setForumnama(rs.getString("forumname"));
                    forums.setChairman(rs.getString("chairman"));
                    forums.setTheme(rs.getString("theme"));
                    forums.setTime(rs.getString("time"));
                    //System.out.println(forums.getForumnama()+forums.getChairman()+forums.getTheme()+forums.getTime());
                }
                list.add(forums);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }
    public void addForums(Forums forums){
        String sql = "insert into forums values" + "(" + "\"" + forums.getForumnama() + "\""+","+ "\""+forums.getTheme()+ "\""+","+ "\""+forums.getChairman()+ "\""+","+ "\""+forums.getTime()+ "\""+")"+";";
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
    }
    
    public List<Forums> getAllForums() {
    	String sqlString = "select * from forums";
    	ArrayList<Forums> fs = new ArrayList<Forums>();
    	try {
			conn = DBUtil.getConnection();
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sqlString);
			Forums forums;
			while (rs.next())
			{
				forums = new Forums();
				forums.setForumnama(rs.getString("forumname"));
                forums.setChairman(rs.getString("chairman"));
                forums.setTheme(rs.getString("theme"));
                forums.setTime(rs.getString("time"));
                fs.add(forums);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return fs;
    }
}
