import groovyx.net.http.HTTPBuilder
def http = new HTTPBuilder('http://localhost:8080')
def aa = ""
def bb = ""
http.get([path : '/demo/project']) { resp, reader ->
    aa = aa + a
    bb = bb + b
    println(reader)
}
aa = aa + a
bb = bb + b
def http2 = new HTTPBuilder('http://localhost:8080')
http.get([path : '/demo/project']) { resp, reader ->
    aa = aa + a
    bb = bb + b
    println(reader)
}
println aa
println bb
