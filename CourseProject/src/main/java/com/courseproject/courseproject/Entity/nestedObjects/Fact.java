package com.courseproject.courseproject.Entity.nestedObjects;

import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Fact implements ORAData, ORADataFactory {
	private int factId;
	private String fact;
	
	public Fact(int factId,String fact) {
		this.fact = fact;
		this.factId = factId;
	}
	public Fact() {
	}
	@Override
	public ORAData create(Datum datum, int i) throws SQLException {
		if (datum != null) {
			SQLInput sqlInput = (SQLInput) datum;
			Fact fact = new Fact();
			fact.factId = sqlInput.readInt();
			fact.fact = sqlInput.readString();
			return fact;
		}
		return null;
	}
	public void readSQL(SQLInput sqlInput, String string) throws SQLException {
		this.factId = sqlInput.readInt();
		this.fact = sqlInput.readString();
	}
	
	
	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeInt(this.factId);
		sqlOutput.writeString(this.fact);
	}
	
	@Override
	public Datum toDatum(Connection connection) throws SQLException {
		StructDescriptor descriptor = StructDescriptor.createDescriptor("INTERESTING_FACT_TYPE_CONSTRUCTOR", connection);
		Object[] attributes = {this.factId, this.fact};
		return new STRUCT(descriptor, connection, attributes);
	}
	
	public int getFactId() {
		return factId;
	}
	
	public void setFactId(int factId) {
		this.factId = factId;
	}
	
	public String getFact() {
		return fact;
	}
	
	public void setFact(String fact) {
		this.fact = fact;
	}
}
