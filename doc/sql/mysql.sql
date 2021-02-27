-- SQL语法
-- 查询
select user_id, user_name, account from t_system_user;
-- 创建数据库
create database database_name;
-- 创建表
create table table_name(column1_name data_type constraints, columns2_name data_type constratints,...);
-- 如果不存在则创建表
create table if not exists table_name(column1_name data_type constraints, columns2_name data_type constratints,...);
-- 插入数据
INSERT INTO table_name(column1,column2,column3...) values(value1,value2,value3...);
-- 6. 数据类型, INT, DECIMAL(精确的精度),CHAR(定长),VARCHAR(变长),TEXT,DATE(YYYY-MM-DD),DATETIME(YYYY-MM-DD HH:MM:SS),TIMESTAMP
-- 7. 约束, NOT NULL, PRIMARY KEY, AUTO_INCREMENT,UNIQUE, FOREIGN KEY, CHECK(限制可以放入列中的值, 但是MSYQL不支持此约束),DEFAULT
-- 查询
SELECT column1_name, column2_name,column3_name FROM table_name;
-- 根据条件选择记录
SELECT column_list FROM table_name WHERE condition;
-- WHERE子句中允许的运算符:=,>,<,>=,<=,LIKE,IN,BETWEEN..AND..,AND, OR, 
SELECT column_list FROM table_name WHERE condition1 AND (conditon2 OR condition3);
SELECT column_list FROM table_name WHERE column_name IN (value1,value2...);
SELECT column_list FROM table_name WHERE column_name NOT IN (value1,value2...);
-- BETWEEN: 数值范围,日期范围,字符串范围
SELECT column_list FROM table_name WHERE column_name BETWEEN mix_value AND max_value;
-- 对结果排序: order by, 当指定多个排序列时，结果集首先按第一列排序，然后按第二列对该有序列表排序，依此类推。
SELECT column_list FROM table_name ORDER BY column_name ASC|DESC;
-- 限制结果集LIMIT
SELECT column_list FROM table_name LIMIT number;
-- 在LIMIT子句中设置行偏移
SELECT column_list FROM table_name LIMIT (行偏移,最大行数);
-- 检索不同的值: distinct(从结果集中删除重复的行)
SELECT DISTINCT column_list FROM table_name;
-- 更新表数据
UPDATE table_name SET column1=value1,column2=value2,column3=value3... WHERE CONDITION;
-- 从表中删除数据
DELETE FROM table_name WHERE CONDITION;
-- 清空表:truncate没有where子句并且删除后会重新创建表
truncate TABLE table_name;
-- 从数据库删除表
DROP TABLE table1_name,table2_name,...;
-- 删除数据库
DROP DATABASE database_name;
-- 联表查询: join
-- 内部联接
SELECT t1.emp_id, t1.emp_name, t1.hire_date, t2.dept_name
FROM employees AS t1 INNER JOIN departments AS t2
ON t1.dept_id = t2.dept_id ORDER BY emp_id;
-- 左外联接
SELECT t1.emp_id, t1.emp_name, t1.hire_date, t2.dept_name
FROM employees AS t1 LEFT JOIN departments AS t2
ON t1.dept_id = t2.dept_id ORDER BY emp_id;
-- 右外联接
SELECT t1.emp_id, t1.emp_name, t1.hire_date, t2.dept_name
FROM employees AS t1 RIGHT JOIN departments AS t2
ON t1.dept_id = t2.dept_id ORDER BY dept_name;
-- union运算符: 两张表的column_list字段, 顺序都要一致, 数据类型兼容. UNION 会消除重复行, 用UNION ALL则会全部展示.
SELECT column_list FROM table_name1 
UNION
SELECT column_list FROM table_name2;
-- LIKE运算符: 百分号%: 匹配任意数量的字符; 下划线_: 完全匹配一个字符; 二进制字符串加上binary会区分大小写
SELECT column_list FROM table_name WHERE name binary LIKE '_x%';
-- 修改表 alter table
-- 添加新列
ALTER TABLE table_name ADD column_name data_type CONSTRAINTS;
-- 在指定位置添加新列
ALTER TABLE table_name ADD column_name data_type CONSTRAINTS AFTER '指定列';
-- 更改列位置
ALTER TABLE table_name MODIFY mod_column_name data_type AFTER column_name(指定列);
-- 添加约束
ALTER TABLE table_name ADD unique(column_name,...);
ALTER TABLE table_name ADD PRIMARY key(column_name,...);
-- 删除列
ALTER TABLE table_name DROP column column_name;

