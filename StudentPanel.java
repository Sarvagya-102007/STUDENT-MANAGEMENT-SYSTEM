import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private final StudentManager manager;
    private final Runnable addStudent;

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public StudentPanel(
            StudentManager manager,
            Runnable refresh,
            Runnable addStudent) {

        this.manager = manager;
        this.addStudent = addStudent;

        setBackground(Main.BACKGROUND);
        setLayout(new BorderLayout());

        createInterface();
        loadTable(manager.getStudents());
    }

    private void createInterface() {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(Main.BACKGROUND);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 15, 30
                )
        );

        JPanel heading = new JPanel();

        heading.setBackground(Main.BACKGROUND);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Student Management");

        title.setForeground(Main.TEXT);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Manage student records and information"
                );

        subtitle.setForeground(Main.SECONDARY);

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        heading.add(title);

        heading.add(
                Box.createVerticalStrut(5)
        );

        heading.add(subtitle);

        header.add(
                heading,
                BorderLayout.WEST
        );

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        searchPanel.setBackground(Main.BACKGROUND);

        searchField =
                new JTextField(16);

        JButton searchButton =
                new JButton("Search");

        JButton resetButton =
                new JButton("Reset");

        JButton addButton =
                new JButton("+ Add Student");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);
        searchPanel.add(addButton);

        header.add(
                searchPanel,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Name",
                "Course",
                "Semester",
                "Email",
                "Phone",
                "CGPA",
                "Attendance"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        table =
                new JTable(model);

        table.setRowHeight(32);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 30, 10, 30
                )
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        bottom.setBackground(Main.BACKGROUND);

        bottom.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 30, 20, 30
                )
        );

        JButton editButton =
                new JButton("Edit");

        JButton deleteButton =
                new JButton("Delete");

        JButton profileButton =
                new JButton("View Profile");

        bottom.add(editButton);
        bottom.add(deleteButton);
        bottom.add(profileButton);

        add(
                bottom,
                BorderLayout.SOUTH
        );

        searchButton.addActionListener(
                e -> search()
        );

        searchField.addActionListener(
                e -> search()
        );

        resetButton.addActionListener(
                e -> {
                    searchField.setText("");
                    loadTable(manager.getStudents());
                }
        );

        addButton.addActionListener(
                e -> addStudent.run()
        );

        editButton.addActionListener(
                e -> editSelected()
        );

        deleteButton.addActionListener(
                e -> deleteSelected()
        );

        profileButton.addActionListener(
                e -> viewProfile()
        );
    }

    private void search() {

        String keyword =
                searchField
                        .getText()
                        .trim();

        if (keyword.isEmpty()) {

            loadTable(
                    manager.getStudents()
            );

            return;
        }

        List<Student> results =
                manager.searchStudents(
                        keyword
                );

        loadTable(results);
    }

    private void loadTable(
            List<Student> students) {

        model.setRowCount(0);

        for (Student student : students) {

            model.addRow(
                    new Object[]{
                            student.getId(),
                            student.getName(),
                            student.getCourse(),
                            student.getSemester(),
                            student.getEmail(),
                            student.getPhone(),
                            String.format(
                                    "%.2f",
                                    student.getCgpa()
                            ),
                            String.format(
                                    "%.1f%%",
                                    student.getAttendance()
                            )
                    }
            );
        }
    }

    private Student getSelectedStudent() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }

        String id =
                model.getValueAt(
                        row,
                        0
                ).toString();

        return manager.findStudent(id);
    }

    private void editSelected() {

        Student student =
                getSelectedStudent();

        if (student == null) {
            return;
        }

        JTextField nameField =
                new JTextField(
                        student.getName()
                );

        JTextField courseField =
                new JTextField(
                        student.getCourse()
                );

        JTextField semesterField =
                new JTextField(
                        String.valueOf(
                                student.getSemester()
                        )
                );

        JTextField emailField =
                new JTextField(
                        student.getEmail()
                );

        JTextField phoneField =
                new JTextField(
                        student.getPhone()
                );

        JTextField cgpaField =
                new JTextField(
                        String.valueOf(
                                student.getCgpa()
                        )
                );

        JPanel form =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                10
                        )
                );

        form.add(new JLabel("Name:"));
        form.add(nameField);

        form.add(new JLabel("Course:"));
        form.add(courseField);

        form.add(new JLabel("Semester:"));
        form.add(semesterField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("Phone:"));
        form.add(phoneField);

        form.add(new JLabel("CGPA:"));
        form.add(cgpaField);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "Edit Student",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            int semester =
                    Integer.parseInt(
                            semesterField
                                    .getText()
                                    .trim()
                    );

            double cgpa =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            if (semester < 1 || semester > 8) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semester must be between 1 and 8."
                );

                return;
            }

            if (cgpa < 0 || cgpa > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "CGPA must be between 0 and 10."
                );

                return;
            }

            student.setName(
                    nameField.getText().trim()
            );

            student.setCourse(
                    courseField.getText().trim()
            );

            student.setSemester(semester);

            student.setEmail(
                    emailField.getText().trim()
            );

            student.setPhone(
                    phoneField.getText().trim()
            );

            student.setCgpa(cgpa);

            manager.updateStudent(student);

            loadTable(
                    manager.getStudents()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );
        }
    }

    private void deleteSelected() {

        Student student =
                getSelectedStudent();

        if (student == null) {
            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete " +
                                student.getName() +
                                "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm ==
                        JOptionPane.YES_OPTION
        ) {

            manager.deleteStudent(
                    student.getId()
            );

            loadTable(
                    manager.getStudents()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully."
            );
        }
    }

    private void viewProfile() {

        Student student =
                getSelectedStudent();

        if (student == null) {
            return;
        }

        String message =
                "<html>" +
                "<h2>" +
                student.getName() +
                "</h2>" +
                "<b>Student ID:</b> " +
                student.getId() +
                "<br><br>" +
                "<b>Course:</b> " +
                student.getCourse() +
                "<br>" +
                "<b>Semester:</b> " +
                student.getSemester() +
                "<br>" +
                "<b>Email:</b> " +
                student.getEmail() +
                "<br>" +
                "<b>Phone:</b> " +
                student.getPhone() +
                "<br><br>" +
                "<b>CGPA:</b> " +
                String.format(
                        "%.2f",
                        student.getCgpa()
                ) +
                "<br>" +
                "<b>Grade:</b> " +
                student.getGrade() +
                "<br>" +
                "<b>Attendance:</b> " +
                String.format(
                        "%.1f%%",
                        student.getAttendance()
                ) +
                "</html>";

        JOptionPane.showMessageDialog(
                this,
                new JLabel(message),
                "Student Profile",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}