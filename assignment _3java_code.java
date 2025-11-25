// Custom Exception Class
public class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}
public class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks = new int[3];

    public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;

        validateMarks(); // validate at creation
    }

    // Validate marks are between 0–100
    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < 3; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid marks for subject " + (i + 1) + ": " + marks[i]);
            }
        }
    }

    // Average marks
    public double calculateAverage() {
        return (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    // Display result
    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);

        System.out.print("Marks: ");
        for (int m : marks) System.out.print(m + " ");
        System.out.println();

        double avg = calculateAverage();
        System.out.println("Average: " + avg);

        System.out.println("Result: " + (avg >= 40 ? "Pass" : "Fail"));
    }

    public int getRollNumber() {
        return rollNumber;
    }
}
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultManager {

    private Student[] students = new Student[100];
    private int count = 0;
    private Scanner sc = new Scanner(System.in);

    // Add student
    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            // Create Student object (validates marks)
            students[count++] = new Student(roll, name, marks);

            System.out.println("Student added successfully. Returning to main menu...");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Input mismatch! Please enter valid numbers.");
            sc.nextLine(); 
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    // Show a specific student's details
    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int roll = sc.nextInt();

            for (int i = 0; i < count; i++) {
                if (students[i].getRollNumber() == roll) {
                    students[i].displayResult();
                    System.out.println("Search completed.");
                    return;
                }
            }

            System.out.println("Student not found.");

        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a valid integer roll number.");
            sc.nextLine();
        }
    }

    // Menu
    public void mainMenu() {
        try {
            while (true) {
                System.out.println("===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        showStudentDetails();
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } finally {
            sc.close();
            System.out.println("Scanner closed. Program terminated safely.");
        }
    }

    public static void main(String[] args) {
        ResultManager manager = new ResultManager();
        manager.mainMenu();
    }
}
