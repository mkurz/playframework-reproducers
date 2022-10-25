# Steps to reproduce

Terminal 1:
```
cd local-website
sbt stage && ./target/universal/stage/bin/local-website
```
Starts a server on port 9001

Terminal 2:
```
cd reproducer
sbt stage ; ./target/universal/stage/bin/reproducer-11506
```

Terminal 3:
```
bombardier -c 100 -d 10s "http://localhost:9000/website?address=http://localhost:9001"
```
