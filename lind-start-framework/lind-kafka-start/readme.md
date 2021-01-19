# docker简单部署
```js
# zk
docker run -d --cpus 0.5 -m 200M   --restart always  -u root  --name zookeeper  -p 2181:2181  -p 2888:2888  -p 3888:3888  zookeeper:3.5.7
# kafka
docker run --cpus 1 -d -m 1g --restart always --name kafka-2.5.0 -p 9092:9092 --link zookeeper -e KAFKA_HEAP_OPTS="-Xmx2g -Xms2g" -e KAFKA_ZOOKEEPER_CONNECT=192.168.1.6:2181 -e KAFKA_ADVERTISED_HOST_NAME=192.168.1.6 -e KAFKA_ADVERTISED_PORT=9092 -e KAFKA_LOG_RETENTION_BYTES=10737418240 -e KAFKA_LOG_RETENTION_HOURS=24 -e KAFKA_DELETE_TOPIC_ENABLE=true -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -e KAFKA_LOG_DIRS="/kafka/kafka-logs-2.5.0" wurstmeister/kafka:2.12-2.5.0
```
# 带有目录挂载的docker部署
```js
#zookeeper采用官方版本：3.5.7
docker run -d --cpus 0.5 -m 200M \
--restart always \
-u root \
--name zookeeper \
-p 2181:2181 \
-p 2888:2888 \
-p 3888:3888 \
-v /etc/localtime:/etc/localtime \
-v /mnt/zookeeper-3.5.7/data:/data \
-v /mnt/zookeeper-3.5.7/log:/datalog \
-v /mnt/zookeeper-3.5.7/conf:/conf \
zookeeper:3.5.7

#kafka则使用github使用率较高的wurstmeister/kafka,相对简单，更新到位
docker run --cpus 4 -d -m 4g \
--restart always \
--name kafka-2.5.0 \
-p 9092:9092 \
--link zookeeper \
-e KAFKA_HEAP_OPTS="-Xmx2g -Xms2g" \
-e KAFKA_ZOOKEEPER_CONNECT=172.19.**.**:2181 \
-e KAFKA_ADVERTISED_HOST_NAME=47.100.***.*** \
-e KAFKA_ADVERTISED_PORT=9092 \
-e KAFKA_LOG_RETENTION_BYTES=10737418240 \
-e KAFKA_LOG_RETENTION_HOURS=24 \
-e KAFKA_DELETE_TOPIC_ENABLE=true \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
-e KAFKA_LOG_DIRS="/kafka/kafka-logs-2.5.0"
-v /etc/localtime:/etc/localtime \
-v /mnt/kafka-2.5.0:/kafka \
-v /var/run/docker.sock:/var/run/docker.sock \
wurstmeister/kafka:2.12-2.5.0
```