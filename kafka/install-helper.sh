#!/usr/bin/env bash

#Homebrew installation
echo "Is homebrew installed?"
brew help
code=$?
if [ $code -eq 0 ]
then
    echo "Homebrew is installed... \n continuing..."
else
    echo "Installing Homebrew..."
    mkdir /usr/local/homebrew && curl -L https://github.com/Homebrew/brew/tarball/master | tar xz --strip 1 -C homebrew
    echo "Installation complete..."
fi

sleep 3

#Verify user has at least Java 8
if type -p java; then
    echo "Found java executable in PATH"
    j=java
elif [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]
then
    echo "found java executable in JAVA_HOME"
    j="$JAVA_HOME/bin/java"
else
    echo "You dont have Java installed, please install Java"
    exit 1
fi

if [[ "$j" ]]; then
    version=$("$j" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo version "$version"
    if [[ "$version" > "1.8" ]]; then
        echo "Java version is OK"
    else
        echo "You need Java 8 or higher"
        exit 1
    fi
fi

#Install Kafka
echo "Is Kafka installed?"
brew list kafka
code=$?
if [ $code -eq 0 ]
then
    echo "Installed"
    echo "All good!"
else
    echo "Not installed... \n Installing Kafka..."
    brew install kafka
fi

echo "Is Spark installed?"
brew install apache-spark

echo "Copy necessary properties files to run three Kafka Servers "
cp server*.properties /usr/local/etc/kafka

echo "Done!"
