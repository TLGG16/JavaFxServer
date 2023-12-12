package condorcet.Models.Entities;

import jakarta.persistence.*;

import java.io.Serializable;


//Пока не работает
@Entity
@Table(name = "persondata")
public class Person implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long person_id ;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public Person() {

        this.name = "";

        this.surname = "";
    }

    public Person(int id, String name, String lastname) {
        this.person_id =  id;
        this.name = name;
        this.surname = lastname;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String lastname) {
        this.surname = lastname;
    }

}
