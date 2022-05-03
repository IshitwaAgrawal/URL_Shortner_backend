package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.model.UserUrl;
import com.ishitwa.url_shortner.repository.UserUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserUrlService {
    @Autowired
    private UserUrlRepo userUrlRepo;

    public UserUrl addNewEntry(UUID userId,UUID urlId){
        return userUrlRepo.save(new UserUrl(userId,urlId));
    }

    public List<UUID> findUrlsByUserId(UUID userid){
        List<UserUrl> userUrls=userUrlRepo.findUserUrlByUserId(userid);
        List<UUID> res=new ArrayList<>();
        for(UserUrl k:userUrls){
            res.add(k.getUrlId());
        }
        return res;
    }

    public UUID findUserFromUrl(UUID urlId){
        UserUrl userUrl=userUrlRepo.findUserUrlByUrlId(urlId);
        return userUrl.getUserId();
    }

    @Transactional
    public void deleteUrl(UUID urlId) {
        UserUrl userUrl=userUrlRepo.findUserUrlByUrlId(urlId);
        userUrlRepo.delete(userUrl);
    }
}
