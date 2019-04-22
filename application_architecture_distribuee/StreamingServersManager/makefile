clean:
	rm -rf ./generated/*

build: clean
	slice2py --output-dir ./generated/ ./ice/MP3Collection.ice
	./node_modules/slice2js/bin/slice2js-cli.js --output-dir ./generated ./ice/MP3Collection.ice

run_server:
	python3 server.py

run_client:
	node client.js