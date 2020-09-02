package eu.jgen.beegen.model.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Level;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.sqlite.*;

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
		try {
			assertTrue(genContainer.connect(MODEL_PATH) != null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			assertFalse(false);
		}	
	}
	
	@Test
	void testGetModelLocation() {
		try {
			assertTrue(genContainer.connect(MODEL_PATH) != null);
			assertEquals(genContainer.getModelLocation(), MODEL_PATH);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			assertFalse(false);
		}			
	}
	
	@Test
	void testGetLogger() {
		try {
			assertTrue(genContainer.connect(MODEL_PATH) != null);
			assertEquals(genContainer.getLogger().getLevel(), Level.SEVERE);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			assertFalse(false);
		}
	}

}
