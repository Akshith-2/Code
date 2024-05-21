package com.example.demo.service;

import com.example.demo.MandatoryFields;
import com.example.demo.ValidationResponse;
import com.example.demo.controller.Controller;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServiceImplTest {
    ServiceImpl service=new ServiceImpl();
    Controller controller=new Controller(service);
    String json99="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": \"s3a://venice-dev/test_1m_recs/sample_input_1000/\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"assignmentJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"UNIQUE_ID\",\n" +
            "                    \"value1\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"null\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String json1="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": \"s3a://venice-dev/test_1m_recs/sample_input_1000/\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"assignmentJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"0000000\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";


    String json2="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": \"s3a://venice-dev/test_1m_recs/sample_input_1000/\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"assignmentJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"UNIQUE_ID\",\n" +
            "                    \"value1\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"0000000\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";


    String json3="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": null,\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"assignmentJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"UNIQUE_ID\",\n" +
            "                    \"value1\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"0000000\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String json4="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": \"s3a://venice-dev/test_1m_recs/sample_input_1000/\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"translationJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"0000000\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String json5="{\n" +
            "    \"actionType\": \"TRANSLATION_ID\",\n" +
            "    \"s3SourceLocation\": \"s\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"translationJobProperties\": {\n" +
            "            \"fieldMappings\": [\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STREET1\",\n" +
            "                    \"value1\": \"ADDRESS_STREET1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_CITY\",\n" +
            "                    \"value1\": \"ADDRESS_CITY\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_STATE\",\n" +
            "                    \"value1\": \"ADDRESS_STATE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"ADDRESS_POSTALCODE\",\n" +
            "                    \"value1\": \"ADDRESS_POSTALCODE\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_FIRST\",\n" +
            "                    \"value1\": \"NAME_FIRST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"NAME_LAST\",\n" +
            "                    \"value1\": \"NAME_LAST\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"PHONE_NUMBER\",\n" +
            "                    \"value1\": \"0000000\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"name\": \"EMAIL_ADDRESS\",\n" +
            "                    \"value1\": \"EMAIL_ADDRESS\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    String json100="{\n" +
            "    \"actionType\": \"ID_ASSIGNMENT\",\n" +
            "    \"s3SourceLocation\": \"s\",\n" +
            "    \"s3TargetLocation\": \"s3a://venice-dev/test_1m_recs/venice_output_loose_scrub/output_dataset/\",\n" +
            "    \"jobProperties\": {\n" +
            "        \"assignmentJobProperties\": {\n" +
            "                           }\n" +
            "    }\n" +
            "}";
    @Test
    public void checkIfDataIsValid() throws IOException {
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }
    @Test
    public void MandatoryFieldCheck() throws IOException{
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void sourceAndNullCheck() throws IOException {
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void statusCheck() throws IOException{
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void mismatchCheck() throws IOException{
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void translationIdCheck() throws IOException{
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }




    @Test
    public void sourcePattern() throws IOException {
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json99);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void propertiesNullCheck() throws IOException{
        Controller controller=new Controller(service);
        ResponseEntity<StringBuilder> responseEntity=controller.postData(json100);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_IMPLEMENTED);
    }

}