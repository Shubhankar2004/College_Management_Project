package universitymanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateDepartment extends JFrame implements ActionListener {
	private JTextField dnametextField;
	private JTextField dheadtextField;
	private JButton dnameButton,dheadButton,resetButton,cancelButton;
	private JComboBox<String>comboBox;
	public UpdateDepartment() {
		setTitle("Update Department");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE DEPARTMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(230, 52, 327, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Department ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(56, 142, 244, 37);
		getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(56, 189, 256, 37);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Department Name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(56, 236, 244, 37);
		getContentPane().add(lblNewLabel_2);
		
		dnametextField = new JTextField();
		dnametextField.setBounds(56, 283, 256, 37);
		getContentPane().add(dnametextField);
		dnametextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Department Head :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(56, 330, 244, 37);
		getContentPane().add(lblNewLabel_3);
		
		dheadtextField = new JTextField();
		dheadtextField.setBounds(56, 377, 256, 37);
		getContentPane().add(dheadtextField);
		dheadtextField.setColumns(10);
		
		dnameButton = new JButton("Update");
		dnameButton.setBackground(Color.GREEN);
		dnameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dnameButton.setBounds(338, 283, 85, 37);
		dnameButton.addActionListener(this);
		getContentPane().add(dnameButton);
		
		dheadButton = new JButton("Update");
		dheadButton.setBackground(Color.GREEN);
		dheadButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dheadButton.setBounds(338, 377, 85, 37);
		dheadButton.addActionListener(this);
		getContentPane().add(dheadButton);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		resetButton.setBounds(56, 480, 101, 49);
		resetButton.addActionListener(this);
		getContentPane().add(resetButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(56, 576, 101, 49);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/universitymanagementsystem/icons/depart2.jpg"));
		Image i2 = i1.getImage().getScaledInstance(307,483,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(451, 142, 307, 483);
		getContentPane().add(image);
		
		comboBox.addItem("0");
		
		try {
			Conn con = new Conn();
			ResultSet rs = con.s.executeQuery("select depart_id from department");
			
			while(rs.next()) {
				String item = ""+rs.getInt("depart_id");
				comboBox.addItem(item);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setLocation(400,50);
		setSize(806,764);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Conn con = new Conn();
		ResultSet rs;
		int did = Integer.parseInt(""+comboBox.getSelectedItem());
		if(ae.getSource()==comboBox) {
			try {
				
				rs = con.s.executeQuery("select * from department where depart_id = "+did+" ");
			
				if(rs.next()) {
					dnametextField.setText(rs.getString("depart_name"));
					dheadtextField.setText(rs.getString("depart_head"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource()==resetButton) {
			comboBox.setSelectedItem("0");
			dnametextField.setText(null);
			dheadtextField.setText(null);
		}else if(ae.getSource()==cancelButton) {
			setVisible(false);
		}else if(ae.getSource()==dnameButton) {
			try {
				
				con.s.executeUpdate("Update department set depart_name = '"+dnametextField.getText()+"' where depart_id = "+did+" ");
			
				JOptionPane.showMessageDialog(null,"Updation Successful !!!! ");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(ae.getSource()==dheadButton) {
			try {

				con.s.executeUpdate("Update department set depart_head = '"+dheadtextField.getText()+"' where depart_id = "+did+" ");
			
				JOptionPane.showMessageDialog(null,"Updation Successful !!!! ");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
