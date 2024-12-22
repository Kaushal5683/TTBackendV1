package com.example.timetable.repository;

import com.example.timetable.entity.Classroom;
import com.example.timetable.entity.TimetableSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimetableSlotRepository extends JpaRepository<TimetableSlot, Long> {

    @Query("SELECT MAX(t.timetableNumber) FROM TimetableSlot t WHERE t.classroom = :classroom")
    Integer findMaxTimetableNumberByClassroom(Classroom classroom);

    List<TimetableSlot> findByTimetableNumber(int timetableNumber);
}
