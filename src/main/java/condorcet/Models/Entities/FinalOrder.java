package condorcet.Models.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "final_order")
public class FinalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_order_id")
    private int id;

    @Column(name = "final_order_price")
    private float totalPrice;

    @Column(name = "delivery_price")
    private float deliveryprice;

    @Column(name = "order_date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(float deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

}
