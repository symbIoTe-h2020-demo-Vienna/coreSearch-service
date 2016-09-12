package eu.h2020.symbiote;

import eu.h2020.symbiote.messaging.MessagingSubscriptions;
import eu.h2020.symbiote.search.SearchStorage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CoreSearchServiceApplication {

	public static final String DIRECTORY = "/corePlatformTriplestore";

	private static Log log = LogFactory.getLog(CoreSearchServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CoreSearchServiceApplication.class, args);
		try {
			MessagingSubscriptions.subscribeForSearchService();
		} catch (Exception e) {
			log.error("Error occured during subscribing from search service", e);
		}
	}

	@RestController
	class RegistrationController {



		public RegistrationController() {
		}

		@RequestMapping(method = RequestMethod.POST, value = "/search", consumes = "application/json")
		String query(@RequestBody SearchObject searchObject) {
			String result = SearchStorage.getInstance( DIRECTORY ).query(searchObject.getModelGraphUri(), searchObject.getQuery());
			log.info("Search returns string: ");
			log.info( result );
			return result;
		}
	}

}
