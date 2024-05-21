package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/***+
 * This POJO class represents the assignmentJobProperties in the input json describing the fieldMappings
 * inside it.
 */
@JsonTypeName("translationJobProperties")
public class TranslationJobProperties implements JobProperties{
    public List<Field> fieldMappings;

    public List<Field> getFieldMappings() {
        return fieldMappings;
    }

    public void setFieldMappings(List<Field> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }

}
