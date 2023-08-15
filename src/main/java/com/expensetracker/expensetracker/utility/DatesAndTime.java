package com.expensetracker.expensetracker.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatesAndTime {
	public List<String> splitMonthAndYear(String monthAndYear){
		List<String> list = new ArrayList<>(Arrays.asList(monthAndYear.split("-")));
		log.info(list+"");
		return list;
	}

	public List<Integer> getNoOfDaysInMonth(String month) {
		List<Integer> list = new ArrayList<>();
		month = month.toLowerCase();
		switch(month) {
		case "jan": 
			list.add(31);
			list.add(1);
			break;
			
		case "feb": 
			list.add(28);
			list.add(2);
			break;
			
		case "mar": 
			list.add(31);
			list.add(3);
			break;
			
		case "apr": 
			list.add(30);
			list.add(4);
			break;
			
		case "may": 
			list.add(31);
			list.add(5);
			break;
			
		case "jun": 
			list.add(30);
			list.add(6);
			break;
			
		case "jul": 
			list.add(31);
			list.add(7);
			break;
			
		case "aug": 
			list.add(31);
			list.add(8);
			break;
			
		case "sep": 
			list.add(30);
			list.add(9);
			break;
			
		case "oct": 
			list.add(31);
			list.add(10);
			break;
			
		case "nov": 
			list.add(30);
			list.add(11);
			break;
			
		case "dec": 
			list.add(31);
			list.add(12);
			break;
		default: break;
		}
		return list;
	}
	
	public List<Date> getInDateFormat(List<String> monYear,List<Integer> NoDaysAndMonth) {
		// set in yyyy-mm-dd in string
		String fromDate = monYear.get(1)+"-"+String.format("%02d",NoDaysAndMonth.get(1))+"-01";
		String toDate = monYear.get(1)+"-"+String.format("%02d",NoDaysAndMonth.get(1))+"-"+NoDaysAndMonth.get(0);
		
		// formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the string to LocalDate 
        LocalDate fDate = LocalDate.parse(fromDate, formatter);
        LocalDate tDate = LocalDate.parse(toDate,formatter);
        
        // LocalDate to Date
        Date f = Date.from(fDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date t = Date.from(tDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        List<Date> dates = new ArrayList<>();
        dates.add(f);
        dates.add(t);
        
        return dates;
	}
	
	public List<Date> getDatesofYear(String year){
		// set in yyyy-mm-dd in string
		String fromDate = year+"-01-01";
		String toDate = year+"-12-31";
		// formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// Parse the string to LocalDate 
		LocalDate fDate = LocalDate.parse(fromDate, formatter);
		LocalDate tDate = LocalDate.parse(toDate,formatter);
		        
		// LocalDate to Date
		Date f = Date.from(fDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date t = Date.from(tDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		List<Date> dates = new ArrayList<>();
		dates.add(f);
        dates.add(t);
        
		return dates;
	}
}
