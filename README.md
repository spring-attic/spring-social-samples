Spring Social Samples
=====================
This repository contains sample projects illustrating usage of the Spring Social project.

Samples include:
 * **spring-social-popup**, demonstrates how to do a popup-based connection flow.
 * **spring-social-quickstart**, designed to get you up and running quickly in a Spring Framework 3.1 or > environment.
 * **spring-social-quickstart-3.0.x**, a version of the quickstart that is Spring Framework 3.0.x compatible. (Note that as of Spring Social 1.1, Spring Social no longer supports Spring 3.0.) 
 * **spring-social-showcase**, illustrating most of the features.
 * **spring-social-showcase-xml**, a version of spring-social-showcase that uses XML-based configuration.
 * **spring-social-showcase-sec**, a version of spring-social-showcase that uses SocialAuthenticationFilter instead of  ProviderSignInController for a tighter integration with Spring Security. 
 * **spring-social-showcase-sec-xml**, a version of spring-social-showcase that uses both XML-based configuration and SocialAuthenticationFilter.
 * **spring-social-movies**, showing how to extend the framework to implement a new ServiceProvider + API binding.
 * **spring-social-twitter4j**, showing how to extend the framework and re-use an existing API binding (also shows XML config, multiple connections per provider, and is Spring 3.0.x compatible).

See the README in each sub directory for information on the sample and instructions on how to run the samples.

**NOTE**: Previously there was a **spring-social-canvas** sample included among these samples. Although it worked, it approached the problem of authorization in a way not best suited for Facebook Canvas applications. As a result, was presenting an anti-pattern of how to develop Facebook Canvas applications with Spring Social and causing some confusion among the Spring Social community. Therefore, it has been removed for now. It will be put back once a proper canvas example is presented.