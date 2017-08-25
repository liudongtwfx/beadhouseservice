#!/bin/bash

# tomcat_image start
sh ~/program_files/apache-image/bin/shutdown.sh

# kafka
cd ~/program_files/kafka_2.10-0.10.2.1/
bin/zookeeper-server-stop.sh &
bin/kafka-server-stop.sh &

# es


