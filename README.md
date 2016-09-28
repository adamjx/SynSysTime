# SynSysTime
可以修改系统时间的demo
##注意事项
修改系统时间需要具有root权限，或者app运行在系统中才可以，本例采用第二种方式：
- *在manifest.xml中添加如下代码：*
`android:sharedUserId="android.uid.system"`
- *进入$android_source\build\target\product\security目录，找到***platform.pk8***和***platform.x509.pem***两个文件*
- *生成带签名的apk*
- *利用***signapk.jar***这个包，生成带系统密匙的apk，cmd下执行*`java -jar signapk.jar platform.x509.pem platform.pk8 test.apk testnew.apk`*命令，
生成带系统签名的apk包*
- *注：以上自带的signapk.jar包可以在系统源码的out编译目录找到并编译*
