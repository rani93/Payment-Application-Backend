### get by id
GET http://localhost:8080/payment/id/{{id}}

### get all payment
GET http://localhost:8080/payment/allPayments

### get by type of payment
GET http://localhost:8080/payment/paymentType/{{paymentType}}

### get by description keyword
GET http://localhost:8080/payment/description/{{keyword}}

### post the payment
POST http://localhost:8080/payment/save
Content-Type: application/json

{
  "id": 0,
  "paymentType": "",
  "description": "",
  
}


### delete payment by id
DELETE http://localhost:8080/payment/delete/id/{{id}}

### update payment 
PUT http://localhost:8080/payment/update
Content-Type: application/json

{
  "id": 0,
  "paymentType": "",
  "description": "",
  
}

### update payment by id
PUT http://localhost:8080/payment/update/{{id}}/description/{{description}}
Content-Type: application/x-www-form-urlencoded


