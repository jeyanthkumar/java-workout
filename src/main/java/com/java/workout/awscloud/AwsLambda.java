package com.java.workout.awscloud;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.Request;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.DeleteFunctionRequest;
import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ListFunctionsResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.amazonaws.util.TimingInfo;

/**
 * 
 * @author Jeyanthkumar_S
 * AWS Lambda runs code without provisioning servers, scales automatically, processes real-time streaming data, builds web, IoT, mobile backends.
 *
 */
public class AwsLambda {

	public static void main(String[] args) {

		try {
			// Invoke lambda method & fetch the result
			String functionName = args[0];
			InvokeRequest invokeRequest = new InvokeRequest()
					.withFunctionName(functionName)
					.withPayload("{\n" +
							" \"Hello \": \"Paris\",\n" +
							" \"countryCode\": \"FR\"\n" +
							"}");
			InvokeResult invokeResult = null;

			AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion(Regions.US_WEST_2).build();

			invokeResult = awsLambda.invoke(invokeRequest);

			String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

			// write out the return value
			System.out.println(ans);
			System.out.println(invokeResult.getStatusCode());

			// List down lambda function name
			ListFunctionsResult functionResult = awsLambda.listFunctions();

			List<FunctionConfiguration> list = functionResult.getFunctions();

			for (Iterator iter = list.iterator(); iter.hasNext();) {
				FunctionConfiguration config = (FunctionConfiguration) iter.next();

				System.out.println("The function name is " + config.getFunctionName());
			}

			// Delete lambda function
			DeleteFunctionRequest delFunc = new DeleteFunctionRequest();
			delFunc.withFunctionName(functionName);

			// Delete the function
			awsLambda.deleteFunction(delFunc);
			System.out.println("The function is deleted");

		} catch (ServiceException e) {
			System.out.println(e);
		}

	}
}

//class HandlerList implements RequestHandler<List<Integer>, Integer>{
//
//	@Override
//	/*
//	 * Takes a list of Integers and returns its sum.
//	 */
//	public Integer handleRequest(List<Integer> event, Context context)
//	{
//		LambdaLogger logger = context.getLogger();
//		logger.log("EVENT TYPE: " + event.getClass().toString());
//		return event.stream().mapToInt(Integer::intValue).sum();
//	}
//
//}
