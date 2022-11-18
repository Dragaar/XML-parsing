package com.epam.rd.java.basic.topic08.controller;


import com.epam.rd.java.basic.topic08.entity.Flowers;
import com.epam.rd.java.basic.topic08.entity.FlowersFactory;
import com.epam.rd.java.basic.topic08.entity.FlowersXmlTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;

import static com.epam.rd.java.basic.topic08.entity.FlowersXmlTag.*;

/**
 * Controller for DOM parser.
 */
public class DOMController {

	private String xmlFileName;

	private FlowersFactory flowersFactory = new FlowersFactory();
	private Flowers flowers = flowersFactory.createFlowers();
	private Flowers.Flower currentFlower;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() {
		DocumentBuilder docBuilder;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();

			Document doc = docBuilder.parse(xmlFileName);
			Element root = doc.getDocumentElement();
			// getting a list of <flower> child elements
			NodeList flowerList = root.getElementsByTagName("flower");
			for (int i = 0; i < flowerList.getLength(); i++) {
				Element flowerElement = (Element) flowerList.item(i);

				Flowers.Flower flower = buildFlower(flowerElement);
				flowers.getFlowers().add(flower);
			}
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace(); // log
		}
	}
	private Flowers.Flower buildFlower(Element flowerElement) {
		initialiseCurrentFlower();

		//<---------------------------------------------------------------->

		currentFlower.setName(getElementTextContent(flowerElement, NAME.getValue()));
		currentFlower.setSoil(getElementTextContent(flowerElement, SOIL.getValue()));
		currentFlower.setOrigin(getElementTextContent(flowerElement, ORIGIN.getValue()));

		//<---------------------------------------------------------------->
		Flowers.Flower.VisualParameters visualPar = currentFlower.getVisualParameters();
		Element vsParElement =
				(Element) flowerElement.getElementsByTagName(VISUALPARAMETERS.getValue()).item(0);

		visualPar.setStemColour(getElementTextContent(vsParElement, STEMCOLOUR.getValue()));
		visualPar.setLeafColour(getElementTextContent(vsParElement, LEAFCOLOUR.getValue()));
			//aveLenFlower measure
			visualPar.getAveLenFlower().setMeasure(getSubElemAttribute(vsParElement, AVELENFLOWER, MEASURE));
			//----------------
			Integer aveLenFlower = Integer.parseInt(getElementTextContent(vsParElement, AVELENFLOWER.getValue()));
		visualPar.getAveLenFlower().setValue(aveLenFlower);


		//<---------------------------------------------------------------->
		Flowers.Flower.GrowingTips growingTips = currentFlower.getGrowingTips();
		Element grTpElement =
				(Element) flowerElement.getElementsByTagName(GROWINGTIPS.getValue()).item(0);

			//tempreture measure
			growingTips.getWatering().setMeasure(getSubElemAttribute(grTpElement, TEMPRETURE, MEASURE));
			//----------------
			Integer tempreture = Integer.parseInt(getElementTextContent(grTpElement, TEMPRETURE.getValue()));
		growingTips.getTempreture().setValue(tempreture);
		//атрибут lighting в якості поля
		growingTips.getLighting().setLightRequiring(getSubElemAttribute(grTpElement, LIGHTING, LIGHTREQUIRING));
		//----------------
			//watering measure
			growingTips.getWatering().setMeasure(getSubElemAttribute(grTpElement, WATERING, MEASURE));
			//----------------
			Integer watering = Integer.parseInt(getElementTextContent(grTpElement, WATERING.getValue()));
		growingTips.getWatering().setValue(watering);

		//<---------------------------------------------------------------->
		currentFlower.setMultiplying(getElementTextContent(flowerElement, MULTIPLYING.getValue()));

		return currentFlower;
	}
	//отримати атрибут під-елементу
	private static String getSubElemAttribute(Element mainElement, FlowersXmlTag subElementName, FlowersXmlTag subElementAttribute) {
		//витягуэмо дочірній елемент якому належить атрибут
		Element subElement = (Element) mainElement.getElementsByTagName(subElementName.getValue()).item(0);
		return subElement.getAttribute(subElementAttribute.getValue());

	}
	// get the text content of the tag
	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

	//ініціалізація внутрішніх класів flower
	private void initialiseCurrentFlower() {
		currentFlower = flowersFactory.createFlowersFlower();
		//<------------------------------------------------------------>\\
		currentFlower.setVisualParameters(
				flowersFactory.createFlowersFlowerVisualParameters()
		);
		currentFlower.getVisualParameters().setAveLenFlower(
				flowersFactory.createFlowersFlowerVisualParametersAveLenFlower()
		);
		//<------------------------------------------------------------>\\
		currentFlower.setGrowingTips(
				flowersFactory.createFlowersFlowerGrowingTips()
		);
		currentFlower.getGrowingTips().setTempreture(
				flowersFactory.createFlowersFlowerGrowingTipsTempreture()
		);
		currentFlower.getGrowingTips().setLighting(
				flowersFactory.createFlowersFlowerGrowingTipsLighting()
		);
		currentFlower.getGrowingTips().setWatering(
				flowersFactory.createFlowersFlowerGrowingTipsWatering()
		);
	}

	public Flowers getFlowers(){
		return flowers;
	}
	public void sortByName(){
		Collections.sort(flowers.getFlowers(), Flowers.Flower.compareFlowerByName);
	}
	public void printResult()
	{
		System.out.println(flowers.getFlowers());
	}
}

