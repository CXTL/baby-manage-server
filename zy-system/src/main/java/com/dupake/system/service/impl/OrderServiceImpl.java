package com.dupake.system.service.impl;

import com.dupake.common.dto.OrderDTO;
import com.dupake.common.utils.IdUtil;
import com.dupake.system.entity.Order;
import com.dupake.system.mapper.OrderMapper;
import com.dupake.system.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:59
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    private OrderMapper orderMapper;




    IdUtil idUtil = new IdUtil(1, 1, 1);

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //执行本地事务时调用，将订单数据和事务日志写入本地数据库
    @Transactional
    @Override
    public void createOrder(OrderDTO orderDTO, String transactionId) {

        //1.创建订单
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCreateTime(System.currentTimeMillis());
        order.setUpdateTime(System.currentTimeMillis());
        orderMapper.insert(order);



        logger.info("订单创建完成。{}", orderDTO);
    }


    @Override
    public Order findInfoById(Integer id) {
        return orderMapper.findInfoById(id);
    }

    @Override
    public PageInfo<Order> findList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderMapper.findList();
        PageInfo<Order> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Order insert(Order order) {
        order.setCreateTime(System.currentTimeMillis());
        order.setUpdateTime(System.currentTimeMillis());
        orderMapper.insert(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        order.setUpdateTime(System.currentTimeMillis());
        orderMapper.update(order);
        return null;
    }

    @Override
    public void deleteByIds(Integer id) {
        orderMapper.delete(id);
    }


}