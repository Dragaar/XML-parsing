package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.entity.Flowers;
import com.epam.rd.java.basic.topic08.entity.FlowersFactory;
import com.epam.rd.java.basic.topic08.entity.FlowersXmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.List;

public class SAXFlowersHandler extends DefaultHandler {

    private FlowersFactory flowersFactory = new FlowersFactory();
    private Flowers flowers = flowersFactory.createFlowers();
    private Flowers.Flower currentFlower;

    //витягування окремого елементу з Enum й його порівняння зменшить читабельність коду
    private static final String ELEMENT_FLOWER = "flower";
    private FlowersXmlTag currentXmlTag;
    private EnumSet<FlowersXmlTag> withText;

    public SAXFlowersHandler() {
        withText = EnumSet.range(FlowersXmlTag.FLOWER, FlowersXmlTag.MULTIPLYING);
    }

    public List<Flowers.Flower> getFlowersAsList(){
        return flowers.getFlowers();
    }

    public Flowers getFlowers(){
        return flowers;
    }

    @Override
    public void startDocument() {
        System.out.println("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        //System.out.println("|Q-"+qName+"| ");
        /*if(FlowersXmlTag.FLOWERS.getValue().equals(qName)){
            flowers = flowersFactory.createFlowers();
        } else */
        if (ELEMENT_FLOWER.equals(qName)){ //створення головного обєкту
            initialiseCurrentFlower();
        } else { //витягнути тег із поданого переліку
            FlowersXmlTag temp = FlowersXmlTag.valueOf(qName.toUpperCase());
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
        //витягування атрибутів
        if (currentXmlTag!= null && attrs.getLength() > 0) {
            switch (currentXmlTag) {
                //aveLenFlower measure
                case AVELENFLOWER: currentFlower.getVisualParameters().getAveLenFlower().setMeasure(attrs.getValue(0)); break;

                //tempreture measure
                case TEMPRETURE: currentFlower.getGrowingTips().getTempreture().setMeasure(attrs.getValue(0)); break;
                //lighting lightRequiring
                case LIGHTING: currentFlower.getGrowingTips().getLighting().setLightRequiring(attrs.getValue(0));
                    currentXmlTag = null; //оскільки тексту не має, слід очистити наявний тег після роботи
                    break;
                //watering measure
                case WATERING: currentFlower.getGrowingTips().getWatering().setMeasure(attrs.getValue(0)); break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag!= null) {
            switch (currentXmlTag) {
                case NAME: currentFlower.setName(data); break;
                case SOIL: currentFlower.setSoil(data); break;
                case ORIGIN: currentFlower.setOrigin(data); break;

                case STEMCOLOUR: currentFlower.getVisualParameters().setStemColour(data); break;
                case LEAFCOLOUR: currentFlower.getVisualParameters().setLeafColour(data); break;
                case AVELENFLOWER: currentFlower.getVisualParameters().getAveLenFlower().setValue(Integer.parseInt(data)); break;


                case TEMPRETURE: currentFlower.getGrowingTips().getTempreture().setValue(Integer.parseInt(data)); break;
                //lighting не має значення, він тільки в якості атрибута
                case WATERING: currentFlower.getGrowingTips().getWatering().setValue(Integer.parseInt(data)); break;

                case MULTIPLYING: currentFlower.setMultiplying(data); break;

                default: throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_FLOWER.equals(qName)) {
            //містить список Flower
            flowers.getFlowers().add(currentFlower);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nParsing ended");
    }

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
    /*
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
    }*/
}
