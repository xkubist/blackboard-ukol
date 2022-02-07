package blackoard.interview.task.controller;

import blackoard.interview.task.exception.ShortUrlNotReachableException;
import blackoard.interview.task.model.PageUrl;
import blackoard.interview.task.service.ShortUrlService;
import blackoard.interview.task.urlShorting.ShortUrlIdTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/short/")
public class RedirectController {
    private ShortUrlService shortUrlService;

    public RedirectController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

//    @CrossOrigin
    @GetMapping("{shortenUrl}")
    public RedirectView getShortUrlById(@PathVariable("shortenUrl") String shortUrlId) {
        long id = ShortUrlIdTranslator.translateUrlTOID(shortUrlId);
        RedirectView redirectView = new RedirectView();
        PageUrl pageUrl;
        try {
            pageUrl = shortUrlService.getShortUrlById(id);
        } catch (ShortUrlNotReachableException ex) {
            redirectView.setStatusCode(HttpStatus.NOT_FOUND);
            redirectView.setUrl("");
            return redirectView;
        }
        redirectView.setUrl(pageUrl.getFullLink());
        return redirectView;
    }
}

