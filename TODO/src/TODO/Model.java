package TODO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mysql.jdbc.PreparedStatement;

public class Model {
	final static Logger logger = Logger.getLogger(Model.class);

	static private DBHandler dbHandler;
	static private Connection connection;
	static private PreparedStatement preparedStatement;

	public void addTask(String desc, String date) throws SQLException, ClassNotFoundException {

		dbHandler = new DBHandler();
		connection = dbHandler.getDbConnection();
		String insert = "INSERT INTO tasks (`desc`, `date`) VALUES (?,?)";
		preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
		preparedStatement.setString(1, desc);
		preparedStatement.setString(2, date);

		preparedStatement.executeUpdate();
		preparedStatement.close();
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Task added successfully");

	}

	public void deleteTask(int id) throws SQLException, ClassNotFoundException {
		dbHandler = new DBHandler();
		connection = dbHandler.getDbConnection();
		String query = "DELETE FROM tasks where `id` = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Task deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String readFromDB() throws SQLException, ClassNotFoundException {
		dbHandler = new DBHandler();
		connection = dbHandler.getDbConnection();

		String query = "SELECT * from tasks";
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		/*
		 * while (resultSet.next()) { System.out.println("Names: " +
		 * resultSet.getInt("id") + " " + resultSet.getString("desc") + " " +
		 * resultSet.getInt("date")); }
		 */
		String result = "";
		while (resultSet.next()) {
			result += resultSet.getInt("id") + " " + resultSet.getString("desc") + " " + resultSet.getString("date")
					+ System.lineSeparator();
		}
		PropertyConfigurator.configure("log4j.properties");
		logger.info("read from database completed successfully");
		return result;

	}

	public void updateTask(int id, String newDesc, String newDate)throws SQLException, ClassNotFoundException {
		dbHandler = new DBHandler();
		connection = dbHandler.getDbConnection();
		
		String query = "UPDATE tasks SET `desc` = ?, `date` = ? "
				+ " where `id` = ? ";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, newDesc);
			preparedStatement.setString(2, newDate);
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Task updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
