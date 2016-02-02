# SpringMVC-Spring-Hibernate
整合了SpringMVC-Spring-Hibernate,RESTful风格的CRUD。     
##编程中遇到的注意事项
1. 使用注解来管理事务，配置<tx:annotation-driven/>要记得加入com.springsource.org.aopalliance包，不然报错.
2. 使用xml来管理事务，要加入com.springsource.org.aspectj.weaver包，不然404.

 
