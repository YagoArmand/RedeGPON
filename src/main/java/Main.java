package main.java;

import javax.swing.SwingUtilities;
import main.java.view.InterfacePON;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfacePON();
        });
    }
}

