package condorcet.Services;

import condorcet.DataAccessObjects.FinalOrderDAO;
import condorcet.DataAccessObjects.OrderDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.FinalOrder;
import condorcet.Models.Entities.Order;

import java.util.List;

public class FinalOrderService implements Service<FinalOrder> {
    private static FinalOrderDAO finalOrderDAO;

    public FinalOrderService() {
        finalOrderDAO = new FinalOrderDAO();
    }

    @Override
    public FinalOrder findEntity(int id) {
        FinalOrder order = finalOrderDAO.findById(id);
        return order;
    }

    @Override
    public void saveEntity(FinalOrder entity) {
        finalOrderDAO.saveEntity(entity);

    }

    @Override
    public List<FinalOrder> findAllEntities() {
        List<FinalOrder> orders = finalOrderDAO.findAll();
        return orders;
    }

    @Override
    public void deleteEntity(FinalOrder entity) {
        finalOrderDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(FinalOrder entity) {
        finalOrderDAO.updateEntity(entity);
    }

}
