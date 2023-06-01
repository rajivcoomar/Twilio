//Windows CMD
//set TWILIO_AUTH_TOKEN=<Auth token value>
//set TWILIO_ACCOUNT_SID=<Account sid>

//Unix 
//$ TWILIO_AUTH_TOKEN=<Auth token value>
//$ TWILIO_ACCOUNT_SID=<Account sid>

//For Dialogflow Set access Token
//gcloud auth login
//gcloud projects list
//gcloud config set project 
//gcloud auth application-default login

//The generated url ( plus ngrok if local) from this code to be placed in Twilio SMS configuration -> A message comes in -> Webhook

const dialogflow = require('@google-cloud/dialogflow').v2beta1;
const express = require('express');
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

const port = 3000;

app.post('/HandleReply', express.json(), (req, res) =>  {
  
	
	console.log(req.body);
	
	
	var userMessage = req.body.Body;
	var userPhoneNumber = req.body.From;
	
	console.log(userPhoneNumber);
	console.log(userMessage);
	
	callDetectIntent(res,userMessage,userPhoneNumber);
  
});

async function callDetectIntent(res,userMessage,userPhoneNumber){
	
	
	 const sessionClient = new dialogflow.SessionsClient();

	 const projectId = '<project id>';
	 const sessionId = userPhoneNumber; // Keeping as phonenumber to maintain the session/context
	 const languageCode = 'en-US';
	 
	 //Dont forget to setup the gcloud auth login steps as mentioned in Dialogflow tutorial
	 
	// const query = `phrase(s) to pass to detect, e.g. What is covid`;

	// Define session path
	const sessionPath = sessionClient.projectAgentSessionPath(
	  projectId,
	  sessionId
	);
	

	// The audio query request
	const request = {
	  session: sessionPath,
	  queryInput: {
		text: {
		  text: userMessage,
		  languageCode: languageCode,
		},
	  },
	
	   headers: { 
				
				 'content-type': 'application/json; charset=utf-8' 
				
			  }
	};

	const responses = await sessionClient.detectIntent(request);
	
	const result = responses[0].queryResult.fulfillmentText;
	console.log(result);
	await callInitSendSMS(result,userPhoneNumber);
	res.send("ok");
	  
}

async function callInitSendSMS(messageDraft,mobileNumber) {
	
	const accountSid = "" //process.env.TWILIO_ACCOUNT_SID;
	const authToken = "" //process.env.TWILIO_AUTH_TOKEN;
	
	const client = require('twilio')(accountSid, authToken);
	
	
	return new Promise((resolve,reject)=>{
		client.messages
		  .create({
			 body: messageDraft,
			 from: "+1xxx",// Broadcast number
			 to: "+91xxxxxx",//User mobileNumber
			
		   })
		   
		  .then((message) => {
			  console.log(message.sid);
			  resolve( message.sid)
		  });
	});
	
	
}

/*
app.post('/BotReply', express.json(), (req, res) =>  {
  
	
	console.log(req.body);
	
  
	res.send('send via callback');
});
*/

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});







