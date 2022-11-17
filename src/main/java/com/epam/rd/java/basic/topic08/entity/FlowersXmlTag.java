package com.epam.rd.java.basic.topic08.entity;

public enum FlowersXmlTag {
    //мусять бути ідентичні до значення + uppercase, інакше алгоритм їх не побачить
        //константи які є присутні, але не обробляються
        FLOWERS("flowers"),
        VISUALPARAMETERS("visualParameters"),
        GROWINGTIPS("growingTips"),
    FLOWER("flower"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    //visualParameters
        STEMCOLOUR("stemColour"),
        LEAFCOLOUR("leafColour"),
        AVELENFLOWER("aveLenFlower"),
    //growingTips
        TEMPRETURE("tempreture"),
        LIGHTING("lighting"),
        WATERING("watering"),
    MULTIPLYING("multiplying"),

    //атрибути
    MEASURE("measure"),
    LIGHTREQUIRING("lightRequiring"),

    ;
    private String value;
    FlowersXmlTag(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
