# spring-boot-gui-seed
spring-boot上的薄封装,提供一个gui界面,用来配置端口和启动服务


项目第一次启动时，会自动创建yml配置文件，log日志路径，static静态文件路径以及index.html文件

你可以根据自己的业务，扩展UI界面和业务代码，实现一个免tomcat的服务端独立应用。用来构建轻量级的业务或者微服务再合适不过。

`
UICore 中的UI是通过IntelliJ IDEA自动生成的,原则上是java代码,但是当文件名改为groovy,会有一个小问题,需要将最后一行/** @noinspection ALL */前,增加个换行解决
`