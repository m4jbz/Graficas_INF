package iti.informatica.graficas;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Grafica extends JFrame {
    public Grafica(JFreeChart datos) {
        // Tamaño de la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // Lo divido entre eso porque segun yo da una buena relacion anchura-altura
        width /= 2.5;
        height /= 1.8;

        // Visualiza la grafica
        ChartPanel panel = new ChartPanel(datos);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(width, height));

        setContentPane(panel);
    }
}