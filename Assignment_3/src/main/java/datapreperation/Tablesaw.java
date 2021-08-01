package datapreperation;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import tech.tablesaw.api.*;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tablesaw {
    Table titanicData;
    String dataPath = "src/main/resources/data/titanic.csv";

    public Tablesaw() {
    }

    public static void main(String[] args) {
        Tablesaw tda = new Tablesaw();
        try {
            tda.titanicData = tda.loadDataFromCVS (tda.dataPath);
            //getting the Structure of the data
            String structure = tda.getDataInfoStructure (tda.titanicData);
            System.out.println (structure);
            //getting Data summery
            System.in.read ();
            String summary = tda.getDataSummary (tda.titanicData);
            System.out.println (summary);
            System.in.read ();
            // Adding date Column
            Table dataWithDate = tda.addDateColumnToData (tda.titanicData);
            System.out.println ("=====================================================================================");
            System.out.println (dataWithDate.structure ());
            System.in.read ();
            //Sorting on the added Date Field
            Table sortedData = dataWithDate.sortAscendingOn ("Fake Date");

            //getting the first 10 rows
            System.out.println ("Printing the first rows of the table");
            System.in.read ();
            Table firstTenRows = sortedData.first (50);

            System.out.println (firstTenRows);
            System.in.read ();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Table mappedData = tda.mapTextColumnToNumber (tda.titanicData);
            Table firstFiveRows = mappedData.first (5);
            System.out.println ("=====================================================================================");
            System.out.println (firstFiveRows);

            //////////////////////////////// Display Scatter Plot //////////////////////////////////////////////////////
            //tda.drawScatterPlot(tda.titanicData);

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///  Load Data From CSV File
    public Table loadDataFromCVS(String path) throws IOException {
        Table titanicData = Table.read ().csv (path);
        return titanicData;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// get the structure of the data
    public String getDataInfoStructure(Table data) {
        Table dataStructure = data.structure ();
        return dataStructure.toString ();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //get Data Summary
    public String getDataSummary(Table data) {
        Table summary = data.summary ();
        return summary.toString ();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Adding Columns
    public Table addDateColumnToData(Table data) {
        int rowCount = data.rowCount ();
        List<LocalDate> dateList = new ArrayList<LocalDate> ();
        for (int i = 0; i < rowCount; i++) {
            dateList.add (LocalDate.of (2021, 3, i % 31 == 0 ? 1 : i % 31));
        }
        DateColumn dateColumn = DateColumn.create ("Fake Date", dateList);
        data.insertColumn (data.columnCount (), dateColumn);
        return data;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // mapping text data to numeric values on the sex field female=1,male=0 and adding a column named gender
    public Table mapTextColumnToNumber(Table data) {
        NumberColumn mappedGenderColumn = null;
        StringColumn gender = (StringColumn) data.column ("Sex");
        List<Number> mappedGenderValues = new ArrayList<Number> ();
        for (String v : gender) {
            if ((v != null) && (v.equals ("female"))) {
                mappedGenderValues.add (new Double (1));
            } else {
                mappedGenderValues.add (new Double (0));
            }
        }
        mappedGenderColumn = DoubleColumn.create ("gender", mappedGenderValues);
        data.addColumns (mappedGenderColumn);
        return data;
    }

    /*
    // Draw Scatter Plot
    public void drawScatterPlot(Table data) {
        data = data.dropRowsWithMissingValues();

        List age_str = data.column(4).asList();
        List fare_str = data.column(8).asList();

        for (int i = 0; i < 5; i++) {
            System.out.println("age_str = " + age_str.get(i));
            System.out.println("fare_str = " + fare_str.get(i));
        }

        List<Double> age = new ArrayList<>();
        for (int i = 0; i < age_str.size(); i++) {
            age.add(Double.parseDouble(age_str.get(i).toString()));
        }

        List<Double> fare = new ArrayList<>();
        for (int i = 0; i < fare_str.size(); i++) {
            fare.add(Double.parseDouble(fare_str.get(i).toString()));
        }

        XYChart scatterChart = new XYChartBuilder()
                .width(600)
                .height(500)
                .title("Age vs Fare")
                .xAxisTitle("Age")
                .yAxisTitle("Fare")
                .build();

        scatterChart.getStyler().setYAxisMin(0.0d);
        scatterChart.getStyler().setXAxisMin(0.0d);
        scatterChart.getStyler().setYAxisMax(1.0d);
        scatterChart.getStyler().setXAxisMax(1.0d);
        scatterChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        scatterChart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        scatterChart.getStyler().setMarkerSize(4);
        scatterChart.addSeries("Age vs Fare", age,fare);

        new SwingWrapper(scatterChart).displayChart();
    }*/
}

