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

        Datos datos = new Datos();
        GraficaPastel ventana = new GraficaPastel(datos.ejemploPastelSimple());
        // GraficaBarra ventana1 = new GraficaBarra(datos.ejemploBarrasSimple());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}

class Datos extends JFrame {
    public JFreeChart ejemploBarrasSimple() {
        int valor1 = 17;
        int valor2 = 12;
        int valor3 = 4;
        int valor4 = 8;
        int valor5 = 20;

        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(valor1, "", "Manzana");
        datos.setValue(valor2, "", "Naranja");
        datos.setValue(valor3, "", "Pera");
        datos.setValue(valor4, "", "Platano");
        datos.setValue(valor5, "", "Mandarina");

        JFreeChart grafico_barras = ChartFactory.createBarChart(
                "Fruta favorita", // Titulo de la gráfica
                "Frutas", // Eje X
                "Numero de estudiantes", // Eje Y
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
                    case 0: return Color.GREEN;
                    case 1: return Color.RED;
                    case 2: return Color.BLUE;
                    case 3: return Color.CYAN;
                    case 4: return Color.YELLOW;
                    // Si se llegan a añadir mas barras y no se le da un color se usará el que viene por defecto
                    default: return super.getItemPaint(row, column);
                }
            }
        };

        // Quita efectos que no me gustan
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardBarPainter());

        plot.setRenderer(renderer);

        return grafico_barras;
    }

    public JFreeChart ejemploPastelSimple() {
        double valor1 = 35;
        double valor2 = 25;
        double valor3 = 15;
        double valor4 = 10;
        double valor5 = 15;

        double total = valor1 + valor2 + valor3 + valor4 + valor5;

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Electronica", valor1/total*100);
        datos.setValue("Ropa", valor2/total*100);
        datos.setValue("Alimentos", valor3/total*100);
        datos.setValue("Hogar", valor4/total*100);
        datos.setValue("Otros", valor5/total*100);

        JFreeChart grafico_pastel = ChartFactory.createPieChart(
            "Ventas por categoría",
            datos,
            true, true, false
        );


        PiePlot plot = (PiePlot) grafico_pastel.getPlot();
        plot.setSectionPaint("Electronica", Color.RED);
        plot.setSectionPaint("Ropa", Color.GREEN);
        plot.setSectionPaint("Alimentos", Color.BLUE);
        plot.setSectionPaint("Hogar", Color.CYAN);
        plot.setSectionPaint("Otros", Color.YELLOW);


        return grafico_pastel;
    }
}

class GraficaPastel extends JFrame {
    public GraficaPastel(JFreeChart datos) {
        // Tamaño de la ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // Lo divido entre eso porque segun yo da una buena relacion anchura-altura
        width /= 2.5;
        height /= 1.8;

        ChartPanel panel = new ChartPanel(datos);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(width, height));

        setContentPane(panel);
    }
}

class GraficaBarra extends JFrame {
    public GraficaBarra(JFreeChart datos) {
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
