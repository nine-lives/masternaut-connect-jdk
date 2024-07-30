package com.nls.masternaut

import spock.lang.Specification

class MasternautServerExceptionSpec extends Specification {

    def "I can construct the exception"() {
        given:
        MasternautError error = new MasternautError("ERRCODE","this error message")

        when:
        MasternautServerException e = new MasternautServerException(401, 'Unauthorised', error)

        then:
        e.statusCode == 401
        e.statusMessage == 'Unauthorised'
        e.error.code == 'ERRCODE'
        e.error.message == 'this error message'
        e.message == 'ERRCODE: this error message'
    }

    def "I can construct the exception without an error"() {
        when:
        MasternautServerException e = new MasternautServerException(401, 'Unauthorised', null)

        then:
        e.statusCode == 401
        e.statusMessage == 'Unauthorised'
        e.error == null
        e.message == '401: Unauthorised'
    }
}
