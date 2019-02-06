package fab.the.chemist.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fab.the.chemist.schema.CourseDetails;
import fab.the.chemist.schema.GetCourseDetailsRequest;
import fab.the.chemist.schema.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndPoint {

	//l'annotation @RequestPayload permet la conversion de xml request vers java
	//l'annotation @ResponsePayload permet la conversion de java vers xml response
	@PayloadRoot(namespace = "http://fabthechemist.net/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That would be a wonderful course!");
		
		response.setCourseDetails(courseDetails);
		
		return response;
	}
	
	/*
	@PayloadRoot(namespace="http://fabthechemist.net/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetai
	lsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest getCourseDetailsRequest) {
		GetCourseDetailsResponse responsex = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(1);
		courseDetails.setName("JPA");
		courseDetails.setDescription("copurs sur jpa");
		responsex.setCourseDetails(courseDetails);
		
		return responsex;
	}
	*/
	
}
