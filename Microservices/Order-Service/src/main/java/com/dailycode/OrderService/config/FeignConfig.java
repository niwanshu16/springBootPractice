package com.dailycode.OrderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dailycode.OrderService.External.Decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	
	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
}
