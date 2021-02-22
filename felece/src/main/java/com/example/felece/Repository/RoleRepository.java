package com.example.felece.Repository;

import com.example.felece.Model.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RoleRepository extends CrudRepository <Role, Long>{
    Role findByName(String name);
}
