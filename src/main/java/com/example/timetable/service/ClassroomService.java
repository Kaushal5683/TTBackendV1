package com.example.timetable.service;

import com.example.timetable.entity.Classroom;
import com.example.timetable.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id);
    }

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }
    
    public Classroom updateClassroom(Long id,Classroom classroomDetails) {
    	Classroom classroom=classroomRepository.findById(id).orElseThrow(() -> new RuntimeException("Classroom not Found"));
    	
    	classroom.setClassroomNumber(classroomDetails.getClassroomNumber());
    	
    	classroom.setYear(classroomDetails.getYear());
    	
    	return classroomRepository.save(classroom);
    }
    
    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}
