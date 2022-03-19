package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Shop.findALl", query = "select i from Shop as i"),
        @NamedQuery(name = "Shop.findById", query = "select i from Shop as i where i.id = :id"),
})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @ManyToMany(mappedBy = "shops")
    @Column(name="ITEM_ID")
    private List<Item> items;

}