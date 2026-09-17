import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AttendancePanel extends JPanel {

    private final StudentManager manager;

    private JTable table;
    private DefaultTableModel model;

    public AttendancePanel(StudentManager manager) {

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
                        "Attendance Management"
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
                        "Track classes attended and attendance percentage"
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
                "Total Classes",
                "Attended",
                "Attendance %",
                "Status"
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
                        "Update Attendance"
                );

        bottom.add(updateButton);

        add(
                bottom,
                BorderLayout.SOUTH
        );

        updateButton.addActionListener(
                e -> updateAttendance()
        );
    }

    private void loadTable() {

        model.setRowCount(0);

        for (Student student :
                manager.getStudents()) {

            double attendance =
                    student.getAttendance();

            String status;

            if (attendance >= 75) {
                status = "Good";
            } else if (attendance >= 60) {
                status = "Warning";
            } else {
                status = "Low";
            }

            model.addRow(
                    new Object[]{
                            student.getId(),
                            student.getName(),
                            student.getCourse(),
                            student.getTotalClasses(),
                            student.getAttendedClasses(),
                            String.format(
                                    "%.1f%%",
                                    attendance
                            ),
                            status
                    }
            );
        }
    }

    private void updateAttendance() {

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

        String totalInput =
                JOptionPane.showInputDialog(
                        this,
                        "Total Classes:",
                        student.getTotalClasses()
                );

        if (totalInput == null) {
            return;
        }

        String attendedInput =
                JOptionPane.showInputDialog(
                        this,
                        "Attended Classes:",
                        student.getAttendedClasses()
                );

        if (attendedInput == null) {
            return;
        }

        try {

            int total =
                    Integer.parseInt(
                            totalInput.trim()
                    );

            int attended =
                    Integer.parseInt(
                            attendedInput.trim()
                    );

            if (
                    total < 0
                    || attended < 0
                    || attended > total
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid attendance values."
                );

                return;
            }

            student.setTotalClasses(total);
            student.setAttendedClasses(attended);

            manager.updateStudent(student);

            loadTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance updated successfully."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );
        }
    }
}