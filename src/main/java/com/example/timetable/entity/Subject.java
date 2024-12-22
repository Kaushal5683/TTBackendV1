package com.example.timetable.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int slotsRequired;

    @ManyToOne
    private Year year;

    @ManyToMany
    @JoinTable(
        name = "subject_teacher",
        joinColumns = @JoinColumn(name = "subject_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSlotsRequired() {
		return slotsRequired;
	}

	public void setSlotsRequired(int slotsRequired) {
		this.slotsRequired = slotsRequired;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Subject(Long id, String name, int slotsRequired, Year year, List<TimetableSlot> timetableSlots,
			List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.slotsRequired = slotsRequired;
		this.year = year;
		this.teachers = teachers;
	}

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
}