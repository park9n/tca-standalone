package com.samsung.tcm.tca.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCMContext {
    private Logger logger = LoggerFactory.getLogger(TCMContext.class);

    private static TCMContext context = new TCMContext();
    private static String ID = null;

    private TCMContext() {
    }

    public static TCMContext getInstance() {
        return context;
    }

    public static String getProjectName() {
        return "Tizen2020";
    }

    public void setID(String id) {
        ID = id;
    }

    public String getID() {
        return ID;
    }
}
