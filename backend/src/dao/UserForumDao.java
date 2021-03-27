package dao;
import java.sql.*;
import java.util.ArrayList;
import domain.*;

public class UserForumDao {
    public boolean addUserForum(UserForum uf) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Message> mesList = new ArrayList<>();
        boolean b = false;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into user_forum(username,forumname) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uf.getUserName());
            ps.setString(2, uf.getForumName());

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

    public ArrayList<User> searchUsers(String forumName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "select * from user_forum where forumname=" + "\'" + forumName + "\'";
            //String sql = "select * from messages " ;
            rs = stmt.executeQuery(sql);

            User user;
            UserDao uDao = new UserDao();
            while(rs.next()){
                String userName = rs.getString(1);
                user = uDao.findUser(userName);

                userList.add(user);
            }

        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return userList;
    }

    public Forums searchForum(String userName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Forums forum = null;

        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "select * from user_forum where username=" + "\'" + userName + "\'";

            rs = stmt.executeQuery(sql);

            ForumsDao fDao = new ForumsDao();
            while(rs.next()){
                String forumName = rs.getString(2);
                forum = fDao.findForums(forumName);
            }

        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return forum;
    }
    
    public ArrayList<Forums> searchForums(String userName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Forums> forums = new ArrayList<Forums>();
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "select * from user_forum where username=" + "\'" + userName + "\'";

            rs = stmt.executeQuery(sql);
            Forums forums2 = new Forums();
            while(rs.next()){
                String forumName = rs.getString(2);
                forums2.setForumnama(forumName);
                forums.add(forums2);
            }

        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
        return forums;
    }
    
    public void deleteUserForum(UserForum uf) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from user_forum where username = \"?\" and forumname = \"?\"";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uf.getUserName());
            ps.setString(2, uf.getForumName());
            ps.execute();
        }
        catch (Exception e){

        }
        finally {
            DBUtil.close(rs,stmt,conn);
        }
    }
}
