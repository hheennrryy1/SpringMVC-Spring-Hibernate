# SpringMVC-Spring-Hibernate
##介绍
整合了SpringMVC-Spring-Hibernate,RESTful风格的CRUD。   
##编程中遇到的注意事项
###事务
1. 使用注解来管理事务，配置<tx:annotation-driven/>要记得加入com.springsource.org.aopalliance包，不然报错。
2. 使用xml来管理事务，要加入com.springsource.org.aspectj.weaver包，不然404但不报任何错误。
3. 通常采用xml的方式配置事务。
 
###Integer和int
```@RequestParam(value="id",required=false)Integer id```会赋为null,因此这里id类型为int时会报错。



 
