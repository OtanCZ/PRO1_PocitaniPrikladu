package otan.pp;

import javax.swing.*;

public class Main {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 800;
    private static PPWindow ppWindow;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
                    ppWindow = new PPWindow(WIDTH, HEIGHT);
                }
        );
    }
}