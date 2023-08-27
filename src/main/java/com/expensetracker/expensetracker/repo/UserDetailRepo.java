package com.expensetracker.expensetracker.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expensetracker.expensetracker.model.UserDetail;


public interface UserDetailRepo extends MongoRepository<UserDetail,String>{

}
