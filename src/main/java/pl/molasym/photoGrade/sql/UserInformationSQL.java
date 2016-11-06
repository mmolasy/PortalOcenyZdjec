package pl.molasym.photoGrade.sql;

public class UserInformationSQL {

	public static final String GET_USER_BY_ID = "SELECT e From User e join fetch e.addresses add WHERE e.id = :id";
	public static final String GET_USER_BY_EMAIL_AND_PASSWORD= "Select e From User e where e.email = :email and e.password = :password";
	public static final String GET_USER_BY_EMAIL = "Select e From User e where e.email = :mail";
}
