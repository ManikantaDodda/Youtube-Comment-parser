# social-feed-sboot
This app would demonstrate how a "Social Feed API" would look like.


Design and Develop APIs for a Social Media (e.g. Instagram). Below contract you need to follow for the evaluation for the Social Feeds model in your application. Field names in the request object (which will be sent to your application), and field names in the response object (which will be received from your application) will be specified exactly as expected.

**Fetch a User Profile**

Get the profile of a user on Social Media Platform
*GET* /api/get-user/:username

Response Code 200 : Expected response should be of the form

```json
{
    "username": "", ?------------ string
    "followers": ["A", "B", "C"], ?------------ string array of usernames that follow the user (empty by default)
    "following": ["B", "A"], ?------------ string array of usernames that user follows (empty by default)
    "posts": [
    {
    "postId": , ?--------- Integer
    "imageUrl": "", ?--------- String
    "caption": "", ?--------- String
    "upvotes": 0 ?--------- Integer (must be zero by default)
    }
    ] ?------------ array of objects that contains details regarding a post (empty list by default)
}
```

Response Code 404 (failure) : Expected response should be of the form:

```json
{} ?--------- empty body
```

**Create a User Account**

Add a user to the platform
*POST* /api/create-user/

The expected request should be of the form

```json
{
    "username": "", ?--------- string (should be unique)
}
```

Response Code 201 : Expected response should be of the form containing following fields

```json
{
    "username" : "" , ?---------- string
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status": "failure", ?--------- string
    "reason": "explanation" ?--------- string (explanation can be any message)
}
```

**Follow a user**

Send a follow request from usernameA -> usernameB
*POST* /api/follow/:usernameA/:usernameB

Response code 202 : Expected response should be of the form:

```json
{
    "status": "success" ?--------- string
}
```

Response code 400 (failure) : Expected response should be of the form:

```json
{
    "status":"failure",
    "reason":"explanation" ?--------- explanation can be any message
}
```

**Add a social media post**

Post something on the social media platform
*POST* /api/create-post/:username

The expected request should be of the form

```json
{
    "caption": "", ?--------- string
    "imageUrl": "" ?--------- string (required)
}
```

Response Code 201 : Expected response should be of the form

```json
{
    "postId": , ?--------- Integer
    "imageUrl": "", ?--------- String
    "caption": "", ?--------- String
    "upvotes": 0 ?--------- Integer (must be zero by default)
}
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status":"failure",
    "reason":"explanation" ?--------- explanation can be any message
}
```

**Get Posts**

Get the list of posts added by the people whom usernameA is following
*GET* /api/all-posts/:usernameA

Response Code 200 : Expected response should be of the form

```json
[
    {
    "postId": , ?--------- Integer
    "imageUrl": "", ?--------- String
    "caption": "", ?--------- String
    "upvotes": 0 ?--------- Integer (must be zero by default)
    },
    {...}
]
```

Response should be of the form in case of an empty list

```json
[]
```

Response Code 400 (failure) : Expected response should be of the form:

```json
{
    "status":"failure",
    "reason":"explanation" ?--------- explanation can be any message
}
```