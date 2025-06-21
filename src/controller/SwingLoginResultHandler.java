package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import service.result.LoginResult;
import ui.Dashboard;

public class SwingLoginResultHandler implements LoginResultHandler {
    private final JFrame frame;

    public SwingLoginResultHandler(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void handle(LoginResult result) {
        if (result.isSuccess()) {
            new Dashboard().setVisible(true);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, result.getMessage());
        }
    }
}
