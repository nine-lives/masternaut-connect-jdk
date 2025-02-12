package com.nls.masternaut

import spock.lang.Specification

class ConfigurationSpec extends Specification {

    private String version

    def setup() {
        Properties versionProperties = new Properties()
        versionProperties.load(Configuration.class.getClassLoader().getResourceAsStream("version.properties"))
        version = versionProperties.getProperty("version")
    }

    def "The defaults are the defaults"() {
        when:
        Configuration config = new Configuration()

        then:
        config.username == null
        config.password == null
        config.customerId == null
        config.endpoint == 'https://api.masternautconnect.com/connect-webservices/services/public/v1/customer/'
        config.maxConnectionsPerRoute == 20
        config.userAgent == "masternaut-connect-jdk/${version}".toString()
        config.requestBurstSize == 20
        config.requestsPerSecond == 5
        !config.blockTillRateLimitReset
        version ==~ /1\.\d+\.\d+/
    }

    def "I can set configuration values"() {
        when:
        Configuration config = new Configuration()
            .withUsername("me")
            .withPassword("secret")
            .withCustomerId("customer id")
            .withEndpoint("https://bpi.masternautconnect.com/")
            .withMaxConnectionsPerRoute(22)
            .withUserAgent("ninelives/9.0.0")
            .withBlockTillRateLimitReset(true)
            .withRequestBurstSize(25)
            .withRequestsPerSecond(10)

        then:
        config.username == 'me'
        config.password == 'secret'
        config.customerId == 'customer id'
        config.endpoint == 'https://bpi.masternautconnect.com/'
        config.maxConnectionsPerRoute == 22
        config.userAgent == "ninelives/9.0.0 masternaut-connect-jdk/${version}".toString()
        config.requestBurstSize == 25
        config.requestsPerSecond == 10
        config.blockTillRateLimitReset
    }

    def "I can set and unset the user agent"() {
        when:
        Configuration config = new Configuration()

        then:
        config.userAgent == "masternaut-connect-jdk/${version}".toString()

        when:
        config.withUserAgent("ninelives/9.0.0")

        then:
        config.userAgent == "ninelives/9.0.0 masternaut-connect-jdk/${version}".toString()

        when:
        config.withUserAgent(null)

        then:
        config.userAgent == "masternaut-connect-jdk/${version}".toString()

        when:
        config.withUserAgent(" ")

        then:
        config.userAgent == "masternaut-connect-jdk/${version}".toString()
    }

}
