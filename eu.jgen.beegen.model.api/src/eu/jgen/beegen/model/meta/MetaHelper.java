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
package eu.jgen.beegen.model.meta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.jgen.beegen.model.api.JGenContainer;

/**
 * This class allows retrieving metadata about metadata information 
 * and helping understand the contents of the model. The average model 
 * could be a massive collection of objects, associations between them, 
 * and object properties. Objects represent many different things, and 
 * associations can have different reasons why two objects are associated. 
 * More, each object can have many properties that are relevant when 
 * generating runtime ready applications. The container maintains 
 * a repository of metadata about metadata describing the models' contents, 
 * and this class provides methods helping retrieve such information.
 * 
 * @author Marek Stankiewicz
 * @since 1.0.0
 *
 */
public class MetaHelper {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected JGenContainer genContainer;
	
	public MetaHelper(JGenContainer genContainer) {
		this.genContainer = genContainer;
		logger.setLevel(this.genContainer.getLogger().getLevel());
	}
	
	/**
	 * The method would return the default value for character type properties 
	 * if the model did not explicitly set value for such property.
	 * 
	 * @param objMetaType code for the type of object
	 * @param prpMetaType code for the type of property
	 * @return default value
	 */
	public char getDefaultCharProperty(ObjMetaType objMetaType, PrpMetaType prpMetaType) {
		PreparedStatement statement;
		String text = " ";
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType = ? AND prpType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			ResultSet rs = statement.executeQuery();
			text = rs.getString(8);
			rs.close();
			statement.close();
			return text.charAt(0);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return ' ';
	}
	
