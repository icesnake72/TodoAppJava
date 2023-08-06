package TodoApp;

import java.sql.SQLException;
import java.util.List;

public class TodoApp {

    public static void main(String[] args) {
        TodoDao db = new TodoDao("todos", "root", "1234");
//        try
//        {
//            db.AddUser("test3@gmail.com", "1234", "맨날코딩");
//        }
//        catch (SQLException e)
//        {
////            e.printStackTrace();
//            System.out.println( e.getMessage() );
//        }
//        catch (ClassNotFoundException e)
//        {
//            // e.printStackTrace();
//            System.out.println( e.getMessage() );
//        }
//        finally {
//            System.out.println("프로그램이 종료되었습니다!");
//        }
//
//
//
//        try
//        {
//            db.AddTodo("자바 열심히 공부해보기!!!", 6);
//        }
//        catch (SQLException e)
//        {
////            e.printStackTrace();
//            System.out.println( e.getMessage() );
//        }
//        catch (ClassNotFoundException e)
//        {
//            // e.printStackTrace();
//            System.out.println( e.getMessage() );
//        }
//        finally {
//            System.out.println("프로그램이 종료되었습니다!");
//        }


        try
        {
            User user = db.loginUser("test3@gmail.com", "1234");
            System.out.println( user.getNick_name() + " 님, 반갑습니다.");
        }
        catch (SQLException e)
        {
//            e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        catch (ClassNotFoundException e)
        {
            // e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        finally {
            System.out.println("프로그램이 종료되었습니다!");
        }







        try
        {
            List<Todo> list = db.getTodos(2);
            for(Todo todo:list)
            {
                System.out.println( todo );
            }
        }
        catch (SQLException e)
        {
//            e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        catch (ClassNotFoundException e)
        {
            // e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        finally {
            System.out.println("프로그램이 종료되었습니다!");
        }




        try
        {
            List<Todo> list = db.getTodos(6);
            for(Todo todo:list)
            {
                System.out.println( todo );
            }
        }
        catch (SQLException e)
        {
//            e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        catch (ClassNotFoundException e)
        {
            // e.printStackTrace();
            System.out.println( e.getMessage() );
        }
        finally {
            System.out.println("프로그램이 종료되었습니다!");
        }
    }
}
