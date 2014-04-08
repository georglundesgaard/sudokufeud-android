package no.lundesgaard.sudokufeud.sudokufeud_android.model;

public class Field {
	private Integer value;
	private boolean locked;
	private Integer id;

	public Field(Integer value, boolean locked, Integer id) {
		this.value = value;
		this.locked = locked;
		this.id = id;
	}

	public Field(Integer value) {
		this.value = value;
		this.locked = false;
		this.id = null;
	}

	public Integer getValue() {
		return value;
	}

	public boolean isLocked() {
		return locked;
	}

	public Integer getId() {
		return id;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Field field = (Field) o;

		if (locked != field.locked) return false;
		if (id != null ? !id.equals(field.id) : field.id != null) return false;
		if (value != null ? !value.equals(field.value) : field.value != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result + (locked ? 1 : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}
}
