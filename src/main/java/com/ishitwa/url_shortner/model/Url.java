package com.ishitwa.url_shortner.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Url {
	@Id
	private UUID id;
	private String short_url;
	private String long_url;
	private UUID user_id;
	private Date created_date;
	private Date expire_date;
	private int days_remaining;

	public Url(){this.id=UUID.randomUUID();}

	public Url(String short_url, String long_url, UUID user_id, Date created_date, Date expire_date, int days_remaining) {
		this.short_url = short_url;
		this.long_url = long_url;
		this.user_id = user_id;
		this.created_date = created_date;
		this.expire_date = expire_date;
		this.days_remaining = days_remaining;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public int getDays_remaining() {
		return days_remaining;
	}

	public void setDays_remaining(int days_remaining) {
		this.days_remaining = days_remaining;
	}
}
