package src.services;

import src.models.Person;
import java.util.*;
import java.util.stream.Collectors;

public class PersonService {
    private List<Person> people;

    public PersonService() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getAllPeople() {
        return new ArrayList<>(people);
    }

    public Person findPersonByName(String name) {
        return people.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    // Stream API operations
    public double getAverageSalary() {
        return people.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> getAverageSalaryByDepartment() {
        return people.stream()
                .collect(Collectors.groupingBy(
                    Person::getDepartment,
                    Collectors.averagingDouble(Person::getSalary)
                ));
    }

    public List<Person> getPeopleAboveAge(int age) {
        return people.stream()
                .filter(person -> person.getAge() > age)
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    public Map<String, List<Person>> getPeopleByCity() {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getCity));
    }

    public Optional<Person> getHighestPaidPerson() {
        return people.stream()
                .max(Comparator.comparingDouble(Person::getSalary));
    }

    public List<String> getUniqueDepartments() {
        return people.stream()
                .map(Person::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public double getTotalSalaryExpense() {
        return people.stream()
                .mapToDouble(Person::getSalary)
                .sum();
    }

    public List<Person> getTopEarners(int n) {
        return people.stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
} 