package com.example.demo.service;


import com.example.demo.ValidationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.demo.ErrorMessages.NO_ERROR;

/***+
 * This is the class of service layer of the application which consists of postData method which gets the output from the validation
 * method and changes the ResponseEntity according to the Data.This class implements the interface DataService
 * and implements its method postData.
 */
@Service
public class ServiceImpl implements DataService{
    /**+
     * This method evaluates the data that it is valid or not by checking that error_message is empty which means the data is valid or not.
     * If the error_message is empty, the method returns ResponseEntity with status code as OK.
     * If the error_message is not empty which shows that input data is not valid, the method returns ResponseEntity with status code as Not_Implemented.
     * @param validationResponse validationResponse is an Object of the Class ValidationResponse which consists of error_message describing the data's missing and null fields if the data is not valid
     *                           and code which describes HttpStatus of the response.
     *
     * @return  ResponseEntity
     *
     */
    public ResponseEntity<ValidationResponse> postData(ValidationResponse validationResponse){
        if(validationResponse.error_message.isEmpty()){
            validationResponse.setError_message(new StringBuilder(NO_ERROR));
            return new ResponseEntity<>(validationResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(validationResponse,HttpStatus.NOT_IMPLEMENTED);
    }


}






