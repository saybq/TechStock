package IMS;
import java.sql.*;
public class DatabaseManager {
	 private static final String URL = "jdbc:mysql://localhost:3306/inventoryDB";
	    private static final String USER = "root";
	    private static final String PASSWORD = "************";

	    private Connection conn;

	    public DatabaseManager() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("✅ Connected to MySQL.");
	        } catch (Exception e) {
	            System.out.println("❌ Failed to connect to database.");
	            e.printStackTrace();
	        }
	    }
	    public Connection getConnection() {
	        return conn;
	    }
	    
	    public ResultSet getItems() {
	        String sql = "SELECT * FROM items";
	        try {
	            Statement stmt = conn.createStatement();
	            return stmt.executeQuery(sql);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public ResultSet searchItems(String keyword) throws SQLException {
	        String sql = "SELECT * FROM items WHERE name LIKE ? OR id LIKE ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        String likePattern = "%" + keyword + "%";
	        stmt.setString(1, likePattern);
	        stmt.setString(2, likePattern);
	        return stmt.executeQuery();
	    }
	    
	    
}
