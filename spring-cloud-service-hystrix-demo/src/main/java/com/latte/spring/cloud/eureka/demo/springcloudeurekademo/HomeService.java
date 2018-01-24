package com.latte.spring.cloud.eureka.demo.springcloudeurekademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HomeService {

	@Autowired
	private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "hiError")
	public String hiService(String name) {
		return restTemplate.getForObject("http://EUREKA-DEMO-CLIENT/home?userName=" + name, String.class);
	}
    
    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
