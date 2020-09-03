package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.meta.AscMetaType;
import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

class MetaTypeTest {
	
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
	
	@Test
	void testGetAssociationCodes() {		
		List<AscMetaType> list = genContainer.meta.getAssociationCodes(ObjMetaType.FUNCDEF);
		assertEquals(60,  list.size());
		//System.out.println(list.size());
		for (AscMetaType ascMetaType : list) {
			//System.out.println(ascMetaType.name());
			assertEquals("BUILTFRM", ascMetaType.name());
			break;
		}		 
	}
	
	@Test
	void testGetPropertyCodes() {		
		List<PrpMetaType> list = genContainer.meta.getPropertyCodes(ObjMetaType.FUNCDEF);
		assertEquals(52,  list.size());
		//System.out.println(list.size());
		for (PrpMetaType prpMetaType : list) {
			//System.out.println(prpMetaType.name());
			assertEquals("ARTPTR", prpMetaType.name());
			break;
		}		 
	}
	
	@Test
	void testIsAssociationOnetoOne() {
		boolean isCardOne  = genContainer.meta.isAssociationOnetoOne(ObjMetaType.FUNCDEF, AscMetaType.HASAUTO);
		assertTrue(isCardOne);
		//System.out.println(isCardOne);
	}
	
	@Test
	void testIsAssociationOptional() {
		boolean optionality  = genContainer.meta.isAssociationOptional(ObjMetaType.FUNCDEF, AscMetaType.HASAUTO);
		assertFalse(optionality);
		//System.out.println(isCardOne);
	}
	
	@Test
	void testIsAssociationOrdered() {
		boolean ordered  = genContainer.meta.isAssociationOptional(ObjMetaType.FUNCDEF, AscMetaType.HASAUTO);
		assertFalse(ordered);
		//System.out.println(isCardOne);
	}
	
	@Test
	void testGetsAssociationInverse() {
		short inverse  = genContainer.meta.getAssociationInverse(ObjMetaType.FUNCDEF, AscMetaType.HASAUTO);
		assertEquals(100, inverse);
		//System.out.println(isCardOne);
	}

	@Test
	void testGetPropertyFormat() {
		String format  = genContainer.meta.getPropertyFormat(ObjMetaType.FUNCDEF, PrpMetaType.NAME);
		assertEquals("NAME", format); 
	//	System.out.println(format);
	}
	
	@Test
	void testGetPropertyLength() {
		short length  = genContainer.meta.getPropertyLength(ObjMetaType.FUNCDEF, PrpMetaType.OPCODE);
		assertEquals(2, length); 
	//	System.out.println(format);
	}
}
