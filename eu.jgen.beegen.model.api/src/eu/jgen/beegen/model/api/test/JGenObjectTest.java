package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.api.JGenObject;
import eu.jgen.beegen.model.meta.AscMetaType;
import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

class JGenObjectTest {
	
	
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

	@Test
	void testFindCharacterProperty() {		
		JGenObject genObject = genModel.findObjectById(25165824);		
		char character = genObject.findCharacterProperty(PrpMetaType.DOMAN);
		assertEquals('T',character);
		character = genObject.findCharacterProperty(PrpMetaType.TISUPPLY);
		assertEquals('Y',character);
	}
	
	@Test
	void testFindTextProperty() {		
		JGenObject genObject = genModel.findObjectById(25165824);		
		String text = genObject.findTextProperty(PrpMetaType.NAME);
		assertEquals("CONCAT",text);
	}
	
	@Test
	void testFindNumberProperty() {		
		JGenObject genObject = genModel.findObjectById(25165824);		
		int number = genObject.findNumberProperty(PrpMetaType.CEID);
		assertEquals(88,number);
		number = genObject.findNumberProperty(PrpMetaType.OPCODE);
		assertEquals(24,number);
	}
	
	@Test
	void testFindAssociationMany() {
		JGenObject genObject = genModel.findObjectById(118489142);
		int count = genObject.findAssociationMany(AscMetaType.DSCBYA).size();
		assertEquals(4,count);
	}
	
	@Test
	void testFindAssociationOne() {
		JGenObject genObject = genModel.findObjectById(118489142);
		JGenObject toGenObject = genObject.findAssociationOne(AscMetaType.DETLOF);
		assertEquals(79691776,toGenObject.objId);
	}
	
	@Test
	void testGetObjMetaType() {
		JGenObject genObject = genModel.findObjectById(118489142);	
		assertEquals(genObject.objType, genObject.getObjMetaType().code);
	}
	
	@Test
	void testFindNamedObject() {
		List<JGenObject> list = genModel.findNamedObjects(ObjMetaType.FUNCDEF, PrpMetaType.NAME, "CONCAT");	
		for (JGenObject genObject : list) {
			//System.out.println(genObjectt.name);
			assertEquals("CONCAT", genObject.name);
			break;
		}
		
		//assertEquals(genObject.objType, genObject.getObjMetaType().code);
	}
	

}
