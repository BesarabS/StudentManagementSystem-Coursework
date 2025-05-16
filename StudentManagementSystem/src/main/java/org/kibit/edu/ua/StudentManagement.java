package org.kibit.edu.ua;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentManagement extends JFrame {
    private StudentManager manager;
    private JTextArea outputArea;
    private JTextField idField, nameField, subjectField, gradeField;

    public StudentManagement() {
        DBHelper.initializeDatabase();
        manager = new StudentManager();

        setTitle("Система обліку студентів");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("ID студента:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Ім’я студента:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Предмет:"));
        subjectField = new JTextField();
        inputPanel.add(subjectField);

        inputPanel.add(new JLabel("Оцінка:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Додати/оновити студента");
        addButton.addActionListener(e -> addStudentWithGrade());
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Видалити студента за ID");
        deleteButton.addActionListener(e -> deleteStudentById());
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JButton showButton = new JButton("Показати всіх студентів");
        showButton.addActionListener(e -> showAllStudents());
        add(showButton, BorderLayout.SOUTH);
    }

    private void addStudentWithGrade() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String subject = subjectField.getText();
            double grade = Double.parseDouble(gradeField.getText());
            manager.addOrUpdateStudent(id, name, subject, grade);
            outputArea.append("Додано/оновлено студента: " + name + "\n");
        } catch (Exception e) {
            outputArea.append("Помилка: " + e.getMessage() + "\n");
        }
    }

    private void deleteStudentById() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean removed = manager.deleteStudent(id);
            if (removed) {
                outputArea.append("Студента з ID " + id + " видалено.\n");
            } else {
                outputArea.append("Студента з ID " + id + " не знайдено.\n");
            }
        } catch (Exception e) {
            outputArea.append("Помилка: " + e.getMessage() + "\n");
        }
    }

    private void showAllStudents() {
        List<String> students = manager.getAllStudents();
        outputArea.append("--- Усі студенти ---\n");
        for (String s : students) {
            outputArea.append(s + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagement().setVisible(true));
    }
}