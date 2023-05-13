const express = require("express");
const path = require("path");

const port = 8080;
const app = new express();

app.use(express.urlencoded({extended: true}));

app.use("/", express.static(path.join(__dirname, "content/")));

app.listen(port, () => {
    console.log("The server is listening on port " + port);
});
