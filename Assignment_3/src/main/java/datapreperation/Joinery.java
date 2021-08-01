package datapreperation;

import joinery.DataFrame;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.GGPlot2Theme;

public class Joinery {
    public static void main(String args[]){
        try {
            DataFrame<Object> df= DataFrame.readCsv ("src/main/resources/data/titanic.csv");

            DataFrame<Object> df_info= df.describe ();
            System.out.println (df_info.toString ());

            System.out.println ("==================================== Mean ============================================");
            DataFrame<Object>  df_mean= df.retain("pclass", "age", "fare")
                    .groupBy("pclass")
                    .mean ();
            System.out.println(df_mean.toString());
            System.out.println ("======================================================================================");

            System.out.println ("===================================== Min ============================================");
            DataFrame<Object>  df_min= df.retain("pclass", "age", "fare")
                    .groupBy("pclass")
                    .min ();
            System.out.println(df_min.toString());
            System.out.println ("======================================================================================");

            System.out.println ("===================================== Max ============================================");
            DataFrame<Object>  df_max= df.retain("pclass", "age", "fare")
                    .groupBy("pclass")
                    .max ();
            System.out.println(df_max.toString());
            System.out.println ("======================================================================================");

            System.out.println ("============================= Standard Deviation =====================================");
            DataFrame<Object>  df_std= df.retain("pclass", "age", "fare")
                    .groupBy("pclass")
                    .stddev();
            System.out.println(df_std.toString());
            System.out.println ("======================================================================================");

            System.out.println ("==================================== Count ===========================================");
            DataFrame<Object>  df_count= df.retain("pclass", "age", "fare")
                    .groupBy("pclass")
                    .count();
            System.out.println(df_count.toString());
            System.out.println ("======================================================================================");


            /* Generate and Display Bar Chart */

            DataFrame<Object>  p_count= df.groupBy("pclass")
                                          .count()
                                          .retain("pclass", "name");

            List<String> p_classes = new ArrayList<>();
            List<Integer> class_count = new ArrayList<>();
            for (int i = 0; i < p_count.length(); i++) {
                p_classes.add(p_count.get(i, 0).toString());
                class_count.add(Integer.valueOf(p_count.get(i, 1).toString()));
            }

            CategoryChart barChart = new CategoryChartBuilder().width(800).height(700).title("number of passengers").xAxisTitle("Class").yAxisTitle("Count").build();
            barChart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            barChart.getStyler().setHasAnnotations(true);
            barChart.addSeries("Titanic", p_classes, class_count);
            new SwingWrapper(barChart).displayChart();

            DataFrame<Object> df_age_fare = df.retain("age", "fare").dropna();
            List<Double> ages = new ArrayList<>();
            List<Double> fares = new ArrayList<>();
            for (int i = 0; i < df_age_fare.length(); i++) {
                ages.add(Double.parseDouble(df_age_fare.get(i, 0).toString()));
                fares.add(Double.parseDouble(df_age_fare.get(i, 1).toString()));
            }

            CategoryChart histChart = new CategoryChartBuilder().width(1000).height(700).title("Fare Histogram").xAxisTitle("Fares").yAxisTitle("Count").build();
            Histogram histogram = new Histogram(fares,10);
            histChart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            histChart.getStyler().setHasAnnotations(true);
            histChart.addSeries("Fares", histogram.getxAxisData(), histogram.getxAxisData());
            new SwingWrapper(histChart).displayChart();


        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
