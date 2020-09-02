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

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.jgen.beegen.model.meta.ObjMetaType;
import eu.jgen.beegen.model.meta.PrpMetaType;

public class JGenModel {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	protected JGenContainer genContainer;

	/*
	 * Creates instance of the model.
	 */
	public JGenModel(JGenContainer genContainer) {
		this.genContainer = genContainer;
		logger.setLevel(this.genContainer.getLogger().getLevel());
	}
	
	/*
	 * Gets name of the model.
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
	
	/*
	 * Gets version of the utility creating Bee Gen Model.
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
	
	/*
	 * Gets schema level of the CA Gen Model.
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

	/*
	 * Finds number objects in the model.
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

	/*
	 * Counts number of the objects of the specified type.
	 */
	public long countTypeObjects(ObjMetaType objMetaType) {
		PreparedStatement statement;
		long count = 0;
		try {
			statement = genContainer.connection.prepareStatement("SELECT COUNT(*) FROM GenObjects WHERE objMnemonic = ?;");
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

	/*
	 * Finds object in the model using object id.
	 */
	public JGenObject findObjectById(int id) {
		PreparedStatement statement;
		try {
			statement = genContainer.connection.prepareStatement("SELECT * FROM GenObjects WHERE id = ?;");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			JGenObject genObject = new JGenObject(this, rs.getInt("id"), rs.getShort("objType"), rs.getString("objMnemonic"),
					rs.getString("name"));
			return genObject;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot execute query.");
		}
		return null;
	}

	/*
	 * Returns list of all objects in the model.
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

	public JGenObject findNamedObject(ObjMetaType objMetaType, PrpMetaType prpMetaType, String name) {
		return null;
	}

	/*
	 * Gets container owning this model.
	 */
	public JGenContainer getContainer() {
		return genContainer;
	}

}
