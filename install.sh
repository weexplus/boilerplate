#!/bin/bash
SOURCE="$0"
while [ -h "$SOURCE"  ]; do # resolve $SOURCE until the file is no longer a symlink
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
cd $DIR
pwd
cp -rf dist/    platforms/android/weexplus/app/src/main/assets/app/;
cp -rf dist/    platforms/ios/weexplus/app/;
cp -rf src/img/    dist/img/;
cp -rf src/config.json    platforms/android/weexplus/app/src/main/assets/app/config.json;
cp -rf src/config.json    platforms/ios/weexplus/app/config.json;
pwd
