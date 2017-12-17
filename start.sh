#!/bin/bash

# mysql 
service mysql start

# tomcat_image start
sh ~/program_files/apache-image/bin/startup.sh

# kafka
cd ~/program_files/kafka_2.10-0.10.2.1/
bin/zookeeper-server-start.sh config/zookeeper.properties &
bin/kafka-server-start.sh config/server.properties &

# es
cd ~
# ~/program_files/elasticsearch-5.5.1/bin/elasticsearch &

# beadhouseanalysis
# cd ~/PycharmProjects/beadhouseRateAnalysis
# python3 manage.py runserver 0.0.0.0:8000 &

