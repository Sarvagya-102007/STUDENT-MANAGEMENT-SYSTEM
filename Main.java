import java.awt.*;
import java.util.LinkedHashMap;
import javax.swing.*;

public class Main extends JFrame {

    private final StudentManager manager;
    private JPanel contentPanel;

    public static final Color BACKGROUND =
            new Color(18, 18, 28);

    public static final Color SIDEBAR =
            new Color(24, 24, 38);

    public static final Color CARD =
            new Color(30, 30, 45);

    public static final Color TEXT =
            new Color(245, 245, 250);

    public static final Color SECONDARY =
            new Color(165, 165, 180);

    public static final Color ACCENT =
            new Color(99, 102, 241);

    public static final Color GREEN =
            new Color(34, 197, 94);

    public static final Color ORANGE =
            new Color(249, 115, 22);

    public static final Color RED =
            new Color(239, 68, 68);

    public Main() {

        manager = new StudentManager();

        setTitle(
                "Student Management System"
        );

        setSize(
                1350,
                800
        );

        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createInterface();

        showDashboard();
    }

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        230,
                        800
                )
        );

        sidebar.setBackground(
                SIDEBAR
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo =
                new JLabel(
                        "  STUDENT HUB"
                );

        logo.setForeground(TEXT);

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        logo.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        10,
                        30,
                        10
                )
        );

        sidebar.add(logo);

        JButton dashboardButton =
                createSidebarButton(
                        "Dashboard"
                );

        JButton studentsButton =
                createSidebarButton(
                        "Students"
                );

        JButton academicsButton =
                createSidebarButton(
                        "Academics"
                );

        JButton attendanceButton =
                createSidebarButton(
                        "Attendance"
                );

        JButton reportsButton =
                createSidebarButton(
                        "Reports"
                );

        sidebar.add(dashboardButton);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(studentsButton);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(academicsButton);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(attendanceButton);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(reportsButton);

        sidebar.add(
                Box.createVerticalGlue()
        );

        JLabel footer =
                new JLabel(
                        "<html><center>" +
                        "Java Swing Application<br>" +
                        "Student Management System" +
                        "</center></html>"
                );

        footer.setForeground(
                SECONDARY
        );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        footer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(footer);

        sidebar.add(
                Box.createVerticalStrut(20)
        );

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        setContentPane(mainPanel);

        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        studentsButton.addActionListener(
                e -> showStudents()
        );

        academicsButton.addActionListener(
                e -> showAcademics()
        );

        attendanceButton.addActionListener(
                e -> showAttendance()
        );

        reportsButton.addActionListener(
                e -> showReports()
        );
    }

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        210,
                        48
                )
        );

        button.setPreferredSize(
                new Dimension(
                        210,
                        48
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        10
                )
        );

        button.setBackground(
                SIDEBAR
        );

        button.setForeground(
                TEXT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        return button;
    }

    private void showDashboard() {

        contentPanel.removeAll();

        JPanel dashboard =
                new JPanel();

        dashboard.setBackground(
                BACKGROUND
        );

        dashboard.setLayout(
                new BoxLayout(
                        dashboard,
                        BoxLayout.Y_AXIS
                )
        );

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                BACKGROUND
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        15,
                        30
                )
        );

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Student Management System • Overview & Statistics"
                );

        subtitle.setForeground(
                SECONDARY
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel heading =
                new JPanel();

        heading.setBackground(
                BACKGROUND
        );

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

        dashboard.add(header);

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                18,
                                0
                        )
                );

        cards.setBackground(
                BACKGROUND
        );

        cards.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        30,
                        20,
                        30
                )
        );

        cards.add(
                createStatCard(
                        "TOTAL STUDENTS",
                        String.valueOf(
                                manager.getTotalStudents()
                        ),
                        ACCENT
                )
        );

        cards.add(
                createStatCard(
                        "CSE STUDENTS",
                        String.valueOf(
                                manager.getCourseCount(
                                        "CSE"
                                )
                        ),
                        GREEN
                )
        );

        cards.add(
                createStatCard(
                        "AIML STUDENTS",
                        String.valueOf(
                                manager.getCourseCount(
                                        "AIML"
                                )
                        ),
                        ORANGE
                )
        );

        cards.add(
                createStatCard(
                        "AVERAGE CGPA",
                        String.format(
                                "%.2f",
                                manager.getAverageCGPA()
                        ),
                        RED
                )
        );

        dashboard.add(cards);

        JPanel charts =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        charts.setBackground(
                BACKGROUND
        );

        charts.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        30,
                        20,
                        30
                )
        );

        LinkedHashMap<String, Integer>
                departmentData =
                new LinkedHashMap<>();

        departmentData.put(
                "CSE",
                manager.getCourseCount("CSE")
        );

        departmentData.put(
                "AIML",
                manager.getCourseCount("AIML")
        );

        departmentData.put(
                "ECE",
                manager.getCourseCount("ECE")
        );

        departmentData.put(
                "ME",
                manager.getCourseCount("ME")
        );

        charts.add(
                new ChartPanel(
                        departmentData,
                        "Students by Department"
                )
        );

        LinkedHashMap<String, Integer>
                semesterData =
                new LinkedHashMap<>();

        for (int i = 1; i <= 8; i++) {

            int count = 0;

            for (Student student :
                    manager.getStudents()) {

                if (
                        student.getSemester()
                                == i
                ) {

                    count++;
                }
            }

            semesterData.put(
                    "S" + i,
                    count
            );
        }

        charts.add(
                new ChartPanel(
                        semesterData,
                        "Students by Semester"
                )
        );

        dashboard.add(charts);

        JPanel information =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        information.setBackground(
                BACKGROUND
        );

        information.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        30,
                        30,
                        30
                )
        );

        information.add(
                createInfoCard(
                        "Average Attendance",
                        String.format(
                                "%.1f%%",
                                manager
                                        .getAverageAttendance()
                        ),
                        "Overall attendance of registered students"
                )
        );

        information.add(
                createInfoCard(
                        "System Status",
                        "ONLINE",
                        "Local student database is running"
                )
        );

        dashboard.add(information);

        contentPanel.add(
                dashboard,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createStatCard(
            String title,
            String value,
            Color accent) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        50,
                                        50,
                                        70
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JPanel accentPanel =
                new JPanel();

        accentPanel.setBackground(
                accent
        );

        accentPanel.setPreferredSize(
                new Dimension(
                        5,
                        70
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                SECONDARY
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(
                TEXT
        );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JPanel content =
                new JPanel();

        content.setBackground(CARD);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.add(titleLabel);

        content.add(
                Box.createVerticalStrut(8)
        );

        content.add(valueLabel);

        card.add(
                accentPanel,
                BorderLayout.WEST
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    private JPanel createInfoCard(
            String title,
            String value,
            String description) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        50,
                                        50,
                                        70
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                SECONDARY
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(
                TEXT
        );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setForeground(
                SECONDARY
        );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        JPanel content =
                new JPanel();

        content.setBackground(CARD);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.add(titleLabel);

        content.add(
                Box.createVerticalStrut(7)
        );

        content.add(valueLabel);

        content.add(
                Box.createVerticalStrut(7)
        );

        content.add(descriptionLabel);

        panel.add(
                content,
                BorderLayout.CENTER
        );

        return panel;
    }

    private void showStudents() {

        contentPanel.removeAll();

        contentPanel.add(
                new StudentPanel(
                        manager,
                        this::showStudents,
                        this::showAddStudent
                ),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAcademics() {

        contentPanel.removeAll();

        contentPanel.add(
                new AcademicPanel(manager),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAttendance() {

        contentPanel.removeAll();

        contentPanel.add(
                new AttendancePanel(manager),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showReports() {

        contentPanel.removeAll();

        contentPanel.add(
                new ReportPanel(manager),
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAddStudent() {

        JTextField idField =
                new JTextField();

        JTextField nameField =
                new JTextField();

        JTextField courseField =
                new JTextField();

        JTextField semesterField =
                new JTextField();

        JTextField emailField =
                new JTextField();

        JTextField phoneField =
                new JTextField();

        JTextField cgpaField =
                new JTextField();

        JTextField totalClassesField =
                new JTextField();

        JTextField attendedClassesField =
                new JTextField();

        JPanel form =
                new JPanel(
                        new GridLayout(
                                9,
                                2,
                                10,
                                10
                        )
                );

        form.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        form.add(
                new JLabel("Student ID:")
        );

        form.add(idField);

        form.add(
                new JLabel("Name:")
        );

        form.add(nameField);

        form.add(
                new JLabel("Course:")
        );

        form.add(courseField);

        form.add(
                new JLabel("Semester:")
        );

        form.add(semesterField);

        form.add(
                new JLabel("Email:")
        );

        form.add(emailField);

        form.add(
                new JLabel("Phone:")
        );

        form.add(phoneField);

        form.add(
                new JLabel("CGPA:")
        );

        form.add(cgpaField);

        form.add(
                new JLabel("Total Classes:")
        );

        form.add(totalClassesField);

        form.add(
                new JLabel("Attended Classes:")
        );

        form.add(attendedClassesField);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        form,
                        "Add New Student",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String id =
                    idField
                            .getText()
                            .trim();

            String name =
                    nameField
                            .getText()
                            .trim();

            String course =
                    courseField
                            .getText()
                            .trim();

            int semester =
                    Integer.parseInt(
                            semesterField
                                    .getText()
                                    .trim()
                    );

            String email =
                    emailField
                            .getText()
                            .trim();

            String phone =
                    phoneField
                            .getText()
                            .trim();

            double cgpa =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            int totalClasses =
                    Integer.parseInt(
                            totalClassesField
                                    .getText()
                                    .trim()
                    );

            int attendedClasses =
                    Integer.parseInt(
                            attendedClassesField
                                    .getText()
                                    .trim()
                    );

            if (
                    id.isEmpty()
                    || name.isEmpty()
                    || course.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID, Name and Course are required."
                );

                return;
            }

            if (
                    manager.findStudent(id)
                            != null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID already exists."
                );

                return;
            }

            if (
                    semester < 1
                    || semester > 8
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semester must be between 1 and 8."
                );

                return;
            }

            if (
                    cgpa < 0
                    || cgpa > 10
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "CGPA must be between 0 and 10."
                );

                return;
            }

            if (
                    totalClasses < 0
                    || attendedClasses < 0
                    || attendedClasses > totalClasses
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid attendance values."
                );

                return;
            }

            Student student =
                    new Student(
                            id,
                            name,
                            course,
                            semester,
                            email,
                            phone,
                            cgpa,
                            totalClasses,
                            attendedClasses
                    );

            manager.addStudent(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

            showStudents();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );
        }
    }

    public static void main(
            String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager
                            .getSystemLookAndFeelClassName()
            );

        } catch (ClassNotFoundException
                 | InstantiationException
                 | IllegalAccessException
                 | UnsupportedLookAndFeelException ignored) {
        }

        SwingUtilities.invokeLater(
                () -> {

                    Main app =
                            new Main();

                    app.setVisible(true);
                }
        );
    }
}