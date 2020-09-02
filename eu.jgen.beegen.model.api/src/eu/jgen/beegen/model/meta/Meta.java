package eu.jgen.beegen.model.meta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import eu.jgen.beegen.model.api.JGenContainer;

public class Meta {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected JGenContainer genContainer;
	
	public Meta(JGenContainer genContainer) {
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
			text = rs.getString(7);
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

}
