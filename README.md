<p align="center"><img src="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/DocFiles/ReadMe_logo.jpg?raw=true" width="70%" height="70%"></p>


<p align="center"><h1><b>Bike Maintenance Tracker</b></h1></p>

This Java/Spring Boot app enables the user to track wear and tear on bikes and their components. A comprehensive view shows when worn out parts should be replaced or repaired.


<br/>
<h2><b>Table of Contents:</h2></b>

- [Requirements](#requirements)
- [User Stories](#user-stories)
- [Extra Features](#extra-features)
- [Wireframes](#wireframes)
- [User Story Tests](#user-story-tests)
- [Extra Features Tests](#extra-features-tests)

<br/>

# Requirements
<ul>
<li><a href="https://www.jetbrains.com/idea/">IntelliJ IDEA</a></li>
<li><a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">Java SDK</a></li>
<li><a href="https://www.mamp.info/en/">MAMP</a></li>
<li><a href="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/DatabaseSetup.md">Database Setup</a></li>
</ul>


# User Stories
<ol>
<li>User creates an account</li>
<li>User creates multiple bikes</li>
<li>Bike has different components that the user can change out</li>
<li>User is notified if a part on the bike is due for maintenance</li>
<li>User can view a list or table of parts and basic information like mileage since last maintenance</li>
<li>User can enter mileage for a bike ride on a specific bike</li>
</ol>

# Extra Features
<ol>
<li>User can upload a profile picture and a picture for each bike</li>
<li>User can provide feedback when a part has failed</li>
<li>App can track multiple bikes</li>
<li>Power users can move parts between different bikes</li>
</ol>

# Wireframes

<h5>User log-in and bike add</h5>
<img src="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/DocFiles/IMG_1944.JPG?raw=true" height="480em" width="640em">
<h5>*update bike view wireframe</h5>
<img src="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/DocFiles/updated_bike_view_wireframe_10_12.jpg?raw=true" height="480em" width="640em">

# User Story Tests
<ol>
<li>Create multiple user accounts</li>
<li>Create multiple bikes for individual user</li>
<li>Upon bike creation, add individual parts, which will track miles</li>
<li>After bike is created and parts are added, replace those parts with new parts</li>
<li>Verify the user is notified that parts need maintence based on miles traveled</li>
<li>Verify each component has a unique message based on mileage traveled</li>
<li>Add rides (including miles) to different bikes</li>
<li>Reset mileage on component using the perform maintenace button under each component</li>
</ol>
