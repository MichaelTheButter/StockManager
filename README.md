## Stock Manager
Stock manager is an application that manages stock levels. Change stock levels by creating distinct documents, create various independent stocks, restricts acess by authorisation with three roles: admin, user, viewer. 
Distinct document type has distinct impact on product quantity in a particular stock:
- Goods recieved - increase quantity 
- Dispatch note - reduce quantity
- Inventory - set quantity
  
Authorisation is based on Spring security and JWT Tokens.

## Architecture
![](architecture.jpg)

## Endpoints


|    Endpoint    | Method |  Request  |   Response   |            Function            | Authorization |
|    --------    |-------- |--------  |--------   |            --------            |-------- |
| /auth          | POST   | JSON BODY | Bearer Token | authenticate user              | -             |
| /api/products  | POST   | JSON BODY | JSON BODY    | add new product                | user, admin   |
| /api/products  | GET    | -         | JSON BODY    | get all products in main stock | -             |
| /api/stock     | POST   | JSON BODY | JSON BODY    | add new stock                  | admin         |
| /api/documents | POST   | JSON BODY | JSON BODY    | add new document               | user,admin    |
---------------------------------------------------------------------------------------------

## To use
Clone this repo
Run StockManagerApplication.java
