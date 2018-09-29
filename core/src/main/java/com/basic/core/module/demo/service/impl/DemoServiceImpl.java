package com.basic.core.module.demo.service.impl;

import com.basic.core.module.demo.entity.Demo;
import com.basic.core.module.demo.dao.DemoDao;
import com.basic.core.module.demo.service.DemoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * demo表 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-09-26
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoDao, Demo> implements DemoService {

}
