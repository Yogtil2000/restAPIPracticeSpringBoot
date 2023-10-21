package com.TodayLearning.springboot.restapistartcopy1.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResource 
{
	
	private SurveyService surveyService;
	
	public SurveyResource(SurveyService SurveyService, SurveyService surveyService)
	{
		super();
		this.surveyService=surveyService;
	}
	
      //surveys : it is good idea to use plurals in resource URI 
	   //      /surveys==> returns all surveys 
	@RequestMapping("/surveys")    // here the list of the Survey bean is automatically converted to the json , this is done by the Jackson framework in the background.
	public List<Survey> retrieveAllSurveys()
	{
		return surveyService.retrieveAllSurveys();
	}
	   
	// retrieve the specific survey 
	
	@RequestMapping("/surveys/{id}")
	public Survey retrieveSurveyById(@PathVariable String id)
	{
		Survey survey = surveyService.retrieveSurveyById(id);
		if(survey==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return survey;
	}
	
	//retrieve survey questions 
	@RequestMapping("surveys/{surveyId}/questions")
	public List<Question> retrieveAllQuestions(@PathVariable String surveyId)
	{
		List<Question> questions=surveyService.retrieveAllQuestions(surveyId);
		if(questions==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return questions;
		
	}
	
	//retriveSpecificSurveyQuestion
	@RequestMapping("surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSpecificQuestionFromSurvey(@PathVariable String surveyId,@PathVariable String questionId)
	{
		
		Question question= surveyService.retrieveSpecificQuestionFromSurvey(surveyId,questionId);
		if(question==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return question;
	}
	
	
	
	// Adding a survey question : post 
	@RequestMapping(path="/surveys/{surveyId}/questions",method = RequestMethod.POST)     // "value" is also allowed to use in place of "path"
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyId,@RequestBody Question question)
	{
		String questionId=  surveyService.addNewSurveyQuestion(surveyId,question);
		/*
		 Things need to improve : 
		        1) generating id of question dynamically  ---> use SecureRandom class 
		        2) proper response status of  201 created instead of 200 
		        
		        shortest way to doing second task is : @ResponseStatus(HttpStatus.CREATED)  place it over the method 
		            Another way is using the ResponseEntity.created() 
		            
		        3) want to set the location header inside the created(location)      giving null also allowed  
		  
		 */
		
		//want to show the user that which resource is added , so providing that resource link 
		// surveys/{surveyId}/questions/{questionId}
		// upto here we got surveys/{surveyId}/questions/  using ---> ServletUriComponentsBuilder.fromCurrentRequest()
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();  //name of variable and name of attribute matters here 
		return ResponseEntity.created(location).build();   // here you also able to return location back 
		
		
		/*
		 
Location:	http://localhost:8080/surveys/Survey1/questions/3482649560
		 */
	}
	// Note: if we explicitely doesn't setted the "HttpStatusCode" code it will giving 200 instead of 201 , i.e success instead of created 
	
	
	
	
	
	//delete specific survey question 
	
	@RequestMapping(value = "surveys/{surveyId}/questions/{questionId}",method = RequestMethod.DELETE)  // NOTE : if you have the same URL of 2 methods and requestmethod is not present different to both methods differently then error comes 
	public  ResponseEntity<Object> deleteSpecificQuestionFromSurvey(@PathVariable String surveyId,@PathVariable String questionId)
	{
		
		 surveyService.deleteSpecificQuestionFromSurvey(surveyId,questionId);
	
			return ResponseEntity.noContent().build();
		
	}
	
	
	//update specific survey question 

	@RequestMapping(value = "surveys/{surveyId}/questions/{questionId}",method = RequestMethod.PUT)  // NOTE : if you have the same URL of 2 methods and requestmethod is not present different to both methods differently then error comes 
	public  ResponseEntity<Object> updateSpecificQuestionFromSurvey(@PathVariable String surveyId,@PathVariable String questionId, @RequestBody Question question)
	{
		
		 surveyService.updateSpecificQuestionFromSurvey(surveyId,questionId,question);
	
			return ResponseEntity.noContent().build();
		
	}
	
	
	
}
