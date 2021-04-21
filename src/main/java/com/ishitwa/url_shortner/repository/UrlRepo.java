package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UrlRepo extends JpaRepository<Url, UUID> {
}
