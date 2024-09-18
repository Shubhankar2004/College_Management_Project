package universitymanagementsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProfessorDetails extends JFrame implements ActionListener{
	private JTable table;
	private JButton showAllButton,ShowIdButton,resetButton,cancelButton;
	JComboBox<String>comboBox;
	
	public ProfessorDetails() {
		setTitle("Professor Details");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PROFESSOR DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(395, 43, 311, 37);
		getContentPane().add(lblNewLabel);
		
		showAllButton = new JButton("Show All");
		showAllButton.setBackground(Color.GREEN);
		showAllButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showAllButton.setBounds(56, 118, 101, 49);
		showAllButton.addActionListener(this);
		getContentPane().add(showAllButton);
		
		JLabel lblNewLabel_1 = new JLabel("Professor ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(167, 118, 141, 49);
		getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(318, 118, 126, 49);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
		
		ShowIdButton = new JButton("Show");
		ShowIdButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ShowIdButton.setBackground(Color.GREEN);
		ShowIdButton.setBounds(483, 118, 101, 49);
		ShowIdButton.addActionListener(this);
		getContentPane().add(ShowIdButton);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		resetButton.setBounds(631, 118, 101, 49);
		resetButton.addActionListener(this);
		getContentPane().add(resetButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.RED);
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(780, 118, 101, 49);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 229, 1089, 445);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox.addItem("0");
		
		try {
			Conn con = new Conn();
			ResultSet rs = con.s.executeQuery("select prof_id from professor");
			
			while(rs.next()) {
				String item = ""+rs.getInt("prof_id");
				comboBox.addItem(item);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setLocation(200,50);
		setSize(1195,764);
		setVisible(true);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Conn con = new Conn();
		ResultSet rs;
		ResultSetMetaData rsmd;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if(ae.getSource()==showAllButton) {
			try {
				
				rs = con.s.executeQuery("select * from professor inner join department on professor.depart_id = department.depart_id");
			
				rsmd=rs.getMetaData();
				
				int cols = rsmd.getColumnCount();
				Vector<String> colName = new Vector<String>();
				for(int i=0;i<cols;i++)
					colName.add(rsmd.getColumnName(i+1));
				
				model.setColumnIdentifiers(colName);
				String pid,fname,lname,eml,mob,did,dpid,dname,dhead;
				
				while(rs.next()) {
					pid=rs.getString(1);
					fname=rs.getString(2);
					lname=rs.getString(3);
					eml=rs.getString(4);
					mob=rs.getString(5);
					did=rs.getString(6);
					dpid=rs.getString(7);
					dname=rs.getString(8);
					dhead=rs.getString(9);
					//Vector<String>row=new Vector<String>();
					String[] row = {pid,fname,lname,eml,mob,did,dpid,dname,dhead};
					model.addRow(row);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource()==ShowIdButton) {
			try {
				
				int id = Integer.parseInt(""+comboBox.getSelectedItem());
				
				rs = con.s.executeQuery("select * from professor inner join department on professor.depart_id = department.depart_id where professor.prof_id = "+id+" ");
			
				rsmd=rs.getMetaData();
				
				int cols = rsmd.getColumnCount();
				Vector<String> colName = new Vector<String>();
				for(int i=0;i<cols;i++)
					colName.add(rsmd.getColumnName(i+1));
				
				model.setColumnIdentifiers(colName);
				String pid,fname,lname,eml,mob,did,dpid,dname,dhead;
				
				while(rs.next()) {
					pid=rs.getString(1);
					fname=rs.getString(2);
					lname=rs.getString(3);
					eml=rs.getString(4);
					mob=rs.getString(5);
					did=rs.getString(6);
					dpid=rs.getString(7);
					dname=rs.getString(8);
					dhead=rs.getString(9);
					//Vector<String>row=new Vector<String>();
					String[] row = {pid,fname,lname,eml,mob,did,dpid,dname,dhead};
					model.addRow(row);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource()==resetButton) {
			table.setModel(new DefaultTableModel());
		}else if(ae.getSource()==cancelButton) {
			setVisible(false);
		}
	}

}
