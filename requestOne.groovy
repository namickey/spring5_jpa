import groovyx.net.http.HTTPBuilder
def http = new HTTPBuilder('http://localhost:8080')
http.get([path : '/demo/project']) { resp, reader ->
    a = a + a
    b = b + b
    println(reader)
}
a = a + a
b = b + b
def http2 = new HTTPBuilder('http://localhost:8080')
http.get([path : '/demo/project']) { resp, reader ->
    a = a + a
    b = b + b
    println(reader)
}
println a
println b
