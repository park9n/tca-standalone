package com.samsung.tcm.tca.standalone;

public class SchemaFetchRunnerWithLocal extends SchemaFetchRunner {
    @Override
    public int run(int a, int b) {
        TCMContext context = TCMContext.getInstance();
        context.setID("Tizen");
        return a + b;
    }
}
