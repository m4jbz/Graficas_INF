package iti.informatica.graficas;

import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class Graficas extends JFrame {
    public static void main(String[] args) {
        Datos datos = new Datos();
        // Grafica pastel = new Grafica(datos.ejemploPastelSimple());
        // Grafica barras = new Grafica(datos.ejemploBarrasSimple());
        // Grafica lineas = new Grafica(datos.ejemploLineasSimple());
        Grafica histograma = new Grafica(datos.ejemploHistrogramaSimple());
        histograma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        histograma.pack();
        histograma.setVisible(true);
    }
}

class Datos {
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

    public JFreeChart ejemploHistrogramaSimple() {
        HistogramDataset datos = new HistogramDataset();
        Random random = new Random();

        double[] valores = new double[100];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = 1.0 + (random.nextDouble() * 5.0); // Genera valores entre 1.0 y 6.0
        }
        datos.addSeries("Frecuencia", valores, 10);

        JFreeChart histograma = ChartFactory.createHistogram(
            "Distribucion de frequencia",
            "Valor",
            "Frequencia",
            datos,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        XYPlot plot = histograma.getXYPlot();
        XYBarRenderer renderer = new XYBarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                return new Color(random.nextInt(50), random.nextInt(256), 150 + random.nextInt(106)); 
            }
        };

        // Quita efectos que no me gustan
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardXYBarPainter());

        plot.setRenderer(renderer);

        return histograma;
    }

    public JFreeChart ejemploLineasSimple() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.addValue(5000, "Ventas", "Enero");
        datos.addValue(7000, "Ventas", "Febrero");
        datos.addValue(8000, "Ventas", "Marzo");
        datos.addValue(6000, "Ventas", "Abril");
        datos.addValue(11000, "Ventas", "Mayo");

        JFreeChart grafico_lineas = ChartFactory.createLineChart(
                "Ventas Mensuales",   // Título
                "Mes",                // Etiqueta eje X
                "Ventas ($)",         // Etiqueta eje Y
                datos,       // Datos
                PlotOrientation.VERTICAL,
                true, true, false
        );

        return grafico_lineas;
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

class Grafica extends JFrame {
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
