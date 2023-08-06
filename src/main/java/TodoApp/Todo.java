package TodoApp;

import java.sql.Timestamp;

public class Todo {
    private String nick_name;
    private String todo;
    private boolean done;
    private Timestamp created_time;
    private int user_id;

    public Todo(String nickName, String todo, boolean done, Timestamp createdTime, int userId)
    {
        nick_name = nickName;
        this.todo = todo;
        this.done = done;
        created_time = createdTime;
        user_id = userId;
    }

    public String getTodo()
    {
        return todo;
    }

    public String getNick_name() {
        return nick_name;
    }

    public boolean isDone() {
        return done;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public int getUser_id() {
        return user_id;
    }


    @Override
    public String toString() {
        String result = String.format("닉네임 : %s, 할일 : %s, 완료 : %b, 생성날짜 : %s",
                getNick_name(), getTodo(), isDone(), getCreated_time().toString());
        return result;
    }
}
