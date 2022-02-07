package blackoard.interview.task.exception;

import blackoard.interview.task.urlShorting.ShortUrlIdTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShortUrlNotReachableException extends RuntimeException{
    public ShortUrlNotReachableException() {
        super();
    }
}
