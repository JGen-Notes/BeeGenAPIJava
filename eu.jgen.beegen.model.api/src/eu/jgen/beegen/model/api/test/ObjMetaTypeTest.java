package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

class ObjMetaTypeTest {
	
private static final String MODEL_PATH = JGenContainerTest.MODEL_PATH;
	
	JGenContainer genContainer;
	JGenModel genModel;
	
 
	@BeforeEach
	void setUp() throws Exception {
		genContainer = new JGenContainer();
		genModel = genContainer.connect(MODEL_PATH);
	}

	@AfterEach
	void tearDown() throws Exception {
		genContainer.disconnect();
		genContainer = null;
	}
	
	@Test void testObjMetaType() {
		assertEquals(24, ObjMetaType.valueOf("FUNCDEF").code);
		assertEquals("FUNCDEF", ObjMetaType.FUNCDEF.name());
		assertEquals("FUNCDEF", ObjMetaType.DISCOVER.getType((short) 24).name());
	}


	@Test
	void testGetDefaultCharProperty() {		
		char character = genContainer.meta.getDefaultCharProperty(ObjMetaType.FUNCDEF, PrpMetaType.DOMAN);
		assertEquals('T', character);
	}
	
	@Test
	void testGetDefaultTextProperty() {		
		String text = genContainer.meta.getDefaultTextProperty(ObjMetaType.FUNCDEF, PrpMetaType.NAME);
		assertEquals("", text);
	}
	
	
	@Test
	void testGetDefaultNumberProperty() {		
		int number = genContainer.meta.getDefaultNumberProperty(ObjMetaType.DEVICE, PrpMetaType.ROWS);
		assertEquals(24, number);
	}

}
