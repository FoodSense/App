package com.example.nikma.fs20;

public class Object {
    private String objName;
    private String objDTS;
    private String objWeight;

    public Object() {}

    public Object(String objDTS, String objName, String objWeight) {
        this.objName = objName;
        this.objDTS = objDTS;
        this.objWeight = objWeight;
    }



    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjDTS() {
        return objDTS;
    }

    public void setObjDTS(String objDTS) {
        this.objDTS = objDTS;
    }

    public String getObjWeight() {
        return objWeight;
    }

    public void setObjWeight(String objWeight) {
        this.objWeight = objWeight;
    }
}
