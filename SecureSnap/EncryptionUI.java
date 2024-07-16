package SecureSnap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class EncryptionUI extends JFrame {

    
    private JTextField filePathField;

    public EncryptionUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Image Encryption");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JLabel instructionLabel = new JLabel("Select the image file for encryption:");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());

        filePathField = new JTextField();
        filePathField.setEditable(false);
        filePanel.add(filePathField, BorderLayout.CENTER);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });
        filePanel.add(browseButton, BorderLayout.EAST);

        JButton encryptButton = new JButton("Encrypt Image");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptImage();
            }
        });

        mainPanel.add(instructionLabel);
        mainPanel.add(filePanel);
        mainPanel.add(encryptButton);

        add(mainPanel);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void encryptImage() {
        String filePath = filePathField.getText().trim();
        if (!filePath.isEmpty()) {
            try {
                // Call the encryption logic from Encryption1 class
                Encryption1.encryptImage();
                // Display a success message (you can customize this part)
                JOptionPane.showMessageDialog(this, "Image encryption completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error encrypting image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select the image file for encryption.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void startEncryptionUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EncryptionUI().setVisible(true);
            }
        });
    }
}
