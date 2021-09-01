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
  <version>2.0.2</version>
</dependency>

<repositories>
  <repository>
    <id>pdos-repos</id>
    <name>PDOS Releases</name>
    <url>http://39.100.71.73:31021/repository/maven-public/</url>
  </repository>
</repositories>
```

# usage

- postgres
  - [create datacontext](create-postgres-datacontext)
  - [create table](create-postgres-table)
- mysql
  - [create datacontext](create-mysql-datacontext)
  - [create table](create-mysql-table)

## create-postgres-datacontext

```
Properties props = new Properties(); 
props.put("druid.driverClassName", "org.postgresql.Driver"); 
props.put("druid.url", "jdbc:postgresql://127.0.0.1:5432/postgres"); 
props.put("druid.username", "postgres"); 
props.put("druid.password", "xxx");
props.put("druid.initialSize", 10); 
props.put("druid.maxActive", 100);
props.put("druid.maxWait", 0);

DataContext dataContext = new PostgresDataContext(props)
System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.createDatabase(new CreatePostgresDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.checkDababase(new CheckPostgresDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.dropDababase(new DropPostgresDatabase("abc")));
```

## create-postgres-table

```
String sql = "CREATE TABLE public.henry2019 (\r\n"
			+ "    name character varying(256)  NOT NULL,\r\n"
			+ "    artifactid character varying(128)  NOT NULL,\r\n"
			+ "    created timestamp without time zone  NOT NULL)";

Database database = context.currentDatabase();
System.out.println(database.checkTable(new CheckPostgresTable("henry2019")));
System.out.println(database.createTable(new CreatePostgresTableBuilder().sql(sql).build()));
System.out.println(database.checkTable(new CheckPostgresTable("henry2019")));
Collection<Table<?>> tables = database.tables();
for (Table<?> t : tables) {
	System.out.println(t.name());
	System.out.println(t.schema()); // please execute sql/generate_create_table_statement.sql
}
Thread.sleep(10000);
System.out.println(database.dropTable(new DropPostgresTable("henry2019")));
```


## create-mysql-datacontext

```
Properties props = new Properties(); 
props.put("druid.driverClassName", "com.mysql.cj.jdbc.Driver"); 
props.put("druid.url", "jdbc:postgresql://127.0.0.1:3306/mysql"); 
props.put("druid.username", "root"); 
props.put("druid.password", "xxx");
props.put("druid.initialSize", 10); 
props.put("druid.maxActive", 100);
props.put("druid.maxWait", 0);

DataContext dataContext = new MysqlDataContext(props)
System.out.println(context.checkDababase(new CheckMysqlDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.createDatabase(new CreateMysqlDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.checkDababase(new CheckMysqlDatabase("abc")));
Thread.sleep(10000);
System.out.println(context.dropDababase(new DropMysqlDatabase("abc")));
```

### create-mysql-table

```
String sql = "CREATE TABLE henry2019 (name varchar(512), namespace varchar(128), "
			+ "apigroup varchar(128), created datetime, updated datetime, "
			+ "data json, primary key(name, namespace, apigroup)) DEFAULT CHARSET=utf8";
      
Database database = context.currentDatabase();
System.out.println(database.checkTable(new CheckMysqlTable("henry2019")));
System.out.println(database.createTable(new CreateMysqlTableBuilder().sql(sql).build()));
System.out.println(database.checkTable(new CheckMysqlTable("henry2019")));
Collection<Table<?>> tables = database.tables();
for (Table<?> t : tables) {
	System.out.println(t.name());
	System.out.println(t.schema()); 
}
Thread.sleep(10000);
System.out.println(database.dropTable(new DropMysqlTable("henry2019")));
```

# docs

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

