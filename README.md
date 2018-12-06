# easyshop

 ## Getting started
 1. Clone this repository to a local drive.
 2. Under the easyshop directory, where the pom.xml file is located, run mvn clean install.
 3. Once the above step succeeds, cd to the target directory and run java -jar easyshop-0.1.0.jar
 4. You can now use Postman or other tools in order to call the available REST APIs, as described below.


## REST API usage examples

    PUT /product  // adds a new product
    Payload: {
      "name":"HP laptop",
      "price":899.99
    }

    PUT /product  // adds a new product
    Payload: {
      "name":"Samsung printer",
      "price":109.99
    }

    GET /products  // returns current products
    Server returns: [{
      "id": 1,
      "name": "HP laptop",
      "price": 899.99
    }, {
      "id": 2,
      "name": "Samsung printer",
      "price": 109.99
    }]

    POST /product/1  // update an existing product
    Payload: {
      "name":"HP notebook",
      "price":849.99
    }

    GET /products  // returns current products
    Server returns: [{
      "id": 1,
      "name": "HP notebook",
      "price": 849.99
    }, {
      "id": 2,
      "name": "Samsung printer",
      "price": 109.99
    }]

    POST /product/10  // update a product that doesn't exist
    Payload: {
      "name":"HP notebook",
      "price":849.99
    }
    Server returns: Unknown product

    PUT /order  // place an order
    Payload: {
      "buyerEmail":"someemail@someserver.com",
      "productIds":[1,2]
    }

    PUT /order  // place an order
    Payload: {
      "buyerEmail":"someemail@someserver.com",
      "productIds":[1]
    }

    GET /orders/1544009710000/1544009800000  // get all orders between start and stop dates
    Server returns: [{
      "id": 1,
      "products": [{
        "id": 1,
        "name": "HP notebook",
        "price": 849.99
      },
      {
        "id": 2,
        "name": "Samsung printer",
        "price": 109.99
      }],
      "buyerEmail": "someemail@someserver.com",
      "time": 1544009713687,
      "totalAmount": 1009.98
    }, {
      "id": 2,
      "products": [{
        "id": 1,
        "name": "HP notebook",
        "price": 849.99
      }],
      "buyerEmail": "someemail@someserver.com",
      "time": 1544009795572,
      "totalAmount": 849.99
    }]
    
    POST /product/1  // update an existing product
    Payload: {
      "name":"HP notebook",
      "price":800
    }

    GET /products  // returns current products
    Server returns: [{
      "id": 1,
      "name": "HP notebook",
      "price": 800
    }, {
      "id": 2,
      "name": "Samsung printer",
      "price": 109.99
    }]

    GET /orders/1544009710000/1544009800000  // get all orders between start and stop dates, notice the total ammount values haven't changed
    Server returns: [{
      "id": 1,
      "products": [{
        "id": 1,
        "name": "HP notebook",
        "price": 849.99
      },
      {
        "id": 2,
        "name": "Samsung printer",
        "price": 109.99
      }],
      "buyerEmail": "someemail@someserver.com",
      "time": 1544009713687,
      "totalAmount": 1009.98
    }, {
      "id": 2,
      "products": [{
        "id": 1,
        "name": "HP notebook",
        "price": 849.99
      }],
      "buyerEmail": "someemail@someserver.com",
      "time": 1544009795572,
      "totalAmount": 849.99
    }]
