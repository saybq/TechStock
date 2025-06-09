package IMS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel{
	
	private JTextField nameField, quantityField, priceField, descriptionField;
	private JTextField DeleteIDField;
    private JTextField updateIDField, updateValueField;
    private JComboBox <String> updateFieldCombo;
    
    private DefaultTableModel model;
    private JTable table;
    
    private JDialog addDialog;
    private JDialog removeDialog;
    private JDialog updateDialog;
    
    CardLayout mainCard;
    JPanel mainPanel;
    JButton goBack;
    
    InventoryPanel(CardLayout mainCard, JPanel mainPanel){
    	this.mainCard = mainCard;
    	this.mainPanel = mainPanel;
    	setLayout(null);
    	
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(190,0,1350,50); 
        header.setBackground(new Color(44, 90, 100));
        add(header);
        
        JLabel headerLabel = new JLabel("I N V E N T O R Y  M A N A G E M E N T");
        headerLabel.setBounds(5, 0, 350, 50);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(headerLabel);
        
        JLabel surnames = new JLabel("CAROLINO | DOLOR | GUSTO | LEYNES");
        surnames.setBounds(1100,18,320,20);
        surnames.setForeground(Color.WHITE);
        surnames.setFont(new Font("Arial", Font.PLAIN, 9));
        header.add(surnames);
        
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel leftP = new JPanel();
        leftP.setLayout(null);
        leftP.setBounds(0,0,190,980);
        leftP.setBackground(new Color(44, 90, 100));
        add(leftP);
        
        JLabel logo = new JLabel("TECH"); 
        logo.setBounds(40,20,150,50); 
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Impact", Font.BOLD, 50)); 
        leftP.add(logo); 
        JLabel logo1 = new JLabel("STOCK"); 
        logo1.setBounds(25,70,150,50); 
        logo1.setForeground(Color.WHITE); 
        logo1.setFont(new Font("Impact", Font.BOLD, 50)); 
        leftP.add(logo1); 
        
        goBack = new JButton("LOG OUT");
        goBack.setBounds(15, 880, 160, 40);
		goBack.setBackground(new Color(44, 90, 100));
		goBack.setFocusPainted(false);
		goBack.setForeground(Color.WHITE);
		leftP.add(goBack);

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
        JPanel InventoryPart = new JPanel();
         InventoryPart.setLayout(null);
         InventoryPart.setBounds(191, 51,1300, 900 );
         InventoryPart.setBackground(new Color(240, 240, 240));
         add(InventoryPart);

         JLabel InvetoryLabel = new JLabel("ITEMS");
         InvetoryLabel.setBounds(80,10, 150,30);
         InvetoryLabel.setFont(new Font("Arial", Font.BOLD, 30));
         InventoryPart.add(InvetoryLabel);
         
         JTextField searchField = new JTextField("    Search by Name or ID...");
         searchField.setBounds(80, 55, 1000, 50);
         searchField.setFocusable(true);
         InventoryPart.add(searchField);
         
         JButton searchButton = new JButton("Search");
         searchButton.setBounds(1090, 55, 100, 48);
         searchButton.setBackground(new Color(44, 90, 100));
         searchButton.setFocusPainted(false);
         searchButton.setForeground(Color.WHITE);
         InventoryPart.add(searchButton);
         
         JButton addButton = new JButton("ADD");
         addButton.setBounds(80,130,100,30);
         addButton.setBackground(new Color(44, 90, 100));
         addButton.setFocusPainted(false);
         addButton.setForeground(Color.WHITE);
         InventoryPart.add(addButton);
         
         JButton removeButton = new JButton("REMOVE");
         removeButton.setBounds(185,130,100,30);
         removeButton.setBackground(new Color(44, 90, 100));
         removeButton.setFocusPainted(false);
         removeButton.setForeground(Color.WHITE);
         InventoryPart.add(removeButton);
         
         JButton updateButton = new JButton("UPDATE");
         updateButton.setBounds(290,130,100,30);
         updateButton.setBackground(new Color(44, 90, 100));
         updateButton.setFocusPainted(false);
         updateButton.setForeground(Color.WHITE);
         InventoryPart.add(updateButton);
         
         JButton resetTable = new JButton("Refresh");
         resetTable.setBounds(395,130,100,30);
         resetTable.setBackground(new Color(44, 90, 100));
         resetTable.setFocusPainted(false);
         resetTable.setForeground(Color.WHITE);
         InventoryPart.add(resetTable);
         
         String[] columnNames = {"Stock_ID","Name", "Quantity Stock", "Price", "Total Product Amount", "Description"};
         model = new DefaultTableModel(columnNames, 0);
         table = new JTable(model);
         table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
         table.getTableHeader().setBackground(new Color(44, 90, 100));
         table.getTableHeader().setForeground(Color.WHITE);
         table.setFont(new Font("Arial", Font.PLAIN, 15)); // 🔍 Bigger cell text
         table.setRowHeight(22); // 🧱 More height to accommodate bigger text
         centerTableCells(table);
        
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBounds(80,180,1113,650);
         scrollPane.setBackground(new Color(20, 120, 120));
         InventoryPart.add(scrollPane);
         
         JButton dLreport = new JButton("Download Report as .csv");
         dLreport.setBounds(1010, 840, 180, 25);
         dLreport.setBackground(new Color(44, 90, 100));
         dLreport.setFocusPainted(false);
         dLreport.setForeground(Color.WHITE);
         InventoryPart.add(dLreport);
         
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // EVENT WHEN BUTTONS ARE CLICK ---------------------------------------------------------
         searchField.addFocusListener(new FocusAdapter() {
        	    public void focusGained(FocusEvent e) {
        	        if (searchField.getText().equals("    Search by Name or ID...")) {
        	            searchField.setText("    ");
        	            searchField.setForeground(Color.BLACK);
        	        }
        	    }

        	    public void focusLost(FocusEvent e) {
        	        if (searchField.getText().isEmpty()) {
        	            searchField.setText("Search...");
        	            searchField.setForeground(Color.GRAY);
        	        }
        	    }
        	});
         searchButton.addActionListener(e -> {
        	    String keyword = searchField.getText().trim();

        	    if (keyword.isEmpty() || keyword.equals("    Search by Name or ID...")) {
        	        loadItemsToTable(); // Load all items if empty search
        	        return;
        	    }

        	    try {
        	        DatabaseManager dbManager = new DatabaseManager();
        	        ResultSet resultSet = dbManager.searchItems(keyword);

        	        model.setRowCount(0); // Clear table

        	        while (resultSet.next()) {
        	            int stock_id = resultSet.getInt("id");
        	            String name = resultSet.getString("name");
        	            int quantity = resultSet.getInt("quantity");
        	            int price = resultSet.getInt("price");
        	            String description = resultSet.getString("description");
        	            int totalAmount = quantity * price;

        	            model.addRow(new Object[]{stock_id, name, quantity, price, totalAmount, description});
        	        }
        	    } catch (SQLException ex) {
        	        ex.printStackTrace();
        	        JOptionPane.showMessageDialog(this, "Error during search: " + ex.getMessage());
        	    }
        	});
         resetTable.addActionListener(e -> {
        	 searchField.setText("    Search by Name or ID...");
        	 loadItemsToTable(); 
         });

         
         addButton.addActionListener(e ->{
        	 	addDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(InventoryPart), "Add Item", true);
        	    addDialog.setLayout(null);
        	    addDialog.setSize(445, 270);
        	    addDialog.setLocationRelativeTo(null);
        	    
        	    JLabel nameLabel = new JLabel("Name");
        	    nameLabel.setBounds(20, 30, 80, 25);
        	    nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        	    addDialog.add(nameLabel);
        	    
        	    nameField = new JTextField();
        	    nameField.setBounds(140, 30, 250, 25);
        	    addDialog.add(nameField);
        	    
        	    JLabel quantityLabel = new JLabel("Quantity");
        	    quantityLabel.setBounds(20, 60, 80, 25);
        	    quantityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        	    addDialog.add(quantityLabel);
        	    
        	    quantityField = new JTextField();
        	    quantityField.setBounds(140, 60, 250, 25);
        	    addDialog.add(quantityField);
        	    
        	    JLabel priceLabel = new JLabel("Price");
        	    priceLabel.setBounds(20, 90, 80, 25);
        	    priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        	    addDialog.add(priceLabel);
        	    
        	    priceField = new JTextField();
        	    priceField.setBounds(140, 90, 250, 25);
        	    addDialog.add(priceField);
        	    
        	    JLabel descriptionLabel = new JLabel("Description");
        	    descriptionLabel.setBounds(20, 120, 95, 25);
        	    descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15));
        	    addDialog.add(descriptionLabel);
        	    
        	    JLabel optionalLabel = new JLabel("(Optional)");
        	    optionalLabel.setBounds(105, 129, 95, 10);
        	    optionalLabel.setFont(new Font("Arial", Font.PLAIN, 8));
        	    addDialog.add(optionalLabel);
        	    
        	    descriptionField = new JTextField();
        	    descriptionField.setBounds(140, 120, 250, 25);
        	    addDialog.add(descriptionField);
        	    
        	    JButton saveButton = new JButton("SAVE");
        	    saveButton.setBounds(175,170,100,30);
                saveButton.setBackground(new Color(44, 90, 100));
                saveButton.setFocusPainted(false);
                saveButton.setForeground(Color.WHITE);
                addDialog.add(saveButton);
                
                saveButton.addActionListener(ev -> {
                	try {
                        String name = nameField.getText().trim();
                        String quantityText = quantityField.getText().trim();
                        String priceText = priceField.getText().trim();
                        String description = descriptionField.getText().trim();

                        
                        if (name.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "❗ Name, Quantity, and Price fields are required!");
                            clearFields(1);
                            return;
                        }

                        int quantityS = Integer.parseInt(quantityText);
                        int price = Integer.parseInt(priceText);

                        if (quantityS < 0 || price < 0) {
                            JOptionPane.showMessageDialog(this, "❗ Quantity and Price must be non-negative.");
                            return;
                        }

                        Inventory item = new Inventory(name, description, quantityS, price);
                        item.addItem();
                        loadItemsToTable();
                        JOptionPane.showMessageDialog(this, "✅ Item added successfully to database!");
                        clearFields(1);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "❌ Quantity and Price must be valid numbers.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                    addDialog.dispose();
                });
        	    addDialog.setVisible(true);
         });
         
         removeButton.addActionListener(e ->{
        	removeDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(InventoryPart), "Remove Item", true);
     	    removeDialog.setLayout(null);
     	    removeDialog.setSize(445, 200);
     	    removeDialog.setLocationRelativeTo(null);
     	    
     	    JLabel deleteLabel = new JLabel("Stock ID ");
     	    deleteLabel.setBounds(20, 30, 80, 25);
     	    deleteLabel.setFont(new Font("Arial", Font.BOLD, 15));
     	    removeDialog.add(deleteLabel);
   	    
     	    DeleteIDField = new JTextField();
     	    DeleteIDField.setBounds(140, 30, 250, 25);
     	    removeDialog.add(DeleteIDField);
     	    
     	   JButton RsaveButton = new JButton("SAVE");
     	   RsaveButton.setBounds(175,90,100,30);
           RsaveButton.setBackground(new Color(44, 90, 100));
           RsaveButton.setFocusPainted(false);
           RsaveButton.setForeground(Color.WHITE);
           removeDialog.add(RsaveButton);
           
           RsaveButton.addActionListener(ev -> {
        	   try {
          	     int id = Integer.parseInt(DeleteIDField.getText());

          	     int confirm = JOptionPane.showConfirmDialog(this, 
          	     "Are you sure you want to delete item with ID " + id + "?", 
          	     "Confirm Delete", JOptionPane.YES_NO_OPTION);

          	     if (confirm != JOptionPane.YES_OPTION) return;

          	     Inventory item = new Inventory("","",0,0);
          	     boolean success = item.removeItem(id);
          	     
          	     if (success) {
          	         JOptionPane.showMessageDialog(this, "✅ Item removed successfully!");
          	         loadItemsToTable(); // Refresh the table
          	         clearFields(2);
          	     } else {
          	         JOptionPane.showMessageDialog(this, "⚠️ No item found with that ID.");
          	      	}

          	 } catch (NumberFormatException ex) {
          	        JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
          	 } catch (Exception ex) {
          	        ex.printStackTrace();
          	        JOptionPane.showMessageDialog(this, "Error removing item: " + ex.getMessage());
          	 }
        	   removeDialog.dispose();
           });
     	   removeDialog.setVisible(true);
         });
         
         updateButton.addActionListener(e ->{
         	updateDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(InventoryPart), "Update Item", true);
      	    updateDialog.setLayout(null);
      	    updateDialog.setSize(445, 270);
      	    updateDialog.setLocationRelativeTo(null);
      	    
      	    JLabel updateLabel = new JLabel("Stock ID ");
      	    updateLabel.setBounds(20, 30, 80, 25);
      	    updateLabel.setFont(new Font("Arial", Font.BOLD, 15));
      	    updateDialog.add(updateLabel);
  	    
      	    updateIDField = new JTextField();
      	  	updateIDField.setBounds(140, 30, 250, 25);
      	  	updateDialog.add(updateIDField);
      	  	
      	  	JLabel choicesLabel = new JLabel("Field to be Updated");
      	  	choicesLabel.setBounds(20, 60, 100, 25);
      	  	choicesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
      	  	updateDialog.add(choicesLabel);
      	  	
      	  	String[] updateOptions = {"Name", "Quantity", "Price", "Description"};
      	  	updateFieldCombo = new JComboBox<>(updateOptions);
      	  	updateFieldCombo.setBounds(140, 60, 250, 25);
      	  	updateDialog.add(updateFieldCombo);
      	  	
      	  	JLabel NewValueLabel = new JLabel("New Value ");
      	  	NewValueLabel.setBounds(20, 90, 100, 25);
      	  	NewValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
      	  	updateDialog.add(NewValueLabel);

      	  	updateValueField = new JTextField(); 
      	  	updateValueField.setBounds(140, 90, 250, 25);
      	  	updateDialog.add(updateValueField);
      	  	
      	  	JButton UsaveButton = new JButton("SAVE");
      	  	UsaveButton.setBounds(175,170,100,30);
      	  	UsaveButton.setBackground(new Color(44, 90, 100));
      	  	UsaveButton.setFocusPainted(false);
      	  	UsaveButton.setForeground(Color.WHITE);
      	  	updateDialog.add(UsaveButton);
      	  	
      	  	UsaveButton.addActionListener(ev ->{
      	  	try {
                String idText = updateIDField.getText().trim();
                String fieldToUpdate = updateFieldCombo.getSelectedItem().toString().toLowerCase(); // match DB column names
                String newValue = updateValueField.getText().trim();

                if (idText.isEmpty() || newValue.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "❗ ID and New Value fields are required!");
                    clearFields(3);
                    return;
                }

                int idToUpdate = Integer.parseInt(idText);

                // Validate numeric fields
                if (fieldToUpdate.equals("quantity") || fieldToUpdate.equals("price")) {
                    try {
                        int numericValue = Integer.parseInt(newValue);
                        if (numericValue < 0) {
                            JOptionPane.showMessageDialog(this, "❗ Quantity and Price must be non-negative.");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "❌ " + fieldToUpdate + " must be a valid number.");
                        return;
                    }
                }

                Inventory item = new Inventory("", "", 0, 0); // dummy item object just for update method
                item.updateItem(idToUpdate, fieldToUpdate, newValue);

                loadItemsToTable();
                JOptionPane.showMessageDialog(this, "✅ Item updated successfully!");
                clearFields(3);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ ID must be a valid number.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "⚠️ Error updating item: " + ex.getMessage());
            }
      	  		updateDialog.dispose();
      	  	});
      	    updateDialog.setVisible(true);
          });

         dLreport.addActionListener(e ->{
        	 JFileChooser fileChooser = new JFileChooser();
        	 fileChooser.setDialogTitle("Save Inventory Report");
        	 fileChooser.setSelectedFile(new java.io.File("inventory_report.csv"));
            
        	 int userSelection = fileChooser.showSaveDialog(this);
        	 if (userSelection == JFileChooser.APPROVE_OPTION) {
        		 java.io.File fileToSave = fileChooser.getSelectedFile();
        		 try {
        			 exportTableToCSV(table, fileToSave.getAbsolutePath());
        			 JOptionPane.showMessageDialog(this, "✅ Report saved to:\n" + fileToSave.getAbsolutePath());
        		 } catch (Exception ex) {
        			 ex.printStackTrace();
        			 JOptionPane.showMessageDialog(this, "❌ Failed to export report: " + ex.getMessage());
        		 }
        	 }
       	});

        
        goBack.addActionListener( e -> {
        		mainCard.show(mainPanel, "dashboard");
        	});
        	loadItemsToTable();
        	setVisible(true);
    	}
    
    	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    	// METHODS USE IN THE CLASS ---------------------------------------------------------
    	public void loadItemsToTable() {
    		try {
    			DatabaseManager dbManager = new DatabaseManager();
    			ResultSet resultSet = dbManager.getItems();
            
    			model.setRowCount(0);

    			// Iterate through the result set and add data to the table model
    			while (resultSet.next()) {
    				int stock_id = resultSet.getInt("id");
    				String name = resultSet.getString("name");
    				int quantity = resultSet.getInt("quantity");
    				int price = resultSet.getInt("price");
    				String description = resultSet.getString("description");
    				
    				int totalAmount = quantity * price;
    				
    				// Add a row to the table model
    				model.addRow(new Object[]{stock_id,name, quantity, price, totalAmount , description});
    				
    				if (quantity == 0) {
    	                JOptionPane.showMessageDialog(this, "❌ '" + name + "' is OUT OF STOCK!", "Out of Stock", JOptionPane.WARNING_MESSAGE);
    	            } else if (quantity <= 5) {
    	                JOptionPane.showMessageDialog(this, "⚠️ '" + name + "' is running low (Only " + quantity + " left).", "Low Stock Warning", JOptionPane.WARNING_MESSAGE);
    	            }
    			}
            
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	public void clearFields(int opt) {
    		switch(opt) {
    		case 1:
    			nameField.setText("");
    			quantityField.setText("");
    			priceField.setText("");
    			descriptionField.setText("");
    			break;
    		case 2: 
    			DeleteIDField.setText("");
    			break;
    		case 3:
    			updateIDField.setText("");
    			updateFieldCombo.setSelectedIndex(0);
    			updateValueField.setText("");
    			break;
    		}
    	}
    	public void centerTableCells(JTable table) {
    		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    		for (int i = 0; i < table.getColumnCount(); i++) {
    			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    		}
    	} 	
    	public void exportTableToCSV(JTable table, String filePath) throws Exception {
    	    try (FileWriter csv = new FileWriter(filePath)) {
    	    	
    	        for (int i = 0; i < table.getColumnCount(); i++) {
    	            csv.write(table.getColumnName(i));
    	            if (i < table.getColumnCount() - 1) csv.write(",");
    	        }
    	        csv.write("\n");

    	        for (int row = 0; row < table.getRowCount(); row++) {
    	            for (int col = 0; col < table.getColumnCount(); col++) {
    	                Object value = table.getValueAt(row, col);
    	                csv.write(value != null ? value.toString() : "");
    	                if (col < table.getColumnCount() - 1) csv.write(",");
    	            }
    	            csv.write("\n");
    	        }
    	    }
    	}

}
