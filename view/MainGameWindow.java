package view;

import javax.swing.*;
import java.awt.*;

public class MainGameWindow extends JFrame {

    // Klasse krijgt (al gemaakte) view onderdelen binnen
    public MainGameWindow(GridBoardView gridView, ControlPanel controlPanel) {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Pakt tekenvel en stoppen het in een scrollbaar lijstje
        JScrollPane scrollPane = new JScrollPane(gridView);
        // Extra commentaar: Omdat het lijstje kleiner is dan het 1000x1000 tekenvel, verschijnen de scrollbalken.
        scrollPane.setPreferredSize(new Dimension(800, 600));

        // Voegt de scrollPane toe
        this.add(scrollPane, BorderLayout.CENTER);
        // Knoppen onderaan
        this.add(controlPanel, BorderLayout.SOUTH);

        // Zorgt dat het venster precies om de inhoud heen past
        this.pack();

        // Zet venster netjes gecentreerd in midden van scherm
        this.setLocationRelativeTo(null);

        // Maakt het zichtbaar
        this.setVisible(true);
    }
}