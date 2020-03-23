package com.samsung.tcm.tca.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisExecuter {
    private Logger logger = LoggerFactory.getLogger(AnalysisExecuter.class);

    private SchemaFetchRunner schemaFetchRunner = null;

//    public AnalysisExecuter() {
//        schemaFetchRunner = new SchemaFetchRunner();
//    }

    public void execute(RunnerFactory runnerFactory, String outDir) {
        SchemaFetchRunner schemaFetchRunner = runnerFactory.getSchemaFecthRunner();
    }

    public int executeSum() {
//        schemaFetchRunner = new SchemaFetchRunner();
        return schemaFetchRunner.run(1, 2);
    }
}
