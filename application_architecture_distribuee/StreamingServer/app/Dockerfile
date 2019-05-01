FROM python:3
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix run.sh
RUN chmod a+x run.sh
RUN pip3 install -r requirements.txt
EXPOSE 5000 10000
CMD ["./run.sh"]