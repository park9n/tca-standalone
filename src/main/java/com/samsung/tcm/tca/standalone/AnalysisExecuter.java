package com.samsung.tcm.tca.standalone;

public class AnalysisExecuter {
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
