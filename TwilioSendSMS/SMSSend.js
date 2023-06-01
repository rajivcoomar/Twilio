//Windows CMD
//set TWILIO_AUTH_TOKEN=<Auth token value>
//set TWILIO_ACCOUNT_SID=<Account sid>

//Unix 
//$ TWILIO_AUTH_TOKEN=<Auth token value>
//$ TWILIO_ACCOUNT_SID=<Account sid>

callInitSendSMS();

async function callInitSendSMS() {
	
	const accountSid = "" //process.env.TWILIO_ACCOUNT_SID;
	const authToken = "" //process.env.TWILIO_AUTH_TOKEN;
	
	const client = require('twilio')(accountSid, authToken);
	
	
	return new Promise((resolve,reject)=>{
		client.messages
		  .create({
			 body: "hi",
			 from: "+1xxxxx", //Your Broadcast number
			 to: "+1xxxxx", //User Phone number
			// statusCallback:"https://b75f-2402-e280-3e3c-120-69c5-5eb-8142-eb51.ngrok-free.app/status" //ngrok url if you like to capture delivery status. Refer SMSCaptureStatus.js
		   })
		   
		  .then((message) => {
			  console.log(message.sid);
			  resolve( message.sid)
		  });
	});
	
	
}