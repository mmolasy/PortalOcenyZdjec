package pl.molasym.photoGrade.sql;

public class UserInformationSQL {
	
	public static final String GET_ALL_USERS = "Select e FROM User e";
	public static final String GET_USER_BY_ACCOUNT_ID = "SELECT e FROM User e WHERE e.userId in (SELECT a.user FROM Account a WHERE a.accountId = :id)";
	public static final String GET_USER_BY_ID = "SELECT e From User e join fetch e.addresses add WHERE e.id = :id";
}
