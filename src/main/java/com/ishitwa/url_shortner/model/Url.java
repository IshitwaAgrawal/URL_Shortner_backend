package com.ishitwa.url_shortner.model;

import net.bytebuddy.utility.RandomString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Document("Url")
public class Url {
	@Id
	private UUID id;
	private String shortUrl;
	private URI longUrl;
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

	public Url(String longUrl) {
		this.longUrl = URI.create(longUrl);
	}

	private static Date addDays(Date date,int days){
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		gregorianCalendar.add(GregorianCalendar.DATE,days);
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

	public URI getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = URI.create(longUrl);
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
		this.longUrl = long_url;
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
				", long_url=" + longUrl +
				", created_date=" + created_date +
				", expire_date=" + expire_date +
				", clicks=" + clicks +
				'}';
	}
}
