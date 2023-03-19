
const express = require('express');
const dialogflow = require('@google-cloud/dialogflow').v2beta1
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
const port = 3000;

const accountSid = process.env.TWILIO_ACCOUNT_SID;
const authToken = process.env.TWILIO_TOKEN;
	
const client = require('twilio')(accountSid, authToken);

async function  callGDF(req) {
	
	
	var messageToSend = "";
	
	const sessionClient = new dialogflow.SessionsClient();

	 const projectId = '<projectId>;
	 const sessionId = Math.floor(Math.random() * 37 ) ; 
	 const languageCode = 'en-US';
	 const knowledgeBaseId = <knowledgeBaseId>;
	// const query = `phrase(s) to pass to detect, e.g. I'd like to reserve a room for six people`;

	// Define session path
	const sessionPath = sessionClient.projectAgentSessionPath(
	  projectId,
	  sessionId
	);
	const knowledgeBasePath =
	  'projects/' + projectId + '/knowledgeBases/' + knowledgeBaseId ;

	// The audio query request
	const request = {
	  session: sessionPath,
	  queryInput: {
		text: {
		  text: req.body.Body,
		  languageCode: languageCode,
		},
	  },
	 queryParams: {
		knowledgeBaseNames: [knowledgeBasePath],
	  },
	   headers: { 
				
				 'content-type': 'application/json; charset=utf-8' 
				
			  }
	};

	const responses = await sessionClient.detectIntent(request);
	
	messageToSend = responses[0].queryResult.fulfillmentText;
	console.log(messageToSend);
	
	
	client.messages
		  .create({
			 body: messageToSend,
			 from: "whatsapp:<Twilio number>",
			 to: "whatsapp:<Your mobile no>"
		   })
		   
		  .then((message) => {
			  console.log(message.sid);
			 
		  });
		  
	
	
	
}

async function  callDetectIntent(req) {
	
	
	var messageToSend = "";
	
	const sessionClient = new dialogflow.SessionsClient();

	 const projectId = 'projectId';
	 const sessionId = Math.floor(Math.random() * 37 ) ; 
	 const languageCode = 'en-US';
	 
	// const query = `phrase(s) to pass to detect, e.g. I'd like to reserve a room for six people`;

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
		  text: req.body.Body,
		  languageCode: languageCode,
		},
	  },
	   headers: { 
				
				 'content-type': 'application/json; charset=utf-8' 
				
			  }
	};

	const responses = await sessionClient.detectIntent(request);
	
	messageToSend = responses[0].queryResult.fulfillmentText;
	console.log(messageToSend);
	
	
	client.messages
		  .create({
			 body: messageToSend,
			 from: "whatsapp:<Twilio number>",
			 to: "whatsapp:<Your mobile no>"
		   })
		   
		  .then((message) => {
			  console.log(message.sid);
			 
		  });
		
	
	
	
}

async function test(req){
await callGDF(req);
}

app.post('/reply', express.json(), (req, res) =>  {
	
   
    console.log(req.body.Body);
	test(req);
	res.send('send via callback');
});





app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});