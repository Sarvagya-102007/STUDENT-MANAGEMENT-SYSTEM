import java.awt.*;
import java.util.Map;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {

    private Map<String, Integer> data;
    private final String title;

    public ChartPanel(Map<String, Integer> data, String title) {

        this.data = data;
        this.title = title;

        setBackground(Main.CARD);
        setPreferredSize(new Dimension(600, 300));
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics.create();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setColor(Main.TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString(title, 25, 35);

        if (data == null || data.isEmpty()) {

            g.setColor(Main.SECONDARY);
            g.setFont(new Font("Arial", Font.PLAIN, 14));

            g.drawString(
                    "No data available.",
                    25,
                    75
            );

            g.dispose();
            return;
        }

        int chartX = 50;
        int chartY = 70;

        int chartWidth = getWidth() - 80;
        int chartHeight = getHeight() - 120;

        int max = 1;

        for (int value : data.values()) {

            if (value > max) {
                max = value;
            }
        }

        int count = data.size();

        int gap = 20;

        int barWidth =
                Math.max(
                        25,
                        (chartWidth - gap * count) / count
                );

        int index = 0;

        for (Map.Entry<String, Integer> entry : data.entrySet()) {

            int value = entry.getValue();

            int barHeight =
                    (int) (
                            value /
                            (double) max *
                            chartHeight
                    );

            int x =
                    chartX +
                    index *
                    (barWidth + gap);

            int y =
                    chartY +
                    chartHeight -
                    barHeight;

            g.setColor(Main.ACCENT);

            g.fillRoundRect(
                    x,
                    y,
                    barWidth,
                    barHeight,
                    10,
                    10
            );

            g.setColor(Main.TEXT);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );

            String valueText =
                    String.valueOf(value);

            g.drawString(
                    valueText,
                    x + barWidth / 2 - 4,
                    y - 8
            );

            g.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            11
                    )
            );

            String label = entry.getKey();

            g.drawString(
                    label,
                    x + barWidth / 2 - 10,
                    chartY + chartHeight + 25
            );

            index++;
        }

        g.dispose();
    }
}