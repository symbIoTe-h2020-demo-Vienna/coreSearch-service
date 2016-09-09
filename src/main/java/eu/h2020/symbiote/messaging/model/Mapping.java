package eu.h2020.symbiote.messaging.model;

/**
 * Created by Mael on 08/09/2016.
 */
public class Mapping {

    private int modelId1;
    private int modelId2;
    private String mapping;

    public Mapping() {
    }

    public Mapping(String mapping, int modelId1, int modelId2) {
        this.mapping = mapping;
        this.modelId1 = modelId1;
        this.modelId2 = modelId2;
    }


    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public int getModelId1() {
        return modelId1;
    }

    public void setModelId1(int modelId1) {
        this.modelId1 = modelId1;
    }

    public int getModelId2() {
        return modelId2;
    }

    public void setModelId2(int modelId2) {
        this.modelId2 = modelId2;
    }
}
