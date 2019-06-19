package com.github.adminfaces.starter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Entity(name = "Bitcoin")
@Table(name = "bitcoin")
public class Bitcoin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bitcoin_id")
	private Integer id;

	@Audited
	private String owner;

	@Audited
	private Double quantity;

	@Transient
	private Date revisionDate;

	@Transient
	private String ip;

	@Transient
	private String username;

	public Bitcoin() {
	}

	public Bitcoin(Integer id, String owner, Double quantity) {
		super();
		this.id = id;
		this.owner = owner;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
