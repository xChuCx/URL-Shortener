package com.xchucx.URLShortener;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

@RequestMapping("/url")
@RestController
public class UrlShortenerResource {

    public static final Jedis jedis = new Jedis();

    public static String getUrl(String id){
        String url = jedis.get(id);

        if (url == null){
            throw new RuntimeException("ID Invalid:"+id);
        }
        else {
            return jedis.get(id);
        }
    }

    @PostMapping
    public String create(@RequestBody String url){
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http","https"}
        );
        if (urlValidator.isValid(url)){
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            jedis.set(id,url);
            return id;
        }
        else{
            throw new RuntimeException("URL Invalid:"+url);
        }

    }
}
