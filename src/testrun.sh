javac *.java
rm ../pictures/*

touch ../timestamps/current/startstamp.txt

java Main /mnt/SlaveDrive2/BigPictureProject/full/archive/ /home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/            --no-cache

# java Main /home/austin/Documents/CodeBase/GitCode/SunsetProject/magnificent/ /home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/            --no-cache


touch ../timestamps/current/stopstamp.txt
rm *.class
