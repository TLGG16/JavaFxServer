package condorcet.Services;

import condorcet.DataAccessObjects.SupplierDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Supplier;

import java.util.List;

public class SupplierService implements Service<Supplier> {
    private static SupplierDAO supplierDAO;
    public SupplierService(){supplierDAO = new SupplierDAO();}
    @Override
    public Supplier findEntity(int id) {
        Supplier supplier = supplierDAO.findById(id);
        return supplier;
    }

    @Override
    public void saveEntity(Supplier entity) {
        supplierDAO.saveEntity(entity);

    }



    @Override
    public List<Supplier> findAllEntities() {
        List<Supplier> suppliers = supplierDAO.findAll();
        return suppliers;
    }

    @Override
    public void deleteEntity(Supplier entity) {
        supplierDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Supplier entity) {
        supplierDAO.updateEntity(entity);
    }
}
