# Can not reproduce!

## Case 1

1.  Start the app in prod mode:
    ```sh
    rm -rf ./target/ && sbt -java-home /opt/jvm/adoptopenjdk17/ stage && ./target/universal/stage/bin/playframework-11220 -java-home /opt/jvm/adoptopenjdk17/
    ```
1. Go to http://localhost:9000/
1. Everything works.

## Case 2

1.  Package the app as zip:
    ```sh
    rm -rf ./target/ && sbt -java-home /opt/jvm/adoptopenjdk17/ dist && unzip -l target/universal/playframework-11220-1.0-SNAPSHOT.zip | grep validation-api
        91930  2019-08-10 23:46   playframework-11220-1.0-SNAPSHOT/lib/jakarta.validation.jakarta.validation-api-2.0.2.jar
    ```
1. As you can see the validation-api dependency in version 2.0.2 gets packaged.

## Case 3

1.  Print the dependencies:
    ```sh
    rmTargetFolders ; sbt -java-home /opt/jvm/adoptopenjdk17/ dependencyTree
    [info] welcome to sbt 1.6.2 (Eclipse Adoptium Java 17.0.1)
    [info] loading global plugins from /home/mkurz/.sbt/1.0/plugins
    [info] loading settings for project playframework-11220-build from plugins.sbt ...
    [info] loading project definition from /home/mkurz/work/playframework-reproducers/playframework-11220/project
    [info] loading settings for project root from build.sbt ...
    [info]   __              __
    [info]   \ \     ____   / /____ _ __  __
    [info]    \ \   / __ \ / // __ `// / / /
    [info]    / /  / /_/ // // /_/ // /_/ /
    [info]   /_/  / .___//_/ \__,_/ \__, /
    [info]       /_/               /____/
    [info] 
    [info] Version 2.8.15 running Java 17.0.1
    [info] 
    [info] Play is run entirely by the community. Please consider contributing and/or donating:
    [info] https://www.playframework.com/sponsors
    [info] 
    [info] Running Play on Java 17 is experimental. Tweaks are necessary:
    [info] https://github.com/playframework/playframework/releases/2.8.15
    [info] 
    [info] com.example:playframework-11220_2.13:1.0-SNAPSHOT [S]
    ...
    [info]   | +-org.hibernate.validator:hibernate-validator:6.1.7.Final
    [info]   | | +-com.fasterxml:classmate:1.3.4
    [info]   | | +-jakarta.validation:jakarta.validation-api:2.0.2
    [info]   | | +-org.jboss.logging:jboss-logging:3.3.2.Final
    ...
    [success] Total time: 2 s, completed Apr 6, 2022, 5:04:48 PM
    ```
1. As you can see the validation-api dependency in version 2.0.2 is here.
