package IMS;

public class Main {
	public static void main(String[] args) {	
		System.setProperty("sun.java2d.uiScale", "0.9");
		
		javax.swing.SwingUtilities.invokeLater(() -> {
			new LaunchFrame();
        });	
		
    }
}
