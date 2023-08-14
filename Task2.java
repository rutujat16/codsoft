package task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable 
{
    private String name;
    private int rollNumber; 
    private String grade;

    public Student(String name, int rollNumber, String grade) 
    {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() 
    {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) 
    {
        this.rollNumber = rollNumber;
    }

    public String toString() 
    {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem 
{
    private List<Student> students;

    public StudentManagementSystem() 
    {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) 
    {
        students.add(student);
    }

    public void removeStudent(String rollNumber) 
    {
        students.removeIf(student -> Integer.toString(student.getRollNumber()).equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) 
    {
        for (Student student : students) 
        {
            if (Integer.toString(student.getRollNumber()).equals(rollNumber)) 
            {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() 
    {
        for (Student student : students) 
        {
            System.out.println(student);
        }
    }

    public void saveToFile(String fileName) 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) 
        {
            oos.writeObject(students);
            System.out.println("Data saved successfully!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) 
        {
            students = (List<Student>) ois.readObject();
            System.out.println("Data loaded successfully!");
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
}

public class Task2 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
        String fileName = "student_data.ser";

        File file = new File(fileName); // Load data from file (if it exists)
        if (file.exists()) 
        {
            studentManagementSystem.loadFromFile(fileName);
        }

        while (true) 
        {
        	 System.out.println("\n============================================");
            System.out.println("\n	 Student Management System ");
            System.out.println("\n=============================================");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save and Exit");
            System.out.println("\n=============================================");
            System.out.print("Enter your choice (1-5): ");
         
            
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    Student student = getStudentInfoFromUser(scanner);
                    studentManagementSystem.addStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case "2":
                    System.out.print("Enter the roll number of the student to remove: ");
                    int rollNumberToRemove = Integer.parseInt(scanner.nextLine());
                    studentManagementSystem.removeStudent(Integer.toString(rollNumberToRemove));
                    System.out.println("Student removed successfully!");
                    break;
                
                case "3":
                    System.out.print("Enter the roll number of the student to search for: ");
                    int rollNumberToSearch = Integer.parseInt(scanner.nextLine());
                    Student searchedStudent = studentManagementSystem.searchStudent(Integer.toString(rollNumberToSearch));
                    if (searchedStudent != null) 
                    {
                        System.out.println("Student found:");
                        System.out.println(searchedStudent);
                    } 
                    else 
                    {
                        System.out.println("Student not found!");
                    }
                    break;

                case "4":
                    System.out.println("\nAll Students:");
                    studentManagementSystem.displayAllStudents();
                    break;

                case "5":
                    studentManagementSystem.saveToFile(fileName);
                    System.out.println("Data saved. Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Student getStudentInfoFromUser(Scanner scanner) 
    {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = Integer.parseInt(scanner.nextLine()); // Read an integer for roll number

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        return new Student(name, rollNumber, grade);
    }
}