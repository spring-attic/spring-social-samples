Spring Social Canvas
====================
This sample app demonstrates how to use Spring Social within a Facebook Canvas application.

Before you can run the application, you'll need to obtain application credentials from Facebook, Twitter, and LinkedIn by registering the application with each of the service providers:

 * Facebook: https://developers.facebook.com/apps
 * Twitter: https://apps.twitter.com/
 * LinkedIn: https://www.linkedin.com/secure/developer

Be sure to read each platform's usage policies carefully and understand how they impact your use of Spring Social with those platforms.

IMPORTANT: Note that the steps for running the sample differ slightly from the other Spring Social samples:

 1. Build the application using Gradle:

```sh
$ gradlew build
```

 2. Deploy and start the application on a Servlet 2.5 or > container such as Tomcat 6 or 7 *and* ensure that HTTPS support is enabled. For convenience, you should be able to run the application using Maven with `mvn tomcat:run`.
 3. Navigate to http://apps.facebook.com/springsocialcanvas in your browser. This is the canvas page configured in Facebook for the Spring Social Canvas sample.

The pertinent details of the application's Facebook configuration is as follows:
 - __Display Name__: Spring Social Canvas
 - __Namespace__: springsocialcanvas
 - __Canvas Page__: http://apps.facebook.com/springsocialcanvas
 - __Canvas URL__: http://localhost:8080/spring-social-canvas/canvas/
 - __Secure Canvas URL__: https://localhost:8443/spring-social-canvas/canvas/

Discuss at forum.springsource.org and collaborate with the development team at jira.springframework.org/browse/SOCIAL.
