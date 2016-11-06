package pl.molasym.photoGrade.exceptions;

/**
 * Created by moliq on 06.11.16.
 */
public class UserAlreadyRegistered extends Exception {

    private String msg;

    public UserAlreadyRegistered()
    {
        super();
    }

    public UserAlreadyRegistered(String msg)
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
