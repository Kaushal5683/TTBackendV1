package com.example.timetable.service;

import com.example.timetable.entity.Classroom;
import com.example.timetable.entity.Subject;
import com.example.timetable.entity.Teacher;
import com.example.timetable.entity.TimetableSlot;
import com.example.timetable.entity.Year;
import com.example.timetable.repository.ClassroomRepository;
import com.example.timetable.repository.SubjectRepository;
import com.example.timetable.repository.TimetableSlotRepository;
import com.example.timetable.repository.YearRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TimetableService {

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TimetableSlotRepository timetableSlotRepository;

    /**
     * Generates a timetable for a given year and assigns a unique timetable number to each timetable.
     */
    public void generateTimetable(Long yearId) {
        Year year = yearRepository.findById(yearId)
                .orElseThrow(() -> new RuntimeException("Year not found"));
        int days = year.getNumberOfDays();
        int slotsPerDay = year.getSlotsPerDay();

        List<Classroom> classrooms = classroomRepository.findByYear(year);
        List<Subject> subjects = subjectRepository.findByYear(year);

        // Teacher and Subject mapping
        Map<String, Map<Integer, Teacher>> subjectTeacherMap = new HashMap<>();
        Map<Teacher, Set<Integer>> teacherSchedule = new HashMap<>();

        for (Subject subject : subjects) {
            for (Teacher teacher : subject.getTeachers()) {
                subjectTeacherMap.putIfAbsent(subject.getName(), new HashMap<>());
                for (Classroom classroom : classrooms) {
                    subjectTeacherMap.get(subject.getName()).put(classroom.getClassroomNumber(), teacher);
                    teacherSchedule.putIfAbsent(teacher, new HashSet<>());
                }
            }
        }

        // Generate timetables for each classroom
        for (Classroom classroom : classrooms) {
            List<TimetableSlot> timetable = generateClassroomTimetable(
                    subjects, days, slotsPerDay, subjectTeacherMap, teacherSchedule, classroom
            );

            // Assign a unique timetable number
            int nextTimetableNumber = getNextTimetableNumber(classroom);
            for (TimetableSlot slot : timetable) {
                slot.setTimetableNumber(nextTimetableNumber);
            }

            // Save the generated timetable
            timetableSlotRepository.saveAll(timetable);
        }
    }

    /**
     * Generates a timetable for a specific classroom.
     */
    private List<TimetableSlot> generateClassroomTimetable(List<Subject> subjects, int days, int slotsPerDay,
                                                           Map<String, Map<Integer, Teacher>> subjectTeacherMap,
                                                           Map<Teacher, Set<Integer>> teacherSchedule,
                                                           Classroom classroom) {
        List<TimetableSlot> timetable = new ArrayList<>();
        Map<Teacher, Set<Integer>> localTeacherSchedule = new HashMap<>();

        for (Subject subject : subjects) {
            Teacher teacher = subjectTeacherMap.get(subject.getName()).get(classroom.getClassroomNumber());
            localTeacherSchedule.put(teacher, new HashSet<>());
        }

        Random random = new Random();
        for (Subject subject : subjects) {
            int slotsForSubject = subject.getSlotsRequired();
            Teacher teacher = subjectTeacherMap.get(subject.getName()).get(classroom.getClassroomNumber());

            int assignedSlots = 0;
            int attempts = 0;
            int maxAttemptsBeforeRepetition = 10;

            while (assignedSlots < slotsForSubject) {
                attempts++;
                if (attempts > days * slotsPerDay * 50) {
                    System.out.println("Failed to schedule subject " + subject.getName() + " for classroom " + classroom.getClassroomNumber());
                    break;
                }

                int day = random.nextInt(days);
                boolean allowRepetition = attempts > maxAttemptsBeforeRepetition;

                if (!allowRepetition && localTeacherSchedule.get(teacher).contains(day)) {
                    continue;
                }

                for (int slot = 0; slot < slotsPerDay; slot++) {
                    int finalDay = day;
                    int finalSlot = slot;
                    int index = finalDay * slotsPerDay + finalSlot;

                    boolean isSlotFree = timetable.stream()
                            .noneMatch(t -> t.getDay() == finalDay && t.getSlot() == finalSlot);

                    if (isSlotFree && (!teacherSchedule.get(teacher).contains(index) || allowRepetition)) {
                        TimetableSlot timetableSlot = new TimetableSlot();
                        timetableSlot.setClassroom(classroom);
                        timetableSlot.setSubject(subject);
                        timetableSlot.setTeacher(teacher);
                        timetableSlot.setDay(finalDay);
                        timetableSlot.setSlot(finalSlot);

                        timetable.add(timetableSlot);
                        localTeacherSchedule.get(teacher).add(finalDay);
                        teacherSchedule.get(teacher).add(index);
                        assignedSlots++;
                        break;
                    }
                }
            }
        }

        return timetable;
    }

    /**
     * Retrieves the next timetable number for the given classroom.
     */
    private int getNextTimetableNumber(Classroom classroom) {
        Integer maxNumber = timetableSlotRepository.findMaxTimetableNumberByClassroom(classroom);
        return (maxNumber == null) ? 1 : maxNumber + 1;
    }

    /**
     * Retrieves all timetables for a given timetable number.
     */
    @Transactional(readOnly = true)
    public List<TimetableSlot> getTimetableByNumber(int timetableNumber) {
        return timetableSlotRepository.findByTimetableNumber(timetableNumber);
    }

    /**
     * Retrieves all timetables.
     */
    @Transactional(readOnly = true)
    public List<TimetableSlot> getAllTimetables() {
        return timetableSlotRepository.findAll();
    }
}
