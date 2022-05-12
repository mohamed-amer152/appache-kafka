package com.amer.spring.cloud.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
	
	private int userid ;
	private String username ;
	public user(int userid, String username) {
		super();
		this.userid = userid;
		this.username = username;
	}
	@Override
	public String toString() {
		return "user [userid=" + userid + ", username=" + username + "]";
	}
	
	
	
	

}
