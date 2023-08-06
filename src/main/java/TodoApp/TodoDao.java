package TodoApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final String DB_URI = "jdbc:mysql://localhost:3306/";
    private final String INSERT_USER = "insert into users(email, password, nick_name) values(?, ?, ?)";
    private final String INSERT_TODO = "insert into todos(todo, user_id) values(?, ?)";

    private final String GET_TODOS = "select users.nick_name, todos.todo, todos.done, todos.created_time, todos.user_id " +
            "from todos " +
            "inner join users on users.id=todos.user_id " +
            "where todos.user_id=?";

    private final String USER_LOGIN = "select id, email, nick_name from users where email=? and password=?";
    private final int TIME_OUT = 10;


    private String userId;
    private String userPwd;
    private String strConn;

    public TodoDao(String db_name, String user_id, String user_pwd)
    {
        strConn = String.format("%s%s", DB_URI, db_name);
        userId = user_id;
        userPwd = user_pwd;
    }

    private boolean checkEnvironment()
    {
        if ( strConn.isEmpty() || userId.isEmpty() || userPwd.isEmpty() )
            return false;

        return true;
    }

    public int AddUser(String user_id, String pwd, String nick_name) throws SQLException, ClassNotFoundException {
        if (!checkEnvironment())
            return 0;

        int result = 0;

        // 단계 1: MySQL JDBC 드라이버 로드 및 등록
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 단계 2: 데이터베이스와 연결
        Connection conn = DriverManager.getConnection(strConn, userId, userPwd);
        if ( conn.isValid(TIME_OUT) )
        {
            // 단계 3: SQL 쿼리를 실행할 Statement 객체 생성
            PreparedStatement ps = conn.prepareStatement(INSERT_USER);
            ps.setString(1, user_id);
            ps.setString(2, pwd);
            ps.setString(3, nick_name);

            result = ps.executeUpdate();

            // 단계 6: 리소스 정리
            ps.close();
            conn.close();
        }

        return result;
    }


    public int AddTodo(String todo, int user_id) throws SQLException, ClassNotFoundException
    {
        if (!checkEnvironment())
            return 0;

        int result = 0;

        // 단계 1: MySQL JDBC 드라이버 로드 및 등록
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 단계 2: 데이터베이스와 연결
        Connection conn = DriverManager.getConnection(strConn, userId, userPwd);
        if ( conn.isValid(TIME_OUT) )
        {
            // 단계 3: SQL 쿼리를 실행할 PreparedStatement 객체 생성
            PreparedStatement ps = conn.prepareStatement(INSERT_TODO);
            ps.setString(1, todo);
            ps.setInt(2, user_id);

            // 단계 4: SQL 쿼리 실행
            result = ps.executeUpdate();

            // 단계 6: 리소스 정리
            ps.close();
            conn.close();
        }

        return result;
    }


    public User loginUser(String user_id, String user_pwd) throws SQLException, ClassNotFoundException
    {
        if (!checkEnvironment())
            return null;

        // 단계 1: MySQL JDBC 드라이버 로드 및 등록
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 단계 2: 데이터베이스와 연결
        Connection conn = DriverManager.getConnection(strConn, userId, userPwd);
        if ( conn.isValid(TIME_OUT) )
        {
            // 단계 3: SQL 쿼리를 실행할 PreparedStatement 객체 생성
            PreparedStatement ps = conn.prepareStatement(USER_LOGIN);
            ps.setString(1, user_id);
            ps.setString(2, user_pwd);

            // 단계 4: 쿼리 실행
            ResultSet rs = ps.executeQuery();

            rs.next();
            User user = new User(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));


            rs.close();
            ps.close();
            conn.close();

            return user;
        }

        return null;
    }


    public List<Todo> getTodos(int id) throws SQLException, ClassNotFoundException
    {
        List<Todo> list = new ArrayList<>();
        if (!checkEnvironment())
            return list;

        // 단계 1: MySQL JDBC 드라이버 로드 및 등록
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 단계 2: 데이터베이스와 연결
        Connection conn = DriverManager.getConnection(strConn, userId, userPwd);
        if ( conn.isValid(TIME_OUT) )
        {
            // 단계 3: SQL 쿼리를 실행할 PreparedStatement 객체 생성
            PreparedStatement ps = conn.prepareStatement(GET_TODOS);
            ps.setInt(1, id);

            // 단계 4: 쿼리 실행
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getBoolean(3));
//                System.out.println(rs.getTimestamp(4));
//                System.out.println(rs.getInt(5));

                Todo todo = new Todo(rs.getString(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getTimestamp(4),
                        rs.getInt(5));

                list.add(todo);
            }

            rs.close();
            ps.close();
            conn.close();
        }

        return list;
    }

}
