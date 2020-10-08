package dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	ConnectionManager cm = new ConnectionManager();

	@Override
	public int signUp(User user) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		String email = user.getEmail();
		String pass = user.getPassword();
		Date date = user.getDate();

		String sql = "Insert into user(email,password,date) values(?,?,?)";

		PreparedStatement st = cm.getConnection().prepareStatement(sql);

		st.setString(1, email);
		st.setString(2, pass);
		st.setDate(3, date);

		st.executeUpdate();
		cm.getConnection().close();

		return 0;
	}

	@Override
	public boolean loginUser(User user) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		String email = user.getEmail();
		String pass = user.getPassword();

		Statement st = cm.getConnection().createStatement();

		ResultSet rs = st.executeQuery("Select * from USERDETAILS");

		boolean check = false;

		while (rs.next()) {

			if (email.equals(rs.getObject(1)) && pass.equals(rs.getObject(2))) {
				check = true;
			} else {
				check = false;
			}
		}

		return check;

	}

}