/**
 * [The "BSD license"]
 * Copyright (c) 2016, JGen Notes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions 
 *    and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package eu.jgen.beegen.model.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.jgen.beegen.model.meta.AscMetaType;
import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;



/**
 * This model object represents a single element of the model. Model 
 * can own any number of objects of many different types. Each object 
 * may have number of its properties and associations with other 
 * objects. You can follow associations discovering many other objects. 
 * The model functionality allows you to select a number of objects 
 * of your interest and they can be a starting point to the navigation 
 * thru the model.
 * 
 * @author Marek Stankiewicz
 *
 * @since 1.0.0
 */
public class JGenObject {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public JGenContainer genContainer;

	public JGenModel genModel;
	public long objId = -1;
	public short objType = -1;
	public String objMnemonic = "UNKNOWN";
	public String name = "";
	private ObjMetaType objMetaType;


	public JGenObject(JGenModel genModel, long objId, short objType, String objMnemonic, String name) {
		this.genModel = genModel;
		this.genContainer = genModel.genContainer;
		this.objId = objId;
		this.objType = objType;
		this.objMnemonic = objMnemonic;
		this.name = name;
		this.objMetaType = ObjMetaType.valueOf(objMnemonic);
		logger.setLevel(this.genModel.genContainer.getLogger().getLevel());
	}
	
	public JGenObject(JGenModel genModel, long objId, short objType, String objMnemonic) {
		this.genModel = genModel;
		this.genContainer = genModel.genContainer;
		this.objId = objId;
		this.objType = objType;
		this.objMnemonic = objMnemonic;
		this.objMetaType = ObjMetaType.valueOf(objMnemonic);
		logger.setLevel(this.genModel.genContainer.getLogger().getLevel());
	}
	
	/**
	 * The method finds a character property of the specified type for this object. 
	 * It returns if the object does not have the property of the specified type or 
	 * value existing property is not character type.
	 * 
	 * @param prpMetaType type of property
	 * @return value of the property
	 */
	public char findCharacterProperty(PrpMetaType prpMetaType) {
		PreparedStatement statement;
		char character = ' ';
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenProperties WHERE objid=? AND mnemonic=?;");
			statement.setLong(1, objId);
			statement.setString(2, prpMetaType.name());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String text = rs.getString(5);
				if (text.length() >= 1) {
					character = text.charAt(0);
				} 							
			} else {
				character = genContainer.meta.getDefaultCharProperty(ObjMetaType.valueOf(this.objMnemonic), prpMetaType);
			}
			rs.close();
			statement.close();
			return character;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return character;
	}


	/**
	 * The method finds a number property of the specified type for this object. 
	 * It returns in the object's case that does not have of the specified type or 
	 * value existing property is not character type.
	 * 
	 * @param prpMetaType type of property
	 * @return value of the property
	 */
	public int findNumberProperty(PrpMetaType prpMetaType) {
		PreparedStatement statement;
		int number = 0;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenProperties WHERE objid=? AND mnemonic=?;");
			statement.setLong(1, objId);
			statement.setString(2, prpMetaType.name());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				number = Integer.parseInt(rs.getString(5)); 							
			} else {
				number = genContainer.meta.getDefaultNumberProperty(ObjMetaType.valueOf(this.objMnemonic), prpMetaType);
			}
			rs.close();
			statement.close();
			return number;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return number;
	}

	/**
	 * The method finds a text property of the specified type for this object. It returns an 
	 * empty string if the object, for some reason, does not have the property of the 
	 * specified type or value existing property is not character type.
	 * 
	 * @param prpMetaType type of property
	 * @return value of the property
	 */
	public String findTextProperty(PrpMetaType prpMetaType) {
		PreparedStatement statement;
		String text = "";
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenProperties WHERE objid=? AND mnemonic=?;");
			statement.setLong(1, objId);
			statement.setString(2, prpMetaType.name());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				text = rs.getString(5); 							
			} else {
				text = genContainer.meta.getDefaultTextProperty(ObjMetaType.valueOf(this.objMnemonic), prpMetaType);
			}
			rs.close();
			statement.close();
			return text;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return text;
	}


	/**
	 * Finds and returns the collection of associated objects with itself. You specify as a parameter 
	 * what type of associations to return. Returns empty collection if no matching associations 
	 * are found.
	 * 
	 * @param ascMetaType type of association
	 * @return list of associated objects
	 */
	public List<JGenObject> findAssociationMany(AscMetaType ascMetaType) {
		List<JGenObject> list = new ArrayList<JGenObject>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenAssociations WHERE fromObjid=? AND ascMnemonic=?;");
			statement.setLong(1, objId);
			statement.setString(2, ascMetaType.name());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				JGenObject genObject = genModel.findObjectById(rs.getInt("toObjid"));				
				list.add(genObject);				
			}
			rs.close();
			statement.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return list;
	}

	/**
	 * Finds and returns a single associated object with itself. You specify as a parameter 
	 * what type of association to return. Returns null if such association does not exist.
	 * 
	 * @param ascMetaType type of association
	 * @return single associated object or null
	 */
	public JGenObject findAssociationOne(AscMetaType ascMetaType) {
		JGenObject genObject = null;
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenAssociations WHERE fromObjid=? AND ascMnemonic=?;");
			statement.setLong(1, objId);
			statement.setString(2, ascMetaType.name());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				genObject = genModel.findObjectById(rs.getInt("toObjid"));
				break;
			}
			rs.close();
			statement.close();
			return genObject;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return genObject;
	}

	public long getId() {		
		return objId;
	}

	public ObjMetaType getObjMetaType() {
		return objMetaType;
	}
 
	public String toString() {		
		String name = "";
		if (this.name.length() > 0) {
			name = ", name=" + this.name;
		}
		return "[" + this.objId + ", objType=" + this.objType + ", mnemonic=" + this.objMnemonic + name + "]" ;
	}

}
