package com.example.timetable.service;

import com.example.timetable.entity.Year;
import com.example.timetable.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class YearService {

    @Autowired
    private YearRepository yearRepository;

    public List<Year> getAllYears() {
        return yearRepository.findAll();
    }

    public Optional<Year> getYearById(Long id) {
        return yearRepository.findById(id);
    }

    public Year createYear(Year year) {
        return yearRepository.save(year);
    }
    
    public Year updateYear(Long id, Year yearDetails) {
        Year year = yearRepository.findById(id).orElseThrow(() -> new RuntimeException("Year not found"));

        year.setYearNumber(yearDetails.getYearNumber());
        year.setNumberOfClassrooms(yearDetails.getNumberOfClassrooms());
        year.setNumberOfDays(yearDetails.getNumberOfDays());
        year.setSlotsPerDay(yearDetails.getSlotsPerDay());
        year.setNumberOfSubjects(yearDetails.getNumberOfSubjects());
        // Update other fields as needed

        return yearRepository.save(year);
    }

    public void deleteYear(Long id) {
        yearRepository.deleteById(id);
    }
}












