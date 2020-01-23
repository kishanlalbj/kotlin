import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.2"

project {

    vcsRoot(HttpsGithubComKishanlalbjHelloWorldSpring4gitRefsHeadsMaster)

    buildType(SpringBuild)
}

object SpringBuild : BuildType({
    name = "Spring Build"

    vcs {
        root(HttpsGithubComKishanlalbjHelloWorldSpring4gitRefsHeadsMaster)
    }

    steps {
        maven {
            goals = "clean compile"
            pomLocation = "demo/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_5()
        }

        maven {
            goals = "test"
            pomLocation = "demo/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_5()
        }

        maven {
            goals = "clean package"
            pomLocation = "demo/pom.xml"
            mavenVersion = bundled_3_5()
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComKishanlalbjHelloWorldSpring4gitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/kishanlalbj/hello-world-spring-4.git#refs/heads/master"
    url = "https://github.com/kishanlalbj/hello-world-spring-4.git"
    authMethod = password {
        userName = "kishanlalbj"
        password = "credentialsJSON:3948445e-2e80-4600-a8fb-105501f9af3a"
    }
})
