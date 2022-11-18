package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.entity.Flowers;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Collections;

/**
 * Controller for SAX parser.
 */
public class SAXController {
	
	private String xmlFileName;
	SAXFlowersHandler handler;
	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(){
		try {
		// SAX parser creating & configuring
			handler = new SAXFlowersHandler();

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setContentHandler(handler);
			//reader.setErrorHandler(new StudentErrorHandler());
			reader.parse(xmlFileName);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public Flowers getFlowers(){
		return handler.getFlowers();
	}
	public void sortByOrigin(){
		Collections.sort(handler.getFlowersAsList(), Flowers.Flower.compareFlowerByOrigin);
	}

	public void sortByGrowingTemperature(){
		Collections.sort(handler.getFlowersAsList(), Flowers.Flower.compareFlowerByGrowingTemperature);
	}
	public void printResult()
	{
		System.out.println(handler.getFlowersAsList());
	}

}