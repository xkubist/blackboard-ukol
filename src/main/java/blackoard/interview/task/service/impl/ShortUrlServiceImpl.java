package blackoard.interview.task.service.impl;

import blackoard.interview.task.exception.ShortUrlNotReachableException;
import blackoard.interview.task.model.PageUrl;
import blackoard.interview.task.repository.ShortUrlRepository;
import blackoard.interview.task.service.ShortUrlService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private ShortUrlRepository shortUrlRepository;

    public ShortUrlServiceImpl(ShortUrlRepository employeeRepository) {
        this.shortUrlRepository = employeeRepository;
    }

    @Override
    public PageUrl saveShortUrl(PageUrl pageUrl) {
        return shortUrlRepository.save(pageUrl);
    }

    @Override
    public PageUrl getShortUrlById(long id) {
        return shortUrlRepository.findById(id).orElseThrow(() ->
                new ShortUrlNotReachableException());
    }

    @Override
    public PageUrl getShortUrlByFullUrl(String url) {
        List<PageUrl> list = shortUrlRepository.findAll()
                .stream()
                .filter(c -> c.getFullLink().equals(url))
                .collect(Collectors.toList());
        if (list.size() == 0) {
            System.out.println("null");
            return null;
        } else {
            System.out.println(list.get(0));
            return list.get(0);
        }
    }
}
