package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Category.findALl", query = "select i from Category as i"),
        @NamedQuery(name = "Category.getById", query = "select i from Category as i where i.id = :id")
})
public class Category {
    public Category(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @OneToMany(mappedBy = "category")
    private List<Item> items;
}