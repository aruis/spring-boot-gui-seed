package com.aruistar.serverui

import org.yaml.snakeyaml.Yaml

import javax.swing.*

/**
 * Created by liurui on 15/11/25.
 */
class Application {
    public static void main(String[] args) {
        //SpringApplication.run(Server.class, args);
        UICore ui = new UICore();
//        def path = Application.class.getResource("/").toString().replace("file:", "").replace("jar:", "").replace("!", "");
        File file = new File(getProjectPath() + '/application.yml');
        Yaml yaml = new Yaml();
        if (!file.exists()) {
            file.write('''
server:
  port: 7070

isLog: true

spring:
  resources: {add-mappings: true}
logging:
  level: {org.springframework.web: ERROR}
''');
        }
        def config = yaml.load(file.text);

        ui.config = config;
        if (config.isLog) {
            config.logging.path = getProjectPath() + '/log';
        } else {
            config.logging.path = null;
        }

//        config.spring['resources.static-locations']=getProjectPath() + '/';

        ui.save = { -> file.write(yaml.dump(config)) }

        file.write(yaml.dump(config));
        ui.setPort(config.server.port);
        ui.setIsLog(config.isLog);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("软件应用配置");
                frame.setSize(520, 360);
                frame.setContentPane(ui);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);// 居中显示
                frame.pack();
                frame.setVisible(true);
            }
        });


    }

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
