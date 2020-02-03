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
                id: $(consumer(regex(anInteger())), producer(42)),
                name: $(consumer(anyNonBlankString()), producer("TBook")),
                page: $(consumer(optional(regex(anInteger()))), producer(13))
        ])
    }
        response {
            status CREATED()
            headers {
                contentType(applicationJson())
            }
            body([
                    id: $(producer(anyInteger())),
                    name: fromRequest().body('$.name'),
                    page: fromRequest().body('$.page')
            ])
        }
    }
