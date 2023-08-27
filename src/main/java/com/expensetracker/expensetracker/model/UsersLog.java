package com.expensetracker.expensetracker.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UsersLog {
	private String Email;
	private String Password;
}
