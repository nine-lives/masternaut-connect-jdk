package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

/**
 * Masternaut connect JDK entry point
 */
public final class MasternautConnect {
    private final HttpClient client;

    private MasternautConnect(Configuration configuration) {
        this.client = new HttpClient(configuration);
    }

    /**
     * Get a Masternaut connect instance for your given api key.
     *
     * @param username your username
     * @param password your password
     * @param customerId your customer id
     * @return a Masternaut connect instance
     */
    public static MasternautConnect make(String username, String password, String customerId) {
        return new MasternautConnect(new Configuration()
                .withUsername(username)
                .withPassword(password)
                .withCustomerId(customerId));
    }

    /**
     * Get a Masternaut connect instance using finer grained control over configuration.
     *
     * @param configuration your configuration
     * @return a Masternaut connect instance
     */
    public static MasternautConnect make(Configuration configuration) {
        return new MasternautConnect(configuration);
    }

    public GroupConnect groups() {
        return new GroupConnect(client);
    }

    public LocationConnect locations() {
        return new LocationConnect(client);
    }

    public LocationCategoryConnect locationCategories() {
        return new LocationCategoryConnect(client);
    }

    public VehicleConnect vehicles() {
        return new VehicleConnect(client);
    }

    public DriverConnect drivers() {
        return new DriverConnect(client);
    }

    public TrackingConnect tracking() {
        return new TrackingConnect(client);
    }

}
