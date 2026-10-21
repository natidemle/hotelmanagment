
package dao;

import db.DBConnection;
import model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY room_number";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setType(rs.getString("room_type"));
                room.setPricePerNight(rs.getDouble("price_per_night"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setImagePath(rs.getString("image_path"));
                room.setDescription(rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE is_available = TRUE ORDER BY room_number";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setType(rs.getString("room_type"));
                room.setPricePerNight(rs.getDouble("price_per_night"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setImagePath(rs.getString("image_path"));
                room.setDescription(rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    

    public Room getRoomByNumber(int roomNumber) {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setType(rs.getString("room_type"));
                room.setPricePerNight(rs.getDouble("price_per_night"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setImagePath(rs.getString("image_path"));
                room.setDescription(rs.getString("description"));
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, room_type, price_per_night, is_available, image_path, description) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setInt(1, room.getRoomNumber());
            pstmt.setString(2, room.getType());
            pstmt.setDouble(3, room.getPricePerNight());
            pstmt.setBoolean(4, room.isAvailable());
            pstmt.setString(5, room.getImagePath());
            pstmt.setString(6, room.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAvailability(int roomNumber, boolean available) {
        String sql = "UPDATE rooms SET is_available = ? WHERE room_number = ?";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setBoolean(1, available);
            pstmt.setInt(2, roomNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_type = ?, price_per_night = ?, is_available = ?, image_path = ?, description = ? WHERE room_number = ?";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setString(1, room.getType());
            pstmt.setDouble(2, room.getPricePerNight());
            pstmt.setBoolean(3, room.isAvailable());
            pstmt.setString(4, room.getImagePath());
            pstmt.setString(5, room.getDescription());
            pstmt.setInt(6, room.getRoomNumber());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean deleteRoom(int roomNumber) {
        String sql = "DELETE FROM rooms WHERE room_number = ?";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Room> searchRooms(String roomType, Double minPrice, Double maxPrice) {
        List<Room> rooms = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM rooms WHERE 1=1");
        
        if (roomType != null && !roomType.trim().isEmpty()) {
            sql.append(" AND room_type LIKE ?");
        }
        if (minPrice != null) {
            sql.append(" AND price_per_night >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND price_per_night <= ?");
        }
        sql.append(" ORDER BY room_number");
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql.toString())) {
            int index = 1;
            
            if (roomType != null && !roomType.trim().isEmpty()) {
                pstmt.setString(index++, "%" + roomType + "%");
            }
            if (minPrice != null) {
                pstmt.setDouble(index++, minPrice);
            }
            if (maxPrice != null) {
                pstmt.setDouble(index++, maxPrice);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setType(rs.getString("room_type"));
                room.setPricePerNight(rs.getDouble("price_per_night"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setImagePath(rs.getString("image_path"));
                room.setDescription(rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    public List<Room> getRoomsByType(String roomType) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE room_type = ? ORDER BY room_number";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setString(1, roomType);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setType(rs.getString("room_type"));
                room.setPricePerNight(rs.getDouble("price_per_night"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setImagePath(rs.getString("image_path"));
                room.setDescription(rs.getString("description"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
    public boolean roomExists(int roomNumber) {
        String sql = "SELECT COUNT(*) FROM rooms WHERE room_number = ?";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalRooms() {
        String sql = "SELECT COUNT(*) FROM rooms";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
   
    public int getAvailableRoomsCount() {
        String sql = "SELECT COUNT(*) FROM rooms WHERE is_available = TRUE";
        
        try (PreparedStatement pstmt = DBConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

  
}