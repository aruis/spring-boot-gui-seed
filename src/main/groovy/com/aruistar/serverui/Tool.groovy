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

    public static initYml() {
        //自动创建yml配置文件
        File ymlfile = new File(Application.getProjectPath() + '/application.yml');

        //考虑到jar内获取文件,所以同过getResourceAsStream获取文件路径
        if (!ymlfile.exists()) {
            ymlfile.write(this.getClass().getResourceAsStream("/application.yml").text);
        }
    }
}
