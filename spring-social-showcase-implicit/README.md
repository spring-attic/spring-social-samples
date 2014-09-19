Spring Social Showcase (Spring Boot)
====================================
This sample app demonstrates many of the capabilities of the Spring Social project, including:
* Connect to Facebook, Twitter, and LinkedIn
* Sign in using Facebook, Twitter, and Linked in using ProviderSignInController for provider-signin

To run, simply import the project into your IDE and deploy to a Servlet 3.0 or > container such as Tomcat 6 or 7.
Access the project at http://localhost:8080/spring-social-showcase

Alternatively, you can build the application with Gradle:

```sh
$ gradlew build
```

To make it easier to build the project with Gradle, the Gradle wrapper has been included. The Gradle wrapper makes it possible to run Gradle without having to explicitly install Gradle to your system.

Once the build completes you can either deploy build/libs/spring-social-showcase.war to your favorite Servlet 3.0 or > container *or* you can run the WAR file directly from the command line:

```sh
$ java -jar build/libs/spring-social-showcase.war
```

When running the application from the command line, you can access it at http://localhost:8080 from your browser.

Discuss at http://forum.spring.io/forum/spring-projects/web/social and collaborate with the development team at jira.springframework.org/browse/SOCIAL.
