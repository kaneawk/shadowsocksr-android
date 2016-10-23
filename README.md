## Shadowsocks R for Android

A [Shadowsocks R](https://github.com/breakwa11/shadowsocks-rss/) client for Android, written in Scala.

### CI STATUS

[![Build Status](https://api.travis-ci.org/shadowsocks/shadowsocks-android.svg)](https://travis-ci.org/shadowsocks/shadowsocks-android)

### PREREQUISITES

* JDK 1.8
* SBT 0.13.0+
* Go 1.4+
* Android SDK
  - Build Tools 25+
  - Android Support Repository and Google Repository (see `build.sbt` for version)
* Android NDK r12b+

### BUILD

* Set environment variable `ANDROID_HOME` to `/path/to/android-sdk`
* Set environment variable `ANDROID_NDK_HOME` to `/path/to/android-ndk`
* Set environment variable `GOROOT_BOOTSTRAP` to `/path/to/go`
* Create your key following the instructions at https://developer.android.com/studio/publish/app-signing.html
* Create `local.properties` from `local.properties.example` with your own key information
* Invoke the building like this

```bash
    git submodule update --init

    # Build the App
    sbt native-build clean android:package-release
```

## OPEN SOURCE LICENSES

* shadowsocksr-libev: [GPLv3](https://github.com/breakwa11/shadowsocks-libev/blob/master/LICENSE)
* shadowsocksr-obfsplugin: [MIT](https://github.com/breakwa11/obfsplugin/blob/master/LICENSE)
* tun2socks: [BSD](https://github.com/shadowsocks/badvpn/blob/shadowsocks-android/COPYING)
* redsocks: [APL 2.0](https://github.com/shadowsocks/redsocks/blob/master/README)
* OpenSSL: [OpenSSL](https://github.com/shadowsocks/openssl-android/blob/master/NOTICE)
* pdnsd: [GPLv3](https://github.com/shadowsocks/shadowsocks-android/blob/master/src/main/jni/pdnsd/COPYING)
* libev: [GPLv2](https://github.com/shadowsocks/shadowsocks-android/blob/master/src/main/jni/libev/LICENSE)
* libevent: [BSD](https://github.com/shadowsocks/libevent/blob/master/LICENSE)
* kcptun: [MIT](https://github.com/xtaci/kcptun)

### LICENSE

Copyright (C) 2016 by Max Lv <<max.c.lv@gmail.com>>
Copyright (C) 2016 by Mygod Studio <<contact-shadowsocks-android@mygod.be>>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
