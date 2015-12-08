package com.aruistar.serverui

import com.aruistar.serverui.ui.UICore
import org.yaml.snakeyaml.Yaml

/**
 * Created by liurui on 15/11/25.
 */
class Application {
    public static void main(String[] args) {

        //自动创建static 静态文件
        File webapp = new File(getProjectPath() + '/static/')
        if (!webapp.exists()) {
            webapp.mkdir();
            File index = new File(getProjectPath() + '/static/index.html')
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

        UICore ui = new UICore();

        Tool.initYml();

        //自动创建yml配置文件
        File ymlfile = new File(getProjectPath() + '/application.yml');
        Yaml yaml = new Yaml();
        def config = yaml.load(ymlfile.text);

        ui.config = config;
        if (config.isLog) {
            config.logging.path = getProjectPath() + '/log';
        } else {
            config.logging.path = null;
        }

        //确定静态文件路径
        config.spring.resources['static-locations'] = 'file:' + getProjectPath() + '/static/';

        ui.save = { -> ymlfile.write(yaml.dump(config)) }

        ymlfile.write(yaml.dump(config));

        //向ui提供"端口"和"是否启动日志"信息
        ui.setPort(config.server.port);
        ui.setIsLog(config.isLog);


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
