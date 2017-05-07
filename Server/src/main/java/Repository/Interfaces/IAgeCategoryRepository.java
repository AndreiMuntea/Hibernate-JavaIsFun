package Repository.Interfaces;

import Domain.AgeCategory;
import Repository.Exceptions.RepositoryException;

/**
 * Created by andrei on 2017-05-07.
 */
public interface IAgeCategoryRepository extends ICrudRepository<String, AgeCategory> {

    AgeCategory findSuitableAgeCategory(Integer age) throws RepositoryException;

    AgeCategory findSuitableAgeCategory(Integer minAge, Integer maxAge) throws RepositoryException;
}
