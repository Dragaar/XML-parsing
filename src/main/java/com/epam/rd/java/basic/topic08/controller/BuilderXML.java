package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.entity.Flowers;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

import static com.epam.rd.java.basic.topic08.entity.FlowersXmlTag.*;

public class BuilderXML {

    public static void build(Flowers flowers, String resultFileName) throws TransformerException, ParserConfigurationException, IOException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.newDocument();

        Element root = doc.createElement(FLOWERS.getValue());
        doc.appendChild(root);
        root.setAttribute("xmlns", "http://www.nure.ua");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd");

        for(Flowers.Flower flower : flowers.getFlowers())
        {
            Element localRoot = doc.createElement(FLOWER.getValue());
            root.appendChild(localRoot);
            addElement(doc, localRoot, NAME.getValue(), flower.getName(), null, null);
            addElement(doc, localRoot, SOIL.getValue(), flower.getSoil(), null, null);
            addElement(doc, localRoot, ORIGIN.getValue(), flower.getOrigin(), null, null);

            //<--------------------------------------------------------------------------------->
            Element visualParam = doc.createElement(VISUALPARAMETERS.getValue());
            localRoot.appendChild(visualParam);
                //витягування даних об'єкту
                String stColour = flower.getVisualParameters().getStemColour();
                String leafColour = flower.getVisualParameters().getLeafColour();
                Flowers.Flower.VisualParameters.AveLenFlower
                        avelenFlower = flower.getVisualParameters().getAveLenFlower();

                //передача даних в doc
                addElement(doc, visualParam, STEMCOLOUR.getValue(), stColour, null, null);
                addElement(doc, visualParam, LEAFCOLOUR.getValue(), leafColour, null, null);
                addElement(doc, visualParam, AVELENFLOWER.getValue(), avelenFlower.getValue().toString(),
                        MEASURE.getValue(), avelenFlower.getMeasure());

            //<--------------------------------------------------------------------------------->
            Element growingTips = doc.createElement(GROWINGTIPS.getValue());
            localRoot.appendChild(growingTips);
                //витягування даних об'єкту
                Flowers.Flower.GrowingTips.Tempreture
                        tempreture = flower.getGrowingTips().getTempreture();
                Flowers.Flower.GrowingTips.Lighting
                        lighting = flower.getGrowingTips().getLighting();
                Flowers.Flower.GrowingTips.Watering
                        watering = flower.getGrowingTips().getWatering();

                //передача даних в doc
                addElement(doc, growingTips, TEMPRETURE.getValue(), tempreture.getValue().toString(),
                        MEASURE.getValue(), tempreture.getMeasure());

                addElement(doc, growingTips, LIGHTING.getValue(), "",
                        LIGHTREQUIRING.getValue(), lighting.getLightRequiring());

                addElement(doc, growingTips, WATERING.getValue(), watering.getValue().toString(),
                        MEASURE.getValue(), watering.getMeasure());

            //<--------------------------------------------------------------------------------->
            addElement(doc, localRoot, MULTIPLYING.getValue(), flower.getMultiplying(), null, null);

        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileWriter(resultFileName));
        transformer.transform(source, result);
    }

    private static void addElement(Document doc, Element localRoot, String childElName, String value, String attrName, String attrValue){
        Element subElement = doc.createElement(childElName);

        if(attrName!= null && attrValue!=null){
            subElement.setAttributeNode(addAttr(doc, attrName, attrValue));
        }
        subElement.appendChild(doc.createTextNode(value));
        localRoot.appendChild(subElement);
    }

    private static Attr addAttr(Document doc, String name, String value){
        Attr attr = doc.createAttribute(name);
        attr.setValue(value);
        return attr;
    }
}
