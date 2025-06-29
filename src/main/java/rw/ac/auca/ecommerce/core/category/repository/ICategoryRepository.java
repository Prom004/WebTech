package rw.ac.auca.ecommerce.core.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.ecommerce.core.category.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The class ICategoryRepository.
 *
 * @author Jeremie Ukundwa Tuyisenge
 * @version 1.0
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByIdAndActive(UUID id, Boolean active);
    Optional<Category> findByNameAndActive(String name, Boolean active);
    List<Category> findAllByActive(Boolean active);
}
