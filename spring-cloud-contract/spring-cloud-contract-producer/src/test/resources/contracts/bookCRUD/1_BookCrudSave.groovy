package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should add book"
    request {
        method POST()
        headers {
            contentType(applicationJson())
        }
        url("/book/")
        body(file("BookCrudSave.json"))
    }
        response {
            status CREATED()
        }
    }
