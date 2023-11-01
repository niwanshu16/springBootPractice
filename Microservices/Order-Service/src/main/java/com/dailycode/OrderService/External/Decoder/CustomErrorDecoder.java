package com.dailycode.OrderService.External.Decoder;

import java.io.IOException;



import com.dailycode.OrderService.Exception.CustomException;
import com.dailycode.OrderService.External.Error.ErrorResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		log.info("::{} ", response.request().url());
		log.info("::{} ", response.request().headers());
		
		try {
			ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			
			return new CustomException(errorResponse.getMessage(),errorResponse.getErrorCode(), response.status());
		} catch (IOException e) {
			throw new CustomException("Internal Server Error Niwanshu", "INTERNAL_SERVER_ERROR", 500);
		}
		
	}

}
