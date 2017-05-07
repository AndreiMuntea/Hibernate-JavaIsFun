package Services;

import Domain.AgeCategory;
import Repository.Exceptions.RepositoryException;
import Repository.Interfaces.IAgeCategoryRepository;

import java.util.List;

/**
 * Created by andrei on 2017-04-13.
 */
public class AgeCategoryServices {
    private IAgeCategoryRepository repository;

    public AgeCategoryServices(IAgeCategoryRepository repository) {
        this.repository = repository;
    }

    public AgeCategory FindSuitableAgeCategory(String age) throws ServiceException {
        Integer ageArgument = ConverterServices.ConvertToInt32(age);
        try {
            return repository.findSuitableAgeCategory(ageArgument);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public AgeCategory GetItem(String age) throws ServiceException {
        try {
            return repository.getItem(age);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public AgeCategory FindSuitableAgeCategory(String minAge, String maxAge) throws ServiceException {
        Integer minAgeArgument = ConverterServices.ConvertToInt32(minAge);
        Integer maxAgeArgument = ConverterServices.ConvertToInt32(maxAge);
        try {
            return repository.findSuitableAgeCategory(minAgeArgument, maxAgeArgument);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<AgeCategory> getAgeCategories() throws ServiceException {
        try {
            return repository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
