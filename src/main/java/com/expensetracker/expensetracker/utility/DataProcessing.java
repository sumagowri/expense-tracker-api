package com.expensetracker.expensetracker.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.expensetracker.expensetracker.model.ExpenseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataProcessing {
	
//	public List<ExpenseModel> getEachDayAmount(List<ExpenseModel> datas){
//		List<ExpenseModel> eachDayData = new ArrayList<>();
//		ExpenseModel prevData = datas.get(0);
//		if(datas.size()==1) eachDayData.add(prevData);
//		
//		List<String> tags = prevData.getTag();
//		String sum=prevData.getAmount_encrypted();
//		Date curDate = prevData.getSpent_on();
//		Date prevDate = curDate;
//		
//		for(int i=1;i<datas.size();i++) {
//			ExpenseModel curData = datas.get(i);
//			curDate = curData.getSpent_on();
//			while(i<datas.size() && curDate.compareTo(prevDate)==0 ) {
//				
//				sum+=curData.getAmmount();
//				for(String tag:curData.getTag()) {
//					tags.add(tag);
//				}
//				prevData = curData;
//				prevDate = curDate;
//				i++;
//				
//				if(i<datas.size()) {
//					curData = datas.get(i);
//					curDate = curData.getSpent_on();
//				}
//			}
//			
//			prevData.setAmount_encrypted(sum);
//			prevData.setTag(tags);
//			log.info(prevData+"  "+i);
//			eachDayData.add(prevData);
//			prevData = curData;
//			prevDate = curDate;
//			tags = curData.getTag();
//			sum=curData.getAmmount();
//		}
//		if(!eachDayData.contains(prevDate)) eachDayData.add(prevData);	
//		return eachDayData;
//	}

}
