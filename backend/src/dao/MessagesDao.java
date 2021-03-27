package dao;
import java.sql.*;
import java.util.ArrayList;

public class MessagesDao {
    ArrayList<Message> getMessages(String forumName) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> mesList = new ArrayList<>();

        conn = DBUtil.getConnection();
        stmt = conn.createStatement();

        String sql = "select * from messages where forumname=" + "\'" + forumName + "\'";
        //String sql = "select * from messages " ;
        rs = stmt.executeQuery(sql);

        try{
            Message m;
            while(rs.next()){
                String id = rs.getString(1);
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

    ArrayList<Message> getAllMessages() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Message> mesList = new ArrayList<>();

        conn = DBUtil.getConnection();
        stmt = conn.createStatement();

        String sql = "select * from messages " ;
        rs = stmt.executeQuery(sql);

        try{
            Message m;
            while(rs.next()){
                String id = rs.getString(1);
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
}
