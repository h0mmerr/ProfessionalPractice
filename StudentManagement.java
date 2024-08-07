import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String code;
    private int gender; // 0 for male, 1 for female
    private float grade;

    public Student(String name, int age, String email, String phone, String code, int gender, float grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.gender = gender;
        this.grade = grade;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public int getGender() {
        return gender;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", gender=" + (gender == 0 ? "Male" : "Female") +
                ", grade=" + grade +
                '}';
    }
}

public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add new student");
            System.out.println("2. Display student list");
            System.out.println("3. Delete student by code");
            System.out.println("4. Sort students by grade (descending)");
            System.out.println("5. Search student by code or name");
            System.out.println("6. Search students with grade >= x");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    deleteStudentByCode();
                    break;
                case 4:
                    sortStudentsByGrade();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    searchStudentsByGrade();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter code: ");
        String code = scanner.nextLine();
        System.out.print("Enter gender (0 for Male, 1 for Female): ");
        int gender = scanner.nextInt();
        System.out.print("Enter grade: ");
        float grade = scanner.nextFloat();

        students.add(new Student(name, age, email, phone, code, gender, grade));
        System.out.println("Student added successfully!");
    }

    private static void displayStudents() {
        System.out.println("\nStudent List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void deleteStudentByCode() {
        System.out.print("Enter student code to delete: ");
        String code = scanner.nextLine();
        Student toRemove = null;
        for (Student student : students) {
            if (student.getCode().equalsIgnoreCase(code)) {
                toRemove = student;
                break;
            }
        }

        if (toRemove != null) {
            students.remove(toRemove);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student with code " + code + " not found.");
        }
    }

    private static void sortStudentsByGrade() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
        System.out.println("Students sorted by grade in descending order.");
    }

    private static void searchStudent() {
        System.out.print("Enter student code or name to search: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getCode().equalsIgnoreCase(input) || student.getName().equalsIgnoreCase(input)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with code or name " + input);
        }
    }

    private static void searchStudentsByGrade() {
        System.out.print("Enter grade to search for students with grade >= x: ");
        float x = scanner.nextFloat();
        boolean found = false;
        for (Student student : students) {
            if (student.getGrade() >= x) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with grade >= " + x);
        }
    }
}
