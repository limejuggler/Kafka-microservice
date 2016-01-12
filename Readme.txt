How to run Kafka 

Windows
1. Download Kafka and unzip: http://kafka.apache.org/downloads.html (use 0.9.0.0 Binary downloads)
2. Open cmd as Administrator
3. cd/to/location/of/kafka/folder
4. run this command to run zookeeper: 	bin\windows\zookeeper-server-start.bat config\zookeeper.properties
5. run this command to run kafka:		bin\windows\kafka-server-start.bat config\server.properties
6. Run MinimumConsumer.class
7. Run MinimumProducer.class 

Linux
1. Follow instructions on: https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-14-04
2. Run MinimumConsumer.class
3. Run MinimumProducer.class 
