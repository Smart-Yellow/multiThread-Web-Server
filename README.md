# multiThread-Web-Server
http1.0
1、在B/S架构中，Web浏览器与Web服务器之间的一次HTTP请求与响应：需要完成以下步骤:

http://localhost:8089/index.html

1）、浏览器端根据所在的HTTP协议解析出对应的url域名：localhost

2）、通过DNS域名解析，查询出该域名对应的IP地址：127.0.0.1

3）、通过URL解析出对应的端口号：8080

4）、浏览器发起并建立到127.0.0.1的连接（Socket中TCP的三次握手）

关于Socket中TCP的三次握手建立连接如下：

a、浏览器向服务器发送一个SYN J，

b、服务器对SYN J进行确认ACK J+1,向浏览器响应一个SYN K、ACK J + 1，

c、浏览器再向服务器发送一个确认ACK K + 1

 

5）、浏览器向服务器发送GET请求

6）、服务器响应浏览器的请求

7）、浏览器读取响应，根据http协议渲染页面

8）、浏览器关闭与服务器的连接（TCP四次挥手）



现在实现一个简单的多线程http服务器:

对于一个web服务器就是在一台虚拟主机上建立的一个特定的socket连接服务端端口，

然后一直等待浏览器发送连接请求，

一旦有一个连接建立就会产生一个Request和Response对象，

然后按照http协议去读取request对象中的请求的参数，找到相应的资源文件，并通过IO进行读写，将要返回的资源封装到response对象中

response再按照http协议返回给浏览器。
