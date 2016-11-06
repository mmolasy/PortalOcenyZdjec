package pl.molasym.photoGrade.exceptions;

/**
 * Created by moliq on 06.11.16.
 */
public class UserWrongCredentials extends  Exception {

    private String msg;

    public UserWrongCredentials() {super();}

    public UserWrongCredentials(String msg)
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
