import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class VoterFrame extends JFrame {
    JComboBox<String> candidatesDropdown;
    JButton voteButton;
    int voterId;

    public VoterFrame(int voterId) {
        this.voterId = voterId;

        setTitle("Voter Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 255));

        // Select Candidate Label
        JLabel selectLabel = new JLabel("Select Candidate:");
        selectLabel.setBounds(20, 50, 120, 25);
        panel.add(selectLabel);

        // Candidate Dropdown
        candidatesDropdown = new JComboBox<>();
        loadCandidates();
        candidatesDropdown.setBounds(150, 50, 200, 25); // Adjusted size
        panel.add(candidatesDropdown);

        // Vote Button
        voteButton = new JButton("Vote");
        voteButton.setBounds(150, 100, 100, 30); // Adjusted button size
        voteButton.setFont(new Font("Arial", Font.PLAIN, 16));
        voteButton.setBackground(new Color(70, 130, 180));
        voteButton.setForeground(Color.WHITE);
        voteButton.setFocusPainted(false);
        voteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        voteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                castVote();
            }
        });
        panel.add(voteButton);

        add(panel);
        setVisible(true);
    }

    private void loadCandidates() {
        try (Connection conn = DBConnection.connect()) {
            String query = "SELECT id, name FROM candidates";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String candidateInfo = rs.getInt("id") + ": " + rs.getString("name");
                candidatesDropdown.addItem(candidateInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void castVote() {
        String selectedCandidate = (String) candidatesDropdown.getSelectedItem();
        int candidateId = Integer.parseInt(selectedCandidate.split(":")[0]);

        try (Connection conn = DBConnection.connect()) {
            // Check if the voter has already voted
            String checkQuery = "SELECT * FROM votes WHERE voter_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, voterId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "You have already voted!");
                return;
            }

            // Insert the vote
            String insertQuery = "INSERT INTO votes (voter_id, candidate_id) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, voterId);
            insertStmt.setInt(2, candidateId);
            insertStmt.executeUpdate();

            // Update candidate's vote count
            String updateQuery = "UPDATE candidates SET votes = votes + 1 WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, candidateId);
            updateStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Vote successfully cast!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
