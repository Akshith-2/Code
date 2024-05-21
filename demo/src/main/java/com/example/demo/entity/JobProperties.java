package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/***+
 * JobProperties is an interface which is implemented by assignmentJobProperties and translationJobProperties
 * which can have either one of them as an inner value.
 *
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AssignmentJobProperties.class),
        @JsonSubTypes.Type(value = TranslationJobProperties.class)
})
public interface JobProperties {

}

