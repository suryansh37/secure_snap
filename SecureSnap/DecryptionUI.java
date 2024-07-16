package SecureSnap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DecryptionUI extends JFrame {

    private JTextField filePathField;

    public DecryptionUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Image Decryption");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JLabel instructionLabel = new JLabel("Select the encrypted image file:");
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

        JButton decryptButton = new JButton("Decrypt Image");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptImage();
            }
        });

        mainPanel.add(instructionLabel);
        mainPanel.add(filePanel);
        mainPanel.add(decryptButton);

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

    private void decryptImage() {
        String filePath = filePathField.getText().trim();
        if (!filePath.isEmpty()) {
            try {
                System.out.println("Decrypting image from path: " + filePath);
                Decryption1.decryptimage();
                JOptionPane.showMessageDialog(this, "Image decryption completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error decrypting image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select the encrypted image file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void startDecryptionUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DecryptionUI().setVisible(true);
            }
        });
    }
}
