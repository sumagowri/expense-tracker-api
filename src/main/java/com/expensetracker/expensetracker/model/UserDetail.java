package com.expensetracker.expensetracker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserDetail {
	private String username;
	private String Email;
	private String Password;
	private boolean verified;
}
