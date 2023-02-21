package hibernate.test.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String  title;
    private float price;
    private int amount;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn (name = "genre_id")
    private Genre genre;

    public Book(String title, float price, int amount) {
        this.title = title;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book " +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", price = " + price +
                ", amount = "  + amount +
                ", author = "  + author.getName() +
                ", genre = "  + genre.getName();
    }
}
