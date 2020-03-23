package com.samsung.tcm.tca.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnerFactoryWithLocal extends RunnerFactory {
    private Logger logger = LoggerFactory.getLogger(RunnerFactoryWithLocal.class);

    SchemaFetchRunnerWithLocal schemaFetchRunnerWithLocal = null;

    public SchemaFetchRunner getSchemaFecthRunner() {
        return schemaFetchRunnerWithLocal;
    }
}
