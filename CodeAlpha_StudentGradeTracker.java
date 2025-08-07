import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class CodeAlpha_StudentGradeTracker{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.next();
            System.out.print("Enter grade of " + name + ": ");
            double grade = sc.nextDouble();
            students.add(new Student(name, grade));
        }

        displaySummary(students);
    }

    static void displaySummary(ArrayList<Student> students) {
        double sum = 0, highest = students.get(0).grade, lowest = students.get(0).grade;
        String topStudent = students.get(0).name, lowStudent = students.get(0).name;

        for (Student s : students) {
            sum += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }
        }

        double average = sum / students.size();

        System.out.println("\n--- Student Grade Summary ---");
        for (Student s : students) {
            System.out.println(s.name + " : " + s.grade);
        }
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest + " (" + topStudent + ")");
        System.out.println("Lowest Score: " + lowest + " (" + lowStudent + ")");
    }
}
