package ch._42lausanne.swingy.view;

import ch._42lausanne.swingy.model.game.Map;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapPanel extends JPanel {
    private BufferedImage heroIcon, villainIcon, backgroundIcon;
    @Setter
    private Map map;


    public MapPanel(Dimension size) {
        this.setPreferredSize(size);
//        this.setBackground(new Color(86, 151, 76));
        this.setBackground(new Color(103, 165, 94));
        try {
            this.heroIcon = ImageIO.read(getClass().getResource("/boy_down_1.png"));
            this.villainIcon = ImageIO.read(getClass().getResource("/skeletonlord_phase2_down_2.png"));
            this.backgroundIcon = ImageIO.read(getClass().getResource("/grass01.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int CELL_SIZE = (int) (this.getHeight() / map.getMapSize().getHeight());

        super.paintComponent(g);
        if (map != null) {
            for (int y = 0; y < map.getMapSize().getWidth(); y++) {
                for (int x = 0; x < map.getMapSize().getHeight(); x++) {
                    int cellValue = map.getMapGrid()[x][y];
                    Image icon = switch (cellValue) {
                        case 0 -> backgroundIcon;
                        case 1, 2, 3, 4 -> heroIcon;
                        case 5 -> villainIcon;
                        default -> throw new IllegalStateException("Unexpected value: " + cellValue);
                    };
                    Image resizedIcon = icon.getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_DEFAULT);
                    g.drawImage(resizedIcon, CELL_SIZE * x, CELL_SIZE * y, null);
                }
            }
        }
    }
}
