package bank.management.system;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class Practice {
    boolean negative;

    public Practice() {
        this(false);
    }

    public Practice(final boolean negative) {
        this.negative = negative;
    }

    public String convert(final BufferedImage image) {
        StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            if (sb.length() != 0) sb.append("\n");
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                double gValue = 0.2989 * pixelColor.getRed()
                        + 0.5870 * pixelColor.getGreen()
                        + 0.1140 * pixelColor.getBlue();
                final char s = negative ? returnStrNeg(gValue) : returnStrPos(gValue);
                sb.append(s);
            }
        }
        return sb.toString();
    }

    private char returnStrPos(double g) {
        if (g >= 230.0) return ' ';
        else if (g >= 200.0) return '.';
        else if (g >= 180.0) return '*';
        else if (g >= 160.0) return ':';
        else if (g >= 130.0) return 'o';
        else if (g >= 100.0) return '&';
        else if (g >= 70.0) return '8';
        else if (g >= 50.0) return '#';
        else return '@';
    }

    private char returnStrNeg(double g) {
        if (g >= 230.0) return '@';
        else if (g >= 200.0) return '#';
        else if (g >= 180.0) return '8';
        else if (g >= 160.0) return '&';
        else if (g >= 130.0) return 'o';
        else if (g >= 100.0) return ':';
        else if (g >= 70.0) return '*';
        else if (g >= 50.0) return '.';
        else return ' ';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "gif", "png"));
            while (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    File f = fileChooser.getSelectedFile();
                    final BufferedImage image = ImageIO.read(f);
                    if (image == null) throw new IllegalArgumentException(f + " is not a valid image.");
                    final String ascii = new Practice().convert(image);

                    int fontSize = Math.max(5, 800 / image.getWidth());
                    final JTextArea textArea = new JTextArea(ascii, image.getHeight(), image.getWidth());
                    textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
                    textArea.setEditable(false);

                    final JDialog dialog = new JOptionPane(new JScrollPane(textArea), JOptionPane.PLAIN_MESSAGE)
                            .createDialog("ASCII Art - " + f.getName());
                    dialog.setResizable(true);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            System.exit(0);
        });
    }
}