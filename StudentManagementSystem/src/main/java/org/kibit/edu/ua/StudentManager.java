package org.kibit.edu.ua;

import java.sql.*;
import java.util.*;

public class StudentManager {

    public void addOrUpdateStudent(int id, String name, String subject, double score) {
        try (Connection conn = DBHelper.connect()) {
            PreparedStatement ps = conn.prepareStatement("INSERT OR IGNORE INTO students (id, name) VALUES (?, ?);");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();

            ps = conn.prepareStatement("INSERT INTO grades (student_id, subject, score) VALUES (?, ?, ?);");
            ps.setInt(1, id);
            ps.setString(2, subject);
            ps.setDouble(3, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteStudent(int id) {
        try (Connection conn = DBHelper.connect()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM grades WHERE student_id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = conn.prepareStatement("DELETE FROM students WHERE id = ?;");
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getAllStudents() {
        List<String> result = new ArrayList<>();
        try (Connection conn = DBHelper.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT s.id, s.name, AVG(g.score) as average " +
                    "FROM students s LEFT JOIN grades g ON s.id = g.student_id GROUP BY s.id;");

            while (rs.next()) {
                result.add(rs.getString("name") + " (ID: " + rs.getInt("id") + ") - середній бал: " + rs.getDouble("average"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
