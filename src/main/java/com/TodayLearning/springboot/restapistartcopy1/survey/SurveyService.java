package com.TodayLearning.springboot.restapistartcopy1.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService 
{
      private static List<Survey> surveys=new ArrayList<>();
      
      static  // as it setups always at the start of the program 
      {
    	  Question question1 = new Question("Question1",
                  "Most Popular Cloud Platform Today", Arrays.asList(
                          "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
          Question question2 = new Question("Question2",
                  "Fastest Growing Cloud Platform", Arrays.asList(
                          "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
          Question question3 = new Question("Question3",
                  "Most Popular DevOps Tool", Arrays.asList(
                          "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");
   
          List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                  question2, question3));
   
          Survey survey = new Survey("Survey1", "My Favorite Survey",
                  "Description of the Survey", questions);
   
          surveys.add(survey);
      }

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurveyById(String id) {
		
		Predicate<? super Survey> predicate = survey->survey.getId().equals(id);       // as we know the match is atmost one so used "findFirst" there ....
		Optional<Survey> optioalSurvey = surveys.stream().filter(predicate).findFirst();   // surveys.stream().filter(predicate)  if you have just this then it still stream and need to find only one from it so use the findFirst() there 
		// here findFirst method returns the Optinal back as 
		// this id may or may not be present there so that's why 
		
		if(optioalSurvey.isEmpty())
		{
			return null;
		}
		return optioalSurvey.get();   // here get() method needs to use to convert from the "optional type" to its "original type"
		
		/* 
		 What's actually the initial scenario is that : when there is no of the surveyid present as that of the mentioned then it will returning the null 
		   i.e the blank screen but we doesn't want like that 
		   
		   
		   And another thing is that it will returning 200 for the unsuccessful operations as well :
		   Request Method:
		GET
		Status Code:
		200 OK

	    so its not correct returnig 200 back for the successful operations is fine but not for the unsuccessful cases 
	    
	    Need a change for it :
	    
	       so need to check for the null in resource :
	       
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


		 */
	
		
	}

	public List<Question> retrieveAllQuestions(String surveyId) {
		
		Survey survey=retrieveSurveyById(surveyId);
		if(survey==null)
		{
			return null;
		}
		return survey.getQuestions();
		
	}

	public Question retrieveSpecificQuestionFromSurvey(String surveyId, String questionId) {
		List<Question> listOfQuestions=retrieveAllQuestions(surveyId);
		if(listOfQuestions==null)
		{
			return null;
		}
	    Predicate<? super Question> predicate = q->q.getId().equals(questionId);
		Optional<Question> question=listOfQuestions.stream().filter(predicate).findFirst();
		if(question.isEmpty())
		{
			return null;
		}
		return question.get();
	}

	// note :
	public String addNewSurveyQuestion(String surveyId, Question question) 
	{
		List<Question> retrieveAllQuestions = retrieveAllQuestions(surveyId);
		
		
		SecureRandom secureRandom=new SecureRandom();
		String randomId= new BigInteger(32,secureRandom).toString();        //(no_of_bits,secureRandom_instance_to_generate_dynamic_number)
		
		question.setId(randomId);
		
		retrieveAllQuestions.add(question);
		return question.getId();
		/*
		 Things need to improve : 
		        1) generating id of question dynamically   ---> use SecureRandom class 
		        2) proper response status of  201 created instead of 200 
		  
		 */
	}
    
	
	public String deleteSpecificQuestionFromSurvey(String surveyId, String questionId) 
	{
		List<Question> listOfQuestions=retrieveAllQuestions(surveyId);
		if(listOfQuestions==null)
		{
			return null;
		}
	    boolean isRemoved=listOfQuestions.removeIf(q->q.getId().equalsIgnoreCase(questionId));
	    if(!isRemoved)
	    {
	    	return null;
	    }
	    return questionId;
	    // functional programming used to remove the element from list using predicate in removeIf
	    // NOTE: removeIf method returns the "boolean" back 
	    
	    // Java concept : when you have the return type as String and in that case even if you return the "null" it is also allowed 
		
	}

	// update operation here in this case basically involves removing the particular question and adding the updated one on that place 
	public void updateSpecificQuestionFromSurvey(String surveyId, String questionId,Question updatedQuestion) {
		
		List<Question> listOfQuestions=retrieveAllQuestions(surveyId);
		listOfQuestions.removeIf(q->q.getId().equalsIgnoreCase(questionId));
		
		listOfQuestions.add(updatedQuestion);
		
		
		
	}
      
}
