package TODO;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ViewModel {
	
	Model model = new Model();
	
	public void addView() throws NumberFormatException, SQLException, ClassNotFoundException{
		
		JFrame frame = new JFrame("Input");
		String descAdd = JOptionPane.showInputDialog(frame, "Please insert description");
		String dateAdd = JOptionPane.showInputDialog(frame, "Please insert Date");
		
		model.addTask( descAdd, dateAdd);
		
		
	}
	
	
	public void deleteView() throws NumberFormatException, SQLException, ClassNotFoundException{
		
		JFrame frame = new JFrame("Input");
		String id = JOptionPane.showInputDialog(frame, "Please insert id");
		
		model.deleteTask(Integer.parseInt(id));
		
		
	}
	
	
	
	public String refreshView() throws NumberFormatException, SQLException, ClassNotFoundException{
		
		return model.readFromDB();
		
	}
	
	public void updateView() throws NumberFormatException, SQLException, ClassNotFoundException{
		
		JFrame frame = new JFrame("Input");
		String id = JOptionPane.showInputDialog(frame, "Please insert task ID");
		String descNew = JOptionPane.showInputDialog(frame, "Please insert new Description");
		String dateNew = JOptionPane.showInputDialog(frame, "Please insert new Date");
		
		model.updateTask(Integer.parseInt(id), descNew, dateNew);
		
		
	}
	
	
	

}
