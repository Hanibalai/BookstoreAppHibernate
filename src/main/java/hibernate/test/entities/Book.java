package hibernate.test.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Book")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", length = 40)
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(String title, BigDecimal price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book: " +
                "ID = " + id +
                ", title = '" + title + '\'' +
                ", price = " + price +
                ", amount = "  + quantity +
                ", author = '" + author.getName() +
                "', genre = '" + genre.getName() + '\'';
    }
}
