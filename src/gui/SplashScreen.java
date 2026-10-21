package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class SplashScreen extends JFrame {
    
    private JProgressBar progressBar;
    private JLabel lblPercentage, lblMessage, lblImage;
    private Timer progressTimer;
    private int progressValue = 0;
    
    public SplashScreen() {
        initComponents();
        startLoading();
    }
    
    private void initComponents() {
        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        
  
        setShape(new RoundRectangle2D.Double(0, 0, 650, 500, 30, 30));
        

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(20, 25, 45), 
                                                      0, h, new Color(5, 10, 25));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
      
        JPanel imageContainer = new JPanel(new GridBagLayout());
        imageContainer.setBackground(new Color(20, 25, 45));
        imageContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        
  
        loadNewImage();
        
        imageContainer.add(lblImage);
        mainPanel.add(imageContainer, BorderLayout.CENTER);
        
    
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(20, 25, 45));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));
        
     
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setBackground(new Color(50, 55, 75));
        progressBar.setForeground(new Color(0, 200, 255));
        progressBar.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 200), 1));
        progressBar.setPreferredSize(new Dimension(570, 25));
        
        
        lblPercentage = new JLabel("0%");
        lblPercentage.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblPercentage.setForeground(new Color(0, 200, 255));
        lblPercentage.setHorizontalAlignment(SwingConstants.CENTER);
       
        lblMessage = new JLabel("✦ Welcome to Habesha Hotel Management System ✦");
        lblMessage.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMessage.setForeground(Color.LIGHT_GRAY);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel percentPanel = new JPanel(new FlowLayout());
        percentPanel.setBackground(new Color(20, 25, 45));
        percentPanel.add(lblPercentage);
        
        bottomPanel.add(progressBar, BorderLayout.NORTH);
        bottomPanel.add(percentPanel, BorderLayout.CENTER);
        bottomPanel.add(lblMessage, BorderLayout.SOUTH);
        
      
        JPanel loadingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loadingPanel.setBackground(new Color(20, 25, 45));
        JLabel loadingDots = new JLabel("Loading");
        loadingDots.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        loadingDots.setForeground(new Color(150, 150, 180));
        loadingPanel.add(loadingDots);
        bottomPanel.add(loadingPanel, BorderLayout.AFTER_LAST_LINE);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    
        Timer dotsTimer = new Timer(500, e -> {
            String text = loadingDots.getText();
            if (text.equals("Loading...")) {
                loadingDots.setText("Loading");
            } else {
                loadingDots.setText(text + ".");
            }
        });
        dotsTimer.start();
    }
    
    private void loadNewImage() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    
                    String imageUrl = "https://images.unsplash.com/photo-1652348716053-3447e551dd1f?w=600&auto=format&fit=crop&q=80";
                    URL url = new URL(imageUrl);
                    ImageIcon originalIcon = new ImageIcon(url);
                    Image originalImage = originalIcon.getImage();
                   
 
                    int targetWidth = 550;
                    int targetHeight = 280;
                    Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(scaledImage));
                    
                } catch (Exception e) {
                    e.printStackTrace();
                   
                    lblImage.setText("🏨 HABESHA HOTEL");
                    lblImage.setFont(new Font("Segoe UI", Font.BOLD, 28));
                    lblImage.setForeground(Color.WHITE);
                }
                return null;
            }
        };
        worker.execute();
    }
    
    private void startLoading() {
        String[] messages = {
            "🚀 ሲስተሙን በማስነሳት ላይ...",
            "💾 ዳታቤዝን በማገናኘት ላይ...",
            "👥 የተጠቃሚ መረጃዎችን በማስጀመር ላይ...",
            "🛏️ ክፍሎችን በማዘጋጀት ላይ...",
            "🔧 አገልግሎቶችን በማዘጋጀት ላይ...",
            "✨ እባክዎ ይጠብቁ ሊከፈት ነው..."
        };
        
        progressTimer = new Timer(60, e -> {
            progressValue++;
            progressBar.setValue(progressValue);
            lblPercentage.setText(progressValue + "%");
            
         
            int messageIndex = Math.min(progressValue / 17, messages.length - 1);
            lblMessage.setText(messages[messageIndex]);
       
            if (progressValue < 30) {
                progressBar.setForeground(new Color(255, 100, 100));
            } else if (progressValue < 70) {
                progressBar.setForeground(new Color(255, 200, 50));
            } else {
                progressBar.setForeground(new Color(0, 255, 100));
            }
            
            if (progressValue >= 100) {
                progressTimer.stop();
                openLoginFrame();
            }
        });
        progressTimer.start();
    }
    
    private void openLoginFrame() {
    
        Timer shrinkTimer = new Timer(15, e -> {
            int width = getWidth();
            int height = getHeight();
            if (width > 100 && height > 80) {
                setSize(width - 15, height - 10);
                setLocationRelativeTo(null);
            } else {
                ((Timer)e.getSource()).stop();
                SwingUtilities.invokeLater(() -> {
                    new LoginFrame().setVisible(true);
                    dispose();
                });
            }
        });
        shrinkTimer.start();
    }
}