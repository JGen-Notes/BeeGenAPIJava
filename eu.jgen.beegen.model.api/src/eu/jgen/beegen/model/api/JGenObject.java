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

import java.util.ArrayList;
import java.util.List;

import com.ca.gen.jmmi.schema.AscTypeCode;
import com.ca.gen.jmmi.schema.ObjTypeCode;
import com.ca.gen.jmmi.schema.PrpTypeCode;

public class JGenObject {

	protected JGenModel genModel;
	public long objId = -1;
	public short objType = -1;
	public String objMnemonic = "UNKNOWN";
	public String name = "";


	public JGenObject(JGenModel genModel, long objId, short objType, String objMnemonic, String name) {
		this.genModel = genModel;
		this.objId = objId;
		this.objType = objType;
		this.objMnemonic = objMnemonic;
		this.name = name;
	}
	
	public JGenObject(JGenModel genModel, long objId, short objType, String objMnemonic) {
		this.genModel = genModel;
		this.objId = objId;
		this.objType = objType;
		this.objMnemonic = objMnemonic;
	}

	public char findCharacterProperty(PrpTypeCode prpTypeCode) {
		
		return ' ';
	}

	public int findNumericProperty(PrpTypeCode prpTypeCode) {

		return -1;
	}

	public String findTextProperty(PrpTypeCode prpTypeCode) {
		
		return null;
	}

	public List<JGenObject> findAssociationMany(AscTypeCode ascTypeCode) {
		List<JGenObject> list = new ArrayList<JGenObject>();


		return list;
	}

	public JGenObject findAssociationOne(AscTypeCode ascTypeCode) {
	
		
		return null;
	}

	public long getId() {		
		return objId;
	}

	public ObjTypeCode getObjTypeCode() {
		return ObjTypeCode.valueOf(objMnemonic);
	}

}
