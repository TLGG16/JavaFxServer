package condorcet.Services;

import condorcet.DataAccessObjects.PersonDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Person;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.List;

public class PersonService implements Service<Person> {
    private static PersonDAO personDAO;

    public PersonService() {
        personDAO = new PersonDAO();
    }

    @Override
    public Person findEntity(int id) {
        Person person = personDAO.findById(id);
        return person;
    }

    @Override
    public void saveEntity(Person entity) {
        personDAO.saveEntity(entity);

    }

    @Override
    public List<Person> findAllEntities() {
        List<Person> persons = personDAO.findAll();
        return persons;
    }

    @Override
    public void deleteEntity(Person entity) {
        personDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Person entity) {
        personDAO.updateEntity(entity);
    }
}