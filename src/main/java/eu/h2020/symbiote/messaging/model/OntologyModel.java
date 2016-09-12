package eu.h2020.symbiote.messaging.model;

import eu.h2020.symbiote.model.RDFFormat;

import java.math.BigInteger;

/**
 * Created by Mael on 07/09/2016.
 */
public class OntologyModel {

    private BigInteger id;
    private String instance;
    private RDFFormat format;

    OntologyModel() {

    }

    public OntologyModel(BigInteger id, String instance, RDFFormat format) {
        this.id = id;
        this.instance = instance;
        this.format = format;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String body) {
        this.instance = body;
    }

    public RDFFormat getFormat() {
        return format;
    }

    public void setFormat(RDFFormat format) {
        this.format = format;
    }

}
