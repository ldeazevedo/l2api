## L2API
Web interface for Lineage2 Admins

This project was created for an admin which had some issues handling his server.

It's a website tool to make some things easier for admins. It works on a Jetty server running on port 8090 (8091 for https)


__How does it works?__

It's a Jetty server which will redirect the request to jsp files using jstl to display content.

__How to access?__

Open port 8090 (8091 for https) in your firewall. Open a web browser and navigate to http://yourserverdomain.com:8090

__It's asking for a username/password, what are the values?__

Username: admin
Password: @qate3!
Take a look at [L2API.java:196](https://github.com/ldeazevedo/l2api/blob/master/src/main/java/com/l2timeus/L2API.java#L196)


__How to put it in my server?__

Add the following line at the very end of your GameServer constructor:

```
L2API.init();
```
