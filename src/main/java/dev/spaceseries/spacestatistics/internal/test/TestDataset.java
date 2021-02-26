package dev.spaceseries.spacestatistics.internal.test;

import java.util.HashMap;
import java.util.Map;

public class TestDataset extends Dataset<String, Integer> {

    /**
     * Construct test dataset
     */
    public TestDataset() {
        // make set
        Map<String, Integer> dataset = new HashMap<>();

        dataset.put("Jacob", 31);
        dataset.put("Carol", 33);
        dataset.put("Matt", 1);
        dataset.put("June", 513);

        // set
        this.setSet(dataset);
    }
}
