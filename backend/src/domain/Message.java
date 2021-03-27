package domain;

public class Message {
    private int id;
    private String message;
    private String forum;

    public Message(int id,String message,String forum)
    {
        this.id = id;
        this.message =message;
        this.forum = forum ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForum() {
        return forum;
    }

    public void setForum(String forum) {
        this.forum = forum;
    }
}
