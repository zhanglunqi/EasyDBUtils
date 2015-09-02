#EasyDBUtils

	软件说明：
	EasyDBUtils是对DBUtils的功能封装

	主要功能有：
	简化了事务管理————只需要在业务层接口中需要管理事务的方法上进行@StartTransaction的注解声明，EasyDBUtils就会自动的帮你管理事务
	简化了持久层的代码————以后在写持久层代码的时候不用在持久层里面传入DataSource或者是Connection了，也都是EasyDBUtils帮你完成。
	以后在你写的代码中再也不会出现Connection和DataSource了！！！

	使用方法：

	====准备工作和配置====

	1.实现EasyDataSource接口，完成与数据库连接池的对接，主要是为了给EasyDBUtils自动获取到DataSource和Connection提供帮助
	2.在src的根目录下创建两个配置文件  easyDBUtilsDao.properties  以及  easyDBUtilsService.properties  
	easyDBUtilsService.properties 中的key是业务层的接口名称，value是业务层的实现类的具体路径
	easyDBUtilsDao.properties     中的key是持久层的接口名称，value是持久层的实现类的具体路径

	====如何使用=====

	核心类：
	EasyFactory：用于创建业务层对象和持久层对象以及创建Runner对象
	Runner：用于替代DBUtils的QueryRunner
	
	创建业务层对象的方法
	EasyFactory.newServiceInstance(业务层接口.class)；

	创建持久层对象的方法
	EasyFactory.newDaoInstance(业务层接口.class)；

	如何开启事务
	将@StratTransaction注解标记在需要开启事务的业务层接口的方法上

	如何执行持久层
	EasyFactory.getRunner().XXXXXX;
	XXXXX的用法跟DBUtils一样，只是不用传Connection参数了。

	====请大家关注我的博客（写的不多，还在整理）==
	http://my.oschina.net/lunqi/blog

	===虽然这是个小东西，如果大家觉得感兴趣，可以加这个群，我们一起讨论==
	QQ群： 481599768   

	===我喜欢做一点轻量级的框架用来方便开发，如果你也喜欢，请联系我，我们一起玩，公司找人的也可以找我，我还在找工作中。。。===
	QQ:876986919

	===Author====
	张伦琦-Carl
	lunqi
	876986919


	