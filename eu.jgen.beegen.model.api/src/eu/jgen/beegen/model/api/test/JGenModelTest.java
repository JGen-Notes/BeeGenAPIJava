package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.api.JGenObject;

import eu.jgen.beegen.model.meta.ObjMetaType;

class JGenModelTest {
	
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
	void testCountObjects() {
		//System.out.println(genModel.countObjects());
		assertEquals(1228, genModel.countObjects());
	}
	
	@Test
	void testCountTypeObjects() {
		//System.out.println(genModel.countTypeObjects(ObjMetaType.FUNCDEF));
		assertEquals(51, genModel.countTypeObjects(ObjMetaType.FUNCDEF));
	}
	
	@Test
	void testFindTypeObjects() {
		List<JGenObject> list = genModel.findTypeObjects(ObjMetaType.FUNCDEF);
		assertEquals(51, list.size());
	}
	
	@Test
	void testFindObjectById() {
		assertEquals(25165874, genModel.findObjectById(25165874).objId);
	}
	
	@Test
	void testFindAllObjects() {
		List<JGenObject> list = genModel.findAllObjects();
		assertEquals(1228, list.size());
	}
	
	

}
