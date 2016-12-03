# Linux env.

### basic env.
1. 在虚拟机上装三台linux。

```
三台虚拟机的用户名都是root，密码都是ga。
IP地址分别是：
192.168.5.128
192.168.5.129
192.168.5.130
```

2. 更改运行级别 inittab为3。
3. 设置虚拟机网络为“仅主机”。
4. 关闭防火墙

```
    查看防护墙状态
    service iptables status
    关闭
    service iptables stop
    查看防火墙开机启动状态
    chkconfig iptables --list
    关闭开机启动
    chkconfig iptables off
```

4. 设置IP及其主机名映射。

```
vim etc/hosts
分别设置IP及其主机名映射：
192.168.5.128 ga01
192.168.5.129 ga02
192.168.5.130 ga03
```

### Advanced env.

**1. 安装JDK。**

```
上传JDK到根目录下的tmp目录。
tar -xzvf jdk-8u111-linux-x64.tar.gz
cp -r jdk1.8.0_111/ /dev/
vim /etc/profile

export JAVA_HOME=/dev/jdk1.8.0_111
export PATH=$PATH:$JAVA_HOME/bin

source /etc/profile
```
JDK 安装在usr。

------------------------------------
**2. 安装hadoop**
hadoop安装在usr下。

文件：hadoop-env.sh
`export JAVA_HOME=/usr/jdk1.8.0_111`

文件：core-site.xml
```
<property>
<name>fs.defaultFS</name>
<value>hdfs://ga01:9000</value>
</property>
<property>
<name>hadoop.tmp.dir</name>
<value>/usr/hadoop-2.4.1/tmp</value>
</property>
```

文件：hdfs-site.xml
```
<property>
<name>dfs.replication</name>
<value>1</value>
</property>
```

文件：mapred-site.xml.template

`mv mapred-site.xml.template mapred-site.xml`

```
<property>
<name>mapreduce.framework.name</name>
<value>yarn</value>
</property>
```

文件：yarn-site.xml
```
<property>
<name>yarn.resourcemanager.hostname</name>
<value>ga01</value>
</property>
<property>
<name>yarn.nodemanager.aux-services</name>
<value>mapreduce_shuffle</value>
</property>
```
------------------------------------
**3. 配置hadoop环境变量**
`vim etc.profile`
```
export JAVA_HOME=/dev/jdk1.8.0_111
export HADOOP_HOME=/usr/hadoop-2.4.1
export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
```
`source etc/profile`

------------------------------------
**4. 格式化启动hadoop**
`hadoop namenode -format`

启动hadoop
先启动HDFS
`sbin/start-dfs.sh`

再启动YARN
`sbin/start-yarn.sh`
		
验证是否启动成功
`jps`
```
27408 NameNode
28218 Jps
27643 SecondaryNameNode
28066 NodeManager
27803 ResourceManager
27512 DataNode
```
	
http://192.168.5.128:50070 （HDFS管理界面）
http://192.168.5.128:8088 （MR管理界面）
		
**5. 设置三台主机之间的ssh登录**
`ssh-keygen -t rsa` 一直回车
进入`/root/.ssh/id_rsa`
创建`authorized_keys`

```
scp id_rsa.pub ga02:/home
cat id_rsa.pub > /root/.ssh/authorized_keys
chmod 600 authorized_keys
```

# Window env

1. 安装xshell及其文件传送程序来连接linux。
2. 保证及其上配置好了JDK。
3. 安装好eclipse。
