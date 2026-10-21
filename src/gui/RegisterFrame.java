package gui;

import dao.UserDAO;
import model.User;
import utils.PasswordUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class RegisterFrame extends JFrame {
    
    private JTextField txtFullName, txtUsername, txtEmail, txtPhone, txtAddress;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegister, btnCancel;
    private JLabel lblTitle;
    private Timer fadeTimer;
    private float alpha = 0f;
    
    public RegisterFrame() {
        initComponents();
        startEntranceAnimation();
    }
    
    private void initComponents() {
        setTitle("Hotel Management - New User Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 580);  
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        
       
        setShape(new RoundRectangle2D.Double(0, 0, 500, 580, 25, 25));
 
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 60, 90), 
                                                      0, getHeight(), new Color(15, 35, 60));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
 
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        
        lblTitle = new JLabel("CREATE NEW ACCOUNT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel subTitle = new JLabel("Please fill in the information below");
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subTitle.setForeground(new Color(200, 200, 200));
        subTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.add(lblTitle);
        titlePanel.add(subTitle);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name *");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        nameLabel.setForeground(Color.WHITE);
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        txtFullName = new JTextField(15);
        styleTextField(txtFullName);
        formPanel.add(txtFullName, gbc);
        
      
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel userLabel = new JLabel("Username *");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        userLabel.setForeground(Color.WHITE);
        formPanel.add(userLabel, gbc);
        
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        styleTextField(txtUsername);
        formPanel.add(txtUsername, gbc);
        
     
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Email *");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        emailLabel.setForeground(Color.WHITE);
        formPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        styleTextField(txtEmail);
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        phoneLabel.setForeground(Color.WHITE);
        formPanel.add(phoneLabel, gbc);
        
        gbc.gridx = 1;
        txtPhone = new JTextField(15);
        styleTextField(txtPhone);
        formPanel.add(txtPhone, gbc);
        
        
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        addressLabel.setForeground(Color.WHITE);
        formPanel.add(addressLabel, gbc);
        
        gbc.gridx = 1;
        txtAddress = new JTextField(15);
        styleTextField(txtAddress);
        formPanel.add(txtAddress, gbc);
        
     
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel passLabel = new JLabel("Password *");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        passLabel.setForeground(Color.WHITE);
        formPanel.add(passLabel, gbc);
        
        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        stylePasswordField(txtPassword);
        formPanel.add(txtPassword, gbc);
        
        // Confirm Password
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel confirmLabel = new JLabel("Confirm Password *");
        confirmLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        confirmLabel.setForeground(Color.WHITE);
        formPanel.add(confirmLabel, gbc);
        
        gbc.gridx = 1;
        txtConfirmPassword = new JPasswordField(15);
        stylePasswordField(txtConfirmPassword);
        formPanel.add(txtConfirmPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        JLabel noteLabel = new JLabel("* Required fields | Default Role: Receptionist");
        noteLabel.setFont(new Font("Segoe UI", Font.ITALIC, 9));
        noteLabel.setForeground(new Color(255, 200, 100));
        formPanel.add(noteLabel, gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        btnRegister = new JButton("REGISTER");
        btnCancel = new JButton("CANCEL");
        
        styleButton(btnRegister, new Color(0, 150, 100), new Color(0, 180, 120));
        styleButton(btnCancel, new Color(150, 60, 60), new Color(180, 80, 80));
        
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
     
        btnRegister.addActionListener(e -> registerUser());
        btnCancel.addActionListener(e -> playExitAnimation());
    }
    
    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBackground(new Color(255, 255, 255, 240));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setPreferredSize(new Dimension(200, 35)); 
    }
    
    private void stylePasswordField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBackground(new Color(255, 255, 255, 240));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setPreferredSize(new Dimension(200, 35));
    }
    
    private void styleButton(JButton button, Color bgColor, Color hoverColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(110, 38)); 
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
    
    private void startEntranceAnimation() {
        setOpacity(0f);
        fadeTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    setOpacity(alpha);
                    fadeTimer.stop();
                }
                setOpacity(alpha);
            }
        });
        fadeTimer.start();
    }
    
    private void playExitAnimation() {
        Timer exitTimer = new Timer(20, new ActionListener() {
            float exitAlpha = 1.0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAlpha -= 0.05f;
                if (exitAlpha <= 0) {
                    exitAlpha = 0;
                    ((Timer)e.getSource()).stop();
                    dispose();
                }
                setOpacity(exitAlpha);
            }
        });
        exitTimer.start();
    }
    
    private void registerUser() {
        String fullName = txtFullName.getText().trim();
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        

        if (fullName.isEmpty()) {
            showError("Please enter your full name!", txtFullName);
            return;
        }
        
        if (username.isEmpty()) {
            showError("Please enter username!", txtUsername);
            return;
        }
        
        if (email.isEmpty()) {
            showError("Please enter email!", txtEmail);
            return;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            showError("Please enter a valid email address!", txtEmail);
            return;
        }
        
        if (password.isEmpty()) {
            showError("Please enter password!", txtPassword);
            return;
        }
        
        if (password.length() < 4) {
            showError("Password must be at least 4 characters!", txtPassword);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match!", txtConfirmPassword);
            return;
        }
        
        btnRegister.setEnabled(false);
        btnRegister.setText("CREATING...");
        
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            private String errorMsg = null;
            
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    UserDAO dao = new UserDAO();
                    
                    if (!dao.isUsernameAvailable(username)) {
                        errorMsg = "Username already exists!";
                        return false;
                    }
                    
                    if (!dao.isEmailAvailable(email)) {
                        errorMsg = "Email already registered!";
                        return false;
                    }
                    
                    String hashedPassword = PasswordUtil.hashPassword(password);
                    
                    User user = new User();
                    user.setFullName(fullName);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(hashedPassword);
                    user.setPhone(phone);
                    user.setAddress(address);
                    user.setRole("Receptionist");
                    
                    return dao.registerUser(user);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    errorMsg = "Database error: " + ex.getMessage();
                    return false;
                }
            }
            
            @Override
            protected void done() {
                btnRegister.setEnabled(true);
                btnRegister.setText("REGISTER");
                
                try {
                    boolean success = get();
                    if (success) {
                        JOptionPane.showMessageDialog(RegisterFrame.this, 
                            "✅ Registration Successful!\n\nWelcome " + fullName + "!\nYou can now login.", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        playExitAnimation();
                    } else {
                        showError(errorMsg != null ? errorMsg : "Registration failed!", null);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showError("An error occurred!", null);
                }
            }
        };
        worker.execute();
    }
    
    private void showError(String message, JComponent focusComponent) {
        JOptionPane.showMessageDialog(this, 
            "❌ " + message, 
            "Registration Error", 
            JOptionPane.ERROR_MESSAGE);
        if (focusComponent != null) {
            focusComponent.requestFocus();
        }
    }
}