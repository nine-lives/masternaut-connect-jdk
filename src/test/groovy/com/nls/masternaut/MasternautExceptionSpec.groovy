package com.nls.masternaut

import spock.lang.Specification

class MasternautExceptionSpec extends Specification {

    def "I can create an exception with just a message"() {
        when:
        MasternautException e = new MasternautException("error message")

        then:
        e.message == "error message"
    }

    def "I can create an exception with just a cause"() {
        given:
        IllegalArgumentException cause = new IllegalArgumentException();
        when:
        MasternautException e = new MasternautException(cause)

        then:
        e.cause == cause
    }

}
