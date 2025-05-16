package org.kibit.edu.ua;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Grade> grades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (Grade g : grades) {
            sum += g.getScore();
        }
        return sum / grades.size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}