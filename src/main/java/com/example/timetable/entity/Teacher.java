package com.example.timetable.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long id, String name, List<Subject> subjects, List<TimetableSlot> timetableSlots) {
		super();
		this.id = id;
		this.name = name;
	}

    // Getters and Setters
}