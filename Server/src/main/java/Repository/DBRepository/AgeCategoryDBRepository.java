package Repository.DBRepository;

import Domain.AgeCategory;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IAgeCategoryRepository;

import java.util.List;

/**
 * Created by andrei on 2017-05-07.
 */
public class AgeCategoryDBRepository implements IAgeCategoryRepository {

    private DAO dao;

    public AgeCategoryDBRepository() {
        dao = new DAO();
    }

    @Override
    public AgeCategory findSuitableAgeCategory(Integer age) throws RepositoryException {
        List<AgeCategory> allAgeCategories = getAll();
        AgeCategory res = null;
        for (AgeCategory allAgeCategory : allAgeCategories) {
            res = allAgeCategory;
            if (res.getMinAge() <= age && age <= res.getMaxAge()) {
                return res;
            }
        }
        throw new RepositoryException("No suitable age category found!\n");

    }

    @Override
    public void addItem(AgeCategory item) throws RepositoryException {
        dao.add(AgeCategory.class, item);
    }

    @Override
    public AgeCategory findSuitableAgeCategory(Integer minAge, Integer maxAge) throws RepositoryException {
        List<AgeCategory> allAgeCategories = getAll();
        AgeCategory res = null;
        for (AgeCategory allAgeCategory : allAgeCategories) {
            res = allAgeCategory;
            if (res.getMinAge().equals(minAge) && maxAge.equals(res.getMaxAge())) {
                return res;
            }
        }
        throw new RepositoryException("No suitable age category found!\n");
    }

    @Override
    public void deleteItem(String s) throws RepositoryException {
        dao.delete(AgeCategory.class, s);
    }

    @Override
    public Boolean existsItem(String s) throws RepositoryException {
        return dao.exists(AgeCategory.class, s);
    }

    @Override
    public AgeCategory getItem(String s) throws RepositoryException {
        return dao.get(AgeCategory.class, s);
    }

    @Override
    public void updateItem(AgeCategory updatedItem) throws RepositoryException {
        dao.update(AgeCategory.class, updatedItem);
    }

    @Override
    public List<AgeCategory> getAll() throws RepositoryException {
        return dao.getAll(AgeCategory.class);
    }
}