-- 更改列的数据类型
-- mysql不支持alter column
ALTER TABLE table_name ALTER COLUMN column_name new_data_type;
-- mysql支持modify
ALTER TABLE table_name MODIFY column_name new_data_type [可以在最后添加约束];
-- 重命名表
ALTER TABLE current_table_name RENAME new_table_name;
RENAME TABLE current_table_name to new_table_name
-- group by子句: group by与select语句和聚合函数结合使用. 在SQL SELECT语句中，GROUP BY子句必须出现在FROM和WHERE子句之后，并且出现在ORDER BY之前。
SELECT t1.dept_name, count(t2.emp_id) AS total_employees
FROM departments AS t1 LEFT JOIN employees AS t2
ON t1.dept_id = t2.dept_id
GROUP BY t1.dept_name;
-- HAVING子句: 根据条件过滤组, having通常与group by一起使用, 以指定组或者集合的过滤条件, 并且只能用在select语句中
-- SELECT查询可以包含WHERE和HAVING子句，但是在这种情况下，WHERE子句必须出现在GROUP BY子句之前，而HAVING子句必须出现在GROUP BY子句之后但在ORDER BY子句之前。
SELECT t1.dept_name, count(t2.emp_id) AS total_employees
FROM departments AS t1 LEFT JOIN employees AS t2
ON t1.dept_id = t2.dept_id
GROUP BY t1.dept_name
HAVING total_employees = 0;

-- SQL创建视图(CREATE VIEW)
-- 使用CREATE VIEW创建视图
CREATE VIEW view_name AS select_statement;
-- 视图创建举例
CREATE VIEW emp_dept_view AS
SELECT t1.emp_id, t1.emp_name, t2.dept_name
FROM employees AS t1 LEFT JOIN departments AS t2
ON t1.dept_id = t2.dept_id;
-- 替换现有视图
CREATE OR REPLACE view view_name AS select_statement;
-- 删除视图
DROP VIEW view_name;
-- 创建索引 create index
CREATE INDEX index_name ON table_name(column_name);
-- 显示指定表上的可用索引
SHOW indexs FROM table_name;
-- 创建多列索引
CREATE INDEX index_name ON table_name(column1,column2);
-- 删除索引
DROP INDEX index_name ON table_name;
-- 日期和时间: DATE(YYYY-MM-DD), TIME(HH:MM:SS),DATETIME(YYYY-MM-DD HH:MM:SS),TIMESTAMP(YYYY-MM-DD HH:MM:SS),YEAR(YYYY)
-- now(), current_timestamp: 插入当前时间戳 
-- 提取日期或时间的一部分
SELECT year(birth_date) FROM persons;
-- 格式化日期或时间
SELECT date_format(birth_date, '格式') FROM persons;
-- SQL复制表
-- 1. 创建空表
CREATE TABLE new_table LIKE original_table;
-- 2. 将数据插入表
INSERT INTO new_table SELECT * FROM original_table;
-- 如果只想从另一个表创建一个表而不考虑任何列属性和索引，则可以使用简单的一行语句：
CREATE TABLE new_table SELECT * FROM original_table;
-- 创建临时表
CREATE TEMPORARY table_name(...);
-- 创建现有表的临时副本
CREATE TEMPORARY TABLE temp_name SELECT * FROM table_name;
-- 删除临时表
DROP TEMPORARY TABLE table_name;
-- SQL注入: 用户输入  ' OR 'x'='x ; 通过sql注入可以获取表所有的数据
SELECT * FROM users WHERE username='' OR 'x'='x' AND password='' OR 'x'='x';









-- 登录mysql服务器并使用. 使用数据库demo
-- 1. 登录: mysql -u root -p
-- 2. 创建数据库: 创建名叫demo的数据库
create database demo;
-- 3. 避免创建已存在的数据库
create database if not exists demo;
-- 4. 选择数据库
use demo;
-- 5. 创建表
create table persons (
	id int not null primary key auto_increment,
	name varchar(50) not null,
	birth_date date,
	phone varchar(15) not null UNIQUE,
	salary int NOT NULL check(salary >= 3000 AND salary <=10000)
	country varchar(30) NOT NULL DEFAULT 'China',
	role_id int NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles(id);
);

-- 6. 插入数据
INSERT INTO persons(name, birth_date,phone,salary,country, role_id) values('mon', '2001-01-05','15623652365',3000,'china', 1);

-- mysql高级函数用法
-- 用group_concat()函数列转行
SELECT TYPE, group_concat(user_id SEPARATOR '&') FROM t_system_log GROUP BY `type`;
-- CASE WHEN 用法
-- 1. 简单函数: CASE [column_name] WHEN [value1] THEN [result]...ELSE [default] END
SELECT account, 
CASE brh_id 
WHEN '0099990000' THEN '管理机构'
WHEN '0229003027' THEN '运营机构' 
ELSE '合作机构' END AS '机构类型' FROM t_system_user;
-- 2. 搜索函数: CASE WHEN [expression] THEN [result1] ... ELSE [default] END 
SELECT account,
CASE WHEN login_count =0 THEN '未登录' 
WHEN login_count >0 AND login_count <300  THEN '经常登录'
WHEN login_count > 400 THEN '频繁登录' END  AS '登录频次'
FROM t_system_user;
