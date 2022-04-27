/*
These scripts can be adapted to fir into your P1, and should be ample guidance to help consume your API
Note that there are several POSTs to the same resource, and a "mode" header that indicates how the server
should handle the request.

You should start by adapting the URL to match your resource endpoint.
*/

//let userResourceURL = "http://localhost:port/contextpath/resourcepath"; //CHANGE ME!
//Example backend location:
let userResourceURL = "http://localhost:8080/ERS/User";
//Note the context path is set to "/api" make sure to change that in the build config
/*
register function - sends a POST request with a new user object. 
newUser should be an object that your API can parse, something like:

let newUser = {
    username: un,
    password: pw,
    email: em,
    firstName: fn,
    lastName: ln,
    role: ro
}

*/
async function registerRequest(newUser) {
    let response = await fetch(
        userResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "mode": "register"
            },
            body: JSON.stringify(newUser)
        }
    );

    return response;
}

/*
login function - sends a POST request with necessary credentials
authorization is a data transfer object containing username and password.
let authorization = {
    username: un,
    password: pw
}
*/
async function loginRequest(authorization) {
    let response = await fetch(
        userResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "mode": "login"
            },
            body: JSON.stringify(authorization)
        }
    );

    return response;
}

/*
update function - sends a PUT request with updated user data
user is an object that should have the unique ID for a user, and may contain updated info
let user = {
    userId: id,
    username: un,
    password: pw,
    email: em,
    firstName: fn,
    lastName: ln,
    role: ro
}
*/
async function updateUserRequest(user) {
    let response = await fetch(
        userResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user)
        }
    );

    return response;
}

async function getUser(username) {
    let response = await fetch(
        userResourceURL,
        {
            method: "GET",
            headers: {
                "username": "username"
            }
        }
    );

    return response;
}

async function deleteUser(username) {
    let response = await fetch(
        userResourceURL,
        {
            method: "DELETE",
            headers: {
                username: username
            },
            body: JSON.stringify(user)
        }
    );

    return response;
}

