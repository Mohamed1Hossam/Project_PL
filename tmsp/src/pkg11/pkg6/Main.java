/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11.pkg6;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 *
 * @author hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
                SwingUtilities.invokeLater(() -> {
          //  new gui().setVisible(true);
            //new NewJFrame().setVisible(true);
           // new NewJFrame2().setVisible(true);
            new login().setVisible(true);
        });
    }
    
}
