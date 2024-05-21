package com.example.demo.controller;
import com.example.demo.ValidationResponse;
import com.example.demo.entity.InputData;
import com.example.demo.service.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.demo.ValidationCheck.validation;

/***+
 * Controller is the controller layer of the application which defines the endpoints interacting with
 * api handling the data.
 */
@RestController
public class Controller {
    @Autowired
    ServiceImpl service;
    public Controller(ServiceImpl service){
        this.service=service;
    }

    /***+
     * Takes the input using @RequestBody annotation in the form of String json.The json automatically
     * gets converted to string and using the objectMapper , the conversion of string to InputData class
     * is done.Every field of the json is mapped to the POJO classes created.
     * Validation method is called to perform the validation on the InputData and the output is passed
     * as a parameter to postData method in service method.
     * @param inputJson
     * @return ResponseEntity
     * @throws IOException
     */
    @PostMapping("/data")
    public ResponseEntity postData(@RequestBody String inputJson) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        InputData inputData =objectMapper.readValue(inputJson, InputData.class);
        ValidationResponse validationResponse=validation(inputData);
        return service.postData(validationResponse);
    }
}
