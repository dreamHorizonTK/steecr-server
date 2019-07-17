## 简介
此项目为 Steecr 爬虫项目的后端服务，前端项目见 [steecr-admin](https://github.com/dreamHorizonTK/steecr-admin)

## 环境搭建
1. jdk 11.x
2. IDE 需要安装 Lombok 插件，否则开发过程中 IDE 会报错

## 配置修改
1. 修改日志输出地址

修改 **resources/log4j2.xml** 配置文件第10行 ```<Property name="filePath">D://logs</Property>```

## 项目打包
mvn clean package -Dmaven.test.skip=true

## 项目部署
