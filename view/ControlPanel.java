package view;

import javax.swing.*;

public class ControlPanel extends JPanel {
    // Knoppen
    public JButton startBtn = new JButton("Start");
    public JButton pauseBtn = new JButton("Pauze");
    public JButton resumeBtn = new JButton("Hervatten");
    public JButton resetBtn = new JButton("Reset");
    public JButton fasterBtn = new JButton("Sneller");
    public JButton slowerBtn = new JButton("Langzamer");

    // Statistieken
    public JLabel statsLabel = new JLabel("Ticks: 0 | Conway: 0 | Alternatief: 0");

    // Constructor
    public ControlPanel() {
        this.add(startBtn);
        this.add(pauseBtn);
        this.add(resumeBtn);
        this.add(resetBtn);
        this.add(fasterBtn);
        this.add(slowerBtn);
        this.add(statsLabel);
    }

    // Wordt aangeroepen door de StatisticsTracker
    public void updateStats(long ticks, int conwayCount, int altCount) {
        statsLabel.setText("Ticks: " + ticks + " | Conway: " + conwayCount + " | Alternatief: " + altCount);
    }
}