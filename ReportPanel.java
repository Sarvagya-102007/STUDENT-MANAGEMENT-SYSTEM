import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ReportPanel extends JPanel {

    private final StudentManager manager;

    private JTable table;
    private DefaultTableModel model;

    public ReportPanel(StudentManager manager) {

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

        JPanel heading = new JPanel();

        heading.setBackground(Main.BACKGROUND);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Reports");

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
                        "Generate and export student reports"
                );

        subtitle.setForeground(Main.SECONDARY);

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

        JButton refreshButton =
                new JButton(
                        "Refresh Report"
                );

        JButton exportButton =
                new JButton(
                        "Export CSV"
                );

        bottom.add(refreshButton);
        bottom.add(exportButton);

        add(
                bottom,
                BorderLayout.SOUTH
        );

        refreshButton.addActionListener(
                e -> loadTable()
        );

        exportButton.addActionListener(
                e -> exportCSV()
        );
    }

    private void loadTable() {

        model.setRowCount(0);

        for (Student student :
                manager.getStudents()) {

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
                            String.format(
                                    "%.1f%%",
                                    student.getAttendance()
                            )
                    }
            );
        }
    }

    private void exportCSV() {

        try {

            try (FileWriter writer =
                    new FileWriter(
                            "student_report.csv"
                    )) {

            writer.write(
                    "ID,Name,Course,Semester,CGPA,Grade,Attendance\n"
            );

            for (Student student :
                    manager.getStudents()) {

                writer.write(
                        escape(student.getId())
                                + ","
                                + escape(student.getName())
                                + ","
                                + escape(student.getCourse())
                                + ","
                                + student.getSemester()
                                + ","
                                + String.format(
                                        "%.2f",
                                        student.getCgpa()
                                )
                                + ","
                                + student.getGrade()
                                + ","
                                + String.format(
                                        "%.1f",
                                        student.getAttendance()
                                )
                                + "\n"
                );
            }

            }

            JOptionPane.showMessageDialog(
                    this,
                    "Report exported successfully."
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to export report."
            );
        }
    }

    private String escape(String text) {

        if (
                text.contains(",")
                || text.contains("\"")
        ) {

            return "\"" +
                    text.replace(
                            "\"",
                            "\"\""
                    ) +
                    "\"";
        }

        return text;
    }
}