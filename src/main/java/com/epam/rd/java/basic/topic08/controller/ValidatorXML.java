package com.epam.rd.java.basic.topic08.controller;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class ValidatorXML {
    static String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    static String fileName = "input.xml";
    static String schemaName = "input.xsd";

    private ValidatorXML() {
    }
    public static void ValidateXML(){
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try{
            // schema creation
            Schema schema = factory.newSchema(schemaLocation);
            // creating a schema-based validator
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            // document check
            validator.validate(source);
            System.out.println(fileName + " is valid");
        } catch (SAXException | IOException e) {
            System.err.println(fileName + " is not correct or valid");
        }
    }

}
