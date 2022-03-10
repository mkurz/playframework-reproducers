# Steps to reproduce

1. `sbt run` (will listen on port 9000)
1.  Start the app the body gets redirected to:
    ```sh
    cd ../playframework-10937-body-redirect-to-server
    sbt run
    ```
    (will listen on port 9001)
1. `curl -X POST http://localhost:9000 -d 'foo=bar'`

In the second app you should see something like (which tells us the body was correctly redirected):
```
Headers:
--------
Transfer-Encoding: chunked
Content-Type: application/octet-stream
Timeout-Access: <function1>
Remote-Address: 127.0.0.1:44106
Raw-Request-URI: /
Tls-Session-Info: Session(1646949657956|SSL_NULL_WITH_NULL_NULL)
Host: localhost:9001
Accept: */*
user-agent: AHC/2.1

Body size: 1

Body:
-----
foo=bar
```
