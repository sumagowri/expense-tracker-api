package com.expensetracker.expensetracker.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserAuth {
	private String username;
	private String aesTestString;
	private String jwt;
}
