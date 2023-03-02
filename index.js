const express = require('express');
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
const port = 3000;

const accountSid =  //process.env.TWILIO_ACCOUNT_SID;
const token = //process.env.TWILIO_TOKEN;
const authToken = token;
	
const client = require('twilio')(accountSid, authToken);

app.get('/', (req, res) => {
  res.send('Hello World!');
});




app.post('/reply', express.json(), (req, res) =>  {
	return new Promise((resolve,reject)=>{
   
    console.log(req.body.Body);
	
	
	var messageToSend = "";
	
	if(req.body.Body == 'hi')
	{
		messageToSend = "Hello There, How I can assist you";
	}
	else
	{
		messageToSend = "Hello "+req.body.Body+"How are you! Let me know how I can assist you";
	}
	
	client.messages
		  .create({
			 body: messageToSend,
			 from: "whatsapp:+14155238886",
			 to: "whatsapp:+919765496594"
		   })
		   
		  .then((message) => {
			  console.log(message.sid);
			  resolve( message.sid)
		  });
	});


  res.send('send via callback');
});



app.post('/callback', (req, res) => {
  res.send('Hello World!');
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});