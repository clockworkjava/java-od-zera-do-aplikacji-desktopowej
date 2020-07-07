package pl.clockworkjava;

import pl.clockworkjava.ui.text.TextUI;
import pl.clockworkjava.util.Properties;

import java.io.IOException;

public class App {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textUI.showSystemInfo();
        textUI.showMainMenu();
    }
}
