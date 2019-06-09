import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PopGUI {
    private JPanel mainPanel;
    private JPanel panelTop;
    private JTextArea txtUsername;
    private JPasswordField passwordField;
    private JTextArea txtUrl;
    private JButton btnLogin;
    private JLabel server;
    private JPanel panelCenter;
    private JPanel panelBottom;
    private JPanel panelCenterMid;
    private JPanel panelCenterTop;
    private JButton statButton;
    private JButton listButton;
    private JButton retrButton;
    private JButton deleButton;
    private JButton quitButton;
    private JTextArea txtInput;
    private JLabel lblInput;
    private JButton clearButton;
    private JScrollPane jsp;
    private JTextArea txtOutput;
    private JButton uidlButton;
    private JButton rsetButton;

    private static POPClient pop;
    public int port = 110;
    public int num;


    public static void main(String[] args) {
        JFrame frame = new JFrame("PopGUI");
        frame.setContentPane(new PopGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public PopGUI() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = txtUrl.getText();
                String username = txtUsername.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (host.length() == 0) {
                    JOptionPane.showMessageDialog(null, "ServerURL cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (username.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Username cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (password.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    pop = new POPClient(host, port);
                    txtOutput.append(pop.getAnswer() + "\r\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    pop.login(username, password);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        statButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pop.sendCommand("STAT");
                txtOutput.append(pop.getAnswer() + "\r\n");
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    txtOutput.append("Please input the number of the mail you want to check into the blank above" + "\r\n");
                } else {
                    num = Integer.valueOf(txtInput.getText());
                    pop.sendCommand("LIST " + num);
                    txtOutput.append(pop.getAnswer() + "\r\n");
                    txtInput.setText("");
                }
            }
        });

        retrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    txtOutput.append("Please input the number of the mail you want to check into the blank above" + "\r\n");
                } else {
                    num = Integer.valueOf(txtInput.getText());
                    pop.sendCommand("RETR " + num);
                    String answer = pop.getAnswer();
                    if (answer.startsWith("+OK")) {
                        txtOutput.append(answer + "\r\n");
                        txtOutput.append(pop.getContent());
                    }
                    txtInput.setText("");
                }
            }
        });

        deleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    txtOutput.append("Please input the number of the mail you want to delete into the blank above" + "\r\n");
                } else {
                    num = Integer.valueOf(txtInput.getText());
                    pop.sendCommand("DELE " + num);
                    txtOutput.append(pop.getAnswer() + "\r\n");
                }
            }
        });

        rsetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pop.sendCommand("RSET");
                txtOutput.append(pop.getAnswer() + "\r\n");
            }
        });

        uidlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    txtOutput.append("Please input the number of the mail you want to query the id into the blank above" + "\r\n");
                }else {
                    num = Integer.valueOf(txtInput.getText());
                    pop.sendCommand("UIDL " + num);
                    txtOutput.append(pop.getAnswer() + "\r\n");
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pop.sendCommand("QUIT");
                txtOutput.append(pop.getAnswer() + "\r\n");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.setText("");
                txtOutput.setText("");
            }
        });
    }
}

