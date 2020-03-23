package com.samsung.tcm.tca.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnerFactory {
    private Logger logger = LoggerFactory.getLogger(RunnerFactory.class);

    SchemaFetchRunner schemaFetchRunner = null;

    public SchemaFetchRunner getSchemaFecthRunner() {
        return schemaFetchRunner;
    }
}
