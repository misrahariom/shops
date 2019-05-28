/**
 * 
 */
package com.example.demo.Delegates;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author hari.om.misra
 *
 */
@Service
public class CustomerServiceDelegate {
	
	@Autowired
    RestTemplate restTemplate;
     
    @HystrixCommand(fallbackMethod = "callCustomerServiceAndGetData_Fallback")
    public String callCustomerServiceAndGetData() {
    	 
        System.out.println("Getting Customers details for all customers");
 
		/*
		 * String response = restTemplate .exchange("http://localhost:8082/showall/" ,
		 * HttpMethod.GET , null , new ParameterizedTypeReference<String>() { },
		 * schoolname).getBody();
		 */
        String response = restTemplate.getForObject("http://customers:8082/customers/showall/", String.class);
        System.out.println("Response Received as " + response + " -  " + new Date());
 
        return response;
    }
    
    
    @HystrixCommand(fallbackMethod = "callCustomerServiceAndGetData_Fallback2")
    public String callCustomerServiceAndGetCustomerByName(String firstName) {
    	 
        System.out.println("Getting Customers details for all customers");
		
		String response = restTemplate.exchange("http://customers:8082/customers/firstname" ,
		HttpMethod.GET , null , new ParameterizedTypeReference<String>() { },
		firstName).getBody();
        System.out.println("Response Received as " + response + " -  " + new Date());
 
        return response;
    }
     
    @SuppressWarnings("unused")
    private String callCustomerServiceAndGetData_Fallback() {
 
        System.out.println("Customer Service is down!!! fallback route enabled...");
		/*
		 * return
		 * "CIRCUIT BREAKER ENABLED!!! No Response From Customer Service at this moment. "
		 * + " Service will be back shortly - " + new Date();
		 */
        
        String response = restTemplate.getForObject("http://demo:8081/hello/", String.class);
        System.out.println("Response Received as " + response + " -  " + new Date());
        return response;
    }
    
    @SuppressWarnings("unused")
    private String callCustomerServiceAndGetData_Fallback2(String firstName) {
 
        System.out.println("Customer Service is down!!! fallback route enabled...");
		/*
		 * return
		 * "CIRCUIT BREAKER ENABLED!!! No Response From Customer Service at this moment. "
		 * + " Service will be back shortly - " + new Date();
		 */
        
        String response = restTemplate.getForObject("http://demo:8081/hello/", String.class);
        System.out.println("Response Received as " + response + " -  " + new Date());
        return response;
    }
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
