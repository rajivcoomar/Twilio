const dialogflow = require('@google-cloud/dialogflow').v2beta1;
const express = require('express');
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

const port = 3000;

app.post('/HandleReply', express.json(), (req, res) =>  {
  
	
	console.log(req.body);
	
  
	res.send('send via callback');
});



app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});







