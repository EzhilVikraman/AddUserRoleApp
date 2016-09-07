package com.compass;

import org.springframework.data.repository.CrudRepository;
import java.lang.String;
import com.compass.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
User findByFirstName(String firstname);
User findByUsername(String username);

}
