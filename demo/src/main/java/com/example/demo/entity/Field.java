package com.example.demo.entity;

/***+
 * This class is used to store the values of the fieldMappings.Name and value1 are the attributes of the
 * fieldMappings.
 */
public class Field {
    public String name;
    public String value1;

    public Field(String name, String value1) {
        this.name = name;
        this.value1 = value1;
    }

    public Field() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }
}
