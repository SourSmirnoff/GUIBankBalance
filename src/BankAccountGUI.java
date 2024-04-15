import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountGUI extends JFrame {
    private JTextField balanceField, amountField;
    private JButton depositButton, withdrawButton;
    private BankAccount account;

    public BankAccountGUI() {
        account = new BankAccount(0); // Start with a zero balance
        createUI();
    }

    private void createUI() {
        setTitle("Bank Account Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Layout setup
        setLayout(new BorderLayout());

        // North panel for balance display
        JPanel balancePanel = new JPanel();
        balanceField = new JTextField(10);
        balanceField.setEditable(false);
        balanceField.setText(String.format("Balance: $%.2f", account.getBalance()));
        balancePanel.add(balanceField);

        // Center panel for input and buttons
        JPanel actionPanel = new JPanel();
        amountField = new JTextField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        actionPanel.add(new JLabel("Amount: "));
        actionPanel.add(amountField);
        actionPanel.add(depositButton);
        actionPanel.add(withdrawButton);

        // Add action listeners
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                updateBalanceDisplay();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.withdrawal(amount);
                updateBalanceDisplay();
            }
        });

        // Add panels to frame
        add(balancePanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
    }

    private void updateBalanceDisplay() {
        balanceField.setText(String.format("Balance: $%.2f", account.getBalance()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankAccountGUI().setVisible(true);
            }
        });
    }
}