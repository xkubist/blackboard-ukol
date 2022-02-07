package blackoard.interview.task.urlShorting;
import java.util.List;
import java.util.stream.Collectors;

public class ShortUrlIdTranslator {
    private static final List<Character> UNRESERVED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            .chars()
            .mapToObj(ch -> (char)ch)
            .collect(Collectors.toList());

    public static long translateUrlTOID(String url) {
        long id = 0;
        for (int i = 0; i < url.length(); i++) {
            id = id * 62 + UNRESERVED_CHARACTERS.indexOf(url.charAt(i))+1;
            System.out.println("encoding "+id);
        }
        return id;
    }

    public static String translateIDToURl(long id) {
        String shortUrl = "";
        while (id > 0) {
            long tmp = (id % 62L)-1L;
            shortUrl = UNRESERVED_CHARACTERS.get((int)tmp) + shortUrl;
            id = id / 62;
            System.out.println("decoding "+id);
        }
        return shortUrl;
    }
}
