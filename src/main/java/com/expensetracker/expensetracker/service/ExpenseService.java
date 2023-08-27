package com.expensetracker.expensetracker.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.expensetracker.expensetracker.model.ExpenseModel;
import com.expensetracker.expensetracker.model.UserAuth;
import com.expensetracker.expensetracker.model.UsersLog;
import com.expensetracker.expensetracker.repo.ExpenseRepo;
import com.expensetracker.expensetracker.utility.DataProcessing;
import com.expensetracker.expensetracker.utility.DatesAndTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpenseService {
	@Autowired
	ExpenseRepo repo;
	
	@Autowired
	DatesAndTime dt;
	
	@Autowired
	DataProcessing dp;
	
	RestTemplate rest = new RestTemplate();
	
	public ExpenseService(RestTemplateBuilder restTemplateBuilder) {
		this.rest = restTemplateBuilder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		this.rest.setMessageConverters(messageConverters);
	}
	
	public UserAuth post(UsersLog user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<UsersLog> requestEntity = new HttpEntity<>(user,headers);
		ResponseEntity<UserAuth> responseEntity = null;
		
		try {
			responseEntity = rest.postForEntity("https://expensetrackeringestion.onrender.com/api/login", requestEntity, UserAuth.class);
			log.info("Successfully Authenticated user!");
			return responseEntity.getBody();
		} catch (Exception e) {
			log.error("{} !! info about request-body : {}", e.getMessage(), user);
//			responseEntity = new ResponseEntity<UserAuth>();
		}
		return null;
		
	}
	
	
	public List<ExpenseModel> getAllExpenses() {
//		log.info(repo.findAll().size()+"");
        return repo.findAll();
    }
	
	public List<ExpenseModel> getByMonth(String m){
		// split month and year
		List<String> splitMonYear = dt.splitMonthAndYear(m);
		// get no of days in month and month in mm
		List<Integer> list = dt.getNoOfDaysInMonth(splitMonYear.get(0));
		// return the beginning and end dates of a month
		List<Date> dates = dt.getInDateFormat(splitMonYear.get(1),list);
		// Sort by spent_on date
		Sort sort = Sort.by(Sort.Direction.ASC, "spent_on");
		// find it in databases
		List<ExpenseModel> data = repo.findByDate(dates.get(0), dates.get(1),new ObjectId("649541b39fd8ec645d5ead30"),sort);
//		log.info(data.size()+"");
		return data;
//		if(data.size()>0) return dp.getEachDayAmount(data);
//		else return new ArrayList<>();
	}
	
	public List<List<ExpenseModel>> getExpenseForThreeMonth(String m) throws Exception{
		List<List<ExpenseModel>> data = new ArrayList<>();
		
		List<String> splitMonYear = dt.splitMonthAndYear(m);
		
		List<Integer> list = dt.getNoOfDaysInMonth(splitMonYear.get(0));
		
		List<Integer> threeMonths = dt.getThreeMonths(list.get(1));
		
		Sort sort = Sort.by(Sort.Direction.ASC, "spent_on");
		int startMonth = threeMonths.get(0);
		for(int i = 0; i < 3; i++) {
			int curMonth = threeMonths.get(i);
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("MM");
			Date date = inputFormat.parse(String.format("%02d",curMonth));
			
			SimpleDateFormat output = new SimpleDateFormat("MMM");
			String month = output.format(date);
			List<Date> curDate;
			
			List<Integer> curDays = dt.getNoOfDaysInMonth(month);
			
			if(curMonth > startMonth) {
				curDate = dt.getInDateFormat(Integer.toString(Integer.valueOf(splitMonYear.get(1))-1), curDays);
			}else {
				curDate = dt.getInDateFormat(splitMonYear.get(1), curDays);
			}
			
			List<ExpenseModel> curData = repo.findByDate(curDate.get(0), curDate.get(1),new ObjectId("649541b39fd8ec645d5ead30"),sort);
			data.add(curData);
		}
		
		return data;
	}
	
	public List<ExpenseModel> getByYear(String year){
		// return the beginning and end dates of a year
		List<Date> dates = dt.getDatesofYear(year);
		// Sort by spent_on date
		Sort sort = Sort.by(Sort.Direction.ASC, "spent_on");
		// find it in databases
		List<ExpenseModel> data = repo.findByYear(dates.get(0), dates.get(1),new ObjectId("64a40a85b6288397f3d097e1"),sort);
//		log.info(data.size()+"");
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<ExpenseModel> getByTag(String tag, String m){
		
		List<String> splitMonYear = dt.splitMonthAndYear(m);
		
		List<Integer> list = dt.getNoOfDaysInMonth(splitMonYear.get(0));
		
		List<Date> dates = dt.getInDateFormat(splitMonYear.get(1), list);
		
		List<ExpenseModel> data = repo.findByDateFromTag(dates.get(0), dates.get(1),new ObjectId("649541b39fd8ec645d5ead30"),tag);
 		return data;
	}
	
	public List<ExpenseModel> getByTagYear(String tag,String year){
		List<Date> dates = dt.getDatesofYear(year);
		
		List<ExpenseModel> data = repo.findByYearFromTag(dates.get(0), dates.get(1),new ObjectId("649541b39fd8ec645d5ead30"),tag);
		return data;
	}
	
	
}
