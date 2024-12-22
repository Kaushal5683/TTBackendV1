package com.example.timetable.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int yearNumber;
    private int numberOfClassrooms;
    private int numberOfSubjects;
    private int numberOfDays;
    private int slotsPerDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public void setYearNumber(int yearNumber) {
		this.yearNumber = yearNumber;
	}

	public int getNumberOfClassrooms() {
		return numberOfClassrooms;
	}

	public void setNumberOfClassrooms(int numberOfClassrooms) {
		this.numberOfClassrooms = numberOfClassrooms;
	}

	public int getNumberOfSubjects() {
		return numberOfSubjects;
	}

	public void setNumberOfSubjects(int numberOfSubjects) {
		this.numberOfSubjects = numberOfSubjects;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getSlotsPerDay() {
		return slotsPerDay;
	}

	public void setSlotsPerDay(int slotsPerDay) {
		this.slotsPerDay = slotsPerDay;
	}
	
	public Year(Long id, int yearNumber, int numberOfClassrooms, int numberOfSubjects, int numberOfDays,
			int slotsPerDay, List<Classroom> classrooms, List<Subject> subjects) {
		super();
		this.id = id;
		this.yearNumber = yearNumber;
		this.numberOfClassrooms = numberOfClassrooms;
		this.numberOfSubjects = numberOfSubjects;
		this.numberOfDays = numberOfDays;
		this.slotsPerDay = slotsPerDay;
	}

	public Year() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
}