import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    JTextField candidateNameField, partyField;
    JButton addButton, viewButton;

    public AdminFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 255));

        // Candidate Name
        JLabel nameLabel = new JLabel("Candidate Name:");
        nameLabel.setBounds(20, 50, 120, 25);
        panel.add(nameLabel);

        candidateNameField = new JTextField(20); // Adjusted width
        candidateNameField.setBounds(150, 50, 160, 25); // Adjusted field size
        candidateNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(candidateNameField);

        // Party Name
        JLabel partyLabel = new JLabel("Party:");
        partyLabel.setBounds(20, 90, 120, 25);
        panel.add(partyLabel);

        partyField = new JTextField(20); // Adjusted width
        partyField.setBounds(150, 90, 160, 25); // Adjusted field size
        partyField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(partyField);

        // Add Candidate Button
        addButton = new JButton("Add Candidate");
        addButton.setBounds(20, 130, 150, 30); // Adjusted button size
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCandidate();
            }
        });
        panel.add(addButton);

        // View Candidates Button
        viewButton = new JButton("View Candidates");
        viewButton.setBounds(200, 130, 200, 30); // Adjusted button size
        viewButton.setFont(new Font("Arial", Font.PLAIN, 16));
        viewButton.setBackground(new Color(70, 130, 180));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFocusPainted(false);
        viewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCandidates();
            }
        });
        panel.add(viewButton);

        add(panel);
        setVisible(true);
    }

    private void addCandidate() {
        String name = candidateNameField.getText();
        String party = partyField.getText();

        try (Connection conn = DBConnection.connect()) {
            String query = "INSERT INTO candidates (name, party) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, party);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Candidate added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewCandidates() {
        new ResultsFrame(); // Open the Results Frame
    }
}
