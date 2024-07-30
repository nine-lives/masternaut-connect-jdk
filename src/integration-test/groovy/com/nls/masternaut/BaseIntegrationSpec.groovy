package com.nls.masternaut

import com.nls.masternaut.util.ObjectMapperFactory
import spock.lang.Specification

abstract class BaseIntegrationSpec extends Specification {

    protected static MasternautConnect connect

    def setupSpec() {
        ObjectMapperFactory.setFailOnUnknownProperties(true)
        connect = MasternautConnect.make(new Configuration()
                .withBlockTillRateLimitReset(false)
                .withRequestsPerSecond(1000)
                .withUsername(System.getProperty("masternautUsername") ?: System.getenv("masternautUsername"))
                .withPassword(System.getProperty("masternautPassword") ?: System.getenv("masternautPassword"))
                .withCustomerId(System.getProperty("masternautCustomerId") ?: System.getenv("masternautCustomerId")))
    }
}
