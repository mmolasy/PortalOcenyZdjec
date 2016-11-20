package pl.molasym.photoGrade.exceptions;

/**
 * Created by moliq on 20.11.16.
 */
public class InvitationAlreadyAccepted extends Exception{

    private String msg;

    public InvitationAlreadyAccepted()
    {
        super();
    }

    public InvitationAlreadyAccepted(String msg)
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
