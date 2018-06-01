package com.inql.psqljdbc.model;

import java.util.List;

public class Data {

    private List<String> columnNames;
    private List<List<String>> values;

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }
}
