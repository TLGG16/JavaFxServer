package condorcet.Services;

import condorcet.DataAccessObjects.ReportDAO;
import condorcet.DataAccessObjects.ReviewDAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Report;
import condorcet.Models.Entities.Review;

import java.util.List;

public class ReviewService implements Service<Review> {
    private static ReviewDAO reviewDAO;

    public ReviewService() {
        reviewDAO = new ReviewDAO();
    }

    @Override
    public Review findEntity(int id) {
        Review review = reviewDAO.findById(id);
        return review;
    }

    @Override
    public void saveEntity(Review entity) {
        reviewDAO.saveEntity(entity);

    }

    @Override
    public List<Review> findAllEntities() {
        List<Review> reviews = reviewDAO.findAll();
        return reviews;
    }

    @Override
    public void deleteEntity(Review entity) {
        reviewDAO.deleteEntity(entity);
    }

    @Override
    public void updateEntity(Review entity) {
        reviewDAO.updateEntity(entity);
    }
}
