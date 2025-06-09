package IMS;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.*;
@SuppressWarnings("serial") 
public class Dashboard extends JPanel {
	CardLayout mainCard;
	JPanel mainPanel;
	JButton login;
	
	public Dashboard(CardLayout mainCard, JPanel mainPanel){
		this.mainCard = mainCard;
		this.mainPanel = mainPanel;
		
		setLayout(null);
		
		JPanel leftP = new JPanel();
		leftP.setBounds(0,0,1000,1000);
		leftP.setBackground(new Color(240, 240, 240));
		leftP.setLayout(null);
		add(leftP);                        
		
		ImageIcon dashboardLogo = new ImageIcon(getClass().getResource("/images/dashboardLogo.png"));
		dashboardLogo.setImage(dashboardLogo.getImage().getScaledInstance(1000,1000,Image.SCALE_DEFAULT));
		JLabel pic = new JLabel(dashboardLogo);
		pic.setBounds(210,170,550,500);
		leftP.add(pic);
		
		//-------------------------------------------------------------------------------------------------------
		
		JPanel rightP = new JPanel();
		rightP.setBounds(1000,0,500,1000);
		rightP.setBackground(new Color(44, 90, 100));
		rightP.setLayout(null);
		add(rightP);
		
		JLabel welcome = new JLabel("WELCOME");
		welcome.setBounds(178, 200,  270, 40);
		welcome.setForeground(Color.WHITE);
		welcome.setFont(new Font("Impact", Font.BOLD, 40));      
		rightP.add(welcome);
		
		JLabel to = new JLabel("TO TECHSTOCK");
		to.setBounds(138, 260,  370, 40);
		to.setForeground(Color.WHITE);
		to.setFont(new Font("Impact", Font.BOLD, 40));      
		rightP.add(to);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(78, 400,190,40);
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		rightP.add(userLabel);
		
		JTextField userfield = new JTextField();
		userfield.setBounds(190, 402, 220, 28);
		rightP.add(userfield);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(78, 460,190,40);
		passLabel.setForeground(Color.WHITE);
		passLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		rightP.add(passLabel);
		
		JPasswordField passfield = new JPasswordField();
		passfield.setBounds(190, 462, 220, 28);
		rightP.add(passfield);
		
		login = new JButton("LOGIN");
		login.setBounds(230,522,130, 40);
		login.setBackground(new Color(44, 90, 100));
		login.setForeground(Color.WHITE);
		login.setFocusPainted(false);
		rightP.add(login);
		
		
		login.addActionListener( e -> {
			String username = userfield.getText().trim();
			String password = new String(passfield.getPassword()).trim();
			
			if(username.equals("admin") && password.equals("admin")) {
		        mainCard.show(mainPanel, "inventoryPanel");
		        userfield.setText("");
		        passfield.setText("");
		    } else {
		        JOptionPane.showMessageDialog(this, "Incorrect username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
		        userfield.setText("");
		        passfield.setText("");
		    }
			
		});
		
		
		
	}
}
