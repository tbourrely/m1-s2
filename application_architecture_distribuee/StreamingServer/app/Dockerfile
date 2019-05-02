FROM python:3
COPY run.sh /tmp
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix /tmp/run.sh
RUN chmod a+x /tmp/run.sh
COPY requirements.txt /tmp
RUN pip3 install -r /tmp/requirements.txt