package com.sissi.protocol;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.sissi.context.JID;
import com.sissi.protocol.presence.Presence;

/**
 * @author kim 2013-10-24
 */
abstract public class Protocol {

	public static enum Type {

		SET, GET, RESULT, ERROR;

		public String toString() {
			return super.toString().toLowerCase();
		}

		public Boolean equals(String type) {
			return this == Type.parse(type);
		}

		public static Type parse(String value) {
			return Type.valueOf(value.toUpperCase());
		}
	}

	private String id;

	private String from;

	private String to;

	private String type;

	private Protocol parent;

	@XmlTransient
	public Protocol getParent() {
		return parent;
	}

	public void setParent(Protocol parent) {
		this.parent = parent;
	}

	public Boolean hasParent() {
		return this.parent != null;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getFrom() {
		return from;
	}

	public Protocol setFrom(String from) {
		this.from = from;
		return this;
	}

	public Protocol setFrom(JID from) {
		this.from = from.asString();
		return this;
	}

	@XmlAttribute
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Protocol setTo(JID to) {
		this.to = to.asString();
		return this;
	}

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Protocol setType(Presence.Type type) {
		this.type = type.toString();
		return this;
	}

	public Protocol reply() {
		this.setId(this.getId());
		this.setFrom(this.getTo());
		this.setTo(this.getFrom());
		return this;
	}

	public Protocol clear() {
		this.id = null;
		this.from = null;
		this.to = null;
		this.type = null;
		return this;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
