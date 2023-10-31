package com.rajivcoomar.ivrtest.readthemanual.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajivcoomar.ivrtest.readthemanual.model.IVRASRGetLocale;
import com.rajivcoomar.ivrtest.readthemanual.model.IVRGetLocale;
import com.rajivcoomar.ivrtest.readthemanual.model.TestCaseRow;
import com.rajivcoomar.ivrtest.readthemanual.service.ExcelService;
import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Pause;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;

@RestController
//@RequestMapping("/excel")
public class ExecuteIVRTestCase{
	

    @Autowired
    ExcelService  excelService;
    
    public static final String ACCOUNT_SID = "Twilio Account SID";
	public static final String AUTH_TOKEN = "Twilio Atuh Token";
	public static final String fromPhoneNo = "Your Twilio phone number";
	public static final String IVRPhoneNo = "IVR phone number";
    
	// This method handles GET requests for "/ExecuteTestCase" endpoint
	@GetMapping("/ExecuteTestCase")
	public ResponseEntity<JSONObject> greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		
		 // Define the URL for the Twilio call
		 String URL = "https://28f0-2402-e280-3e3c-120-9d2-af03-d811-cda2.ngrok-free.app/sendfirst?SeqNo=1";
		 System.out.println(URL);
		
		// Initialize Twilio with your ACCOUNT_SID and AUTH_TOKEN
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		try {
			 // Create a Twilio call with specified parameters and settings
	        Call call = Call.creator(new PhoneNumber(IVRPhoneNo), new PhoneNumber(fromPhoneNo),
			        new URI(URL)).setRecord(true)
			        .setStatusCallbackMethod(HttpMethod.POST)
					.create();
	     // Create a JSON response indicating success
			JSONObject obj=new JSONObject();
			obj.put("Result","Success");    
			obj.put("Message","Call placed"); 
			return ResponseEntity.ok(obj);
		} catch (URISyntaxException e) {
			// Handle exceptions and create a JSON response indicating failure
			e.printStackTrace();
			JSONObject obj=new JSONObject();
			obj.put("Result","Failed");    
			obj.put("Message",e.getMessage()); 
			return ResponseEntity.ok(obj);
		}
	}
	
	// This method handles POST requests for the "/sendfirst" endpoint
	@RequestMapping(value = "/sendfirst", method = RequestMethod.POST, produces = "application/xml")
	public void sendfirst(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Extract parameters and process data
        // Get the "SeqNo" parameter from the request
		Integer seqno = Integer.parseInt(request.getParameter("SeqNo"));
		// Construct the URI for the next action
		String URI = "/sendNext?SeqNo="+seqno;

		
		// Initialize variables and retrieve data from an Excel service
        // Initialize locale, botInput, and retrieve test case data
		String locale = "en-us";
		String botInput = "";
		List<TestCaseRow> testCaseGCP = excelService.readExcelData();

		for (int i = 0; i < testCaseGCP.size(); i++) {

			TestCaseRow tmp = testCaseGCP.get(i);

			int srNoLong =  tmp.getIndex();
			
			
			if(srNoLong == 1 )
			{
				botInput =  tmp.getBot();
			}
		}

		// Process and set up the TwiML response for gathering user input
		Gather.Language localeASRValue = IVRASRGetLocale.getLocal(locale);
		Gather gather = new Gather.Builder().action(URI).method(HttpMethod.POST).inputs(Gather
	            .Input.SPEECH).maxSpeechTime(60).language(localeASRValue).speechTimeout("auto").hints(botInput).build();
		
		  // Construct the TwiML response
		TwiML twiml = new VoiceResponse.Builder().gather(gather).build();
		 // Write the TwiML response to the HTTP response
		response.getWriter().print(twiml.toXml());
		// Set the response content type
		response.setContentType("application/xml");
	}
	
	// Similar comments can be added for the "/sendNext" endpoint
	@RequestMapping(value = "/sendNext", method = RequestMethod.POST, produces = "application/xml")
	public void sendNext(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer seqno = Integer.parseInt(request.getParameter("SeqNo"));
		System.out.println("seqno in sendNext: "+seqno);

		TwiML twiml = null;
		String botInput = "";
		String locale = "en-us";
		String timeOut = "auto";


			List<TestCaseRow> testCaseGCP = excelService.readExcelData();

			for (int i = 0; i < testCaseGCP.size(); i++) {

				TestCaseRow tmp = testCaseGCP.get(i);

				int srNoLong =  tmp.getIndex();
				
				
				if(srNoLong == seqno )
				{
					int nextSeq = (int) (seqno+1);
					String inputType = tmp.getType();
					String userInput = tmp.getUserInput();
					botInput =  tmp.getBot();
					
					
					String SpeechResult ="";
					if(request.getParameterValues("SpeechResult") != null)
						SpeechResult = request.getParameterValues("SpeechResult")[0];
					
					String[] words = SpeechResult.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
					
					String SpeechResultConverted = "";
					
					for(int j=0;j < words.length;j++)
					{
						SpeechResultConverted = SpeechResultConverted + words[j] + " ";
					}
					System.out.println("speech reco by Twilio:" + SpeechResultConverted);
					
						
						String[] words2 = botInput.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
						
						String botinputConverted = "";
						for(int j=0;j < words2.length;j++)
						{
							botinputConverted = botinputConverted + words2[j] + " ";
						}
						System.out.println("From Excel test case :" + botinputConverted);
						
					
					Say.Language localeValue = IVRGetLocale.getLocal(locale);
					Gather.Language localeASRValue = IVRASRGetLocale.getLocal(locale);
					String URL = "/sendNext?SeqNo="+nextSeq;
						if(inputType.equalsIgnoreCase("say"))
						{
							System.out.println("insdie say");
							Say say = new Say.Builder(userInput).voice(Say.Voice.ALICE)
						            .language(localeValue).build();
							
							Gather gather = new Gather.Builder().action(URL).method(HttpMethod.POST).inputs(Gather
						            .Input.SPEECH).maxSpeechTime(60).language(localeASRValue).speechTimeout(timeOut).hints(botInput).say(say).build();
							
							twiml = new VoiceResponse.Builder().gather(gather).build();
							response.getWriter().print(twiml.toXml());
						}
						else if(inputType.equalsIgnoreCase("dtmf"))
						{
							
							Play play = new Play.Builder("").digits(userInput).build();
							
							Gather gather = new Gather.Builder().action(URL).method(HttpMethod.POST).inputs(Gather
						            .Input.SPEECH).maxSpeechTime(60).language(localeASRValue).speechTimeout(timeOut).hints(botInput).build();
							
							twiml = new VoiceResponse.Builder().play(play).gather(gather).build();
							response.getWriter().print(twiml.toXml());
						}
						else if(inputType.equalsIgnoreCase("hangup"))
						{
							
							Hangup hangup = new Hangup.Builder().build();
							
							twiml = new VoiceResponse.Builder().hangup(hangup).build();
							response.getWriter().print(twiml.toXml());
						}
					
					break;
				}

			}

		response.setContentType("application/xml");
		
	}

}
