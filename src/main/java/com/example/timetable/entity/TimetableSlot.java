package com.example.timetable.entity;

import jakarta.persistence.*;

@Entity
public class TimetableSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;
    private int slot;

    private int timetableNumber; // New field to group timetables

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    // Constructors
    public TimetableSlot() {
        super();
    }

    public TimetableSlot(Long id, int day, int slot, int timetableNumber, Classroom classroom, Subject subject, Teacher teacher) {
        this.id = id;
        this.day = day;
        this.slot = slot;
        this.timetableNumber = timetableNumber;
        this.classroom = classroom;
        this.subject = subject;
        this.teacher = teacher;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getTimetableNumber() {
        return timetableNumber;
    }

    public void setTimetableNumber(int timetableNumber) {
        this.timetableNumber = timetableNumber;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
