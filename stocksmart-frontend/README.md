# StockSmart Angular Frontend

Connected to Spring Boot at `http://localhost:8081/api`.

## Run
1. Start MySQL.
2. Start the Spring Boot backend on port 8081.
3. In this folder run:
   `npm install`
   `npm start`
4. Open `http://localhost:4200`.

## Backend CORS
Every controller used by Angular must allow:
`@CrossOrigin(origins = "http://localhost:4200")`

## Important
Order Item deletion is not exposed in this UI because the current backend delete method does not restore previously deducted inventory.
