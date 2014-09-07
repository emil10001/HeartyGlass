# HeartyGlass

<img src="https://raw.githubusercontent.com/emil10001/Hearty.io/master/raw_assets/icon/color_icon.png" height="400px" width="400px" >

This is the Glassware for the [Hearty.io](https://github.com/emil10001/Hearty.io) service.
It currently has support for heart rate monitoring via a BLE HRM (Bluetooth 4.0 Low Energy
Heart Rate Monitor).

## WARNING

This Glassware is not stable. It is in development and is very subject to change.

## Pre-requisites

* Android SDK installed and up to date
* Android 4.4W installed
* Google Glass APIs installed
* Latest Android Build-tools installed
* Android Studio installed and up to date

## Building

You'll be building from the [Hearty.io](https://github.com/emil10001/Hearty.io) project.

1. Check out the entire project from GitHub:

    `git clone --recursive https://github.com/emil10001/Hearty.io.git`

2. Import into Android Studio as a project.

3. Start hacking!

### Android Studio

Right now, only Android Studio is supported as an environment. This project requires a newer version of
the gradle plugin than IntelliJ, and Eclipse does not do Gradle at all.

### Git Submodules

Here's some documentation on [git submodules](http://git-scm.com/book/en/Git-Tools-Submodules).

To clone everything at once, do the following:

    git clone --recursive https://github.com/emil10001/Hearty.io.git

