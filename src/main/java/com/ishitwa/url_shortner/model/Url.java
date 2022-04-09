package com.ishitwa.url_shortner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Transactional
@Document("Url")
public class Url {
	@Id
	private UUID id;
	private String shortUrl;
	private URI long_url;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	private Date created_date;
	private Date expire_date;
	private long clicks;

	public Url(){
		this.id=UUID.randomUUID();
		this.created_date=new Date();
		int days_remaining=7;
		this.expire_date=addDays(this.created_date,days_remaining);
		this.shortUrl = RandomString.make(10);
		this.clicks=0;
	}

	public Url(String long_url) {
		this.long_url = URI.create(long_url);
	}

	private static Date addDays(Date date,int days){
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.add(gregorianCalendar.DATE,days);
		return gregorianCalendar.getTime();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public URI getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = URI.create(long_url);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user=user;
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

	public void setLong_url(URI long_url) {
		this.long_url = long_url;
	}

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	@Override
	public String toString() {
		return "Url{" +
				"id=" + id +
				", short_url='" + shortUrl + '\'' +
				", long_url=" + long_url +
				", user=" + user +
				", created_date=" + created_date +
				", expire_date=" + expire_date +
				", clicks=" + clicks +
				'}';
	}
}
