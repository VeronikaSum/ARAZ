package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
        @NamedQuery(name = "Item.findALl", query = "select i from Item as i")
})
public class Item {

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Item(String name, int quantity, double price, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public Item(String name, int quantity, double price, Category category, List<Shop> shops) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.shops = shops;
    }

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "PRICE", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name="shops", joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "shop_id"))
    @Column(name="SHOP_ID")
    private List<Shop> shops;
}