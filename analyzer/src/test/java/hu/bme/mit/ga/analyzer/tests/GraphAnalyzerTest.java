package hu.bme.mit.ga.analyzer.tests;

import hu.bme.mit.ga.base.metrics.Metric;
import hu.bme.mit.ga.analyzer.GraphAnalyzer;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static hu.bme.mit.ga.metrics.impl.GraphMetricsEnum.NumberOfEdges;
import static hu.bme.mit.ga.metrics.impl.GraphMetricsEnum.NumberOfNodes;

public class GraphAnalyzerTest {

    protected GraphAnalyzer analyzer;

    public GraphAnalyzerTest() {
        PropertyConfigurator.configure("src/log4j.properties");
        Logger.getRootLogger().setLevel(Level.OFF);
    }

    @BeforeMethod
    public void init() {
        analyzer = new GraphAnalyzer();
    }

    protected void checkSize(int expected) {
        AssertJUnit.assertEquals(expected, analyzer.getMetrics().keySet().size());
        AssertJUnit.assertEquals(expected, analyzer.getMetricsInOrder().size());
    }

    protected void checkMetric(GraphMetricsEnum metric) {
        Metric metricObject = analyzer.getMetric(metric);
        AssertJUnit.assertNotNull(metricObject);
        try {
            AssertJUnit.assertTrue(metric.instantiate().getClass().isInstance(metricObject));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUse() {
        analyzer.use(NumberOfEdges);
        checkSize(1);
    }

    @Test
    public void testMultipleDifferentUse() {
        analyzer.use(NumberOfEdges);
        analyzer.use(NumberOfNodes);
        checkSize(2);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testMultipleSameUse() {
        analyzer.use(NumberOfEdges);
        analyzer.use(NumberOfEdges);
    }

    @Test
    public void testGetMetric() {
        analyzer.use(NumberOfEdges);
        analyzer.use(NumberOfNodes);
        checkMetric(NumberOfEdges);
        checkMetric(NumberOfNodes);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIncorrectUseAll() {
        analyzer.use(NumberOfEdges);
        analyzer.useAll();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIncorrectOmit() {
        analyzer.omit(NumberOfNodes);
    }

    @Test
    public void testOmittingAbsenceMetric() {
        analyzer.use(NumberOfEdges).omit(NumberOfNodes);
        checkSize(1);
        checkMetric(NumberOfEdges);
    }

    @Test
    public void testOmittingExistingMetric() {
        analyzer.use(NumberOfNodes).omit(NumberOfNodes);
        checkSize(0);
    }

    @Test(enabled = false)
    public void testOrder() {
        Assert.fail("Not tested yet");
    }

}
