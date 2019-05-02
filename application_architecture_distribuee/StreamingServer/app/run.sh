#!/bin/bash
cd /app
exec python3 iceServer.py &
exec python3 server.py