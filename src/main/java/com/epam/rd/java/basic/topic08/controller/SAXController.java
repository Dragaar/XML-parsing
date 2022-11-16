package com.epam.rd.java.basic.topic08.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parce(){
		try {
		// SAX parser creating & configuring
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setContentHandler(this);
			//reader.setErrorHandler(new StudentErrorHandler());
			reader.parse("input.xml");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		String tagData = qName + " ";
		for (int i = 0; i < attrs.getLength(); i++) {
			tagData += " " + attrs.getQName(i) + "=" + attrs.getValue(i);
		}
		System.out.print("|S+"+tagData);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print("|C+"+new String(ch, start, length));
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.print("|E+ " + qName);
	}


	@Override
	public void endDocument() throws SAXException {
		System.out.println("\nParsing ended");
	}
}