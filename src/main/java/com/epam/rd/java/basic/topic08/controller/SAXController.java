package com.epam.rd.java.basic.topic08.controller;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Controller for SAX parser.
 */
public class SAXController {
	
	private String xmlFileName;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(){
		try {
		// SAX parser creating & configuring
			SAXFlowersHandler handler = new SAXFlowersHandler();

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setContentHandler(handler);
			//reader.setErrorHandler(new StudentErrorHandler());
			reader.parse(xmlFileName);
			System.out.println(handler.getFlovers());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}