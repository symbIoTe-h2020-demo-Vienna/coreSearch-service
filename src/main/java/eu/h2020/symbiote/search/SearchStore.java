package eu.h2020.symbiote.search;

import com.google.gson.Gson;
import eu.h2020.symbiote.messaging.model.OntologyModel;
import eu.h2020.symbiote.messaging.model.Platform;
import eu.h2020.symbiote.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mael on 07/09/2016.
 */
public class SearchStore {

    private static SearchStore singleton;

    private static Log log = LogFactory.getLog(SearchStore.class);

    private Registry core;
    private SearchEngine searchEngine;

    static {
        singleton = new SearchStore();
    }

    private SearchStore() {
        TripleStore tripleStore = new TripleStore();
        core = new Registry(tripleStore);
        searchEngine = new SearchEngine(tripleStore);
    }

    public static SearchStore getSingleton() {
        return singleton;
    }

    public void registerPlatform( Platform platform ) {
        log.info( "Adding platform " + platform.getId() + " to search...");
        core.registerPlatform(platform.getId(),platform.getInstance(),platform.getFormat(),platform.getModelId());
        log.info( "Platform " + platform.getId() + " added to search");
    }

    public void registerModel( OntologyModel ontologyModel) {
        log.info( "Adding model " + ontologyModel.getId() + " to search...");
        core.registerModel(ontologyModel.getId(),ontologyModel.getBody(),ontologyModel.getFormat());
        log.info( "Model " + ontologyModel.getId() + " added to search");
    }

    public String query( String modelGraphUri, String query) {
        String result = null;
        try {
            List<String> resultList = query(searchEngine, modelGraphUri, query);
            Gson gson = new Gson();
            result = gson.toJson(resultList);
        } catch (IOException e) {
            log.error("Error during executing query",e);
        }
        return result;
    }

    private static List<String> query(SearchEngine searchEngine, String modelGraphUri, String sparql) throws IOException {
        List<String> results = new ArrayList<String>();
        log.info(String.format("executing query: modelId={}, sparql=\n{}", modelGraphUri, sparql));
        ResultSet result = searchEngine.search(modelGraphUri, sparql);
        log.info("----- result ----");
        while (result.hasNext()) {
            QuerySolution solution = result.next();
            Iterator<String> varNames = solution.varNames();
            String temp = "";
            while (varNames.hasNext()) {
                String var = varNames.next();
                if (!temp.isEmpty()) {
                    temp += ", ";
                }
                temp += var + " = " + solution.get(var).toString();
            }
            results.add(temp);
            log.info(temp);
        }
        log.info("----- result finish ----");
        return results;
    }

}

