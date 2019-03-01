#!/bin/zsh
echo "cleaning out dir"
rm -rf ./out/*
echo "compiling"
javac -d ./out src/*.java
ls ./out/*
