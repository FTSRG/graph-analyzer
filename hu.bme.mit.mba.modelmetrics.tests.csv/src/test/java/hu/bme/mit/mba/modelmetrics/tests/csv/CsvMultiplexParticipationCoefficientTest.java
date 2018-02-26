package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class CsvMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }

}
