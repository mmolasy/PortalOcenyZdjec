package pl.molasym.photoGrade.exceptions;

public class UserNotFoundException extends Exception{
	
	private String msg;
	
	public UserNotFoundException()
	{
		super();
	}
	
	public UserNotFoundException(String msg)
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
