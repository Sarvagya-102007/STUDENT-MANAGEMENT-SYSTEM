import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private String name;
    private String course;
    private int semester;
    private String email;
    private String phone;
    private double cgpa;
    private int totalClasses;
    private int attendedClasses;

    public Student(String id, String name, String course, int semester,
                   String email, String phone, double cgpa,
                   int totalClasses, int attendedClasses) {

        this.id = id;
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.email = email;
        this.phone = phone;
        this.cgpa = cgpa;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public double getAttendance() {
        if (totalClasses == 0) {
            return 0;
        }

        return attendedClasses * 100.0 / totalClasses;
    }

    public String getGrade() {
        if (cgpa >= 9.0) return "A+";
        if (cgpa >= 8.0) return "A";
        if (cgpa >= 7.0) return "B+";
        if (cgpa >= 6.0) return "B";
        if (cgpa >= 5.0) return "C";
        return "F";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
    }
}