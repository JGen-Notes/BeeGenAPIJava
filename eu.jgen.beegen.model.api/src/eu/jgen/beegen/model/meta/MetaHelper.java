package eu.jgen.beegen.model.meta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import eu.jgen.beegen.model.api.JGenContainer;
import eu.jgen.beegen.model.api.JGenObject;

public class MetaHelper {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected JGenContainer genContainer;
	
	public MetaHelper(JGenContainer genContainer) {
		this.genContainer = genContainer;
		logger.setLevel(this.genContainer.getLogger().getLevel());
	}
	
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
