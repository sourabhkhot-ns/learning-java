package src;

import src.models.Person;
import src.services.PersonService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonService();

        // Adding sample data
        personService.addPerson(new Person("Alice", 25, "alice@email.com", "New York", 75000.0, "Engineering"));
        personService.addPerson(new Person("Bob", 30, "bob@email.com", "San Francisco", 85000.0, "Engineering"));
        personService.addPerson(new Person("Charlie", 22, "charlie@email.com", "Boston", 65000.0, "Marketing"));
        personService.addPerson(new Person("Diana", 28, "diana@email.com", "New York", 90000.0, "Sales"));
        personService.addPerson(new Person("Eve", 35, "eve@email.com", "San Francisco", 95000.0, "Engineering"));

        // Demonstrating Stream API operations
        System.out.println("\n1. Average Salary: $" + personService.getAverageSalary());

        System.out.println("\n2. Average Salary by Department:");
        Map<String, Double> avgSalaryByDept = personService.getAverageSalaryByDepartment();
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println(dept + ": $" + avg));

        System.out.println("\n3. People above age 25:");
        List<Person> olderPeople = personService.getPeopleAboveAge(25);
        olderPeople.forEach(System.out::println);

        System.out.println("\n4. People grouped by city:");
        Map<String, List<Person>> peopleByCity = personService.getPeopleByCity();
        peopleByCity.forEach((city, cityPeople) -> {
            System.out.println("\n" + city + ":");
            cityPeople.forEach(person -> System.out.println("  - " + person.getName()));
        });

        System.out.println("\n5. Highest paid person:");
        Optional<Person> highestPaid = personService.getHighestPaidPerson();
        highestPaid.ifPresent(System.out::println);

        System.out.println("\n6. Unique departments:");
        List<String> departments = personService.getUniqueDepartments();
        System.out.println(departments);

        System.out.println("\n7. Total salary expense: $" + personService.getTotalSalaryExpense());

        System.out.println("\n8. Top 3 earners:");
        List<Person> topEarners = personService.getTopEarners(3);
        topEarners.forEach(System.out::println);
    }
} 