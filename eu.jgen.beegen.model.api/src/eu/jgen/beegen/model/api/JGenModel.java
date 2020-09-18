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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

/**
 * This object represents a model stored in the container. A container connects
 * to the SQLite database for accessing model data.
 * 
 * @author Marek Stankiewicz
 *
 * @since 1.0.0
 */
public class JGenModel {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	protected JGenContainer genContainer;

	/**
	 * The class constractor creates an instance of the model object and associates
	 * the model object with the container. Container knows how to access the SQLite
	 * database to retrieve information about objects and their associations and
	 * properties.
	 * 
	 * @param genContainer reference to the container object
	 */
	public JGenModel(JGenContainer genContainer) {
		this.genContainer = genContainer;
		logger.setLevel(this.genContainer.getLogger().getLevel());
	}

	/**
	 * Returns name of the model as given during model creation of the model in the CA Gen.
	 * 
	 * @return Name of the model.
	 */
	public String getName() {
		Statement statement;
		try {
			String name = null;
			statement = genContainer.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT value FROM GenModel WHERE key = 'name';");
			name = rs.getString(1);
			rs.close();
			statement.close();
			return name;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return null;
	}

	/**
	 *  Gets the version of the utility creating the Bee Gen Model.
	 *  
	 * @return Version level.
	 */
	public String getVersion() {
		Statement statement;
		try {
			String version = null;
			statement = genContainer.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT value FROM GenModel WHERE key = 'version';");
			version = rs.getString(1);
			rs.close();
			statement.close();
			return version;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return null;
	}

	/**
	 * Gets the schema level of the CA Gen Model used as a source of metadata.
	 * 
	 * @return Schema level.
	 */
	public String getSchema() {
		Statement statement;
		try {
			String schema = null;
			statement = genContainer.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT value FROM GenModel WHERE key = 'schema';");
			schema = rs.getString(1);
			rs.close();
			statement.close();
			return schema;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return null;
	}

	/**
	 * Counts number of objects in the model.
	 * 
	 * @return number of objects in the model
	 */
	public int countObjects() {
		Statement statement;
		try {
			int count = 0;
			statement = genContainer.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM GenObjects;");
			count = rs.getInt(1);
			rs.close();
			statement.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return 0;

	}

	/**
	 * Counts a number of objects of the specified type in the model.
	 * 
	 * @param objMetaType object meta type
	 * @return number of objects of the specified type in the model.
	 */
	public long countTypeObjects(ObjMetaType objMetaType) {
		PreparedStatement statement;
		long count = 0;
		try {
			statement = genContainer.connection
					.prepareStatement("SELECT COUNT(*) FROM GenObjects WHERE objMnemonic = ?;");
			statement.setString(1, objMetaType.toString());
			ResultSet rs = statement.executeQuery();
			count = rs.getLong(1);
			rs.close();
			statement.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return count;
	}

	/**
	 * Finds the object in the model having a specified object id.
	 * 
	 * @param id unique identifier of the object
	 * @return object having specified identifier or <code>null</code> if not found
	 */
	public JGenObject findObjectById(int id) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenObjects WHERE id = ?;");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			JGenObject genObject = new JGenObject(this, rs.getInt("id"), rs.getShort("objType"),
					rs.getString("objMnemonic"), rs.getString("name"));
			return genObject;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return null;
	}

	/**
	 *  Finds and returns a list of all objects in the model.
	 *  
	 * @return list of objects
	 */
	public List<JGenObject> findAllObjects() {
		List<JGenObject> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenObjects;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new JGenObject(this, rs.getLong("id"), rs.getShort("objType"), rs.getString("objMnemonic"),
						rs.getString("name")));
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

	/*
	 * Finds all objects with the matching object type code.
	 */
	/**
	 * @param objMetaType code of the object type
	 * @return list of objects of the specified type
	 */
	public List<JGenObject> findTypeObjects(ObjMetaType objMetaType) {
		List<JGenObject> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenObjects WHERE objMnemonic = ?;");
			statement.setString(1, objMetaType.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new JGenObject(this, rs.getLong("id"), rs.getShort("objType"), rs.getString("objMnemonic"),
						rs.getString("name")));
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
	 * Finds and returns a list of objects having the specified  object type, property type and name.
	 * 
	 * @param objMetaType Type of object
	 * @param prpMetaType Type of property.
	 * @param name Name given to the object.
	 * @return List of objects
	 */
	public List<JGenObject> findNamedObjects(ObjMetaType objMetaType, PrpMetaType prpMetaType, String name) {
		List<JGenObject> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement(
					"SELECT * FROM GenObjects, GenProperties WHERE objType = ? AND id = objid AND prpType = ? AND value = ?;");
			statement.setShort(1, objMetaType.code);
			statement.setShort(2, prpMetaType.code);
			statement.setString(3, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new JGenObject(this, rs.getLong("id"), rs.getShort("objType"), rs.getString("objMnemonic"),
						rs.getString("name")));
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
	 * @return reference to the container object
	 */
	public JGenContainer getContainer() {
		return genContainer;
	}

	public String toString() {
		return "[" + ", name=" + this.getName() + "]";
	}

}