	/**
	 * The method would return the default value for text type properties 
	 * if the model did not explicitly set value for such property.
	 * 
	 * @param objMetaType code for the type of object
	 * @param prpMetaType code for the type of property
	 * @return default value
	 */
	public String getDefaultTextProperty(ObjMetaType objMetaType, PrpMetaType prpMetaType) {
		PreparedStatement statement;
		String text = "";
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType = ? AND prpType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				text = rs.getString(7);
			}			
			rs.close();
			statement.close();
			return text;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return "";
	}
	
	/**
	 * The method would return the default value for number type properties 
	 * if the model did not explicitly set value for such property.
	 * 
	 * @param objMetaType code for the type of object
	 * @param prpMetaType code for the type of property
	 * @return default value
	 */
	public int getDefaultNumberProperty(ObjMetaType objMetaType, PrpMetaType prpMetaType) {
		PreparedStatement statement;
		int number = 0;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType = ? AND prpType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			ResultSet rs = statement.executeQuery();
			number = rs.getInt(6);
			rs.close();
			statement.close();
			return number;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return 0;
	}
	
	/**
	 * The method returns a list of association type codes for the specified object type.
	 * 
	 * @param objMetaType code for the type of object
	 * @return list of codes
	 */
	public List<AscMetaType> getAssociationCodes(ObjMetaType objMetaType ) {		
		List<AscMetaType> list = new ArrayList<AscMetaType>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType=?;");
			statement.setShort(1, objMetaType.code);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {			
				list.add(AscMetaType.valueOf(rs.getString(3)));				
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
	 * The method returns a list of property type codes for the specified object type.
	 * 
	 * @param objMetaType code for the type of object
	 * @return list of codes
	 */
	public List<PrpMetaType> getPropertyCodes(ObjMetaType objMetaType ) {		
		List<PrpMetaType> list = new ArrayList<PrpMetaType>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType=?;");
			statement.setShort(1, objMetaType.code);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {			
				list.add(PrpMetaType.valueOf(rs.getString(3)));				
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
	 * The method returns true if specified association for the association's 
	 * specified object cardinality is type one-to-one, otherwise returns false.
	 * 
	 * @param objMetaType code for the type of object
	 * @param ascMetaType code for the type of association
	 * @return true if cardinality of association is one-to-one
	 */
	public boolean isAssociationOnetoOne(ObjMetaType objMetaType, AscMetaType ascMetaType) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType = ? AND ascType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, ascMetaType.code);
			ResultSet rs = statement.executeQuery();
			String card = rs.getString(7);
			rs.close();
			statement.close();
			if (card.equals("1")) {
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return false;		 
	} 
	
	/**
	 * The method returns true if specified association for the specified 
	 * object direction of the association is the type of forward, 
	 * otherwise returns false.
	 * 
	 * @param objMetaType code for the type of object
	 * @param ascMetaType code for the type of association
	 * @return true if direction of association is forward
	 */
	public boolean isAssociationForward(ObjMetaType objMetaType, AscMetaType ascMetaType) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType = ? AND ascType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, ascMetaType.code);
			ResultSet rs = statement.executeQuery();
			String direction = rs.getString(4);
			rs.close();
			statement.close();
			if (direction.equals("F")) {
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return false;		 
	} 
	
	/**
	 * The method returns true if specified association for the specified 
	 * object is optional, otherwise returns false.
	 * 
	 * @param objMetaType code for the type of object
	 * @param ascMetaType code for the type of association
	 * @return true if direction of association is forward
	 */
	public boolean isAssociationOptional(ObjMetaType objMetaType, AscMetaType ascMetaType) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType = ? AND ascType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, ascMetaType.code);
			ResultSet rs = statement.executeQuery();
			String optionality = rs.getString(6);
			rs.close();
			statement.close();
			if (optionality.equals("Y")) {
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return false;		 
	}
	
	/**
	 * The method returns true if specified association for the specified 
	 * object has to be in the correct sequence, otherwise returns false. 
	 * 
	 * @param objMetaType code for the type of object
	 * @param ascMetaType code for the type of association
	 * @return true if order of returned associated objects has to be preserved
	 */
	public boolean isAssociationOrdered(ObjMetaType objMetaType, AscMetaType ascMetaType) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType = ? AND ascType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, ascMetaType.code);
			ResultSet rs = statement.executeQuery();
			String ordered = rs.getString(8);
			rs.close();
			statement.close();
			if (ordered.equals("Y")) {
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return false;		 
	}
	
	/**
	 * The method returns the raw value of the association type, which 
	 * indicates reverse direction.
	 * 
	 * @param objMetaType code for the type of object
	 * @param ascMetaType code for the type of association
	 * @return raw value of the association type code
	 */
	public short getAssociationInverse(ObjMetaType objMetaType, AscMetaType ascMetaType) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaAssociations WHERE fromObjType = ? AND ascType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, ascMetaType.code);
			ResultSet rs = statement.executeQuery();
			short inverse = rs.getShort(5);
			rs.close();
			statement.close();
			return inverse;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return -1;		 
	}
	
	/**
	 * The method returns the property type format for the specified type of the object.
	 * 
	 * @param objMetaType code for the type of object
	 * @param prpMetaType code for the type of property
	 * @return text describing property format
	 */
	public String getPropertyFormat(ObjMetaType objMetaType, PrpMetaType prpMetaType) {
		PreparedStatement statement;
		String text = "";
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType = ? AND prpType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			ResultSet rs = statement.executeQuery();
			text = rs.getString(4);
			rs.close();
			statement.close();
			return text;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return "";
	}
	
	/**
	 * The method returns the property length  for the specified type of the object.
	 * 
	 * @param objMetaType code for the type of object
	 * @param prpMetaType code for the type of property
	 * @return length of property 
	 */
	public short getPropertyLength(ObjMetaType objMetaType, PrpMetaType prpMetaType) {
		PreparedStatement statement;
		short length = 0;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenMetaProperties WHERE objType = ? AND prpType = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			ResultSet rs = statement.executeQuery();
			length = rs.getShort(5);
			rs.close();
			statement.close();
			return length;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}		
		return 0;
	}
	
	public AscMetaType getAscMetaType(short code) {		
		for (AscMetaType ascMetaType : AscMetaType.values()) {
			if (ascMetaType.code == code) {
				return ascMetaType;
			}
		}
		return AscMetaType.INVALID;		
	}
	
	public ObjMetaType getObjMetaType(short code) {		
		for (ObjMetaType objMetaType : ObjMetaType.values()) {
			if (objMetaType.code == code) {
				return objMetaType;
			}
		}
		return ObjMetaType.INVALID;		
	}
	
	public PrpMetaType getPrpMetaType(short code) {		
		for (PrpMetaType prpMetaType : PrpMetaType.values()) {
			if (prpMetaType.code == code) {
				return prpMetaType;
			}
		}
		return PrpMetaType.INVALID;		
	}


}
