package com._2je7.pofol.Config.Swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
	
	@GetMapping("/swagger/index")
	public String api() { 
		System.out.println("swagger mapping api OK");
		return "redirect:/swagger-ui/index.html"; 
	}
}
