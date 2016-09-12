package eu.h2020.symbiote.messaging.model;


import eu.h2020.symbiote.model.RDFFormat;

import java.math.BigInteger;

/**
 * Created by Mael on 30/08/2016.
 */
public class Platform {

	private BigInteger id;
	private String instance;
	private RDFFormat format;
	private BigInteger modelId;

	Platform() {

	}

	public Platform(BigInteger id, String instance, RDFFormat format, BigInteger modelId) {
		this.id = id;
		this.instance = instance;
		this.format = format;
		this.modelId = modelId;
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

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public RDFFormat getFormat() {
		return format;
	}

	public void setFormat(RDFFormat format) {
		this.format = format;
	}

	public BigInteger getModelId() {
		return modelId;
	}

	public void setModelId(BigInteger modelId) {
		this.modelId = modelId;
	}
}
