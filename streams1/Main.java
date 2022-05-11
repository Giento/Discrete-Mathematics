package hr.fer.oop.streams.streams1;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> gradesMap = Loader.loadData();

        System.out.println("Average grade: " + getAverageGrade(gradesMap));

        increaseGrades(gradesMap, "arts");

        System.out.println("Average grade: " + getAverageGrade(gradesMap));
    }

    private static void increaseGrades(Map<String, Map<String, Integer>> grades, String subject) {
        Map<String, Integer> nameGradeMap = grades.get(subject);

        for(String name: nameGradeMap.keySet()) {
            nameGradeMap.compute(name, (key, value) -> value < 5 ? value + 1: value);
        }

    }

    private static double getAverageGrade(Map<String, Map<String, Integer>> grades) {
        return grades.values().stream().
                flatMap(gradesMap -> gradesMap.values().stream()).
                mapToInt(Integer::intValue).average().getAsDouble();
    }


    //ime predmeta, prosjecna ocjena
    private static Map<String, Double> getAverageCourseGrades(Map<String, Map<String, Integer>> gradesMap) {
        return gradesMap.entrySet().stream().
                map(grades -> new SimpleEntry<String, Double>(grades.getKey(),
                        grades.getValue().values().stream().mapToInt(Integer::intValue).average().getAsDouble())).
                collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));


    }
}
