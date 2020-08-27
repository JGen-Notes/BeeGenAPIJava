package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ca.gen.jmmi.schema.ObjTypeCode;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.api.JGenObject;

class JGenModelTest {
	
	private static final String MODEL_PATH = "//Users/marek/Gen/Models/model01.ief/bee/MODEL01.db";
	
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
	void testCountObjects() {
		//System.out.println(genModel.countObjects());
		assertEquals(1349, genModel.countObjects());
	}
	
	@Test
	void testCountTypeObjects() {
		//System.out.println(genModel.countTypeObjects(ObjTypeCode.FUNCDEF));
		assertEquals(51, genModel.countTypeObjects(ObjTypeCode.FUNCDEF));
	}
	
	@Test
	void testFindTypeObjects() {
		List<JGenObject> list = genModel.findTypeObjects(ObjTypeCode.FUNCDEF);
		assertEquals(51, list.size());
		for (JGenObject genObject : list) {
		//	System.out.println(genObject.getId() + "," + genObject.objType + "," + genObject.objMnemonic);
		}
		
		
		//assertEquals(51, genModel.countTypeObjects(ObjTypeCode.FUNCDEF));
	}
	
	@Test
	void testFindObjectById() {
		assertEquals(25165874, genModel.findObjectById(25165874).objId);
	}
	
	

}
