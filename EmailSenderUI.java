import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailSenderUI extends JFrame {

    private JTextField toField;
    private JTextField subjectField;
    private JTextArea messageArea;
    private JButton sendButton;

    public EmailSenderUI() {
        initUI();
    }

    private void initUI() {
        // Set up UI components
        toField = new JTextField();
        subjectField = new JTextField();
        messageArea = new JTextArea();
        sendButton = new JButton("Send Email");

        // Configure layout
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("To:"));
        inputPanel.add(toField);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectField);
        inputPanel.add(new JLabel("Message:"));
        inputPanel.add(new JScrollPane(messageArea));
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(sendButton);

        // Add action listener to the Send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail();
            }
        });

        // Add components to the frame
        add(inputPanel, BorderLayout.CENTER);

        // Configure frame properties
        setTitle("Email Sender");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void sendEmail() {
        // Get values from UI components
        String to = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        // Attach encrypted image file
        // byte[] encryptedImageData = // ... (Get encrypted image data)
        // EmailSender.sendEmail(to, subject, message, encryptedImageData, "encrypted-image.dat");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmailSenderUI();
            }
        });
    }
}

