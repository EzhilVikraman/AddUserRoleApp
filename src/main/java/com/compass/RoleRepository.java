package com.compass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.lang.String;
import com.compass.Role;
import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query(value = "select role.rolename from role where role.roleid = (select user_roles.roles from user_roles where user_roles.user = (select user.userid from user where user.username = ?1))",
		    nativeQuery = true)
	Role findByUsername(String username);
}
