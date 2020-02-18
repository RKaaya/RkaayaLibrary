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
                        id: $(producer(regex(positiveInt()))),
                        name: $(producer(regex(nonEmpty()))),
                        page: $(producer(regex(positiveInt())))
                ],
                [
                        id: $(producer(regex(positiveInt()))),
                        name: $(producer(regex(nonEmpty()))),
                        page: $(producer(regex(positiveInt())))
                ],
                [
                        id: $(producer(regex(positiveInt()))),
                        name: $(producer(regex(nonEmpty()))),
                        page: $(producer(regex(positiveInt())))
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}