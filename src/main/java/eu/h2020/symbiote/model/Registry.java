/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model;

import fr.inrialpes.exmo.align.impl.renderer.RDFRendererVisitor;
import fr.inrialpes.exmo.align.parser.AlignmentParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.semanticweb.owl.align.AlignmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author jab
 */
public class Registry {

    private static Log log = LogFactory.getLog( Registry.class );
    private final TripleStore tripleStore;

    public Registry(TripleStore tripleStore) {

        this.tripleStore = tripleStore;
        log.info("Creating Registry, loading stored data");
//        List<String> data = tripleStore.loadDataFromDataset();
        log.info("Data loaded!" );
    }

    public void registerModel(BigInteger modelId, String rdf, RDFFormat format) {
        tripleStore.insertGraph(Ontology.getModelGraphURI(modelId), rdf, format);
        log.debug(String.format("model registered: modelId={}, format={}, rdf={}", modelId, format, rdf));
    }

    public void registerPlatform(BigInteger platformId, String rdf, RDFFormat format, BigInteger modelId) {
        tripleStore.insertGraph(Ontology.getPlatformGraphURI(platformId), rdf, format);
        tripleStore.insertGraph(Ontology.PLATFORMS_GRAPH, Ontology.getPlatformMetadata(platformId, modelId), format);
        log.debug(String.format("platform registered: platformId={}, modelId={}, format={}, rdf={}", platformId, modelId, format, rdf));
    }

//    public int registerMapping(int modelId1, int modelId2, String mapping) throws UnsupportedEncodingException {
//        // use library to parse mapping file to RDF, then read RDFXML into store
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, "UTF-8")), true)) {
//            AlignmentParser parser = new AlignmentParser(0);
//            parser.initAlignment(null);
//            try {
//                parser.parseString(mapping).render(new RDFRendererVisitor(writer));
//            } catch (AlignmentException e) {
//                LOGGER.error("Couldn't load the alignment:", e);
//            }
//            writer.flush();
//        }
//        String mappingRDF = out.toString();
//        Model model = ModelFactory.createDefaultModel();
//        model.read(new ByteArrayInputStream(out.toByteArray()), null, RDFFormat.RDFXML.toString());
//
//        int mappingId = mappingIdCounter++;
//        tripleStore.insertGraph(Ontology.getMappingGraphURI(mappingId), model, RDFFormat.RDFXML);
//        tripleStore.insertGraph(Ontology.MAPPING_GRAPH, Ontology.getMappingMetadata(modelId1, modelId2, mappingId), RDFFormat.NTriples);
//        LOGGER.debug("mapping registered: modelId1={}, modelId2={}, mapping={}", modelId1, modelId2, mappingRDF);
//        return mappingId;
//    }

}
