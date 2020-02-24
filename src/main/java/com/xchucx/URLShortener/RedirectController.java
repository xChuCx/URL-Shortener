package com.xchucx.URLShortener;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RedirectController {

    @GetMapping("/{id}")
    public RedirectView redirect(@PathVariable String id){
        String url = UrlShortenerResource.getUrl(id);
        return new RedirectView(url);
    }

}
