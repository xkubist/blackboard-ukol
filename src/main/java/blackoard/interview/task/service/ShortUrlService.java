package blackoard.interview.task.service;

import blackoard.interview.task.model.PageUrl;

public interface ShortUrlService{
    PageUrl saveShortUrl(PageUrl employee);
    PageUrl getShortUrlById(long id);
    PageUrl getShortUrlByFullUrl(String url);
}
