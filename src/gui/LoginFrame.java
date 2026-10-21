package gui;

import dao.UserDAO;
import model.User;
import utils.PasswordUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class LoginFrame extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private JLabel imageLabel, titleLabel;
    
    public LoginFrame() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Hotel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(Color.WHITE);
        
      
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
             
                GradientPaint gp = new GradientPaint(0, 0, new Color(20, 50, 80), 
                                                      0, getHeight(), new Color(10, 30, 60));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
   
        JPanel topTextPanel = new JPanel(new GridLayout(2, 1));
        topTextPanel.setOpaque(false);
        topTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel hotelNameLabel = new JLabel("HABESHA HOTEL", SwingConstants.CENTER);
        hotelNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        hotelNameLabel.setForeground(new Color(255, 215, 0)); // Golden color
        
        JLabel subLabel = new JLabel("Luxury & Comfort", SwingConstants.CENTER);
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(new Color(200, 200, 200));
        
        topTextPanel.add(hotelNameLabel);
        topTextPanel.add(subLabel);
        
 
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loadLoginImage();
        
  
        JPanel bottomSpacer = new JPanel();
        bottomSpacer.setOpaque(false);
        bottomSpacer.setPreferredSize(new Dimension(0, 30));

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(topTextPanel, BorderLayout.NORTH);
        centerWrapper.add(imageLabel, BorderLayout.CENTER);
        centerWrapper.add(bottomSpacer, BorderLayout.SOUTH);
        
        leftPanel.add(centerWrapper, BorderLayout.CENTER);
        

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        titleLabel = new JLabel("SIGN IN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(20, 50, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(titleLabel, gbc);
        
  
        JLabel subtitleLabel = new JLabel("Please enter your credentials", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(Color.GRAY);
        gbc.gridy = 1;
        rightPanel.add(subtitleLabel, gbc);
        

        gbc.gridy = 2;
        rightPanel.add(Box.createVerticalStrut(20), gbc);
        
     
        gbc.gridy = 3;
        JLabel userLabel = new JLabel("Username / Email");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        userLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        rightPanel.add(userLabel, gbc);

        gbc.gridy = 4;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        usernameField.setBackground(new Color(250, 250, 250));
        rightPanel.add(usernameField, gbc);
   
        gbc.gridy = 5;
        rightPanel.add(Box.createVerticalStrut(10), gbc);
        
        gbc.gridy = 6;
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        rightPanel.add(passLabel, gbc);
     
        gbc.gridy = 7;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        passwordField.setBackground(new Color(250, 250, 250));
        rightPanel.add(passwordField, gbc);
        
    
        gbc.gridy = 8;
        rightPanel.add(Box.createVerticalStrut(20), gbc);
       
        gbc.gridy = 9;
        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginBtn.setBackground(new Color(20, 50, 80));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setPreferredSize(new Dimension(250, 50));
  
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(30, 70, 100));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(20, 50, 80));
            }
        });
        
        rightPanel.add(loginBtn, gbc);
      
        gbc.gridy = 10;
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerPanel.setBackground(Color.WHITE);
        JLabel noAccountLabel = new JLabel("Don't have an account? ");
        noAccountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        noAccountLabel.setForeground(Color.GRAY);
        
        registerBtn = new JButton("Sign Up");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registerBtn.setBorderPainted(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setForeground(new Color(20, 50, 80));
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerPanel.add(noAccountLabel);
        registerPanel.add(registerBtn);
        rightPanel.add(registerPanel, gbc);
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
        
     
        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
            dispose();
        });
        
   
        getRootPane().setDefaultButton(loginBtn);
    }
    
    private void loadLoginImage() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
              
                    String imageUrl = "https://th.bing.com/th/id/OIP.JN069c-Zd98MOHM_cAvWawHaE8?w=600&h=400&rs=1&pid=ImgDetMain";
                    URL url = new URL(imageUrl);
                    ImageIcon originalIcon = new ImageIcon(url);
                    Image originalImage = originalIcon.getImage();
                    
                    // ምስሉን ማስተካከል (350x350)
                    int targetWidth = 350;
                    int targetHeight = 350;
                    Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                    
                    // Round the image
                    Image roundedImage = makeRoundedImage(scaledImage, targetWidth, targetHeight);
                    imageLabel.setIcon(new ImageIcon(roundedImage));
                    
                } catch (Exception e) {
                    e.printStackTrace();
        
                    imageLabel.setText("🏨");
                    imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
                    imageLabel.setForeground(Color.WHITE);
                }
                return null;
            }
            
            @Override
            protected void done() {
                revalidate();
                repaint();
            }
        };
        worker.execute();
    }
    
    private Image makeRoundedImage(Image image, int width, int height) {
   
        java.awt.image.BufferedImage rounded = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rounded.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Round rectangle shape
        g2d.setClip(new RoundRectangle2D.Double(0, 0, width, height, 60, 60));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return rounded;
    }
    
    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty()) {
            showError("Please enter your username!", usernameField);
            return;
        }
        
        if (password.isEmpty()) {
            showError("Please enter your password!", passwordField);
            return;
        }
        
        if (password.length() < 4) {
            showError("Password must be at least 4 characters!", passwordField);
            return;
        }
        
        // Disable login button during processing
        loginBtn.setEnabled(false);
        loginBtn.setText("LOGGING IN...");
        
        // Use SwingWorker for database operation
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            private User loggedUser = null;
            private String errorMessage = null;
            
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    UserDAO dao = new UserDAO();
                    User user = dao.getUserByUsername(username);
                    
                    if (user == null) {
                        user = dao.getUserByEmail(username);
                    }
                    
                    if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
                        loggedUser = user;
                        return true;
                    } else {
                        errorMessage = "Invalid username or password!";
                        return false;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    errorMessage = "Database error: " + ex.getMessage();
                    return false;
                }
            }
            
            @Override
            protected void done() {
                loginBtn.setEnabled(true);
                loginBtn.setText("LOGIN");
                
                try {
                    boolean success = get();
                    if (success && loggedUser != null) {
                        JOptionPane.showMessageDialog(LoginFrame.this, 
                            "✅ Welcome " + loggedUser.getFullName() + "!\nRole: " + loggedUser.getRole(), 
                            "Login Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                        new MainHotelFrame(loggedUser.getUsername(), loggedUser.getRole()).setVisible(true);
                        dispose();
                    } else {
                        showError(errorMessage != null ? errorMessage : "Login failed!", usernameField);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showError("An error occurred during login!", null);
                }
            }
        };
        worker.execute();
    }
    
    private void showError(String message, JComponent focusComponent) {
        JOptionPane.showMessageDialog(this, 
            "❌ " + message, 
            "Login Error", 
            JOptionPane.ERROR_MESSAGE);
        if (focusComponent != null) {
            focusComponent.requestFocus();
        }
    }
}