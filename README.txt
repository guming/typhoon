Dispatcher 从kafka拉取消息
将消息下发至worker进行redis的检查工作
Worker是2台部署，挂掉任意一台不影响消息的消费
Dispatcher利用kafka的offset机制拉取消息，故障重启继续从失败点的offset再此拉取消息。

工程发布为zip压缩包
typhoon-1.0-SNAPSHOT-bin.zip
解压缩后的目录结构
typhoon-1.0-SNAPSHOT
---bin/     shell文件
---lib      jar文件
---README.txt


1.启动 typhoon_worker.sh 2台机器
2.启动 typhoon_dispatcher.sh

command:start|stop|restart