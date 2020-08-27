package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.*;

import eu.jgen.beegen.model.api.JGenContainer;

class JGenContainerTest {
	
	private static final String MODEL_PATH = "//Users/marek/Gen/Models/model01.ief/bee/MODEL01.db";
	
	JGenContainer genContainer;
 
	@BeforeEach
	void setUp() throws Exception {
		genContainer = new JGenContainer();
	}

	@AfterEach
	void tearDown() throws Exception {
		genContainer.disconnect();
		genContainer = null;
	}

	@Test
	void testConnect() {
		try {
			assertTrue(genContainer.connect(MODEL_PATH) != null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
