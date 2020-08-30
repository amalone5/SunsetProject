javac *.java

touch ../timestamps/current/startstamp.txt
java Main /mnt/SlaveDrive2/BigPictureProject/full/archive/2018/07/ /home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/            --no-cache

# java Main /home/austin/Documents/CodeBase/GitCode/SunsetProject/magnificent/ /home/austin/Documents/CodeBase/GitCode/SunsetProject/pictures/            --no-cache


touch ../timestamps/current/stopstamp.txt
./savestamp.sh
rm *.class
