package contracts.bookCRUDWithDB

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should add single book"
    request {
        method POST()
        headers {
            contentType(applicationJson())
        }
        url("/bookdb/")
        body([
                id: $(consumer(regex(positiveInt())), producer(42)),
                name: $(consumer(regex(nonBlank())), producer("TBook")),
                page: $(consumer(regex(positiveInt())), producer(13))
        ])
    }
        response {
            status CREATED()
            headers {
                contentType(applicationJson())
            }
            body([
                    id: $(producer(regex(positiveInt()))),
                    name: fromRequest().body('$.name'),
                    page: fromRequest().body('$.page')
            ])
        }
    }
