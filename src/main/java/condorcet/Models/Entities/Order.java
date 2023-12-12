package condorcet.Models.Entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_piece")
public class Order implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;



    @ManyToOne
    @JoinColumn(name = "client_id",
            foreignKey = @ForeignKey(name = "client_order")
    )
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name = "order_product")
    )
    private Product product;

    @ManyToOne
    @JoinColumn(name = "final_order_id",
                foreignKey = @ForeignKey(name = "order_final_order")
    )
    private FinalOrder finalOrder;






    public FinalOrder getFinalOrder() {
        return finalOrder;
    }

    public void setFinalOrder(FinalOrder finalOrder) {
        this.finalOrder = finalOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
