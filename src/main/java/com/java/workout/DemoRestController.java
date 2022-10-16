package com.java.workout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoRestController 
{
	@GetMapping("/hello")
	public String hello() 
	{

		return "Hello User!";
	}
}
