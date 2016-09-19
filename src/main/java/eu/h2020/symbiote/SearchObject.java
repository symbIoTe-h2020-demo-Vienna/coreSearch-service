package eu.h2020.symbiote;

public class SearchObject {
    private String modelGraphUri;
    private String query;

    public SearchObject() {

    }

    public SearchObject(String modelGraphUri, String query) {
        this.modelGraphUri = modelGraphUri;
        this.query = query;
    }

    public String getModelGraphUri() {
        return modelGraphUri;
    }

    public void setModelGraphUri(String modelGraphUri) {
        this.modelGraphUri = modelGraphUri;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
