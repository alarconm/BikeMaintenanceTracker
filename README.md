# Bike Maintenance Tracker

This Java/Springboot app help users track the wear and tear on bikes and their components. Providing users with a
comprehensive view when worn out parts should be replaced or repaired.

# Requirements
<ul>
<li>Java IDEA</li>
<li><a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">Java SDK</a></li>
<li><a href="https://www.mamp.info/en/">MAMP</a></li>
</ul>
<p>Create your database table prior to running the app through MAMP, reference src->main->application.properties</p>

# User Stories
<ol>
<li>User would be able to create an account</li>
<li>User would be able to create multi bikes</li>
<li>The bike would have different components that the user would change out.</li>
<li>The user would be notified if a part on the bike need maintenance</li>
<li> A user would be able to view a list or table of parts and basic information like milage since last maintenance</li>
<li>A user would be able to enter milage for a bike ride on a specific bike</li>
</ol>

<h5>Extra Features</h5>
<ol>
<li>User would be able to upload a profile picture and picture for each bike</li>
<li>Provide feedback when a part had failed</li>
<li>Track multiple bikes</li>
<li>Power users would be able to move parts between different bikes</li>
</ol>

# Wireframes

<h5>User log-in and bike add</h5>
<img src="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/IMG_1944.JPG?raw=true" height="480em" width="640em">
<h5>*update bike view wireframe</h5>
<img src="https://github.com/alarconm/BikeMaintenanceTracker/blob/master/updated_bike_view_wireframe_10_12.jpg?raw=true" height="480em" width="640em">

# User Story Tests
<ol>
<li>Create multiple user accounts</li>
<li>Create multiple bikes for individual user</li>
<li>Upon Bike creation, user will be able to add individual parts that will track miles</li>
<li>Components will notify user that parts need maintence based of miles traveled</li>
<li>Each component will have a unique message based off mileage traveled</li>
<li>User will be able to add rides to different bikes</li>
<li>User will be able to reset mileage on component using the perform maintenace button under each component</li>
</ol>
