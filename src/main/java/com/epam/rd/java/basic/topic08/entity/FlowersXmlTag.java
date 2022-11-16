package com.epam.rd.java.basic.topic08.entity;

public enum FlowersXmlTag {

    FLOWERS("flowers"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visualParameters"),
        STEM_COLOUR("stemColour"),
        LEAF_COLOUR("leafColour"),
        AVELEN_FLOWER("aveLenFlower"),
    GROWING_TIPS("growingTips"),
        TEMPERATURE("tempreture"),
        LIGHTING("lighting"),
        WATERING("watering"),
    MULTIPLYING("multiplying"),
    ;
    private String value;
    FlowersXmlTag(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
