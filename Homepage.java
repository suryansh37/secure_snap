import SecureSnap.EncryptionUI;
import SecureSnap.DecryptionUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

    public Homepage() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("SecureSnap");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Set background color
    //     ImageIcon logo = null;
    //     try {
    //         logo = new ImageIcon("C:\\Users\\EVERVITAL\\Desktop\\minipro\\logo.png");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    // // Handle the exception (e.g., log, display an error message)
    //     }

        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome to SecureSnap");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // Add padding

        // Application Name
        JLabel appNameLabel = new JLabel("Image Encryption/Decryption");
        appNameLabel.setHorizontalAlignment(JLabel.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        appNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Add padding

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Encryption Button
        JButton encryptionButton = createStyledButton("Encryption", "icons/encryption.png");
        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEncryptionPage();
            }
        });

        // Decryption Button
        JButton decryptionButton = createStyledButton("Decryption", "icons/decryption.png");
        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDecryptionPage();
            }
        });

        buttonPanel.add(encryptionButton);
        buttonPanel.add(decryptionButton);

        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(appNameLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(50, 150, 255));
        button.setForeground(Color.WHITE);

        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(iconPath);
            button.setIcon(icon);
        }

        return button;
    }

    private void openEncryptionPage() {
        System.out.println("Opening Encryption Page");
        EncryptionUI encryptionUI = new EncryptionUI();
        encryptionUI.startEncryptionUI();
    }

    private void openDecryptionPage() {
        System.out.println("Opening Decryption Page");
        DecryptionUI decryptionUI = new DecryptionUI();
        decryptionUI.startDecryptionUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }
}