package com.neosave.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosave.project.entity.User;
@Repository
public interface UserDao extends CrudRepository<User,Integer> {

}
