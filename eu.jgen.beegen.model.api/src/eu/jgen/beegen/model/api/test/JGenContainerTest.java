package eu.jgen.beegen.model.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Level;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.api.JGenContainer;

 
class JGenContainerTest {
	
	public static final String MODEL_PATH = "/Users/marek/beegen01.ief/bee/BEEGEN01.db";
	
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
		assertTrue(genContainer.connect(MODEL_PATH) != null);	
	}
	
	@Test
	void testGetModelLocation() {
		assertTrue(genContainer.connect(MODEL_PATH) != null);
		assertEquals(genContainer.getModelLocation(), MODEL_PATH);		
	}
	
	@Test
	void testGetLogger() {
		assertTrue(genContainer.connect(MODEL_PATH) != null);
		assertEquals(genContainer.getLogger().getLevel(), Level.SEVERE);
	}

}
