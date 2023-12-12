package condorcet.Services;

import condorcet.DataAccessObjects.OrderDAO;
import condorcet.DataAccessObjects.ReportDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Order;
import condorcet.Models.Entities.Report;

import java.util.List;

public class ReportService implements Service<Report> {
    private static ReportDAO reportDAO;

    public ReportService() {
        reportDAO = new ReportDAO();
    }

    @Override
    public Report findEntity(int id) {
        Report report = reportDAO.findById(id);
        return report;
    }

    @Override
    public void saveEntity(Report entity) {
        reportDAO.saveEntity(entity);

    }

    @Override
    public List<Report> findAllEntities() {
        List<Report> reports = reportDAO.findAll();
        return reports;
    }

    @Override
    public void deleteEntity(Report entity) {
        reportDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Report entity) {
        reportDAO.updateEntity(entity);
    }
}
