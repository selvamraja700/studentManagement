package studentManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ==== Student Class ====
    static class Student {
        private int id;
        private String name;
        private double[] marks; // array to store 5 marks
        private double total;
        private double average;

        public Student(int id, String name, double[] marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            calculateTotalAndAverage();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double[] getMarks() {
            return marks;
        }

        public void setMarks(double[] marks) {
            this.marks = marks;
            calculateTotalAndAverage();
        }

        public double getTotal() {
            return total;
        }

        public double getAverage() {
            return average;
        }

        private void calculateTotalAndAverage() {
            total = 0;
            for (double mark : marks) {
                total += mark;
            }
            average = total / marks.length;
        }

        public String toString() {
            StringBuilder marksStr = new StringBuilder();
            for (int i = 0; i < marks.length; i++) {
                marksStr.append("M").append(i + 1).append(": ").append(marks[i]).append("  ");
            }
            return "ID: " + id + " | Name: " + name + " | " + marksStr +
                    "| Total: " + total + " | Average: " + String.format("%.2f", average);
        }
    }

    // ==== StudentManager Class ====
    static class StudentManager {
        private ArrayList<Student> studentList = new ArrayList<>();
        private Scanner sc = new Scanner(System.in);

        public void addStudent() {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();  // consume newline

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            double[] marks = new double[5];
            for (int i = 0; i < 5; i++) {
                System.out.print("Enter mark " + (i + 1) + ": ");
                marks[i] = sc.nextDouble();
            }

            studentList.add(new Student(id, name, marks));
            System.out.println(" Student Details added successfully!");
        }

        public void viewStudents() {
            if (studentList.isEmpty()) {
                System.out.println(" No students found.");
                return;
            }

            System.out.println("📋 List of Students:");
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println(studentList.get(i));
            }
        }

        public void updateStudent() {
            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < studentList.size(); i++) {
                Student s = studentList.get(i);
                if (s.getId() == id) {
                    System.out.print("Enter New Name: ");
                    s.setName(sc.nextLine());

                    double[] marks = new double[5];
                    for (int j = 0; j < 5; j++) {
                        System.out.print("Enter new mark " + (j + 1) + ": ");
                        marks[j] = sc.nextDouble();
                    }
                    s.setMarks(marks);

                    System.out.println(" Student updated successfully!");
                    return;
                }
            }
            System.out.println(" Student with ID " + id + " not found.");
        }

        public void deleteStudent() {
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getId() == id) {
                    studentList.remove(i);
                    System.out.println(" Student deleted successfully!");
                    return;
                }
            }
            System.out.println(" Student with ID " + id + " not found.");
        }

        public void closeScanner() {
            sc.close();
        }
    }

    // ==== Main Method ====
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    manager.updateStudent();
                    break;
                case 4:
                    manager.deleteStudent();
                    break;
                case 5:
                    System.out.println(" Exiting... Thanks!");
                    break;
                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close(); // Close scanner to prevent resource leak
        manager.closeScanner(); // Close internal scanner
    }
}
