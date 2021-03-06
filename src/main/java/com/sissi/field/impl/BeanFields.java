package com.sissi.field.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sissi.field.Field;
import com.sissi.field.Fields;

/**
 * @author kim 2013年12月11日
 */
public class BeanFields implements Fields {

	private final static ArrayList<Field<?>> EMPTY = new ArrayList<Field<?>>();

	private boolean isEmbed;

	private List<Field<?>> fields;

	public BeanFields(boolean isEmbed) {
		super();
		this.isEmbed = isEmbed;
	}

	public BeanFields(boolean isEmbed, List<Field<?>> field) {
		super();
		this.isEmbed = isEmbed;
		this.fields = field;
	}

	public BeanFields reset() {
		if (this.fields != null) {
			this.fields.clear();
		}
		return this;
	}

	public BeanFields add(Field<?> field) {
		if (this.fields == null) {
			this.fields = new ArrayList<Field<?>>();
		}
		this.fields.add(field);
		return this;
	}

	public BeanFields add(Fields fields) {
		for (Field<?> each : fields) {
			this.add(each);
		}
		return this;
	}

	public BeanFields add(Collection<Field<?>> fields) {
		for (Field<?> each : fields) {
			this.add(each);
		}
		return this;
	}

	public List<Field<?>> getFields() {
		return this.fields;
	}

	@Override
	public Iterator<Field<?>> iterator() {
		return this.fields != null ? this.fields.iterator() : EMPTY.iterator();
	}

	@Override
	public boolean isEmbed() {
		return this.isEmbed;
	}

	public boolean isEmpty() {
		return this.fields != null ? this.fields.isEmpty() : true;
	}

	/*
	 * TODO: 优化
	 * 
	 * @see com.sissi.field.Fields#findFields(java.lang.String)
	 */
	public Fields findFields(String name) {
		BeanFields fields = new BeanFields(false);
		for (Field<?> each : this) {
			if (each.getName().equals(name)) {
				fields.add(each);
			}
		}
		return fields;
	}

	/*
	 * Name.equals(this.name), Class.isAssignableFrom(this.class)</p>TODO: 优化
	 * 
	 * @see com.sissi.field.Fields#findField(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T extends Field<?>> T findField(String name, Class<T> clazz) {
		for (Field<?> each : this) {
			if (name.equals(each.getName()) && clazz.isAssignableFrom(each.getClass())) {
				return clazz.cast(each);
			}
		}
		return null;
	}
}
