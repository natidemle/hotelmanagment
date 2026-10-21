package hotelmanagment;

import gui.SplashScreen;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class HotelManagment {
    public static void main(String[] args) {
     
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        SwingUtilities.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }
}