
package eu.jgen.beegen.model.meta;

public enum ObjMetaType {
	
	DISCOVER ((short) -2),
	INVALID ((short) -1),
	BUSSYS ((short) 220),
	FUNCDEF ((short) 24),
	ACBLKBSD ((short) 112);
	
	private final short code;
	
	
	ObjMetaType(short code) {
		this.code = code;
	}
	
	public short code() {
		return code;
	}
	
	public ObjMetaType getType(short code) {
		for (ObjMetaType obj : ObjMetaType.values()) {
			if (obj.code == code) {
				return obj;
			}
		}
		return ObjMetaType.INVALID;
	}
	
	

}
