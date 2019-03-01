FROM ubuntu:16.04
LABEL Description="hisvak" Version="1.0"

ENV APPLICATION_PATH="/opt/hisvak"
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV JAVA_OPTS="-Xms512m -Xmx750m"

# Install dependencies
RUN apt-get update && \
    apt-get install -y git build-essential curl wget software-properties-common

# Install JDK 8
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    apt-get install -y oracle-java8-installer wget unzip tar && \
    mv /etc/alternatives/java /etc/alternatives/java8 && apt-get install -y maven && mv -f /etc/alternatives/java8 /etc/alternatives/java && ls -l /usr/bin/java && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/cache/oracle-jdk8-installer && \
    mkdir /opt/hisvak

EXPOSE 8983

COPY . /opt/hisvak/

RUN cd $APPLICATION_PATH && \
    wget "https://archive.apache.org/dist/lucene/solr/4.5.1/solr-4.5.1.zip" -O "solr-4.5.1.zip" && \
    unzip "solr-4.5.1.zip" && rm "solr-4.5.1.zip" && \
    mvn clean package && \
    useradd -ms /bin/bash solr && chown -R solr:solr $APPLICATION_PATH && chmod 755 $APPLICATION_PATH/src/main/start.sh

USER solr

CMD ["/opt/hisvak/src/main/start.sh"]