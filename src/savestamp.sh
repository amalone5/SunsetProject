#!/bin/bash

dirname=$(date +"%m%d-%H%M")

rm -f $dirname
mv ../timestamps/current ../timestamps/$dirname
cp -r ../timestamps/$dirname ../timestamps/current
