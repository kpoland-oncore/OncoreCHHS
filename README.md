http://oncorechhsapp01.westus.cloudapp.azure.com:8080/OncoreCHHS-war/

OnCore Consulting was thrilled to participate in the CHHS agile prototype. OnCore has a wide breadth and depth of experience in delivering systems for the public sector, and we saw this prototype as an opportunity to demonstrate our agile technical abilities – a core capability of OnCore Consulting. 

**Our Team:**

Our product manager had the authority and responsibility necessary to be accountable for the quality of the prototype.  We assembled a team with seven more of the ADPQ labor category resources, including an agile coach, technical architect, front-end developer, back-end developer, DevOps Engineer, usability tester, and business analyst. 

**Our Approach:**

The team developed an initial inventory of stories and chores for the prototype.  The list included the technical and development architectures and feature stories to support the prototype’s use cases. 
The team then created an implementation plan using Pivotal Tracker. The plan was iterative, allowing feedback to be gathered in each iteration and be used to influence subsequent work or versions of the prototype.  It showed we could deliver the prototype in three one-week iterations. With each iteration, the team would make incremental improvements to the prototype’s technical and functional features.

**Three Iterations:**

Iteration 1 included establishing the architecture, the developer environment, configuration management, and early usability testing.  Iteration 2 completed core feature development, incorporating user feedback from iteration 1.  Iteration 2 also established a continuous integration system to automate running of tests, and supporting continuous deployments to Azure (using Jenkins), our PaaS provider.  It included automated unit tests, and the ability to deploy the prototype to a container, using Docker for operating system level virtualization.  Iteration 3 focused on user submitted enhancements, bug fixes, and improvements to the technical architecture and deployment approach. All tools used in our prototype are openly licensed and we used the DSP as a guide throughout.

**Iteration 1:** Technical Architecture, Developer Environment, Configuration Management, and Early Usability Testing

In the first iteration, we established a home in the Azure cloud, and achieved rapid build and prototyping through VM developer images of Ubuntu, with NetBeans IDE, GlassFish, Jenkins, a local instance of the MySQL database, and configuration management through Github. It established a technical architecture layer that separated the UI and Business tiers.  Iteration one supported manual builds using Ant and Jenkins, establishing a base framework for integration testing in a deployed environment in future iterations.

We conducted user story design, using four main human centered design techniques: user interviews, whiteboard wireframes, rapid prototyping, role playing. We conducted working sessions with users, interviewing them to understand the purpose of the application and their usage patterns. Then, we engaged users in whiteboard wireframe drawings, allowing them to define their interaction with the pages of the prototype.  Users detailed the fields they wanted displayed and the actions that the UI widgets should execute. 

Finally, the team rapidly developed prototypical pages that the users could react to, and provide feedback.  The developers used the produced story specification documents to quickly produce pages for users to see and react to.  This was completed in the first week!  Pages produced included a view in the mobile device context through mobile browser emulation. Performing usability tests with actual users, with a working prototype, across multiple channels, afforded valuable early feedback that was fed into the downstream iterations. 

**Iteration 2:**  Completing Core Functionality, Elaborating the Technology Platform, and Incorporating Feedback from Early Usability Testing

Iteration 2 focused on completing the core features, incorporating user feedback from early usability testing.  It also elaborated our use of the Azure cloud, including automated builds triggered by changes in GitHub, and automated deployments to the test environment triggered by successful builds.

**Iteration 3:**  Feature Enhancements, Bug Fixes, Responsiveness, and Further Optimization to the Technology Platform  

As the team entered iteration three, the core functionality was complete and we added feature enhancements. Users continued to submit input and bugs as they tested using common browsers and mobile devices. The team delivered enhancements the users considered important: improving the overall user experience, adding messaging features and fixing bugs. We increased the UI responsiveness to ensure consistency across multiple browsers and devices. We used NVDA to review the screens and addressed compliance with the Americans with Disabilities Act (ADA). Lastly, in iteration three we evolved the technical architecture to use RESTful clients for communication to the business tier and defined stereotypes and patterns to further separate the UI, business and data tiers. 

In the third iteration, we used Jenkins to orchestrate a set of Ant tasks to build the current version of the application, passing through a gate of JUnit suites before moving on to the automated deployment to a Docker image, where a set of Selenium test cases were run for end-to-end regression. Integration to StatusCake was used for continuous monitoring.  

**Use of Modern Technology and Developer Documentation:** 

Our solution for the prototype is based on all open source technologies. The technical architecture runs on Glassfish, with Java Enterprise Edition 7 using Java Server Faces (JSF 2.2), and PrimeFaces for the UI framework, based on its strong browser interoperability, mobile support and responsiveness, and ADA compliance. The back end is supported by the Java Persistence Architecture (JPA 2.1), using a MySQL database.  A key tenet of Agile is “responding to change over following a plan.” One of the most challenging decisions our product manager made was for the team to create a custom UI style guide instead of the Digital Services Playbook (DSP) style guide. PrimeFaces is not compatible with the DSP without customization, and this would create risk to our delivery schedule. The additional time gained by this decision enabled the team to meet the responsiveness and ADA compliance requirements. 

Our GitHub repository contains a link to and an extract from our [Pivotal Tracker tool](documentation/PivotalTrackerArtifacts/PivotalTrackerProjectInformation.MD), which details each iteration, stories, backlog, and velocity. Our style guide, java docs, and design specs are available in the [documentation](documentation) folder.  [Developer](DEVELOPER.md) and [DevOps tools and environments](ENVIRONMENTS.md) documentation is also provided.
