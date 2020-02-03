package contracts.bookCRUDWithDB

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return books"
    request{
        method GET()
        headers {
            contentType(applicationJson())
        }
        url("/bookdb/")
    }
    response {
        status OK()
        body([
                [
                        id: $(producer(anyInteger())),
                        name: $(producer(anyNonEmptyString())),
                        page: $(producer(anyInteger()))
                ],
                [
                        id: $(producer(anyInteger())),
                        name: $(producer(anyNonEmptyString())),
                        page: $(producer(anyInteger()))
                ],
                [
                        id: $(producer(anyInteger())),
                        name: $(producer(anyNonEmptyString())),
                        page: $(producer(anyInteger()))
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}