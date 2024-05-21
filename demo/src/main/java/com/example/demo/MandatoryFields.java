package com.example.demo;

/***+
 *
 * This enum describes the mandatory field which should be present in the provided data or else the data
 * is considered as invalid data.True specifies that the enum which is toggled as true is a mandatory field.
 * This enum is dynamic as we can the change the mandatory fields dynamically and still the logic works.
 * @author apoudal
 */
public enum MandatoryFields {


    UNIQUE_ID(true),
    PHONE_NUMBER(true),
    EMAIL_ADDRESS(true),
    ADDRESS_STREET1(true),
    NAME_FIRST(false),
    NAME_LAST(false),
    ADDRESS_CITY(false),
    ADDRESS_STATE(false),
    ADDRESS_POSTALCODE(false);

    /***+
     * isRequired boolean is true for mandatory fields and is false for other fields.Helps in identifying
     * which fields are mandatory and which are not.
     */
    public final boolean isRequired;
    MandatoryFields(boolean isRequired){
        this.isRequired=isRequired;
    }
}
