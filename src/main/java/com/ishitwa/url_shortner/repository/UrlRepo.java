package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UrlRepo extends JpaRepository<Url, UUID> {
    @Query("select u from Url u where u.short_url=?1")
    public Url findUrlByShort_url(String short_url);
    public Url findUrlById(UUID id);
    @Query("select u from Url u where u.user=?1")
    public List<Url> findUrlByUser(User u);
}
