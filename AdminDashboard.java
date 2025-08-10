import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {
    JButton adminFrameButton, resultsFrameButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 255));

        // Admin Frame Button
        adminFrameButton = new JButton("Admin Dashboard");
        adminFrameButton.setBounds(100, 50, 200, 40); // Adjusted button size
        adminFrameButton.setFont(new Font("Arial", Font.PLAIN, 16));
        adminFrameButton.setBackground(new Color(70, 130, 180));
        adminFrameButton.setForeground(Color.WHITE);
        adminFrameButton.setFocusPainted(false);
        adminFrameButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        adminFrameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAdminFrame(); // Redirect to AdminFrame
            }
        });
        panel.add(adminFrameButton);

        // Results Frame Button
        resultsFrameButton = new JButton("View Results");
        resultsFrameButton.setBounds(100, 120, 200, 40); // Adjusted button size
        resultsFrameButton.setFont(new Font("Arial", Font.PLAIN, 16));
        resultsFrameButton.setBackground(new Color(70, 130, 180));
        resultsFrameButton.setForeground(Color.WHITE);
        resultsFrameButton.setFocusPainted(false);
        resultsFrameButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        resultsFrameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openResultsFrame(); // Redirect to ResultsFrame
            }
        });
        panel.add(resultsFrameButton);

        add(panel);
        setVisible(true);
    }

    // Open AdminFrame page
    private void openAdminFrame() {
        new AdminFrame();
        dispose(); // Close current dashboard
    }

    // Open ResultsFrame page
    private void openResultsFrame() {
        new ResultsFrame();
        dispose(); // Close current dashboard
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
