#!/bin/bash

dirname=$(date +"%m%d-%H%M")

rm -f $dirname
mv ../timestamps/current ../timestamps/$dirname
rm -f ../timestamps/current
cp -r ../timestamps/$dirname ../timestamps/current
