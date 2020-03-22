package com.samsung.tcm.tca.standalone;

public class TCMContext {
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
