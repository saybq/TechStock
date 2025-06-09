package IMS;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.*;
@SuppressWarnings("serial")
public class LaunchFrame extends JFrame{
	CardLayout mainCard = new CardLayout(); 
    JPanel mainPanel = new JPanel(mainCard); 
    
	LaunchFrame(){
		 setTitle("TechStock");
	     setSize(1500,1000); 
	     setResizable(false);   
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(EXIT_ON_CLOSE); 
	     
	     ImageIcon icon = new ImageIcon(getClass().getResource("/images/dashboardLogo.png")); 
	     setIconImage(icon.getImage()); 
	        
	     Dashboard dashboard = new Dashboard(mainCard, mainPanel); 
	     InventoryPanel inventoryPanel = new InventoryPanel(mainCard, mainPanel); 
	     
	     mainPanel.add(inventoryPanel, "inventoryPanel");
	     mainPanel.add(dashboard, "dashboard");  
	     
	     mainCard.show(mainPanel, "dashboard"); 
	     add(mainPanel); 
	     
	     setVisible(true); 
	}
}
