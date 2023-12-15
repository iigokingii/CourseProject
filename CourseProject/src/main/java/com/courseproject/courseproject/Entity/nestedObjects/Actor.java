package com.courseproject.courseproject.Entity.nestedObjects;

import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Actor implements ORAData, ORADataFactory {
	private int actorId;
	private String actorName;
	
	public Actor(int actorId,String actorName) {
		this.actorName = actorName;
		this.actorId = actorId;
	}
	public Actor() {
	}
	@Override
	public ORAData create(Datum datum, int i) throws SQLException {
		if (datum != null) {
			SQLInput sqlInput = (SQLInput) datum;
			Actor actor = new Actor();
			actor.actorId = sqlInput.readInt();
			actor.actorName = sqlInput.readString();
			return actor;
		}
		return null;
	}
	public void readSQL(SQLInput sqlInput, String string) throws SQLException {
		this.actorId = sqlInput.readInt();
		this.actorName = sqlInput.readString();
	}
	
	
	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeInt(this.actorId);
		sqlOutput.writeString(this.actorName);
	}
	
	@Override
	public Datum toDatum(Connection connection) throws SQLException {
		StructDescriptor descriptor = StructDescriptor.createDescriptor("ACTOR_TYPE_CONSTRUCTOR", connection);
		Object[] attributes = {this.actorId, this.actorName};
		return new STRUCT(descriptor, connection, attributes);
	}
	
	public int getActorId() {
		return actorId;
	}
	
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	public String getActorName() {
		return actorName;
	}
	
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
}
