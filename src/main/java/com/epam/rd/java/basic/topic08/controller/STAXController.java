package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.entity.Flowers;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collections;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(){

	}

	public void printResult()
	{

	}
	public void sortByGrowingTemperature(){
		//Collections.sort(handler.getFlowers(), Flowers.Flower.compareFlowerByGrowingTemperature);
	}

}