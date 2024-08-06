# Masternaut Connect API Java SDK

[comment]: <> ([![Maven Central]&#40;https://maven-badges.herokuapp.com/maven-central/com.9ls/masternaut-connect-jdk/badge.svg&#41;]&#40;https://maven-badges.herokuapp.com/maven-central/com.9ls/masternaut-connect-jdk&#41;)

[comment]: <> ([![Build Status]&#40;https://api.travis-ci.com/nine-lives/masternaut-connect-jdk.png&#41;]&#40;https://travis-ci.com/nine-lives/masternaut-connect-jdk&#41;)

[comment]: <> ([![Code Quality]&#40;https://api.codacy.com/project/badge/grade/d289b210b4b94dc69384622a5732bb05&#41;]&#40;https://www.codacy.com/app/nine-lives/masternaut-connect-jdk&#41;)

[comment]: <> ([![Coverage]&#40;https://api.codacy.com/project/badge/coverage/d289b210b4b94dc69384622a5732bb05&#41;]&#40;https://www.codacy.com/app/nine-lives/masternaut-connect-jdk&#41;)

## Getting Started

The Masternaut API requires you to have an api username, password and customer id. 

All API calls are routed from the `MasternautConnect` object.

```java
MasternautConnect connect = MasternautConnect.make(
        username, 
        password, 
        customerId);
```

The sdk is hosted on maven central, so you can include it as a dependency 
in your projects as follows:

### Gradle/Grails
```groovy
implementation 'com.9ls:masternaut-connect-jdk:1.34.3'
```

### Apache Maven
```xml
<dependency>
    <groupId>com.9ls</groupId>
    <artifactId>masternaut-connect-jdk</artifactId>
    <version>1.34.3</version>
</dependency>
```

### Apache Ivy
```xml
<dependency org="com.9ls" name="masternaut-connect-jdk" rev="1.34.3" />
```

## Connect General

### List Group

Get the group hierarchy starting from the root node.

```java
List<Group> groups = connect.groups().list().fetch();
```

### Add Group

Add a new group

```java
Group group = connect.groups().add()
    .withName("A new group")
    .withParentId("5045939e60b13e9f54b1e047")
    .commit();
```

### Update Group

Update the group that matches the group id

```java
Group group = connect.groups().update("652939f1e8b9ea0596fd2f2a")
        .withName("A new group name")
        .withParentId("5045939e60b13e9f54b1e047")
        .withVehicleIds(Arrays.asList("54070", "54082"))
        .withPersonIds(Arrays.asList("347626","347627"))
        .commit();
```

### Delete Group

Delete the group that matches the group id

```java
connect.groups().delete("652939f1e8b9ea0596fd2f2a");
```

### List Location

Returns the location (points of interest) matching the specified parameters.

```java
Page<Location> locations = connect.locations().list()
        .withName("Masternaut")
        .withPageSize(50)
        .withPageIndex(1)
        .fetch();
```

The next page can be called from the returned object as follows

```java
if (locations.hasNext()) {
    Page<Location> nextPageOfLocations = locations.next();    
}
```

A helper method exists to fetch all the pages of locations and aggregate them 
into a single list of locations

```java
List<Location> locations = connect.locations().list()
    .withName("Masternaut")
    .collect();
```

### Add Location

Add a new location with geo-fence boundary set to be CIRCULAR

```java
Location location = connect.locations().add()
        .withName("Masternaut Restaurant")
        .withCategoryName("Restaurants")
        .withCoordinate(new Coordinate()
                .withLongitude(new BigDecimal("53.821729867523"))
                .withLatitude(new BigDecimal("-1.3447768777930378")))
        .withRadius(new BigDecimal("0.001"))
        .withAddress(new Address()
                .withRoadNumber("24")
                .withRoad("Priory Park")
                .withCity("Leeds")
                .withPostCode("LE1 1EY")
                .withCountry("England"))
        .withReference("ABC111")
        .withContact("Brad Woodenhouse")
        .withEmail("brad@test.com")
        .withPhoneNumber("+441234567")
        .withNotes("Company restaurant in Leeds")
        .commit();
```

### Update Location

Request to update location details. This is a partial update and only those details specified 
in the request will be updated.

```java
Location location = connect.locations().update("506b072b60b1f908e7b59cef")
        .withName("Masternaut Restaurant")
        .withCategoryName("Restaurants")
        .withCoordinate(new Coordinate()
                .withLongitude(new BigDecimal("53.821729867523"))
                .withLatitude(new BigDecimal("-1.3447768777930378")))
        .withRadius(new BigDecimal("0.001")))
        .commit();
```

You can also prepopulate a `LocationUpdateRequest` with a details from a `Location`
via the `withLocation()` method.

```java
Location location = ...
LocationUpdateRequest request = new LocationUpdateRequest(location);
assert location.getReference() == request.getReference();
```

### Delete Location

Delete a location that matches the location id.

```java
connect.locations().delete("506b072b60b1f908e7b59cef");
```

### List Location Category

Returns the details for all location categories. If a location category identifier is specified, then only
details of the specified location category are returned.

```java
List<LocationCategory> categories = connect.locationCategories().list().fetch();
```

```java
LocationCategory category = connect.locationCategories().get("51d3e8e91f2659398989ad2a");
```

### Add Location Category

Add a new location category.

```java
LocationCategory category = connect.locationCategories().add()
        .withName("Masternaut customer")
        .withIcon("circle-red")
        .commit();
```

### Update Location Category

Update the location category that matches the category id.

```java
LocationCategory category = connect.locationCategories().update("535917de60b113440f8f3df4")
        .withName("Masternaut Customer")
        .withIcon("circle-blue")
        .commit();
```

### Delete Location Category

Delete the location category that matches the category id.

```java
connect.locationCategories().delete("535917de60b113440f8f3df4");
```

### List Vehicle

Returns the vehicles matching the specified parameters.

```java
Page<Vehicle> vehicles = connect.vehicles().list()
        .withVehicleIds(Arrays.asList("54070", "54072"))
        .fetch();
```

The next page can be called from the returned object as follows

```java
if (vehicles.hasNext()) {
    Page<Vehicle> nextPageOfVehicles = vehicles.next();    
}
```

A helper method exists to fetch all the pages of vehicles and aggregate them
into a single list of vehicles.

```java
List<Vehicle> vehicles = connect.vehicles().list()
    .withName("Masternaut")
    .collect();
```

### Update Vehicle

Update the details for an existing vehicle. This is a partial update and only those
values specified will be updated.

```java
Vehicle vehicle = connect.vehicles().update("54082")
        .withGroupId("5045939e60b13e9f54b1e046")
        .withName("YA99RTE_Crane")
        .withType(VehicleType.HGV)
        .withTags(Arrays.asList("crane"))
        .withStatus(VehicleStatus.SOLD)
        .withAssetCosts(new AssetCosts()
                .withYearOfManufacture(2010)
                .withCostCurrency("GBP"))
        .commit();
```

### List Driver

Returns the drivers matching the specified parameters.

```java
Page<Driver> drivers = connect.drivers().list()
        .withPageSize(50)
        .withPageIndex(1)
        .fetch();
```

The next page can be called from the returned object as follows

```java
if (drivers.hasNext()) {
    Page<Driver> nextPageOfDrivers = drivers.next();    
}
```

A helper method exists to fetch all the pages of drivers and aggregate them
into a single list of drivers.

```java
List<Drivers> drivers = connect.drivers().list().collect();
```

### List Driver Details

Returns the details for a driver including their default vehicle.

```java
Driver driver = connect.drivers().get("20464");
```

### Add Driver

Request to add a new driver.

```java
Driver driver = connect.drivers().add()
        .withName("Hayton Wood")
        .withActive(true)
        .withGroupId("5045939e60b13e9f54b1e046")
        .commit();
```

### Update Driver

Request to update the details for an existing driver. This is a partial update and only those
values specified will be updated.

```java
Driver driver = connect.drivers().update("66973")
        .withName("Hayton Wood")
        .withActive(true)
        .withGroupId("5045939e60b13e9f54b1e046")
        .withTags(Arrays.asList("engineer"))
        .withKeys(Arrays.asList(key1, key2, key3))
        .commit();
```

### Delete Driver

Request to delete a driver that matches the driver id.

```java
Driver driver = connect.drivers().delete("66973");
```

### Set Driver Default Vehicle

Set the default vehicle for a driver to the specified vehicle id. An error is
returned in the following cases:

- If there is already a default vehicle assigned to the driver;
- If the vehicle is already assigned to be the default vehicle for another driver;

```java
Driver driver = connect.drivers().setDefaultVehicle("66973", "18117");
```

### Delete Driver Default Vehicle

Delete the default vehicle for a driver.

An error is returned in the following cases:

- There is no default vehicle assigned to the driver.
- The vehicle id does not match the existing default vehicle for the driver.

```java
Driver driver = connect.drivers().unsetDefaultDriver("66973", "18117");
```

### Unlink Driver Default Vehicle

Unlink a driver to their default vehicle without having to specify the existing
default vehicle.

An error is returned in the following cases:

- There is no default vehicle assigned to the driver.

```java
Driver driver = connect.drivers().unlinkDefaultDriver("66973");
```

## Live Tracking

### Live Position

Returns the live position and status for a resource (driver or vehicle). The live position for a
vehicle on a private journey is not returned. By default, vehicles which have a status of SOLD are
not included in the response.

```java
List<LivePosition> live = connect.tracking().live()
        .withVehicleIds(Arrays.asList("54072", "54070"))
        .fetch();
```

### Live Position Latest

Returns the live position and status for a resource (driver or vehicle). The live position for a
vehicle on a private journey is not returned. By default, vehicles which have a status of SOLD
are not included in the response.

This method can be used to return details for only those vehicles where tracking data has been
processed since a specified processed date/time.

```java
LatestLivePositionList positions = connect.tracking().latest()
        .withVehicleIds(Arrays.asList("54072", "54070")),
        .withFromDateTime(LocalDateTime.parse("2024-07-30T07:50:40.233"))
        .fetch();
```

The response provides a method to get a refreshed response for tracking data processed
since the last response.

```java
positions = positions.refresh();
```

There is also a utility class to automatically poll for updates to tracking data. 
The example below will call the latest live positions every 15 seconds and call
all the registered listeners with returned data.

```java
LatestLivePositionPoller poller = LatestLivePositionPoller.builder(connect.tracking().latest()
        .withFromDateTime(LocalDateTime.parse("2024-07-30T07:50:40.233"))
        .withVehicleIds(Arrays.asList("54072", "54070")))
        .withPollingInterval(15, TimeUnit.SECONDS)
        .withFixedRate(true)
        .build();

poller.addListener(new LatestLivePositionListener() {
    public void onUpdate(LatestLivePositionList positions) {
        // Process latest position tracking data
    }

    public void onError(Exception e) {
        // Handle error from call
    }
});

poller.start(0);
```

### Find Nearest Vehicle

Returns all the nearest vehicles to the search point. Details for a vehicle on a private journey are
not returned. The number of vehicles returned is restricted to the maximum specified. The
vehicles are ordered based on distance from the search point.

```java
List<ObjectDistance> matches = connect.vehicles().nearest()
        .withRadius(20)
        .withMaximumResultsToReturn(10)
        .withPostCode("LS25")
        .fetch();
```

### Find Nearest Location

Returns all the nearest Point of Interest (POI) locations to a search point. The number returned is
restricted to the maximum specified. The locations returned are ordered based on distance from
the specified search point.

```java
List<ObjectDistance> matches = connect.locations().nearest()
        .withRadius(20)
        .withMaximumResultsToReturn(10)
        .withPostCode("LS25")
        .fetch();
```

### Distance Travelled

Returns the distance travelled (in kilometres) by a vehicle for all journeys where the journey
started within the period. The end date for the journey does not have to be within the period.
The distance travelled on private journeys that started in the period is included.

```java
List<DistanceTravelled> journeySummaries = connect.tracking().getJourneySummaries()
        .withStartDate(LocalDateTime.parse("2024-03-01T00:00:00"))
        .withEndDate(LocalDateTime.parse("2024-04-01T00:00:00"))
        .withVehicleIds(Arrays.asList("54072"))
        .fetch();
```

## EcoDrive

### Vehicle Fuel Consumption

Returns summary utilisation information for a vehicle for all journeys where the journey started
in the period.

```java
List<VehicleFuelConsumption> fuelConsumptions = connect.vehicles().fuelConsumption()
        .withStartDate(LocalDateTime.parse("2024-03-01T00:00:00"))
        .withEndDate(LocalDateTime.parse("2024-04-01T00:00:00"))
        .withVehicleId("54072")
        .fetch();
```

### Drive Fuel Consumption

Returns the fuel consumption and CO2 information for all journeys where the journey started in
the period. The end date of the journey does not have to be within the period. Separate fuel
consumption details are returned for each vehicle driven by a driver. Includes the fuel used on a
private journey.

```java
List<DriverFuelConsumption> fuelConsumptions = connect.drivers().fuelConsumption()
        .withStartDate(LocalDateTime.parse("2024-03-01T00:00:00"))
        .withEndDate(LocalDateTime.parse("2024-04-01T00:00:00"))
        .fetch();
```

## Custom Configuration

You can also use `Configuration` to configure the SDK. Apart
from the the api key all the other values have defaults.

```
MasternautConnect connect = Masternaut.make(new Configuration()
    .withUsername("username")
    .withPassword("password")
    .withCustomerId("customerId")
    .withEndpoint("https://api.masternautconnect.com/connect-webservices/services/public/v1/customer/")
    .withMaxConnectionsPerRoute(20)
    .withUserAgent("masternaut-connect-jdk 1.34.3")
    .withBlockTillRateLimitReset(false)
    .withRequestsPerSecond(5)
    .withRequestBurstSize(20);
```

| Configuration Attribute | Description |
| ----------------------- | ----------- |
| Endpoint | The base api url. Defaults to https://api.masternautconnect.com/connect-webservices/services/public/v1/customer/ |
| MaxConnectionsPerRoute | The effective maximum number of concurrent connections in the pool. Connections try to make use of the keep-alive directive. Defaults to 20
| UserAgent | The user agent string sent in the request
| BlockTillRateLimitReset | If set to true then the client will block if the rate limit has been reached until the reset timestamp has expired. Defaults to false
| RequestsPerSecond | If rate limited is true then the maximum requests per second 
| RequestBurstSize | If rate limited the number of consecutive requests allowed before rate limit is enforced 


## Build

Once you have checked out the project you can build and test the project with the following command:

```
gradlew check -x integrationTest -x jacocoTestReport
```

 