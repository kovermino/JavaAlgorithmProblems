package com.profile;

import java.awt.Color;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Profiler extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private static final int timingLoopCount = 20;
	private static final int loopCountPerRound = 10;
	private static final int limitTimeFormeasure = 4;
	private Timeable timeable;

	public Profiler(String title, Timeable timeable) {
		super(title);
		this.timeable = timeable;
	}

	public interface Timeable {

		public void setup(int n);
		public void measureTime(int n);
		
	}

	public XYSeries timingLoop(int EntireLoopSize, int limitEndTime) {
        final XYSeries resultData = new XYSeries("Time (ms)");

		int n = EntireLoopSize;
		for (int i=0; i<timingLoopCount; i++) {
			// warm up 차원에서 한 번 실행
			timeIt(n);

			long totalElapsedTime = 0;

			for (int j=0; j<loopCountPerRound; j++) {
				totalElapsedTime += timeIt(n);
			}
			System.out.println(n + ", " + totalElapsedTime);

			if (totalElapsedTime > limitTimeFormeasure) {
				resultData.add(n, totalElapsedTime);
			}

			if (totalElapsedTime > limitEndTime) {
				break;
			}
			
			n *= 2;
		}
		return resultData;
	}

	public long timeIt(int n) {
		timeable.setup(n);
		final long startTime = System.currentTimeMillis();
		timeable.measureTime(n);
		final long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/**
	 * Plots the results.
	 *
	 * @param series
	 */
	public void plotResults(XYSeries series) {
		double slope = estimateSlope(series);
		System.out.println("Estimated slope= " + slope);

		final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        final JFreeChart chart = ChartFactory.createXYLineChart(
            "",          // chart title
            "",               // domain axis label
            "",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,
            false
        );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new LogarithmicAxis("Problem size (n)");
        final NumberAxis rangeAxis = new LogarithmicAxis("Runtime (ms)");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        chart.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
	}

	/**
	 * Uses simple regression to estimate the slope of the series.
	 *
	 * @param series
	 * @return
	 */
	public double estimateSlope(XYSeries series) {
		SimpleRegression regression = new SimpleRegression();

		for (Object item: series.getItems()) {
			XYDataItem xy = (XYDataItem) item;
			regression.addData(Math.log(xy.getXValue()), Math.log(xy.getYValue()));
		}
		return regression.getSlope();
	}
}