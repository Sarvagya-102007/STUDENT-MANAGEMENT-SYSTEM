import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final ArrayList<Student> students = new ArrayList<>();

    private static final String FILE_NAME = "students.dat";

    public StudentManager() {
        loadStudents();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void updateStudent(Student student) {
        saveStudents();
    }

    public void deleteStudent(String id) {
        students.removeIf(
                student -> student.getId().equalsIgnoreCase(id)
        );

        saveStudents();
    }

    public Student findStudent(String id) {

        for (Student student : students) {

            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    public List<Student> searchStudents(String keyword) {

        ArrayList<Student> result = new ArrayList<>();

        String search = keyword.toLowerCase();

        for (Student student : students) {

            if (student.getId().toLowerCase().contains(search)
                    || student.getName().toLowerCase().contains(search)
                    || student.getCourse().toLowerCase().contains(search)
                    || student.getEmail().toLowerCase().contains(search)) {

                result.add(student);
            }
        }

        return result;
    }

    public int getTotalStudents() {
        return students.size();
    }

    public int getCourseCount(String course) {

        int count = 0;

        for (Student student : students) {

            if (student.getCourse().equalsIgnoreCase(course)) {
                count++;
            }
        }

        return count;
    }

    public double getAverageCGPA() {

        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getCgpa();
        }

        return total / students.size();
    }

    public double getAverageAttendance() {

        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getAttendance();
        }

        return total / students.size();
    }

    private void saveStudents() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            output.writeObject(students);

        } catch (IOException e) {

            System.out.println(
                    "Unable to save student data."
            );
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            ArrayList<Student> loaded =
                    (ArrayList<Student>) input.readObject();

            students.clear();
            students.addAll(loaded);

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Starting with empty student database."
            );
        }
    }
}