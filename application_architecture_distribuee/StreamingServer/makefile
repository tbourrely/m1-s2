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