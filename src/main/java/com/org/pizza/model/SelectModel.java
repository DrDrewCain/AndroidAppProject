package com.org.pizza.model;

public class SelectModel {
    private String value;
    private boolean select;

    public SelectModel() {

    }

    public SelectModel(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelect() {
        return select;
    }
}
