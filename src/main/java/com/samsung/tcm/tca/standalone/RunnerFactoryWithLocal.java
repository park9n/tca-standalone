package com.samsung.tcm.tca.standalone;

public class RunnerFactoryWithLocal extends RunnerFactory {
    SchemaFetchRunnerWithLocal schemaFetchRunnerWithLocal = null;

    public SchemaFetchRunner getSchemaFecthRunner() {
        return schemaFetchRunnerWithLocal;
    }
}
