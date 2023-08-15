package com.expensetracker.expensetracker.repo;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.expensetracker.expensetracker.model.ExpenseModel;

public interface ExpenseRepo extends MongoRepository<ExpenseModel,String>{
//	@Query("{'expense_date' : { $gte: ?0, $lte: ?1 },user_id: ?2 }") ObjectId obj, ,new ObjectId("649541b39fd8ec645d5ead30")
	@Query("{'expense_date' : { $gte: ?0, $lte: ?1 } ,user_id: ?2}")
	List<ExpenseModel> findByDate(Date fromDate,Date toDate,ObjectId user_id, Sort sort);

	@Query("{'expense_date' : { $gte: ?0, $lte: ?1 },user_id: ?2 }")
	List<ExpenseModel> findByYear(Date fromDate,Date toDate,ObjectId user_id,Sort sort);

	@Query("{'expense_date' : { $gte: ?0, $lte: ?1 },user_id: ?2, tag: ?3 }")
	List<ExpenseModel> findByDateFromTag(Date fromDate,Date toDate,ObjectId user_id, String tag);
	
	@Query("{'expense_date' : { $gte: ?0, $lte: ?1 },user_id: ?2, tag: ?3 }")
	List<ExpenseModel> findByYearFromTag(Date fromDate,Date toDate,ObjectId user_id, String tag);
}
