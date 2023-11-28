package com.courseproject.courseproject.Repository.nestedObjects;

import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Director implements ORAData, ORADataFactory {
	private int directorId;
	private String directorName;
	
	public Director(int directorId,String directorName) {
		this.directorName = directorName;
		this.directorId = directorId;
	}
	public Director() {
	}
	@Override
	public ORAData create(Datum datum, int i) throws SQLException {
		if (datum != null) {
			SQLInput sqlInput = (SQLInput) datum;
			Director director = new Director();
			director.directorId = sqlInput.readInt();
			director.directorName = sqlInput.readString();
			return director;
		}
		return null;
	}
	public void readSQL(SQLInput sqlInput, String string) throws SQLException {
		this.directorId = sqlInput.readInt();
		this.directorName = sqlInput.readString();
	}
	
	
	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeInt(this.directorId);
		sqlOutput.writeString(this.directorName);
	}
	
	@Override
	public Datum toDatum(Connection connection) throws SQLException {
		StructDescriptor descriptor = StructDescriptor.createDescriptor("DIRECTOR_TYPE_CONSTRUCTOR", connection);
		Object[] attributes = {this.directorId, this.directorName};
		return new STRUCT(descriptor, connection, attributes);
	}
	
	public int getDirectorId() {
		return directorId;
	}
	
	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
	
	public String getDirectorName() {
		return directorName;
	}
	
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
}
