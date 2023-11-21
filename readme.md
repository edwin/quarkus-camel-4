# Quarkus and Camel 4 Sample

## How To
Run the app
```
$ mvn quarkus:dev -s settings.xml
```

Test Rest API 1
```
$ curl -kv http://localhost:8080/api/hello/edwin
*   Trying ::1:8080...
* TCP_NODELAY set
*   Trying 127.0.0.1:8080...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /api/hello/edwin HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json
<
* Connection #0 to host localhost left intact
{"hello":"edwin"}                                    
```

Test Rest API 2
```
$ curl -kv http://localhost:8080/api/call/https%3A%2F%2Fgithub.com%2F
* TCP_NODELAY set
*   Trying 127.0.0.1:8080...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /api/call/github.com HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Accept-Ranges: bytes
< content-language: en-US
< ETag: W/"dcfd8c7528d442eb39a75f4f70070dbc"
< Referrer-Policy: origin-when-cross-origin, strict-origin-when-cross-origin
< Server: GitHub.com
< whatever: header
< X-Content-Type-Options: nosniff
< X-Frame-Options: deny
< X-GitHub-Request-Id: FF3A:D7207:D9FD55:E495AA:655AE883
< X-XSS-Protection: 0
< transfer-encoding: chunked
< Content-Type: text/html; charset=utf-8
<

....                                  

```