package blackoard.interview.task.controller;

import blackoard.interview.task.model.PageUrl;
import blackoard.interview.task.service.ShortUrlService;
import blackoard.interview.task.urlShorting.ShortUrlIdTranslator;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


@RestController
@RequestMapping("/api/urls")
public class ShortUrlController {
    private ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    //create shortUrl rest upi
//    @CrossOrigin
    @PostMapping()
    public ResponseEntity<String> saveShortUrl(HttpServletRequest request, @RequestBody String url) {
        //validate url
        if (!isValid(url)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        //validate url
//        if (!isReachable(url)) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body("URL is not reachable");
//        }

        //gat base of url
        String shortenUrl = request.getRequestURL().toString().replace("/api/urls", "") + "/short/";

        //save url if do not exist
        if (shortUrlService.getShortUrlByFullUrl(url) == null) {
            shortUrlService.saveShortUrl(new PageUrl(url));
        }
        //add text id to url
        shortenUrl += ShortUrlIdTranslator.translateIDToURl(shortUrlService.getShortUrlByFullUrl(url).getId());

        return new ResponseEntity<String>("{\"response\": \"" + shortenUrl + "\"}", HttpStatus.CREATED);
    }

    private boolean isValid(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

//    private boolean isReachable(String url) {
//        HttpURLConnection connection = null;
//        try {
//            connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("HEAD");
//            int responseCode = 0;
//            responseCode = connection.getResponseCode();
//            System.out.println("#################"+responseCode);
//            if (responseCode != 200) {
//                return false;
//            }
//        } catch (ProtocolException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//        return true;
//    }
}
