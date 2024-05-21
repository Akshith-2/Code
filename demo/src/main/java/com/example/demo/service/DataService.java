package com.example.demo.service;

import com.example.demo.ValidationResponse;
import org.springframework.http.ResponseEntity;

/***+
 * DataService interface is declared in service layer to define the methods which are implemented in the
 * ServiceImpl class implementing this interface.
 */
public interface DataService {
    /***+
     * This method is described as to create a method which gets implemented in the ServiceImpl.
     * @param validationResponse
     * @return
     */
    public ResponseEntity postData(ValidationResponse validationResponse);
}
