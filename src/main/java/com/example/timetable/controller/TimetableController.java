package com.example.timetable.controller;

import com.example.timetable.entity.TimetableSlot;
import com.example.timetable.service.TimetableService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/timetables")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping("/generate/{yearId}")
    public ResponseEntity<String> generateTimetable(@PathVariable Long yearId) {
        try {
            timetableService.generateTimetable(yearId);
            return ResponseEntity.ok("Timetable generated successfully for year ID: " + yearId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Year not found: " + yearId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while generating the timetable.");
        }
    }
    @GetMapping
    public ResponseEntity<List<TimetableSlot>> getAllTimetables() {
        List<TimetableSlot> timetables = timetableService.getAllTimetables();
        return ResponseEntity.ok(timetables);
    }//sg account chatgpt latest chat
}
