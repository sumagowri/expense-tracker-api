package com.expensetracker.expensetracker.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_expenses")
public class ExpenseModel {

	private String user_id;
	private String bank;
	private List<String> amount_encrypted;
	private List<String> tag;
	private String expense_type;
	private Date updated_on;
	private Date expense_date;
}
