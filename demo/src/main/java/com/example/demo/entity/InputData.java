package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/***+
 * This is the main input class of the data which has the properties such as actionType,s3SourceLocation,
 * s3TargetLocation,jobProperties.
 * This class is an outer input class for every other POJO Class created.
 */
public class InputData {
    public String actionType;
    public String s3SourceLocation;
    public String s3TargetLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="job_id")
    public JobProperties jobProperties;

    public InputData(){}

    public InputData(String actionType, String s3SourceLocation, String s3TargetLocation, JobProperties jobProperties) {
        this.actionType = actionType;
        this.s3SourceLocation = s3SourceLocation;
        this.s3TargetLocation = s3TargetLocation;
        this.jobProperties = jobProperties;
    }


    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getS3SourceLocation() {
        return s3SourceLocation;
    }

    public void setS3SourceLocation(String s3SourceLocation) {
        this.s3SourceLocation = s3SourceLocation;
    }

    public String getS3TargetLocation() {
        return s3TargetLocation;
    }

    public void setS3TargetLocation(String s3TargetLocation) {
        this.s3TargetLocation = s3TargetLocation;
    }


    public JobProperties getJobProperties() {
        return jobProperties;
    }

    public void setJobProperties(JobProperties jobProperties) {
        this.jobProperties = jobProperties;
    }

}
