//The generated url from this code to be placed in send SMS function at statusCallback


const express = require('express');
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
const port = 3000;

app.post('/status', express.json(), (req, res) =>  {
    console.log(req.body);
	console.log(req.body.SmsStatus);
	res.send('send via callback');
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});