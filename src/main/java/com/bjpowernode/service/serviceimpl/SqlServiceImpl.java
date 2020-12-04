package com.bjpowernode.service.serviceimpl;

import com.bjpowernode.bean.User;
import com.bjpowernode.config.MyThreadLocal;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.SqlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 动态多数据源，这种方案我们需要自定义一个动态数据源的类，可以在程序运行过程中动态切换数据源
 * 缺点
 *   1、有很多的重复代码例如SpringBoot中的配置多个数据源的配置Bean
 *   2、切换主从进行读写操作时需要程序认为手动设置ThreadLocal中数据，这样就有可能会形成向从库写数据向主库读数据
 *   3、如果有很多个从节点，那么我们没有办法进行负载均衡
 *   4、如果某个节点崩溃我们不能切换到另外一台节点中，不能故障转移
 * 注意：
 *   如果需要解决上面的问题我们需要自己写一套管理代码来动态的切换数据源进行读写分离，故障转移以及负载均衡，因此这种
 *   方案通常我们在工作中不推荐使用
 */
@Service("sqlServiceImpl")
public class SqlServiceImpl implements SqlService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int add(User user) {
        //切换数据源为主库
        MyThreadLocal.setDataSource("master");
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> selectAll() {
        //切换数据源为从库
        MyThreadLocal.setDataSource("slave");
        return userMapper.selectAll();
    }
}

