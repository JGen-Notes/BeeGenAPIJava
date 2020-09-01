package eu.jgen.beegen.model.api.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eu.jgen.beegen.model.meta.ObjMetaType;

class ObjMetaTypeTest {

	@Test
	void test() {
		
		System.out.println(ObjMetaType.ACBLKBSD.code);
		System.out.println(ObjMetaType.ACBLKBSD);
		
		System.out.println(ObjMetaType.valueOf("FUNCDEF").code);
		
		System.out.println(ObjMetaType.ACBLKBSD.name());
		
		
		System.out.println(ObjMetaType.DISCOVER.getType((short) 24));
		
	}

}
