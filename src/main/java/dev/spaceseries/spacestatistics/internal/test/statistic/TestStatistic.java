package dev.spaceseries.spacestatistics.internal.test.statistic;

import dev.spaceseries.spacestatistics.api.Statistic;
import dev.spaceseries.spacestatistics.internal.test.Dataset;
import dev.spaceseries.spacestatistics.internal.test.TestDataset;
import dev.spaceseries.spacestatistics.model.SortMode;

public class TestStatistic extends Statistic<String, Integer> {

    /**
     * Test dataset
     */
    private final Dataset<String, Integer> testDataset;

    /**
     * Construct statistic
     */
    public TestStatistic() {
        super("test", SortMode.NORMAL);

        this.testDataset = new TestDataset();


        // initialize keys
        update(testDataset.getSet().keySet(), true, true);
    }

    @Override
    protected Integer get(String key) {
        return testDataset.getSet().get(key);
    }

    @Override
    protected String keyAsString(String s) {
        return s;
    }

    @Override
    protected String valueAsString(Integer integer) {
        return integer.toString();
    }
}
