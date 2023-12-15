package com.courseproject.courseproject.Entity.nestedObjects;

import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Genre implements ORAData, ORADataFactory {
	private int genreId;
	private String genreName;
	
	public Genre() {
	}
	
	public Genre(int genreId, String genreName) {
		this.genreId = genreId;
		this.genreName = genreName;
	}
	@Override
	public ORAData create(Datum datum, int i) throws SQLException {
		if (datum != null) {
			SQLInput sqlInput = (SQLInput) datum;
			Genre genre = new Genre();
			genre.genreId = sqlInput.readInt();
			genre.genreName = sqlInput.readString();
			return genre;
		}
		return null;
	}
	public void readSQL(SQLInput sqlInput, String string) throws SQLException {
		this.genreId = sqlInput.readInt();
		this.genreName = sqlInput.readString();
	}
	
	
	public void writeSQL(SQLOutput sqlOutput) throws SQLException {
		sqlOutput.writeInt(this.genreId);
		sqlOutput.writeString(this.genreName);
	}
	
	@Override
	public Datum toDatum(Connection connection) throws SQLException {
		StructDescriptor descriptor = StructDescriptor.createDescriptor("GENRE_TYPE_CONSTRUCTOR", connection);
		Object[] attributes = {this.genreId, this.genreName};
		return new STRUCT(descriptor, connection, attributes);
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	public String getGenreName() {
		return genreName;
	}
	
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
