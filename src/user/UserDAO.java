package user;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UserDAO {
	public static DataSource getdDataSource() throws NamingException {
		Context initContext = null;
		Context environmentContext = null;
		
		initContext = new InitialContext();
		environmentContext = (Context) initContext.lookup("java:comp/env");
		
		return (DataSource) environmentContext.lookup("jdbc/WebDB");
	}
	
}
