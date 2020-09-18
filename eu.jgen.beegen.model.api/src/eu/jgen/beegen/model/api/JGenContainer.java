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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import eu.jgen.beegen.model.meta.MetaHelper;

/**
 * 
 * This object represents a container.
 * 
 * Bee Gen Model Framework uses the SQLite database to store metadata representing 
 * your application design as imported from the CA Gen Local Model. The container 
 * connects to the specific location of the SQLite database and provides access 
 * to the model. Therefore container represents the SQLite database as its specific physical implementation.
 * 
 * @author Marek Stankiewicz
 *
 *	@since 1.0.0
 */
public class JGenContainer  {
	
	private Logger logger =  Logger.getLogger(this.getClass().getName());
	
	public Connection connection = null;
	
	public MetaHelper meta = null;
	
	protected JGenModel genModel = null;
	
	protected String containerPath = null;
	
	/*
	 * Get logger used by the container.
	 */
	public Logger getLogger() {
		return this.logger;
	}
	
	/**
	 * Returns object representing the model stored in the container 
	 * if the container is connected to the SQLite database 
	 * and contains a valid model.
	 * 
	 * @return   object representing model or <code>null</code> if valid model does not exist.
	 */
	public JGenModel getModel() {
		return genModel;
	}
	
	/**
	 * Connects to the SQLite database storing metadata with the application design. 
	 * 
	 * @param containerPath  path to the location of the SQLite database
	 * @return object representing model or <code>null</code> if connection was unsuccessful.
	 */
	public JGenModel connect(String containerPath) {
        logger.setLevel(Level.SEVERE);
		this.containerPath = containerPath;
		try {
        	Class.forName("org.sqlite.JDBC").newInstance();
        	String url = "jdbc:sqlite:" + containerPath;
			this.connection = DriverManager.getConnection(url);
	        this.logger.info("Connecting to the  model: " + containerPath);
	        this.genModel = new JGenModel(this);
	        this.meta = new MetaHelper(this);
			return genModel;
		} catch (SQLException e) {			
			logger.severe("ERROR: Cannot connect to the model due to SQLite error.");
			e.printStackTrace();
		} catch (InstantiationException e) {			
			logger.severe("ERROR: Cannot instanciate SQLite driver correctly.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			logger.severe("ERROR: Cannot instanciate SQLite driver classdue to illegal access attempt.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			logger.severe("ERROR: Cannot find SQLite driver class.");
			e.printStackTrace();
		}
 		return null;		
	}

	/**
	 * Disconnects from the SQLite database. All actions on the model or 
	 * any part of the model will reject any attempt to use 
	 * any functionality of the API.
	 */
	public void disconnect() {
		logger.info("Disconnecting from the model.");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Problems when closing connection.");
		}
	}
	
	/**
	 * Returns path to the location of the SQLite database as used during connect operation.
	 * 
	 * @return path to the SQLite database location 
	 */
	public String getContainerLocation() { 
		return this.containerPath;
	}
	

	public String toString() {		
		return "[" + ", modelPath=" + this.containerPath + "]" ;
	}

}
