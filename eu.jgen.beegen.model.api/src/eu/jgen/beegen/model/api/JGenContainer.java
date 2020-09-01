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
import java.util.logging.Logger;

public class JGenContainer  {
	
	private Logger logger =  Logger.getLogger(this.getClass().getName());
	
	protected Connection connection = null;
	
	protected JGenModel genModel = null;
	
	/*
	 * Connecting to the Bee Gen model.
	 */
	public JGenModel connect(String modelPath) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
        	Class.forName("org.sqlite.JDBC").newInstance();
        	String url = "jdbc:sqlite:" + modelPath;
			connection = DriverManager.getConnection(url);
	        logger.info("Connecting to the  model: " + modelPath);
	        genModel = new JGenModel(this);
			return genModel;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Cannot connect to the model.");
		}
 		return null;		
	}

	public void disconnect() {
		logger.info("Disconnecting from the model.");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("Problems when closing connection.");
		}
	}

}
