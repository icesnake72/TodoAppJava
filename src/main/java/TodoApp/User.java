package TodoApp;

public class User {
    private String user_id;
    private String nick_name;
    private int id;

    public User(int id, String userId, String nickName)
    {

        user_id = userId;
        nick_name = nickName;
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public int getId() {
        return id;
    }
}
