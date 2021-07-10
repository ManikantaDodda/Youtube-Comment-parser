# social-feed-sboot
This app would demonstrate how a "Social Feed API" would look like.


Design and Develop APIs for a Social Media (e.g. Instagram). Below contract you need to follow for the evaluation for the Social Feeds model in your application. Field names in the request object (which will be sent to your application), and field names in the response object (which will be received from your application) will be specified exactly as expected.

 **Fetch a User Profile**

Get the profile of a user on Social Media Platform
**GET** /api/get-user/:username

Response Code 200 : Expected response should be of the form

{

&quot;username&quot;: &quot;&quot;, ?------------ string

&quot;followers&quot;: [&quot;A&quot;, &quot;B, &quot;C], ?------------ string array of usernames that follow the user (empty by default)

&quot;following&quot;: [&quot;B&quot;, &quot;A&quot;], ?------------ string array of usernames that user follows (empty by default)

&quot;posts&quot;: [

{

&quot;postId&quot;: , ?--------- Integer

&quot;imageUrl&quot;: &quot;&quot;, ?--------- String

&quot;caption&quot;: &quot;&quot;, ?--------- String

&quot;upvotes&quot;: 0 ?--------- Integer (must be zero by default)

}

] ?------------ array of objects that contains details regarding a post (empty list by default)

}

Response Code 404 (failure) : Expected response should be of the form:

{} ?--------- empty body

 **Create a User Account**

Add a user to the platform
**POST** /api/create-user/

The expected request should be of the form

{

&quot;username&quot;: &quot;&quot;, ?--------- string (should be unique)

}

Response Code 201 : Expected response should be of the form containing following fields

{

&quot;username&quot; : &quot;&quot; , ?---------- string

}

Response Code 400 (failure) : Expected response should be of the form:

{

&quot;status&quot;: &quot;failure&quot;, ?--------- string

&quot;reason&quot;: &quot;explanation&quot; ?--------- string (explanation can be any message)

}

 **Follow a user**

Send a follow request from usernameA -\&gt; usernameB
**POST** /api/follow/:usernameA/:usernameB

Response code 202 : Expected response should be of the form:

{

&quot;status&quot;: &quot;success&quot; ?--------- string

}

Response code 400 (failure) : Expected response should be of the form:

{

&quot;status&quot;:&quot;failure&quot;,

&quot;reason&quot;:&quot;explanation&quot; ?--------- explanation can be any message

}

 **Add a social media post**

Post something on the social media platform
**POST** /api/create-post/:username

The expected request should be of the form

{

&quot;caption&quot;: &quot;&quot;, ?--------- string

&quot;imageUrl&quot;: &quot;&quot; ?--------- string (required)

}

Response Code 201 : Expected response should be of the form

{

&quot;postId&quot;: , ?--------- Integer

&quot;imageUrl&quot;: &quot;&quot;, ?--------- String

&quot;caption&quot;: &quot;&quot;, ?--------- String

&quot;upvotes&quot;: 0 ?--------- Integer (must be zero by default)

}

Response Code 400 (failure) : Expected response should be of the form:

{

&quot;status&quot;:&quot;failure&quot;,

&quot;reason&quot;:&quot;explanation&quot; ?--------- explanation can be any message

}

 **Get Posts**

Get the list of posts added by the people whom usernameA is following
**GET** /api/all-posts/:usernameA

Response Code 200 : Expected response should be of the form

[

{

&quot;postId&quot;: , ?--------- Integer

&quot;imageUrl&quot;: &quot;&quot;, ?--------- String

&quot;caption&quot;: &quot;&quot;, ?--------- String

&quot;upvotes&quot;: 0 ?--------- Integer (must be zero by default)

},

{...}

]

Response should be of the form in case of an empty list

[]

Response Code 400 (failure) : Expected response should be of the form:

{

&quot;status&quot;:&quot;failure&quot;,

&quot;reason&quot;:&quot;explanation&quot; ?--------- explanation can be any message

}