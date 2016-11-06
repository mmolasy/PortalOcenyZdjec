package pl.molasym.photoGrade.exceptions;

/**
 * Created by moliq on 06.11.16.
 */
public class ServerException extends Exception{
    private String msg;

    public ServerException()
    {
        super();
    }

    public ServerException(String msg)
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
