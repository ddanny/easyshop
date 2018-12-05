# easyshop







`
    PUT /product
    {
      "name":"HP laptop",
      "price":899.99
    }

    PUT /product
    {
      "name":"Samsung printer",
      "price":109.99
    }

    GET /products
    [{
      "id": 1,
      "name": "HP laptop",
      "price": 899.99
    }, {
      "id": 2,
      "name": "Samsung printer",
      "price": 109.99
    }]

    POST /product/1
    {
      "name":"HP notebook",
      "price":849.99
    }

    GET /products
    [{
      "id": 1,
      "name": "HP notebook",
      "price": 849.99
    }, {
      "id": 2,
      "name": "Samsung printer",
      "price": 109.99
    }]

    POST /product/10
    {
      "name":"HP notebook",
      "price":849.99
    }
    Returns: Unknown product

    PUT /order
    {
      "buyerEmail":"someemail@someserver.com",
      "productIds":[1,2]
    }

    GET /orders/1544009710000/1544009800000
    [{
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
      "time": 1544009713687
    }, {
      "id": 2,
      "products": [{
        "id": 1,
        "name": "HP notebook",
        "price": 849.99
      }],
      "buyerEmail": "someemail@someserver.com",
      "time": 1544009795572
    }]
`
