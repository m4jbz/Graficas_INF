package iti.informatica.graficas;

import java.awt.Color;
import java.awt.Paint;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

public class Datos {
    Color[] palette = {
        new Color(0xF7374F),
        new Color(0x88304E),
        new Color(0x522546),
        new Color(0x2C2C2C),
        new Color(0xCC192F)
    };

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
                return palette[column % palette.length]; // Repite la paleta si hay más barras
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
                return palette[column % palette.length]; // Repite la paleta si hay más barras
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
        plot.setSectionPaint("Electronica", palette[0]);
        plot.setSectionPaint("Ropa", palette[1]);
        plot.setSectionPaint("Alimentos", palette[2]);
        plot.setSectionPaint("Hogar", palette[3]);
        plot.setSectionPaint("Otros", palette[4]);


        return grafico_pastel;
    }
}
