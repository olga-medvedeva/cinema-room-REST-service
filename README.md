# Backend project using Java + Spring Boot

### Virtual movie theater with REST service.

Service has following fuctionality:
- GET /seats returns information about a theater;
- POST /purchase receives a seat in JSON and marks a booked ticket as purchased. Request should contain following data: row — the row number;
column — the column number.
If purchase is successful, server responds with token and information about purchased seat. 
If purchase is not successesful, server responds with a 400 (Bad Request) and specified error message in a responce body.
- POST /return receives a token as JSON object and allows customers to refund their tickets then marks seats as available.
If return is successful, server responds with information about returned seat. 
If return is not successful, server responds with a 400 (Bad Request) and error message "Wrong token!"
- POST /stats return the movie theatre statistics if URL parameters contain a password key with a super_secret value.
If request is successful, server responds with movie theates statistics in following format:<br />
```
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```
Description of keys:<br />
	current_income — shows the total income of sold tickets.<br />
  number_of_available_seats — shows how many seats are available.<br />
  number_of_purchased_tickets — shows how many tickets were purchased.<br />

If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code and error message "The password is wrong!"

## Examples:

**Example 1: a GET /seats request**

Response body:
```
{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },
      {
         "row":1,
         "column":2,
         "price":10
      },
      {
         "row":1,
         "column":3,
         "price":10
      },

      ........

      {
         "row":9,
         "column":8,
         "price":8
      },
      {
         "row":9,
         "column":9,
         "price":8
      }
   ]
}
```

**Example 2: a correct POST /purchase request**

Request body:
```
{
    "row": 3,
    "column": 4
}
```

Response body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```

**Example 3: POST /return with the correct token**

Request body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```
**Example 4: POST /return with an expired token**

Request body:
```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```
{
    "error": "Wrong token!"
}
```
**Example 5: a POST /stats request with no parameters**

Response body:
```
{
    "error": "The password is wrong!"
}
```
**Example 6: a POST /stats request with the correct password**

Response body:
```
{
    "current_income": 30,
    "number_of_available_seats": 78,
    "number_of_purchased_tickets": 3
}
```
