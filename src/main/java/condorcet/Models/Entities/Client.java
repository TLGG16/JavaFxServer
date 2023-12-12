package condorcet.Models.Entities;



import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phonenumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "person_id",
            foreignKey = @ForeignKey(name = "person_client")
    )
    private Person person;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
