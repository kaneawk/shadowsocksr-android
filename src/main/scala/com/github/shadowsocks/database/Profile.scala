/*******************************************************************************/
/*                                                                             */
/*  Copyright (C) 2017 by Max Lv <max.c.lv@gmail.com>                          */
/*  Copyright (C) 2017 by Mygod Studio <contact-shadowsocks-android@mygod.be>  */
/*                                                                             */
/*  This program is free software: you can redistribute it and/or modify       */
/*  it under the terms of the GNU General Public License as published by       */
/*  the Free Software Foundation, either version 3 of the License, or          */
/*  (at your option) any later version.                                        */
/*                                                                             */
/*  This program is distributed in the hope that it will be useful,            */
/*  but WITHOUT ANY WARRANTY; without even the implied warranty of             */
/*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              */
/*  GNU General Public License for more details.                               */
/*                                                                             */
/*  You should have received a copy of the GNU General Public License          */
/*  along with this program. If not, see <http://www.gnu.org/licenses/>.       */
/*                                                                             */
/*******************************************************************************/

package com.github.shadowsocks.database

import java.net.URLEncoder
import java.util.Locale

import android.content.SharedPreferences
import android.os.Binder
import android.util.Base64
import com.github.shadowsocks.utils.Key
import com.j256.ormlite.field.{DataType, DatabaseField}

class Profile {
  @DatabaseField(generatedId = true)
  var id: Int = _

  @DatabaseField
  var name: String = ""

  @DatabaseField
  var host: String = "198.199.101.152"

  // hopefully hashCode = mHandle doesn't change, currently this is true from KitKat to Nougat
  @DatabaseField
  var localPort: Int = 1080 + Binder.getCallingUserHandle.hashCode

  @DatabaseField
  var remotePort: Int = 8388

  @DatabaseField
  var password: String = "u1rRWTssNv0p"

  @DatabaseField
  var protocol: String = "origin"

  @DatabaseField
  var obfs: String = "plain"

  @DatabaseField
  var obfs_param: String = ""

  @DatabaseField
  var method: String = "aes-256-cfb"

  @DatabaseField
  var route: String = "all"

  @DatabaseField
  var proxyApps: Boolean = _

  @DatabaseField
  var bypass: Boolean = _

  @DatabaseField
  var udpdns: Boolean = _

  @DatabaseField
  var dns: String = "8.8.8.8:53"

  @DatabaseField
  var china_dns: String = "114.114.114.114:53,223.5.5.5:53"

  @DatabaseField
  var ipv6: Boolean = _

  @DatabaseField(dataType = DataType.LONG_STRING)
  var individual: String = ""

  @DatabaseField
  var tx: Long = _

  @DatabaseField
  var rx: Long = _

  @DatabaseField
  val date: java.util.Date = new java.util.Date()

  @DatabaseField
  var userOrder: Long = _

  override def toString = "ssr://" + Base64.encodeToString("%s:%d:%s:%s:%s:%s/?obfsparam=%s&remarks=%s".formatLocal(Locale.ENGLISH,
    host, remotePort, protocol, method, obfs, Base64.encodeToString("%s".formatLocal(Locale.ENGLISH,
    password).getBytes, Base64.URL_SAFE | Base64.NO_WRAP), Base64.encodeToString("%s".formatLocal(Locale.ENGLISH,
    obfs_param).getBytes, Base64.URL_SAFE | Base64.NO_WRAP), Base64.encodeToString("%s".formatLocal(Locale.ENGLISH,
    name).getBytes, Base64.URL_SAFE | Base64.NO_WRAP)).getBytes, Base64.URL_SAFE | Base64.NO_WRAP)

  @DatabaseField
  var kcp: Boolean = _

  @DatabaseField
  var kcpPort: Int = 8399

  @DatabaseField
  var kcpcli: String = "--crypt none --mode normal --mtu 1200 --nocomp --dscp 46 --parityshard 0"

  def formattedAddress: String = (if (host.contains(":")) "[%s]:%d" else "%s:%d").format(host, remotePort)
  def nameIsEmpty: Boolean = name == null || name.isEmpty
  def getName: String = if (nameIsEmpty) formattedAddress else name

  def isMethodUnsafe: Boolean = "table".equalsIgnoreCase(method) || "rc4".equalsIgnoreCase(method)

  def serialize(editor: SharedPreferences.Editor): SharedPreferences.Editor = editor
    .putString(Key.name, name)
    .putString(Key.host, host)
    .putInt(Key.localPort, localPort)
    .putInt(Key.remotePort, remotePort)
    .putString(Key.password, password)
    .putString(Key.protocol, protocol)
    .putString(Key.obfs, obfs)
    .putString(Key.obfs_param, obfs_param)
    .putString(Key.route, route)
    .putString(Key.method, method)
    .putBoolean(Key.proxyApps, proxyApps)
    .putBoolean(Key.bypass, bypass)
    .putBoolean(Key.udpdns, udpdns)
    .putString(Key.dns, dns)
    .putString(Key.china_dns, china_dns)
    .putBoolean(Key.ipv6, ipv6)
    .putString(Key.individual, individual)
    .putBoolean(Key.kcp, kcp)
    .putInt(Key.kcpPort, kcpPort)
    .putString(Key.kcpcli, kcpcli)
    .remove(Key.dirty)
  def deserialize(pref: SharedPreferences) {
    // It's assumed that default values are never used, so 0/false/null is always used even if that isn't the case
    name = pref.getString(Key.name, null)
    host = pref.getString(Key.host, null)
    localPort = pref.getInt(Key.localPort, 0)
    remotePort = pref.getInt(Key.remotePort, 0)
    password = pref.getString(Key.password, null)
    protocol = pref.getString(Key.protocol, null)
    obfs = pref.getString(Key.obfs, null)
    obfs_param = pref.getString(Key.obfs_param, null)
    method = pref.getString(Key.method, null)
    route = pref.getString(Key.route, null)
    dns = pref.getString(Key.dns, null)
    china_dns = pref.getString(Key.china_dns, null)
    proxyApps = pref.getBoolean(Key.proxyApps, false)
    bypass = pref.getBoolean(Key.bypass, false)
    udpdns = pref.getBoolean(Key.udpdns, false)
    ipv6 = pref.getBoolean(Key.ipv6, false)
    individual = pref.getString(Key.individual, null)
    kcp = pref.getBoolean(Key.kcp, false)
    kcpPort = pref.getInt(Key.kcpPort, 0)
    kcpcli = pref.getString(Key.kcpcli, null)
  }
}
