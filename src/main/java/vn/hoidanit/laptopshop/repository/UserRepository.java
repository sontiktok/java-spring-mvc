package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;

//Kế thừa lại từ CrudRepository chứa các phương thức giúp thao tác với CSDL
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User theson);
    void deleteById(long id);
    List<User> findByEmail(String email);
    List<User> findAll();
    User findById(long id);
}