package com.example.demo;

import com.example.demo.entity.*;

import org.springframework.http.HttpStatus;


import static com.example.demo.ErrorMessages.*;
import static com.example.demo.StringFormatting.stringFormat;

/***+
 *
 * This class consists of validation,sourceAndTargetCheck,mandatoryFieldCheck,jobPropertiesAndActionTypeMismatch
 * ,errorMessageFormatting,translationActionType methods which help in validating the data according to the business
 * rules.
 * @author apoudal
 */
public class ValidationCheck {

    static StringBuilder null_error_message;
    static StringBuilder missing_error_message;
    static StringBuilder error_message;

    /***+
     * This method validates the data by applying the business rules such as Checking the actionType,validating the source and target
     * locations,checking the type of job properties and evaluating whether mandatory fields are present or not.
     * @param data This consists of overall data which is taken as input from the endpoint consisting actionType,s3sourceLocation,s3targetLocation,jobProperties.
     *             jobProperties conists of assignmentJobProperties or translationJobProperties which have fieldMappings inside them.
     * @return
     */
    public static ValidationResponse validation(InputData data) {
        null_error_message = new StringBuilder();
        missing_error_message = new StringBuilder();
        error_message = new StringBuilder();

        if (data.actionType.equals(TRANSLATION_ID)) {
            translationActionType(data);
            return new ValidationResponse(error_message,Not_Implemented);
        }
        else {
            sourceAndTargetCheck(data);
            if(data.jobProperties!=null) {
                if (data.jobProperties instanceof TranslationJobProperties) {
                    jobPropertiesAndActionTypeMismatch();
                    return new ValidationResponse(error_message,Not_Implemented);
                } else{
                    if(((AssignmentJobProperties) data.getJobProperties()).getFieldMappings()==null){
                        for(MandatoryFields mandatoryFields:MandatoryFields.values()){
                            if(mandatoryFields.isRequired){
                                missing_error_message.append(mandatoryFields).append(" ");
                            }
                        }

                    }
                    else {
                        mandatoryFieldCheck(data);
                    }
                }
            }
            else{
                error_message.append(JOB_PROPERTIES).append(NULL).append(" ");
            }
        }
        errorMessageFormatting();
        if(error_message.isEmpty())
            return new ValidationResponse(error_message,Ok);
        else
            return new ValidationResponse(error_message,Not_Implemented);
    }

    /***+
     * This method checks the source and target locations like are they empty or null or not following the ideal pattern of the source and target locations.
     * @param data This consists of overall data which is taken as input from the endpoint consisting actionType,s3sourceLocation,s3targetLocation,jobProperties.
     *             jobProperties conists of assignmentJobProperties or translationJobProperties which have fieldMappings inside them.
     *
     */
    public static void sourceAndTargetCheck(InputData data) {
        StringBuilder patternCheck=new StringBuilder();
        if (data.s3SourceLocation == null) {
            missing_error_message.append(SOURCE).append(" ");
        } else if (data.s3SourceLocation.isEmpty()) {
            null_error_message.append(SOURCE).append(" ");
        }

        if (data.s3TargetLocation == null) {
            missing_error_message.append(TARGET).append(" ");
        } else if (data.s3TargetLocation.isEmpty()) {
            null_error_message.append(TARGET).append(" ");
        }
        if (data.s3SourceLocation!=null&&!data.s3SourceLocation.isEmpty()) {
            String sourceCheck = data.s3SourceLocation;
            String[] s = sourceCheck.split("://");
            if (!s[0].equals("s3a")&&!s[0].equals("s3")) {
                patternCheck.append(SOURCE).append(" ");
            }
        }
        if (data.s3TargetLocation!=null&&!data.s3TargetLocation.isEmpty()) {
            String targetCheck = data.s3TargetLocation;
            String[] s1 = targetCheck.split("://");
            if (!s1[0].equals("s3a")&&!s1[0].equals("s3")) {
                patternCheck.append(TARGET);
            }
        }
        if(!patternCheck.isEmpty()) {
            patternCheck = stringFormat(patternCheck);
            error_message.append(patternCheck).append(PATTERN).append(" ");
        }
    }

    /***+
     * This method checks whether the mandatory fields which are toggled as true in the MandatoryFields enum are present in the input data.
     * @param data This consists of overall data which is taken as input from the endpoint consisting actionType,s3sourceLocation,s3targetLocation,jobProperties.
     *            jobProperties conists of assignmentJobProperties or translationJobProperties which have fieldMappings inside them.
     *
     */
    public static void mandatoryFieldCheck(InputData data) {
        for (MandatoryFields mandatoryFields : MandatoryFields.values()) {
            NullOrMissing nullOrMissing = new NullOrMissing();
            if (((AssignmentJobProperties) data.getJobProperties()).getFieldMappings() != null) {
                for (Field field : ((AssignmentJobProperties) data.getJobProperties()).getFieldMappings()) {
                    if (mandatoryFields.isRequired && field.name.equals(mandatoryFields.toString())) {
                        nullOrMissing.isFound = true;
                        if (field.value1 != null) {
                            nullOrMissing.isNull = false;
                        }
                    }
                }
                if (mandatoryFields.isRequired && !nullOrMissing.isFound) {
                    missing_error_message.append(mandatoryFields).append(" ");
                } else {
                    if (mandatoryFields.isRequired && nullOrMissing.isNull) {
                        null_error_message.append(mandatoryFields).append(" ");
                    }
                }
            }
        }
    }

    /***+
     * This method is invoked when the actionType and JobProperties are mismatched.This is used in writing the error
     * message for the mismatch and operates on both null_error_message and missing_error_message and combining them into a single error_message.
     */
    public static void jobPropertiesAndActionTypeMismatch(){
        if (!null_error_message.toString().isBlank()) {
            null_error_message = stringFormat(null_error_message);
            null_error_message.append(NULL).append(" ");
            error_message.append(null_error_message);
        }
        if (!missing_error_message.toString().isBlank()) {
            missing_error_message = stringFormat(missing_error_message);
            missing_error_message.append(MISSING).append(" ");
            error_message.append(missing_error_message);
        }
        error_message.append(MISMATCH);
    }

    /***+
     * This method is used when actionType is TRANSLATION_ID which is not part of a valid data and therefore we have to
     * produce an error message and if the JobProperties are mismatched such as assignmentJobProperties for TRANSLATION_ID actionType then another error_message
     * is appended to it saying that those two are mismatched.
     * @param data This consists of overall data which is taken as input from the endpoint consisting actionType,s3sourceLocation,s3targetLocation,jobProperties.
     *             jobProperties conists of assignmentJobProperties or translationJobProperties which have fieldMappings inside them.
     *
     */
    public static void translationActionType(InputData data){
        error_message.append(TRANSLATION_ID_NOT_SUPPORTED).append(" ");
        if (data.jobProperties instanceof AssignmentJobProperties) {
            error_message.append(MISMATCH);
        }

    }

    /***+
     * This method is used to combine null_error_message which mentions the names of those which are null
     * and missing_error_message which mentions the names of those which are not present in the data into
     * error_message which displays error message according to the provided data.
     */
    public static void errorMessageFormatting(){
        if (!null_error_message.toString().isBlank()) {
            null_error_message = stringFormat(null_error_message);
            null_error_message.append(NULL);
            error_message.append(null_error_message).append(" ");
        }
        if (!missing_error_message.toString().isBlank()) {
            missing_error_message = stringFormat(missing_error_message);
            missing_error_message.append(MISSING);
            error_message.append(missing_error_message);
        }
    }
}