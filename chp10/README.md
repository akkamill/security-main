linux da user:pass encode elemek ucun 
echo -n user:c52ec793-4abd-4b8e-8a7e-239fefe20f57 | base64
 

user:pass encode edib asagidaki kimi de cagirmaq olar    
curl -H "Authorization: Basic dXNlcjpjNTJlYzc5My00YWJkLTRiOGUtOGE3ZS0yMzlmZWZlMjBmNTc=" http://localhost:8080/hello


curl -i \-H "Accept: application/json" \-H "Content-Type:application/json" \-X POST --data '{"username": "user", "password": "c9fb21de-98ef-4202-82ee-6864a70ccd98"}' "https://localhost:8080/hello"


curl -X POST -H "Content-type: application/json" -d "John Smith" "http://localhost:8080/response/postbody"


Spring in Practice by Willie Wheeler with Joshua White

curl -u user:pass http://localhost:8080/hello

curl -v http://localhost:8080/hello -v ile olan varianta bax

-v/--verbose

Makes the fetching more verbose/talkative. Mostly usable for debugging. Lines starting with '>' means data sent by curl, '<' means data received by curl that is hidden in normal cases and lines starting with '*' means additional info provided by curl.

Note that if you want to see HTTP headers in the output, -i/--include might be option you're looking for.

If you think this option still doesn't give you enough details, consider using --trace or --trace-ascii instead.

If this option is used twice, the second will again disable verbose

curl -v http://localhost:8080/hello

curl -u user:c52ec793-4abd-4b8e-8a7e-239fefe20f57 http://localhost:8080/hello



curl -X GET http://localhost:8080/hello -H "Content-Type: application/json" --anyauth

curl -XPOST http://localhost:8080/hello

curl -X POST http://localhost:8080/hello -H 'Cookie: JSESSIONID=C3824C1FB3CABD2276C44F8D88202828'  -H 'X-CSRF-TOKEN: afac9fa1-3753-4476-a3be-d39f3c5314fb'

cache edir bir defe postmanda cagiranda 
ona gore maraqli geldi

https://www.codefeetime.com/post/practical-http-get-and-cache-control/


CsrfTokenRepository