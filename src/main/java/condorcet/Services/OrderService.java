package condorcet.Services;

import condorcet.DataAccessObjects.ClientDAO;
import condorcet.DataAccessObjects.OrderDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Order;

import java.util.List;

public class OrderService implements Service<Order> {
    private static OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    @Override
    public Order findEntity(int id) {
        Order order = orderDAO.findById(id);
        return order;
    }

    @Override
    public void saveEntity(Order entity) {
        orderDAO.saveEntity(entity);

    }

    @Override
    public List<Order> findAllEntities() {
        List<Order> orders = orderDAO.findAll();
        return orders;
    }

    @Override
    public void deleteEntity(Order entity) {
        orderDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Order entity) {
        orderDAO.updateEntity(entity);
    }
}
