package com.samsung.tcm.tca.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaFetchRunnerWithLocal extends SchemaFetchRunner {
    private Logger logger = LoggerFactory.getLogger(SchemaFetchRunnerWithLocal.class);

    @Override
    public int run(int a, int b) {
        TCMContext context = TCMContext.getInstance();
        context.setID("Tizen");
        return a + b;
    }
}
