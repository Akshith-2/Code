package com.example.demo;
import org.springframework.http.HttpStatus;


/***
 * This method is the response for the validation which specifies an error_message describing the missing and null things in the input data.
 * @author apoudal
 */
public class ValidationResponse {
    public StringBuilder error_message;
    public String code;
    public ValidationResponse() {}

    public ValidationResponse(StringBuilder error_message, String code) {
        this.error_message = error_message;
        this.code = code;
    }


    public StringBuilder getError_message() {
        return error_message;
    }

    public void setError_message(StringBuilder error_message) {
        this.error_message = error_message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
