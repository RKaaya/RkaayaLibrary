package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
        description "should return a book when request input is an id"
        request{
            method GET()
            headers {
                contentType(applicationJson())
            }
            url("/book/") {
            }
        }
        response {
            status OK()
            body(file("responseAll.json"))
            headers {
                contentType(applicationJson())
            }
        }
    }