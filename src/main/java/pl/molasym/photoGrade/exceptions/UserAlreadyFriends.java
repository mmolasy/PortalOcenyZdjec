package pl.molasym.photoGrade.exceptions;

/**
 * Created by moliq on 20.11.16.
 */
public class UserAlreadyFriends extends Exception{

    private String msg;

    public UserAlreadyFriends()
    {
        super();
    }

    public UserAlreadyFriends(String msg)
    {
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
