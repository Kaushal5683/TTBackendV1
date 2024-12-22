package com.example.timetable.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int classroomNumber;

    @ManyToOne
    private Year year;


	public Classroom(Long id, int classroomNumber, Year year, List<TimetableSlot> timetableSlots) {
		super();
		this.id = id;
		this.classroomNumber = classroomNumber;
		this.year = year;
	}

	public Classroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getClassroomNumber() {
		return classroomNumber;
	}

	public void setClassroomNumber(int classroomNumber) {
		this.classroomNumber = classroomNumber;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

    // Getters and Setters
}