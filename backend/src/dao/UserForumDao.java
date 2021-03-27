package dao;
import java.sql.*;
import java.util.ArrayList;

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
}
