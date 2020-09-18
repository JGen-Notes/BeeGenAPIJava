package eu.jgen.beegen.model.api.example;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenModel;
import eu.jgen.beegen.model.api.JGenObject;
import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

public class ListAllActionBlockNames {

	public static void main(String[] args) {
		JGenContainer genContainer = new JGenContainer();
		JGenModel genModel = genContainer.connect("/Users/Marek/beegen01.ief/bee/BEEGEN01.db");
		System.out.println("List of action blocks in the model: " + genModel.getName() + ", Using schema level: "
				+ genModel.getSchema() + "\n");
		for (JGenObject genObject : genModel.findTypeObjects(ObjMetaType.ACBLKBSD)) {
			System.out.println("\tAction block name: " + genObject.findTextProperty(PrpMetaType.NAME) + ", having id: "
					+ genObject.objId);
		}
		genContainer.disconnect();
		System.out.println("\nCompleted.");
	}

}
