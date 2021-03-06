package hu.bme.mit.ga.metrics.tests;

import hu.bme.mit.ga.base.data.ScalarData;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;
import org.testng.Assert;

import java.util.function.Consumer;

public class NumberOfEdgesTest extends GraphMetricTest<ScalarData<Integer>> {

    @Override
    public GraphMetricsEnum getMetric() {
        return GraphMetricsEnum.NumberOfEdges;
    }

    @Override
    protected Object[] testCase(TestGraphInstances modelType) {
        Consumer<ScalarData<Integer>> checker = (data) -> {
        };
        switch (modelType) {
            case Loop_1T:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 1);
                };
                break;
            case Loop_2T:
            case Motif3N_1:
            case Motif3N_2:
            case Motif3N_4:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 2);
                };
                break;
            case Motif3N_3:
            case Motif3N_3_2T:
            case Motif3N_5:
            case Motif3N_7:
            case Motif3N_7_2T:
            case Motif3N_9:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 3);
                };
                break;
            case Motif3N_6:
            case Motif3N_6_2T:
            case Motif3N_8:
            case Motif3N_8_2T:
            case Motif3N_10:
            case Motif3N_10_2T:
            case Motif3N_11:
            case Motif3N_11_2T:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 4);
                };
                break;
            case Motif3N_12:
            case Motif3N_12_2T:
            case Motif5N_1_3T:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 5);
                };
                break;
            case Motif3N_13:
            case Motif3N_13_2T:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 6);
                };
                break;
            case Motif5N_2_3T:
                checker = (data) -> {
                    Assert.assertEquals(data.getValue().intValue(), 8);
                };
                break;
            default:
                skippedModel(modelType);
        }
        return new Object[]{modelType, checker};
    }

}
