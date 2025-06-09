package IMS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventory {
	private String name;
    private String description;
    private int quantity;
    private int price;
    
    private DatabaseManager dbManager;
    
    public Inventory(String name, String description, int quantity, int price ) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.dbManager = new DatabaseManager();
    }
    public void addItem() {
        String sql = "INSERT INTO items (name, description, quantity, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public boolean removeItem(int id) {
    	String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // true if deleted, false if not found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void updateItem(int id, String field, String newValue) {
    	 String sql = "UPDATE items SET " + field + " = ? WHERE id = ?";
    	    try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(sql)) {
    	        if (field.equalsIgnoreCase("quantity") || field.equalsIgnoreCase("price")) {
    	            stmt.setInt(1, Integer.parseInt(newValue));
    	        } else {
    	            stmt.setString(1, newValue);
    	        }
    	        stmt.setInt(2, id);
    	        stmt.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    }
}
