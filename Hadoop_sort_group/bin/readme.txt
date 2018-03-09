pom.xml文件hadoop开发公用部分.
<dependencies>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-common</artifactId>
      <version>2.5.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-hdfs</artifactId>
      <version>2.5.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-client</artifactId>
      <version>2.5.0</version>
    </dependency>
  </dependencies>

将除readme.txt之外的所有文件复制到src目录下。
这部分代码主要用处：
	1. log4j日志配置
	2. 解决在windows上运行hadoop的异常信息，通过修改源码方式进行。