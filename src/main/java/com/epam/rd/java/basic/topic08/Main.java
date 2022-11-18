package com.epam.rd.java.basic.topic08;

import com.epam.rd.java.basic.topic08.controller.*;
import com.epam.rd.java.basic.topic08.entity.Flowers;

public class Main {
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			return;
		}
		
		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);

		ValidatorXML.ValidateXML();

		Flowers flowers;
		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////
		
		// get container
		DOMController domController = new DOMController(xmlFileName);
		domController.parse();
		flowers = domController.getFlowers();

		// sort (case 1)
		//domController.sortByName();
		//domController.printResult();

		// save
		String outputXmlFile = "output.dom.xml";
		BuilderXML.build(flowers, outputXmlFile);

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////
		
		// get
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse();
		flowers = saxController.getFlowers();

		// sort  (case 2)
		//saxController.sortByOrigin();
		//saxController.sortByGrowingTemperature();
		//saxController.printResult();
		
		// save
		outputXmlFile = "output.sax.xml";
		BuilderXML.build(flowers, outputXmlFile);
		
		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////
		
		// get
		//STAXController staxController = new STAXController(xmlFileName);
		//staxController.parse();
		
		// sort  (case 3)
		//staxController.sortByGrowingTemperature();
		//staxController.printResult();
		
		// save
		outputXmlFile = "output.stax.xml";
		BuilderXML.build(flowers, outputXmlFile);
	}

}
