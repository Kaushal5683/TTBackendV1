package com.example.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.timetable.entity.Year;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {
}