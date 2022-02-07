package blackoard.interview.task.repository;

import blackoard.interview.task.model.PageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<PageUrl,Long> {

}
