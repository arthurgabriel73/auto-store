package com.autostore.bff.infrastructure.adapter.driver.rest.examples;


public class SwaggerExamples {

    public static final String AUTH_USER = """
            {
                "cpf": "636.803.004-68"
            }
            """;

    public static final String REGISTER_USER = """
            {
            	"firstName": "Nair",
            	"lastName": "Moreira",
            	"cpf": "636.803.004-68",
            	"email": "nair_moreira@suplementototal.com.br",
            	"street": "Rua Palomas",
            	"number": "330",
            	"neighborhood": "Jardim das Palmeiras",
            	"city": "Ariquemes",
            	"state": "RO",
            	"zipCode": "76876-642",
            	"complement": "CASA",
            	"country": "Brasil"
            }
            """;

    public static final String REGISTER_PRODUCT = """
                {
                  "code": "MYW-6552",
                  "unitValue": 686670.00,
                  "category": "VEHICLE",
                  "details": {
                    "plate": "MYW-6552",
                    "make": "ASTON MARTIN",
                    "model": "DB9 Volante 6.0",
                    "version": "V12 470cv",
                    "yearFabrication": "2011",
                    "yearModel": "2012",
                    "kilometers": "83909",
                    "color": "Black"
                  }
                }
            """;

    public static final String ADD_PRODUCT_STOCK = """
                {
                  "productCode": "MYW-6552",
                  "quantity": 1
                }
            """;

    public static final String UPDATE_PRODUCT = """
                {
                      "code": "MYW-6552",
                      "unitValue": 666670.00,
                      "category": "VEHICLE",
                      "details": {
                        "plate": "MYW-6552",
                        "make": "ASTON MARTIN",
                        "model": "DB9 Volante 6.0",
                        "version": "V12 470cv",
                        "yearFabrication": "2011",
                        "yearModel": "2012",
                        "kilometers": "88909",
                        "color": "Black"
                      }
                    }
            """;

    public static final String CREATE_ORDER = """
            {
              "customer": "636.803.004-68",
              "products": [
                {
                  "product": {
                    "code": "MYW-6552",
                    "unitValue": 626670.00
                  },
                  "quantity": 1
                }
              ]
            }
            """;

    public static final String REGISTER_PRODUCT_VALIDATION = """
                {
                  "code": "MYW-6552"
                }
            """;

}
