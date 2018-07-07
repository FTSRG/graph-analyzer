package hu.bme.mit.mba.modelmetrics.tests;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import org.testng.Assert;

import java.util.List;
import java.util.function.Consumer;

import static hu.bme.mit.mba.base.testutils.MapDataTesterUtil.checkKeysSize;
import static hu.bme.mit.mba.tests.model.ModelContext.*;

public class EdgeOverlapTest extends ModelMetricTest<MapData<List<String>, Double>> {

    @Override
    public ModelMetricsEnum getMetric() {
        return ModelMetricsEnum.EdgeOverlap;
    }

    @Override
    protected Object[] testCase(TestModelTypes modelType) {
        Consumer<MapData<String, Double>> checker = (data) -> {
        };
        switch (modelType) {
            case Loop_1T:
            case Motif3N_1:
            case Motif3N_2:
            case Motif3N_3:
            case Motif3N_4:
            case Motif3N_5:
            case Motif3N_6:
            case Motif3N_7:
            case Motif3N_8:
            case Motif3N_9:
            case Motif3N_10:
            case Motif3N_11:
            case Motif3N_12:
            case Motif3N_13:
                checker = (data) -> {
                    checkKeysSize(0, data);
                };
                break;
            case Motif3N_3_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    check(data, dim1, dim2, 1);
                    check(data, dim2, dim1, 0.5);
                };
                break;
            case Motif3N_13_2T:
            case Motif3N_8_2T:
            case Loop_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    check(data, dim1, dim2, 1.0);
                    check(data, dim2, dim1, 1.0);
                };
                break;

            case Motif3N_12_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    check(data, dim1, dim2, 1.0);
                    check(data, dim2, dim1, 2.0 / 3.0);
                };
                break;

            case Motif3N_10_2T:
            case Motif3N_6_2T:
            case Motif3N_11_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    check(data, dim1, dim2, 1.0);
                    check(data, dim2, dim1, 1.0 / 3.0);
                };
                break;

            case Motif3N_7_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    check(data, dim1, dim2, 1.0);
                    check(data, dim2, dim1, 1.0 / 2.0);
                };
                break;


            case Motif5N_1_3T:
                checker = (data) -> {
                    checkKeysSize(6, data);
                    check(data, dim1, dim2, 0.0);
                    check(data, dim2, dim1, 0.0);
                    check(data, dim3, dim2, 0.0);
                    check(data, dim2, dim3, 0.0);
                    check(data, dim3, dim1, 0.0);
                    check(data, dim1, dim3, 0.0);
                };
                break;

            case Motif5N_2_3T:
                checker = (data) -> {
                    checkKeysSize(6, data);
                    check(data, dim1, dim2, 2.0 / 3.0);
                    check(data, dim2, dim1, 2.0 / 3.0);
                    check(data, dim3, dim2, 0.0);
                    check(data, dim2, dim3, 0.0);
                    check(data, dim3, dim1, 0.0);
                    check(data, dim1, dim3, 0.0);
                };
                break;
            default:
                skippedModel(modelType);
        }
        return new Object[]{modelType, checker};
    }

    protected void check(MapData<String, Double> data, String firstType, String secondType, double expectedValue) {
        String key = getKey(firstType, secondType);
        Assert.assertEquals(data.get(key), expectedValue);
    }

    protected String getKey(String first, String second) {
        return String.format("%s-%s", first, second);
    }


}