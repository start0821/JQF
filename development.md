# Build
## Error
``` bash
$ mvn package
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] jqf                                                                [pom]
[INFO] jqf-instrument                                                     [jar]
[INFO] jqf-fuzz                                                           [jar]
[INFO] jqf-maven-plugin                                          [maven-plugin]
[INFO] jqf-examples                                                       [jar]
[INFO]
[INFO] ----------------------< edu.berkeley.cs.jqf:jqf >-----------------------
[INFO] Building jqf 1.5-SNAPSHOT                                          [1/5]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] -----------------< edu.berkeley.cs.jqf:jqf-instrument >-----------------
[INFO] Building jqf-instrument 1.5-SNAPSHOT                               [2/5]
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-resources-plugin/3.0.2/maven-resources-plugin-3.0.2.pom
/usr/bin/java: relocation error: /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/amd64/libnio.so: symbol initInetAddressIDs version SUNWprivate_1.1 not defined in file libnet.so with link time reference
```
### Solution 
install new version of java

