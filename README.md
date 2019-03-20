# Honeycomb

For detailed steps on set up and running a Honeycomb microservice, follow the workshop
presentation and video tutorial here -> [Honeycomb Workshop](https://drive.google.com/drive/u/0/folders/1iaFdA7WYgGcZTT2Q7c948hMjLiQnVkLZ).
Otherwise follow the quick start guide below.

## Quick Start

1. You should have Java installed.
1. Download Eclipse Oxygen (package: Eclipse for Java Developers)
1. Make sure the following plugins are installed
   1. [Git](http://www.eclipse.org/egit/) (EGit)
   1. [Maven](http://www.eclipse.org/m2e/) (m2e)

   Those sites have instructions to install those plugins.

1. You will also need to install the Lombok plugin. To do this:
  1. Go to https://projectlombok.org/download and download whatever version of Lombok is at that page.
  So far, they are all backwards-compatible.
  1. Run the jar with the Java JRE by double-clicking on it. This will pop up a window.
  1. The GUI will show your Eclipse installations or you need to specify the location manually.
  Install Lombok. You'll need to restart Eclipse in order for it to work with Lombok.

1. Clone the dev branch of the github repository “honeycomb” from
https://github.com/fabfitfun/honeycomb into your eclipse workspace using the command:
`git clone https://github.com/fabfitfun/honeycomb.git`

   To clone from a specific branch:
   `git clone -b <branch_name> https://github.com/fabfitfun/honeycomb.git`
   
1. This repository uses the private FabFitFun Maven repository to download shared libraries like
[honeycomb-shared](https://github.com/fabfitfun/honeycomb-shared). In order for Honeycomb to compile,
you need to change your Maven settings to use this Maven repository.

   1. Go to the "honeycomb-shared" GitHub repo and follow the steps at [Configure Maven to use private repository](https://github.com/fabfitfun/honeycomb-shared#configure-maven-to-use-private-repository).

1. Open eclipse, navigate to Window→ Show view → Other → Git → Git Repositories and click on Open to
bring up the Git Repositories Window. This should show the newly cloned repository.

1. Now to import the working directory of the repository, go to File → Import → Maven → Existing
Maven Projects and hit Next.
1. Browse the Root directory  as path/to/repo/honeycomb and select all and hit finish. The projects
are now added in the package explorer.
1. Some projects that have errors are mostly because Eclipse issues with the way we build. If you
haven't installed Lombok, that will cause some errors. Another error that occurs the first time you
import Honeycomb is the build-helper-maven-plugin execution error in some of our pom.xml files.
This problem can be remedied by mousing over the offending lines and selecting to have Eclipse
ignore those lines.
1. Make sure the projects in the package explorer have a little cylinder in the icon which means
they are integrated with Git. At this point you should be able to build and run microservices in
Eclipse! Read the README in the Sample directory on Github for the Honeycomb tutorial.

## Sample Microservice

Check out [sample](/sample/) for an exemplary Honeycomb microservice and tutorial.

## Environment Variable Updates
Any updates (addition, deletion or modification) of environmnet variables to a service,
must be documented in `notes.csv`

## Swagger Document Api
We are in the process of enabling Swagger endpoints on all services. For a Honeycomb service on port 8080:
- Swagger json in OpenAPI format: [http://localhost:8080/openapi.json](http://localhost:8080/openapi.json)
- Swagger UI: [http://localhost:8080/webjars/swagger-ui/index.html?url=/openapi.json](http://localhost:8080/webjars/swagger-ui/index.html?url=/openapi.json)
- Instead of localhost, if you're behind VPN, you can also hit directly through the internal IP
