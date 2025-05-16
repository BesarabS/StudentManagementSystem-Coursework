package org.kibit.edu.ua;

public class Grade {
    private Subject subject;
    private double score;

    public Grade(Subject subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public Subject getSubject() {
        return subject;
    }
}
