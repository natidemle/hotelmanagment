package gui;

import dao.RoomDAO;
import model.Room;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainHotelFrame extends JFrame {
    private String username;
    private String role;
    private JTable roomTable;
    private DefaultTableModel model;
    private RoomDAO roomDAO;

    public MainHotelFrame(String username, String role) {
        this.username = username;
        this.role = role;
        this.roomDAO = new RoomDAO();
        setTitle("Hotel Management - Welcome " + username + " (" + role + ")");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
   
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        

        JLabel welcomeLabel = new JLabel("👋 Welcome, " + username + "! - Role: " + role.toUpperCase());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(new Color(0, 102, 204));
       
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel roleBadge = new JLabel(" " + role.toUpperCase() + " ");
        roleBadge.setFont(new Font("Arial", Font.BOLD, 12));
        if (role.equalsIgnoreCase("Admin")) {
            roleBadge.setBackground(new Color(0, 153, 0));
            roleBadge.setForeground(Color.WHITE);
        } else if (role.equalsIgnoreCase("Manager")) {
            roleBadge.setBackground(new Color(255, 153, 0));
            roleBadge.setForeground(Color.WHITE);
        } else {
            roleBadge.setBackground(new Color(0, 102, 204));
            roleBadge.setForeground(Color.WHITE);
        }
        roleBadge.setOpaque(true);
        roleBadge.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton logoutBtn = new JButton("🚪 Logout");
        logoutBtn.addActionListener(e -> logout());
        
        userPanel.add(roleBadge);
        userPanel.add(Box.createHorizontalStrut(10));
        userPanel.add(logoutBtn);
        
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(userPanel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        JPanel imagePanel = new JPanel(new GridLayout(1, 3, 15, 15));
        imagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "🏨 Our Luxury Rooms"));
        imagePanel.setBackground(Color.WHITE);
        String[] roomTypes = {"Standard Room", "Deluxe Room", "Executive Suite"};
        String[] roomPrices = {"1,500 ETB/night", "2,500 ETB/night", "4,500 ETB/night"};
        Color[] colors = {new Color(100, 150, 200), new Color(150, 120, 200), new Color(200, 150, 100)};
        
        for (int i = 0; i < 3; i++) {
            JPanel roomCard = new JPanel();
            roomCard.setLayout(new BorderLayout());
            roomCard.setBackground(colors[i]);
            roomCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
     
            JLabel iconLabel = new JLabel();
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
            
            if (i == 0) {
                iconLabel.setText("🛏️");
            } else if (i == 1) {
                iconLabel.setText("🛋️");
            } else {
                iconLabel.setText("👑");
            }
           
            JLabel typeLabel = new JLabel(roomTypes[i], SwingConstants.CENTER);
            typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
            typeLabel.setForeground(Color.WHITE);
            
            JLabel priceLabel = new JLabel(roomPrices[i], SwingConstants.CENTER);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            priceLabel.setForeground(Color.WHITE);
            
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setOpaque(false);
            infoPanel.add(typeLabel);
            infoPanel.add(priceLabel);
            
            roomCard.add(iconLabel, BorderLayout.CENTER);
            roomCard.add(infoPanel, BorderLayout.SOUTH);
            roomCard.setPreferredSize(new Dimension(180, 120));
            
            imagePanel.add(roomCard);
        }
        
        add(imagePanel, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("📋 Room List & Management"));

        model = new DefaultTableModel(new String[]{"Room #", "Type", "Price/Night (ETB)", "Available", "Image Path"}, 0);
        roomTable = new JTable(model);
        roomTable.setRowHeight(25);
        roomTable.getTableHeader().setBackground(new Color(0, 102, 204));
        roomTable.getTableHeader().setForeground(Color.WHITE);
        roomTable.setSelectionBackground(new Color(200, 220, 240));
        
        refreshTable();
        
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setPreferredSize(new Dimension(950, 200));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
   
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
     
        JButton addBtn = new JButton("➕ Add Room");
        addBtn.setBackground(new Color(0, 153, 0));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        if (!role.equalsIgnoreCase("Admin")) {
            addBtn.setEnabled(false);
            addBtn.setToolTipText("Only Admin can add rooms");
        }
        addBtn.addActionListener(e -> addRoomDialog());
     
        JButton bookBtn = new JButton("Book Room");
        bookBtn.setBackground(new Color(0, 102, 204));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 12));
        bookBtn.addActionListener(e -> bookRoomDialog());

        JButton refreshBtn = new JButton("? Refresh");
        refreshBtn.setBackground(new Color(102, 102, 102));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 12));
        refreshBtn.addActionListener(e -> refreshTable());

        JButton checkOutBtn = new JButton("? Check Out");
        checkOutBtn.setBackground(new Color(204, 102, 0));
        checkOutBtn.setForeground(Color.WHITE);
        checkOutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        checkOutBtn.addActionListener(e -> checkOutRoom());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(bookBtn);
        buttonPanel.add(checkOutBtn);
        buttonPanel.add(refreshBtn);
        
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(tablePanel, BorderLayout.SOUTH);
    
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(new Color(200, 200, 200));
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel statusLabel = new JLabel("✅ System ready | Logged in as: " + username + " (" + role + ")");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        statusBar.add(statusLabel);
        
        add(statusBar, BorderLayout.BEFORE_FIRST_LINE);
     
        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room r : rooms) {
            model.addRow(new Object[]{
                r.getRoomNumber(), 
                r.getType(), 
                r.getPricePerNight(),
                r.isAvailable() ? " Yes" : " No",
                r.getImagePath() == null ? "No Image" : r.getImagePath()
            });
        }
    }

    private void addRoomDialog() {
        JTextField num = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Single", "Double", "Suite", "Deluxe", "Executive"});
        JTextField price = new JTextField();
        JCheckBox avail = new JCheckBox("Available", true);
        JTextField img = new JTextField();

        Object[] fields = {
            "Room Number:", num,
            "Type:", typeCombo,
            "Price per Night (ETB):", price,
            "Available:", avail,
            "Image Path (optional):", img
        };
        
        int option = JOptionPane.showConfirmDialog(this, fields, "Add New Room", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Room r = new Room();
                r.setRoomNumber(Integer.parseInt(num.getText()));
                r.setType((String) typeCombo.getSelectedItem());
                r.setPricePerNight(Double.parseDouble(price.getText()));
                r.setAvailable(avail.isSelected());
                r.setImagePath(img.getText().trim().isEmpty() ? null : img.getText());
                
                if (roomDAO.addRoom(r)) {
                    JOptionPane.showMessageDialog(this, "✅ Room added successfully!");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Failed to add room! Room number may already exist.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid number format! Please enter valid numbers.");
            }
        }
    }

    private void bookRoomDialog() {
        JTextField roomNum = new JTextField();
        JTextField days = new JTextField();
        JTextField guestName = new JTextField();
        
        Object[] fields = {
            "Room Number:", roomNum,
            "Guest Name:", guestName,
            "Number of nights:", days
        };
        
        int option = JOptionPane.showConfirmDialog(this, fields, "Book a Room", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int roomNumber = Integer.parseInt(roomNum.getText());
                int nights = Integer.parseInt(days.getText());
                String guest = guestName.getText().trim();
                
                if (guest.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "❌ Please enter guest name!");
                    return;
                }
                
      
                List<Room> rooms = roomDAO.getAllRooms();
                for (Room r : rooms) {
                    if (r.getRoomNumber() == roomNumber && r.isAvailable()) {
                        double total = r.getPricePerNight() * nights;
                        int confirm = JOptionPane.showConfirmDialog(this, 
                            "📋 Booking Summary:\n" +
                            "Room: " + roomNumber + " (" + r.getType() + ")\n" +
                            "Guest: " + guest + "\n" +
                            "Nights: " + nights + "\n" +
                            "Total: " + total + " ETB\n\n" +
                            "Confirm booking?", 
                            "Confirm Booking", 
                            JOptionPane.YES_NO_OPTION);
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(this, 
                                "✅ Room " + roomNumber + " booked successfully!\n" +
                                "Guest: " + guest + "\n" +
                                "Nights: " + nights + "\n" +
                                "Total: " + total + " ETB\n\n" +
                                "Thank you for choosing our hotel!");
                  
                            roomDAO.updateAvailability(roomNumber, false);
                            refreshTable();
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "❌ Room not available or does not exist!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid input! Please enter valid numbers.");
            }
        }
    }
    
    private void checkOutRoom() {
        JTextField roomNum = new JTextField();
        
        Object[] fields = {
            "Room Number to Check Out:", roomNum
        };
        
        int option = JOptionPane.showConfirmDialog(this, fields, "Check Out", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int roomNumber = Integer.parseInt(roomNum.getText());
                
                List<Room> rooms = roomDAO.getAllRooms();
                for (Room r : rooms) {
                    if (r.getRoomNumber() == roomNumber && !r.isAvailable()) {
                        int confirm = JOptionPane.showConfirmDialog(this, 
                            "Check out room " + roomNumber + "?\n" +
                            "Room Type: " + r.getType(),
                            "Confirm Check Out", 
                            JOptionPane.YES_NO_OPTION);
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            roomDAO.updateAvailability(roomNumber, true);
                            JOptionPane.showMessageDialog(this, "✅ Room " + roomNumber + " checked out successfully!");
                            refreshTable();
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "❌ Room not found or already available!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid room number!");
            }
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Logout", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}