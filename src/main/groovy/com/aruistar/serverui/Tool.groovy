package com.aruistar.serverui

/**
 * Created by liurui on 15/11/26.
 */
class Tool {
    private static void bindPort(String host, int port) throws Exception {
        Socket s = new Socket();
        s.bind(new InetSocketAddress(host, port));
        s.close();
    }

    public static boolean isPortAvailable(int port) {
        try {
//            bindPort("0.0.0.0", port);
            bindPort(InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static initWebStatic() {
        //自动创建static 静态文件
        File webapp = new File(Tool.getProjectPath() + '/static/')
        if (!webapp.exists()) {
            webapp.mkdir();
            File index = new File(Tool.getProjectPath() + '/static/index.html')
            index.write("""
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h1>Hello Spring-Boot-GUI-Seed</h1>
<a href="https://github.com/aruis/spring-boot-gui-seed">Github</a>
</body>
</html>
""")
        }
    }

    public static initYml() {
        //自动创建yml配置文件
        File ymlfile = new File(getProjectPath() + '/application.yml');

        //考虑到jar内获取文件,所以同过getResourceAsStream获取文件路径
        if (!ymlfile.exists()) {
            ymlfile.write(this.getClass().getResourceAsStream("/application.yml").text);
        }
    }

    //获取当前文件的运行路径
    public static String getProjectPath() {


        java.net.URL url = Application.class.getProtectionDomain().getCodeSource().getLocation();

        String filePath = null;

        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8").replace("file:", "").replace("jar:", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (filePath.lastIndexOf('/') == filePath.length() - 1) {
            filePath = filePath.substring(0, filePath.length() - 1)
        }

        if (filePath.endsWith(".jar") || filePath.endsWith(".jar!")) {
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        java.io.File file = new java.io.File(filePath);

        filePath = file.getAbsolutePath();

        return filePath;

    }
}
