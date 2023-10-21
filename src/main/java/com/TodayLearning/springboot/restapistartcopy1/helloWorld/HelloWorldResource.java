package com.TodayLearning.springboot.restapistartcopy1.helloWorld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")   // this line is basically suggested that the URL needs to be start with the /test and then the respective services one 
// ex: http://localhost:8080/test/hello-world   ====> is the url made for the fitst defined service 
public class HelloWorldResource 
{
	   @RequestMapping("/hello-world")
	   //  @ResponseBody   : as we don't want to return the view as like JSP 
	   // instead we want to use the returned object as it is : so used @RestController 
	   // @RestController = @Controller + @ResponseBody
	   //@ResponseBody
       public String helloWorld()
       {
            return "Hello World updated";    	   
       }
	   
	   
	   
	   
	   @RequestMapping("/hello-world-bean")
	   public HelloWorldBean helloWorldBean()
       {
            return new HelloWorldBean("I AM MESSAGE");    	   
       }
	   
	   
	   // pathVariable or Path param also called as query parameter 
	   
	   @RequestMapping("/hello-world-path-param/{name}")  // here to accept the path param needs to put {} to accept 
	   public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name )
	   {
		   return new HelloWorldBean("Latest Name :"+name);
	   }
	   
	   // you can have multiple path params as well 
	   
	   @RequestMapping("/hello-world-multiple-path-params/{name1}/{name2}")
	   public HelloWorldBean helloWorldBeanMultiplePathVariable(@PathVariable String name1,@PathVariable String name2 )
	   {
		   return new HelloWorldBean("First Name "+name1+"  "+"Second Name"+name2);
	   }
	   
	   
}

