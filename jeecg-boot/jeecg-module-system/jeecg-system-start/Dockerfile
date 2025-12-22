FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/java:17-anolis

MAINTAINER jeecgos@163.com

# 1. 安装中文字符集
RUN dnf install -y glibc-langpack-zh glibc-langpack-en \
    && dnf clean all

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

#RUN mkdir -p /jeecg-boot/config/jeecg/

WORKDIR /jeecg-boot

EXPOSE 8080

#ADD ./src/main/resources/jeecg ./config/jeecg
ADD ./target/jeecg-system-start-3.9.0.jar ./

RUN mkdir -p /jeecg-boot/config

# -Dsun.jnu.encoding=UTF-8 -DLC_ALL=en_US.UTF-8
CMD sleep 60;java -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar jeecg-system-start-3.9.0.jar