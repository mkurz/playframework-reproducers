# Steps to reproduce

## Case 1

1. `sbt run`
1. Go to http://localhost:9000/
1. "Execution exception" because the env variable `mytestvar` was not set. That's correct.

## Case 2

1. `mytestvar=99 sbt run` (sets the env var `mytestvar`)
1. Go to http://localhost:9000/
1. Correctly displays "Config value: 99" in the browser.

## Case 3

1. `sbt test`
1. The test in [`test/controllers/HomeControllerTest.java`](https://github.com/mkurz/playframework-reproducers/blob/49e6b7b215e08967e72b2ef7f96d8aaf09cef902/playframework-10972/test/controllers/HomeControllerTest.java#L17-L20) passes. That means it picks up the env variable [set in the `build.sbt`](https://github.com/mkurz/playframework-reproducers/blob/main/playframework-10972/build.sbt#L10) now because the JVM [was forked](https://github.com/mkurz/playframework-reproducers/blob/49e6b7b215e08967e72b2ef7f96d8aaf09cef902/playframework-10972/build.sbt#L12) when running tests.
