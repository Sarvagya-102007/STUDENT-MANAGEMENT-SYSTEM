import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AcademicPanel extends JPanel {

    private final StudentManager manager;

    private JTable table;
    private DefaultTableModel model;

    public AcademicPanel(StudentManager manager) {

        this.manager = manager;

        setBackground(Main.BACKGROUND);
        setLayout(new BorderLayout());

        createInterface();
        loadTable();
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

        JLabel title =
                new JLabel(
                        "Academic Performance"
                );

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
                        "Monitor CGPA, grades and academic performance"
                );

        subtitle.setForeground(Main.SECONDARY);

        JPanel heading =
                new JPanel();

        heading.setBackground(Main.BACKGROUND);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
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

        add(
                header,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Name",
                "Course",
                "Semester",
                "CGPA",
                "Grade",
                "Performance"
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

        JButton updateButton =
                new JButton(
                        "Update CGPA"
                );

        bottom.add(updateButton);

        add(
                bottom,
                BorderLayout.SOUTH
        );

        updateButton.addActionListener(
                e -> updateCGPA()
        );
    }

    private void loadTable() {

        model.setRowCount(0);

        for (Student student :
                manager.getStudents()) {

            String performance;

            if (student.getCgpa() >= 8.0) {
                performance = "Excellent";
            } else if (student.getCgpa() >= 6.0) {
                performance = "Good";
            } else if (student.getCgpa() >= 5.0) {
                performance = "Average";
            } else {
                performance = "Needs Improvement";
            }

            model.addRow(
                    new Object[]{
                            student.getId(),
                            student.getName(),
                            student.getCourse(),
                            student.getSemester(),
                            String.format(
                                    "%.2f",
                                    student.getCgpa()
                            ),
                            student.getGrade(),
                            performance
                    }
            );
        }
    }

    private void updateCGPA() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        String id =
                model.getValueAt(
                        row,
                        0
                ).toString();

        Student student =
                manager.findStudent(id);

        if (student == null) {
            return;
        }

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter new CGPA:",
                        student.getCgpa()
                );

        if (input == null) {
            return;
        }

        try {

            double cgpa =
                    Double.parseDouble(
                            input.trim()
                    );

            if (cgpa < 0 || cgpa > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "CGPA must be between 0 and 10."
                );

                return;
            }

            student.setCgpa(cgpa);

            manager.updateStudent(student);

            loadTable();

            JOptionPane.showMessageDialog(
                    this,
                    "CGPA updated successfully."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid CGPA."
            );
        }
    }
}