package com.expensetracker.expensetracker.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.expensetracker.model.ExpenseModel;
import com.expensetracker.expensetracker.model.UserAuth;
import com.expensetracker.expensetracker.model.UsersLog;
import com.expensetracker.expensetracker.repo.UserDetailRepo;
import com.expensetracker.expensetracker.service.ExpenseService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ExpenseTrackerController {
	@Autowired
	UserDetailRepo repo;
	
	@Autowired
	ExpenseService expService;
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Microservice is Active";
	}
	                                                                         
//	@GetMapping("/allUser")
//	public List<UserDetail> getAllUser(){
//		return repo.findAll();
//	}
	
//	@GetMapping("/allExpense")
//	public List<ExpenseModel> getAllExpense(){		
//		return expService.getAllExpenses();
//	}
	
	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserAuth getJwt(@RequestBody UsersLog user){
		return expService.post(user);
	}
	
	
	@GetMapping("/getByMonth/{monAndYear}")
	public List<ExpenseModel> getByMonth(@PathVariable String monAndYear){
		List<ExpenseModel> list = new ArrayList<>();
		list = expService.getByMonth(monAndYear);
		return list;
	}
	
	@GetMapping("/getEachMonthForYear/{year}")
	public List<ExpenseModel> getEachMonth(@PathVariable String year){
		List<ExpenseModel> list = new ArrayList<>();
		list = expService.getByYear(year);
		return list;
	}
	
	@GetMapping("/getDataByTag/{tag}/{monAndYear}")
	public List<ExpenseModel> getDataByTag(@PathVariable String tag,@PathVariable String monAndYear){
		List<ExpenseModel> list = new ArrayList<>();
		list = expService.getByTag(tag, monAndYear);
		return list;
	}
	
	@GetMapping("/getDataByTagForYear/{tag}/{year}")
	public List<ExpenseModel> getDataByTagForYear(@PathVariable String tag,@PathVariable String year){
		List<ExpenseModel> list = new ArrayList<>();
		list = expService.getByTagYear(tag, year);
		return list;
	}
}
