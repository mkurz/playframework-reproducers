# Steps to reproduce

## Bad case (from incorrect docs)

1. `git checkout 43e6ae3`
   (https://github.com/mkurz/playframework-reproducers/tree/43e6ae31d33fac0c8e5866a1be572a01c60e104a)
1. `sbt run`
1. Compile error "Missing implicit def of HandlerInvokerFactory for Request => WebSocket"

## Fixed

1. `git checkout 00f8d15`
1. `sbt run`
1. Works!

This was the fix: https://github.com/mkurz/playframework-reproducers/compare/43e6ae3...00f8d15
