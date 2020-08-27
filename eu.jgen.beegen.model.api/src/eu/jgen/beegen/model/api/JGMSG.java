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

/**
 * This class provides messages and return values.
 * 
 * @author Marek Stankiewicz
 * @since 1.1
 */
public final class JGMSG {

	private static final String CANNOT_CREATE_A_NEW_MODEL_TEXT = "Cannot create a new model";

	public static final String version = "9.1.A5";

	public static final String THIRD_PARTY_RETURN_CODE = "Error code raised by third party software";

	public static final String MSGTEXT_ILLEGAL_FOR_LOCAL_ENCYCLOPEDIA = "Illegal for local encyclopedia";

	public static final String MSGTEXT_ILLEGAL_FOR_REMOTE_ENCYCLOPEDIA = "Illegal for remote encyclopedia";

	public static final String MSGTEXT_ILLEGAL_FOR_JGEN_ENCYCLOPEDIA = "Illegal for JGen encyclopedia";

	public static final int SUCCESSFUL = 0;

	public static final int MAXIMUM_COUNT_REACHED = 1;

	public static final int ENCY_NOT_FOUND = 2;

	public static final int CONNECT_FAILED = 3;

	public static final int DISCONNECT_FAILED = 4;

	public static final int USER_NOT_FOUND = 5;

	public static final int USER_NOT_AUTH = 6;

	public static final int USER_NOT_LOGGED_ON = 7;

	public static final int NOT_CONNECTED = 8;

	public static final int INCORRECT_PASSWORD = 9;

	public static final int MODEL_NOT_FOUND_RC = 10;

	public static final int MODEL_ALREADY_LOCKED = 11;

	public static final int LOCK_NOT_FOUND = 12;

	public static final int OBJECT_NOT_FOUND = 13;

	public static final int PROP_NOT_FOUND = 14;

	public static final int PTC_INVALID = 15;

	public static final int ATC_INVALID = 16;

	public static final int OTC_INVALID = 17;

	public static final int MNEMONIC_INVALID = 18;

	public static final int DEST_OBJ_NOT_FOUND = 19;

	public static final int SOURCE_OBJ_NOT_FOUND = 20;

	public static final int ASSOC_NOT_FOUND = 21;

	public static final int SUBSET_NOT_FOUND = 22;

	public static final int OBJECT_NOT_IN_SUBSET = 23;

	public static final int OBJECT_NOT_CHECKED_OUT = 24;

	public static final int SUBSET_NOT_CHECKED_OUT = 25;

	public static final int MODEL_NOT_CHECKED_OUT = 26;

	public static final int USER_GROUP_NOT_FOUND = 27;

	public static final int GROUP_NOT_FOUND = 28;

	public static final int AUTH_NOT_FOUND = 29;

	public static final int MODEL_PARENT_NOT_FOUND = 30;

	public static final int CHECKOUT_NOT_FOUND = 31;

	public static final int MODEL_LOCKED = 32;

	public static final int MODEL_INCOMPATIBLE = 33;

	public static final int MODEL_FATAL_ERROR = 34;

	public static final int DIR_READ = 35;

	public static final int BAD_ERRFILE = 36;

	public static final int OBJECT_NOT_SHARED = 37;

	public static final int MODEL_NOT_LOCKED = 38;

	public static final int OBJECT_NOT_ADDED = 39;

	public static final int OBJECT_PROTECTED = 40;

	public static final int OBJECTS_NOT_IN_SAME_MODEL = 41;

	public static final int FROM_OBJECT_NOT_FOUND = 42;

	public static final int TO_OBJECT_NOT_FOUND = 43;

	public static final int INVALID_CARDINALITY = 44;

	public static final int ASSOC_PROTECTED = 45;

	public static final int ASSOC1_PROTECTED = 46;

	public static final int ASSOC2_PROTECTED = 47;

	public static final int OBJECT_1_NOT_FOUND = 48;

	public static final int OBJECT_2_NOT_FOUND = 49;

	public static final int ASSOC1_NOT_FOUND = 50;

	public static final int ASSOC2_NOT_FOUND = 51;

	public static final int ASSOC_NOT_ORDERED = 52;

	public static final int VALUE_INVALID = 53;

	public static final int NOT_AUTHORIZED = 54;

	public static final int ASSOC_ALREADY_EXISTS = 55;

	public static final int COMMIT_UNSUCCESSFUL = 56;

	public static final int OBJECT_NOT_RENAMED = 57;

	public static final int DUPLICATE_OBJECT_OBJ_RENAMED = 58;

	public static final int ILLEGAL_FOR_LOCAL_ENCYCLOPEDIA = 500;

	public static final int NON_GEN_EXTERNAL_ERROR = 501;

	public static final int ILLEGAL_FOR_REMOTE_ENCYCLOPEDIA = 502;

	public static final int ILLEGAL_FOR_JGEN_ENCYCLOPEDIA = 503;

	public static final int NON_GEN_SQL_ERROR = 504;

	public static final int CANNOT_CREATE_NEW_MODEL = 505;

	// range from 600-699 reserved for adapter class implemenation

	public static final String msgs[] = { "Successful", "Maximum count has been reached.",
			"Encyclopedia cannot be found.", "Connect failed.", "Disconnect failed.", "User not found.",
			"User not authorized.", "User not logged on yet.", "Not connected.", "Incorrect password.",
			"Model not found.", "Model already locked.", "Lock not found", "Object not found", "Property not found",
			"Property type code is invalid.", "Association type code is invalid.", "Object type code is invalid.",
			"Mnemonic is invalid.", "Destination object not found.", "Source object not found",
			"Association not found.", "Subset not found.", "Object is not in subset.", "Object is not checked out.",
			"Subset is not checked out.", "Model is not checked out", "User group not found", "Group not found.",
			"Authorization not found.", "Model parent not found.", "Checkout not found", "Model locked",
			"Model incompatible", "Model fatal error.", "Cannot find model in the directory", "BAD_ERRFILE",
			"Object is not shared.", "Model is not locked.", "Object not added.", "Object is protected.",
			"Objects are not in the same model.", "From object not found.", "To object not found",
			"Invalid cardinality", "Association is protected.", "First association is protected.",
			"Second association is protected.", "First object not found.", "Second object not found.",
			"First association not found.", "Second association not found.", "Association is not ordered.",
			"Invalid value.", "Not authorized.", "Association already exists.", "Commit has been unsuccessful.",
			"Object has not been renamed.", "Duplicate object after renaming." };

	/**
	 * 
	 * Returns text message associated with the return code which has been set
	 * by the last executed method.
	 * 
	 * @param returnCode
	 *            return code
	 * 
	 * @return a message associated with the return code
	 */
	public static String getReturnMessage(int returnCode) {
		if (returnCode > 58) {
			if (returnCode == CANNOT_CREATE_NEW_MODEL) {
				return CANNOT_CREATE_A_NEW_MODEL_TEXT;
			}
			return JGMSG.THIRD_PARTY_RETURN_CODE;
		}
		return msgs[returnCode];
	}

}
