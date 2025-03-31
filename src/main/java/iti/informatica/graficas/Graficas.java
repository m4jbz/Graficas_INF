package iti.informatica.graficas;

import java.awt.*;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;

public class Graficas extends JFrame {
    public static void main(String[] args) {

        GraficaPastel ventana = new GraficaPastel();
        // GraficaBarra ventana = new GraficaBarra();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}

class GraficaPastel extends JFrame {
    public GraficaPastel() {
        double valor1 = 300;
        double valor2 = 450;
        double valor3 = 250;

        double total = valor1 + valor2 + valor3;

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Marco", valor1/total*100);
        datos.setValue("Cesar", valor2/total*100);
        datos.setValue("Aaron", valor3/total*100);

        JFreeChart grafico_pastel = ChartFactory.createPieChart(
            "Calificaciones",
            datos,
            true, true, false
        );


        PiePlot plot = (PiePlot) grafico_pastel.getPlot();
        plot.setSectionPaint("Marco", Color.BLACK);

        ChartPanel panel = new ChartPanel(grafico_pastel);
        panel.setMouseWheelEnabled(true);
        // Tamaño de la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // Lo divido entre eso porque segun yo da una buena relacion anchura-altura
        width /= 2.5;
        height /= 1.8;
        panel.setPreferredSize(new Dimension(width, height));

        setContentPane(panel);
    }
}

class GraficaBarra extends JFrame {
    public GraficaBarra() {
        int valor1 = 10;
        int valor2 = 100;
        int valor3 = 40;

        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(valor1, "Investigacion de Operaciones", "Marco");
        datos.setValue(valor2, "Investigacion de Operaciones", "Cesar");
        datos.setValue(valor3, "Investigacion de Operaciones", "Aaron");

        JFreeChart grafico_barras = ChartFactory.createBarChart(
                "Calificaciones de Investigacion de Operaciones", // Titulo de la gráfica
                "Estudiantes de Investigacion de Operaciones", // Eje X
                "Calificacion", // Eje Y
                datos,
                PlotOrientation.VERTICAL, // Posición de las barras
                true, true, false
        );

        CategoryPlot plot = grafico_barras.getCategoryPlot();
        BarRenderer renderer = new BarRenderer() {
            // Cambia el color de las barras por separado
            @Override
            public Paint getItemPaint(int row, int column) {
                switch (column) {
                    case 0: return Color.BLACK;
                    case 1: return Color.RED;
                    case 2: return Color.GREEN;
                    // Si se llegan a añadir mas barras y no se le da un color se usará el que viene por defecto
                    default: return super.getItemPaint(row, column);
                }
            }
        };

        // Quita efectos que no me gustan
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardBarPainter());

        plot.setRenderer(renderer);


        // Visualiza la grafica
        ChartPanel panel = new ChartPanel(grafico_barras);
        panel.setMouseWheelEnabled(true);
        // Tamaño de la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // Lo divido entre eso porque segun yo da una buena relacion anchura-altura
        width /= 2.5;
        height /= 1.8;
        panel.setPreferredSize(new Dimension(width, height));

        setContentPane(panel);
    }
}
