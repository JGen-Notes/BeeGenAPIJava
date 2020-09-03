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

import eu.jgen.beegen.model.meta.Meta;

public class JGenContainer  {
	
	private Logger logger =  Logger.getLogger(this.getClass().getName());
	
	public Connection connection = null;
	
	public Meta meta = null;
	
	protected JGenModel genModel = null;
	
	protected String modelPath = null;
	
	/*
	 * Get logger used by the container.
	 */
	public Logger getLogger() {
		return this.logger;
	}
	
	/*
	 * Get model.
	 */	
	public JGenModel getModel() {
		return genModel;
	}
	
	/*
	 * Connecting to the Bee Gen model.
	 */
	public JGenModel connect(String modelPath) {
        logger.setLevel(Level.SEVERE);
		this.modelPath = modelPath;
		try {
        	Class.forName("org.sqlite.JDBC").newInstance();
        	String url = "jdbc:sqlite:" + modelPath;
			this.connection = DriverManager.getConnection(url);
	        this.logger.info("Connecting to the  model: " + modelPath);
	        this.genModel = new JGenModel(this);
	        this.meta = new Meta(this);
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
	
	/*
	 * Disconnect from the Bee Gen Model.
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
	
	/*
	 * Get path to location of the Bee Gen Model.
	 */
	public String getModelLocation() {
		return this.modelPath;
	}

}
