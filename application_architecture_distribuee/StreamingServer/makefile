clean:
	rm -rf ./generated/*

build: clean
	slice2py --output-dir ./generated/ ./ice/StreamingServer.ice

run_server:
	python3 iceServer.py

run_client:
	python3 client.py