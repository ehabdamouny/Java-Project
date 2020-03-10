package TODO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket.Listener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.*;
import com.mysql.jdbc.*;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class View extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton addButton;
	JButton removeButton;
	JButton refreshButton;
	JButton editButton;
	DefaultListModel<String> listmodel;
	JList<String> list ;
	ViewModel viewmodel = new ViewModel();

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NumberFormatException 
	 */
	public View () throws NumberFormatException, ClassNotFoundException, SQLException {
		//components
		setTitle("TODO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel buttonsPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		
		setContentPane(contentPane);
		// init

		
		addButton = new JButton("Add Task");
		buttonsPanel.add(addButton);
		addButton.addActionListener(this);
		
		removeButton = new JButton("Remove Task");
		buttonsPanel.add(removeButton);
		removeButton.addActionListener(this);
		
		refreshButton = new JButton("Refresh Data");
		buttonsPanel.add(refreshButton);
		refreshButton.addActionListener(this);
		
		
		editButton = new JButton("Edit Task");
		buttonsPanel.add(editButton);
		editButton.addActionListener(this);

		
		contentPane.add(buttonsPanel,BorderLayout.SOUTH);
		
		JLabel title = new JLabel("TODO");
		titlePanel.add(title);
		contentPane.add(titlePanel,BorderLayout.NORTH);
		
		listmodel = new DefaultListModel<String>();
		list = new JList<>(listmodel);
		contentPane.add(list, BorderLayout.CENTER);
		
		displayData();
	}
	
	public void displayData() throws NumberFormatException, ClassNotFoundException, SQLException {

		Thread thread = new Thread(new Runnable()
		{
		   public void run()
		   {
		       // this will be run in a separate thread
				String[] results;
				listmodel.clear();
				String init = "ID Description Date";
				listmodel.addElement(init);
				String result="";
				try {
					result = viewmodel.refreshView();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results = result.split("\n");
				List<String> al = new ArrayList<String>();
				al = Arrays.asList(results);
				for(String s: al) {
					listmodel.addElement(s);
				}
		   }
		});

		// start the thread

		thread.start(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().contentEquals("Add Task")) {
			try {
				try {
					viewmodel.addView();
					displayData();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if(e.getActionCommand().contentEquals("Remove Task")) {
			try {
				try {
					viewmodel.deleteView();
					displayData();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if(e.getActionCommand().contentEquals("Refresh Data")) {
			try {
				try {
					displayData();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().contentEquals("Edit Task")) {
			try {
				try {
					viewmodel.updateView();
					displayData();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}



