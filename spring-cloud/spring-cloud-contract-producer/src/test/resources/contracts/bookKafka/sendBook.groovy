package contracts.bookKafka

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("trigger")
    input {
        triggeredBy("sendBookWithKafka()")
    }
    outputMessage {
        sentTo("toyBook")
        body([
                id: $(producer(anyInteger())),
                name: $(producer(anyNonEmptyString())),
                page: $(producer(anyInteger()))
        ])
    }
}