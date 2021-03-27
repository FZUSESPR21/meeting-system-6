import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ForumsDao {
    Connection conn = null;
    ArrayList<Forums> list = new ArrayList<Forums>();
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
                    System.out.println(forums.getForumnama()+forums.getChairman()+forums.getTheme()+forums.getTime());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return forums;
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
}
