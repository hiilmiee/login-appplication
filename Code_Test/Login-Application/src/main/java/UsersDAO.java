

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsersDAO{

	public Boolean checkLoginSuccessful(Users users) {
		Boolean isLoginSuccessful = false;
		String username = users.getUsername();
		String password = users.getPassword();
		
		String dbUrl = "jdbc:mysql://localhost:3306/db_loginmvc";
		String dbUsername = "root";
		String dbPassword = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
			
			PreparedStatement preparedStatment = connection.prepareStatement(query);
			preparedStatment.setString(1, username);
			preparedStatment.setString(2, password);
			
			ResultSet resultSet = preparedStatment.executeQuery();
			
			String resultUsername;
			String resultPassword;
			while(resultSet.next()) {
				resultUsername = resultSet.getString("username");
				resultPassword = resultSet.getString("password");
				
				if(username.equals(resultUsername) && password.endsWith(resultPassword)) {
					isLoginSuccessful = true;
					return isLoginSuccessful;
				}
			}
			
			resultSet.close();
			preparedStatment.close();
			connection.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		isLoginSuccessful = false;
		return isLoginSuccessful;
	}
}
