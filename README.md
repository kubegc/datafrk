# datafrk
SQL using Kubernetes style

## Maven users

Add this dependency to your project's POM:

### postgres

```xml
<dependency>
  <groupId>io.github.kubesys</groupId>
  <artifactId>datafrk-postgres</artifactId>
  <version>2.0.1</version>
</dependency>

<repositories>
  <repository>
    <id>pdos-repos</id>
    <name>PDOS Releases</name>
    <url>http://39.100.71.73:31021/repository/maven-public/</url>
  </repository>
</repositories>
```

### mysql

```xml
<dependency>
  <groupId>io.github.kubesys</groupId>
  <artifactId>datafrk-mysql</artifactId>
  <version>2.0.1</version>
</dependency>

<repositories>
  <repository>
    <id>pdos-repos</id>
    <name>PDOS Releases</name>
    <url>http://39.100.71.73:31021/repository/maven-public/</url>
  </repository>
</repositories>
```


## docs

**Now postgres and mariadb/mysql support JSON** 


For example, 

```#Postgres
use kube;
select * from pods where data->'metadata'->>'activeName' like '%database%'
```
more details see [postgres docs](https://www.postgresql.org/docs/12/functions-json.html)

```#Mysql
use kube;
select * from pods where JSON_EXTRACT(data, '$.metadata.name') like '%database%'
```
more details see [mysql docs](https://dev.mysql.com/doc/refman/8.0/en/json-search-functions.html)




- [Sonatype](https://mp.weixin.qq.com/s?__biz=Mzg2MDYzODI5Nw==&mid=2247493958&idx=1&sn=d7e47334823f58db7ce012783045f382&source=41#wechat_redirect)

