package condorcet.Services;

import condorcet.DataAccessObjects.ClientDAO;
import condorcet.DataAccessObjects.PersonDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Person;

import java.util.List;

public class ClientService implements Service<Client> {
    private static ClientDAO clientDAO;

    public ClientService() {
        clientDAO = new ClientDAO();
    }

    @Override
    public Client findEntity(int id) {
        Client client = clientDAO.findById(id);
        return client;
    }

    @Override
    public void saveEntity(Client entity) {
        clientDAO.saveEntity(entity);

    }

    @Override
    public List<Client> findAllEntities() {
        List<Client> clients = clientDAO.findAll();
        return clients;
    }

    @Override
    public void deleteEntity(Client entity) {
        clientDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Client entity) {
        clientDAO.updateEntity(entity);
    }
}
