package com.dailycode.CloudGateway.Model;

import lombok.Data;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expiresAt;
	private Collection<String> authorityList;
	
}
