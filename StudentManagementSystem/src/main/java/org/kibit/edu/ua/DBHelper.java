package org.kibit.edu.ua;

import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String createStudents = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL" +
                    ");";
            String createGrades = "CREATE TABLE IF NOT EXISTS grades (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "student_id INTEGER," +
                    "subject TEXT," +
                    "score REAL," +
                    "FOREIGN KEY(student_id) REFERENCES students(id)" +
                    ");";
            stmt.execute(createStudents);
            stmt.execute(createGrades);
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}