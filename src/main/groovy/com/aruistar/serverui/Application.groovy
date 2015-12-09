package com.aruistar.serverui

import com.aruistar.serverui.ui.UICore
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.yaml.snakeyaml.Yaml

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by liurui on 15/11/25.
 */
class Application {
    static UICore ui;

    public static void main(String[] args) {

        Tool.initWebStatic();
        Tool.initYml();

        ui = new UICore();

        JButton saveButton = ui.saveButton;
        JButton startButton = ui.startButton;
        JButton stopButton = ui.stopButton;
        JTextField portTextField = ui.portTextField;
        JCheckBox isLogCheckBox = ui.isLogCheckBox;
        JLabel infoLabel = ui.infoLabel;

        ApplicationContext appContext;

        File ymlfile = new File(Tool.getProjectPath() + '/application.yml');
        Yaml yaml = new Yaml();
        def config = yaml.load(ymlfile.text);

        if (config.isLog) {
            config.logging.path = Tool.getProjectPath() + '/log';
        } else {
            config.logging.path = null;
        }

        //确定静态文件路径
        config.spring.resources['static-locations'] = 'file:' + Tool.getProjectPath() + '/static/';

        ymlfile.write(yaml.dump(config));

        //向ui提供"端口"和"是否启动日志"信息
        portTextField.text = config.server.port;
        isLogCheckBox.selected = config.isLog;

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def port = portTextField.text.toString().trim();
                if (port != "" && port.matches(/[\d]+/) && Integer.parseInt(port) <= 65536) {
                    if (Tool.isPortAvailable(Integer.parseInt(port))) {
                        config.server.port = port;
                    } else {
                        alert('端口无效或已被占用,请更换.')
                        return;
                    }
                } else {
                    alert('端口由纯数字组成,建议选择范围1024至65536.')
                    return;
                }

                config.isLog = ui.isLogCheckBox.selected;
                if (config.isLog) {
                    config.logging.path = Tool.getProjectPath() + '/log';
                } else {
                    config.logging.path = null;
                }
                ymlfile.write(yaml.dump(config))
                alert('保存成功.')

            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//        System.setProperty("spring.resources.static-locations", Tool.getProjectPath()+"/static/");
                System.setProperty('spring.config.location', Tool.getProjectPath() + '/application.yml')
                //String[] args = ['--spring.config.location=' + Tool.getProjectPath() + '/application.yml'].toArray();
                new Thread({ ->
                    isLogCheckBox.enabled = startButton.enabled = stopButton.enabled = portTextField.enabled = saveButton.enabled = false;
                    infoLabel.text = '启动中...'
                    try {
                        appContext = SpringApplication.run(Server.class)
                        infoLabel.text = '已启动'
                        isLogCheckBox.enabled = startButton.enabled = stopButton.enabled = portTextField.enabled = saveButton.enabled = false;
                        stopButton.enabled = true;
                    } catch (ex) {
                        ex.printStackTrace()
                        infoLabel.text = '启动失败'
                        isLogCheckBox.enabled = startButton.enabled = stopButton.enabled = portTextField.enabled = saveButton.enabled = true;
                        stopButton.enabled = false;
                    }


                }).start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (appContext != null) {
                    SpringApplication.exit(appContext);
                    infoLabel.text = ''
                    appContext = null;
                    isLogCheckBox.enabled = startButton.enabled = stopButton.enabled = portTextField.enabled = saveButton.enabled = true;
                    stopButton.enabled = false;
                }
            }
        });
    }


    public static void alert(String text) {
        JOptionPane.showMessageDialog(ui, text);
    }


}
