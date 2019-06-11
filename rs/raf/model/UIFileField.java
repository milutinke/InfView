package rs.raf.model;

public class UIFileField {
	public static final String TYPE_VARCHAR = "TYPE_VARCHAR";
	public static final String TYPE_CHAR = "TYPE_CHAR";
	public static final String TYPE_INTEGER = "TYPE_INTEGER";
	public static final String TYPE_NUMERIC = "TYPE_NUMERIC";
	public static final String TYPE_DECIMAL = "TYPE_DECIMAL";
	public static final String TYPE_DATETIME = "TYPE_DATETIME";

	private String fieldName;
	private String fieldType;
	private int fieldLength;
	private boolean fieldPK;
	private boolean sort;
	private boolean ascending;
	private boolean nullable;

	public UIFileField(String fieldName, String fieldType, int fieldLength, boolean fieldPK) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.fieldPK = fieldPK;
		this.nullable = false;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isFieldPK() {
		return fieldPK;
	}

	public void setFieldPK(boolean fieldPK) {
		this.fieldPK = fieldPK;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
		return fieldName;
	}

	public boolean isSort() {
		return sort;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public boolean isStringType() {
		if (this.getFieldType().equals("CHAR") || this.getFieldType().equals("VARCHAR"))
			return true;
		else
			return false;
	}

	public boolean isNumeric() {
		if (this.getFieldType().equals("INT") || this.getFieldType().equals("DOUBLE PRECISION")
				|| this.getFieldType().contains("NUMERIC") || this.getFieldType().contains("FLOAT"))
			return true;
		else
			return false;
	}

	public boolean isDate() {
		if (this.getFieldType().equals("DATETIME") || this.getFieldType().equals("DATE"))
			return true;
		else
			return false;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isNullable() {
		return nullable;
	}
}
