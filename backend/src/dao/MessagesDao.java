package dao;
import java.sql.*;
import java.util.ArrayList;

import domain.Message;

public class MessagesDao {
    public ArrayList<Message> getMessages(String forumName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> mesList = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "select * from messages where forumname=" + "\'" + forumName + "\'";
            //String sql = "select * from messages " ;
            rs = stmt.executeQuery(sql);

            Message m;
            while(rs.next()){
                int id = rs.getInt(1);
                String message = rs.getString(2);
                String forum = rs.getString(3);
                m = new Message(id,message,forum);
                mesList.add(m);
            }

        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return mesList;
    }

    public ArrayList<Message> getAllMessages()  {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> mesList = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "select * from messages " ;
            rs = stmt.executeQuery(sql);

            Message m;
            while(rs.next()){
                int id = rs.getInt(1);
                String message = rs.getString(2);
                String forum = rs.getString(3);
                m = new Message(id,message,forum);
                mesList.add(m);
            }

        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return mesList;
    }

    public boolean addMessage(Message m) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Message> mesList = new ArrayList<>();
        boolean b = false;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into messages(message,forumname) values(?,?)";
            ps = conn.prepareStatement(sql);
            //ps.setInt(1, m.getId());
            ps.setString(1, m.getMessage());
            ps.setString(2, m.getForum());
            int result = ps.executeUpdate();
            if (result != 0) {
                System.out.println("插入成功！");
                b = true;
            }
        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return b;
    }
}

