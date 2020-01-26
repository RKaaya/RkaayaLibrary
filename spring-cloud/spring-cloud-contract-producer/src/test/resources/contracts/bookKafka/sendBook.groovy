package contracts.bookKafka

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("trigger")
    input {
        triggeredBy("trigger()")
    }
    outputMessage {
        sentTo("toyBook")
        body([
                id: 13,
                name: "Kirby",
                page: 9
        ])
    }
}