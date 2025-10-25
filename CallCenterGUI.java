import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class CallCenterGUI extends JFrame {

    // --- Core Data Structure ---
    private Queue<Call> callQueue;

    // --- GUI Components ---
    private JTextArea queueDisplayArea;
    private JTextField nameField;
    private JTextField issueField;
    private JButton addButton;
    private JButton processButton;
    private JButton clearQueueButton;
    private JLabel queueSizeLabel;

    public CallCenterGUI() {
        // --- 1. Set up the main window (JFrame) ---
        super("Modern Call Center Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Slightly larger
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(15, 15)); // Increased gaps

        // --- Important: Set FlatLaf Look and Feel BEFORE creating components ---
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf Look and Feel: " + ex);
        }

        // --- 2. Initialize the Queue ---
        callQueue = new LinkedList<>();

        // --- Global Font for a consistent look ---
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 14); // A modern, clean font
        UIManager.put("Label.font", defaultFont);
        UIManager.put("Button.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("TextArea.font", new Font("Monospaced", Font.PLAIN, 12)); // Monospaced for display area
        UIManager.put("TitledBorder.font", defaultFont.deriveFont(Font.BOLD, 16));

        // --- 3. Create the Input Panel (North) ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY.brighter()), // Lighter border for light theme
                "Add New Call", TitledBorder.LEFT, TitledBorder.TOP,
                UIManager.getFont("TitledBorder.font"),
                UIManager.getColor("Label.foreground")
        ));
        inputPanel.setBackground(UIManager.getColor("Panel.background"));
        inputPanel.setPreferredSize(new Dimension(getWidth(), 150));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding for components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Caller Name
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("Caller Name:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1;
        nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);

        // Issue
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("Issue:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1;
        issueField = new JTextField(20);
        inputPanel.add(issueField, gbc);

        // Add Button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Span two columns
        gbc.weightx = 1;

        addButton = new JButton("Add Call to Queue");

        addButton.setBackground(new Color(60, 140, 250));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        inputPanel.add(addButton, gbc);

        // --- 4. Create the Queue Display (Center) ---
        queueDisplayArea = new JTextArea("Initializing call center...");
        queueDisplayArea.setEditable(false);
        queueDisplayArea.setMargin(new Insets(15, 15, 15, 15));
        queueDisplayArea.setBackground(UIManager.getColor("Panel.background"));
        queueDisplayArea.setForeground(UIManager.getColor("Label.foreground"));
        JScrollPane scrollPane = new JScrollPane(queueDisplayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY.brighter()), // Lighter border
                "Current Call Queue", TitledBorder.LEFT, TitledBorder.TOP,
                UIManager.getFont("TitledBorder.font"),
                UIManager.getColor("Label.foreground")
        ));

        // --- 5. Create the Action Panel (South) ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(UIManager.getColor("Panel.background"));

        queueSizeLabel = new JLabel("Calls in Queue: 0");
        queueSizeLabel.setFont(defaultFont.deriveFont(Font.BOLD));
        actionPanel.add(queueSizeLabel);

        processButton = new JButton("Process Next Call");

        processButton.setBackground(new Color(40, 170, 100));
        processButton.setForeground(Color.WHITE);
        processButton.setFocusPainted(false);
        actionPanel.add(processButton);

        clearQueueButton = new JButton("Clear All Calls");

        clearQueueButton.setBackground(new Color(220, 80, 80));
        clearQueueButton.setForeground(Color.WHITE);
        clearQueueButton.setFocusPainted(false);
        actionPanel.add(clearQueueButton);


        // --- 6. Add all panels to the main window ---
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Add padding around the entire content
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // --- 7. Add Logic (Action Listeners) ---
        addButton.addActionListener(e -> { // Using lambda for cleaner code
            String name = nameField.getText().trim();
            String issue = issueField.getText().trim();

            if (name.isEmpty() || issue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both a name and an issue.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                callQueue.offer(new Call(name, issue));
                nameField.setText("");
                issueField.setText("");
                updateQueueDisplay();
            }
        });

        processButton.addActionListener(e -> {
            if (callQueue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No calls in queue to process!", "Queue Empty", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Call processedCall = callQueue.poll();
                JOptionPane.showMessageDialog(this, "Call Processed:\n\n" + processedCall.toString(), "Call Completed", JOptionPane.INFORMATION_MESSAGE);
                updateQueueDisplay();
            }
        });

        clearQueueButton.addActionListener(e -> {
            if (callQueue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The queue is already empty!", "Queue Empty", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to clear ALL " + callQueue.size() + " calls from the queue?",
                    "Confirm Clear Queue", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                callQueue.clear();
                updateQueueDisplay();
                JOptionPane.showMessageDialog(this, "All calls have been cleared from the queue.", "Queue Cleared", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Add demo data and initial display update
        loadDemoData();
        updateQueueDisplay();
    }

    /**
     * Helper method to refresh the JTextArea with the current queue contents.
     */
    private void updateQueueDisplay() {
        if (callQueue.isEmpty()) {
            queueDisplayArea.setText("The call queue is currently empty.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("--- Current Call Queue (Front to Back) ---\n\n");
            int position = 1;
            for (Call call : callQueue) {
                sb.append(String.format("%-4d. %s\n", position, call.toString())); // Formatted output
                position++;
            }
            queueDisplayArea.setText(sb.toString());
        }
        queueSizeLabel.setText("Calls in Queue: " + callQueue.size());
    }

    /**
     * Adds a few pre-defined calls to the queue for demonstration.
     */
    private void loadDemoData() {
        callQueue.offer(new Call("Alice Johnson", "Internet speed issues"));
        callQueue.offer(new Call("Bob Williams", "Cannot log into account"));
        callQueue.offer(new Call("Charlie Brown", "Billing discrepancy on last statement"));
        callQueue.offer(new Call("Diana Prince", "Technical support for new device setup"));
    }

    // --- Main method to run the GUI ---
    public static void main(String[] args) {
        // We run the GUI on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            new CallCenterGUI().setVisible(true);
        });
    }
}