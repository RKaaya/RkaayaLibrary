package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
        description "should return a book when request input is an id"
        request{
            method GET()
            headers {
                contentType(applicationJson())
            }
            url("/book/id") {
                queryParameters {
                    parameter("id", "15")
                }
            }
        }
        response {
            status OK()
            body(file("3_response.json"))
            headers {
                contentType(applicationJson())
            }
        }
    }