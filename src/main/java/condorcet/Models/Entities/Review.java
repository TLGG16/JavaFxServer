package condorcet.Models.Entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int id;
    @Column(name = "rate")
    private int rate;
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "client_id",
            foreignKey = @ForeignKey(name = "client_review")
    )
    private Client client;
    @ManyToOne
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name = "product_review")
    )
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
