clean:
	rm -rf ./generated/*

build: clean
	slice2py --output-dir ./generated/ ./ice/StreamingServer.ice

run_server:
	python3 iceServer.py

run_library_manager:
	python3 libraryManager.py

run_security_manager:
	python3 securityManager.py

run_nodemon_server:
	nodemon --exec python3 iceServer.py

run_web_server:
	python3 server.py

docker_build:
	docker build -t streamingserver .

docker_run:
	docker run --rm -p 10000:10000 -p 5000:5000 streamingserver