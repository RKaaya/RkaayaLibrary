package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should add single book"
    request {
        method POST()
        headers {
            contentType(applicationJson())
        }
        url("/book/")
        body(file("singleBookRequest.json"))
    }
        response {
            status CREATED()
        }
    }
