package blackoard.interview.task.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="urls")
//to get at first id high enough
@SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 2147483647)
public class PageUrl {
    public PageUrl(){};
    public PageUrl(String fullLink){
        this.setFullLink(fullLink);
    }
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
    private long id;

    @Column(name = "full_link", nullable = false, columnDefinition="TEXT")
    private String fullLink;

}
