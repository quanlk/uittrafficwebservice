package com.uit.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Account.class)
public class AccountListAdapter {

	private List<Object> list;

	/**
	 * @return the list
	 */
	@XmlElement(name="list")
	public List<Object> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}
}
