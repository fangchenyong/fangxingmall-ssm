# 方兴

| 框架           | 版本 |
| -------------- | ---- |
| SSM            | 1.0  |
| SpringBoot升级 | 2.0  |
| SpringCloud    | 3.0  |

## 一、环境搭建

## 二、数据库及接口

| 表名          | 描述       |
| ------------- | ---------- |
| tb_user       | 用户表     |
| tb_category   | 分类表     |
| tb_product    | 产品表     |
| tb_cart       | 购物车表   |
| tb_pay_info   | 支付信息表 |
| tb_order      | 订单表     |
| tb_order_item | 订单明细表 |
| tb_shipping   | 收货地址表 |

1. tb_user

    ```mysql
    DROP TABLE IF EXISTS `tb_user`;
    CREATE TABLE `tb_user` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
      `username` varchar(50) NOT NULL COMMENT '用户名',
      `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
      `email` varchar(50) DEFAULT NULL,
      `phone` varchar(20) DEFAULT NULL,
      `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
      `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
      `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
      `create_time` datetime NOT NULL COMMENT '创建时间',
      `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `user_name_unique` (`username`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
    ```

2. tb_category

    ```mysql
    DROP TABLE IF EXISTS `tb_category`;
    CREATE TABLE `tb_category` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
      `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
      `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
      `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
      `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8
    ```

3. tb_product

    ```mysql
    DROP TABLE IF EXISTS `tb_product`;
    CREATE TABLE `tb_product` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
      `category_id` int(11) NOT NULL COMMENT '分类id,对应tb_category表的主键',
      `name` varchar(100) NOT NULL COMMENT '商品名称',
      `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
      `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
      `sub_images` text COMMENT '图片地址,json格式,扩展用',
      `detail` text COMMENT '商品详情',
      `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
      `stock` int(11) NOT NULL COMMENT '库存数量',
      `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
    ```

4. tb_cart

    ```mysql
    DROP TABLE IF EXISTS `tb_cart`;
    CREATE TABLE `tb_cart` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) NOT NULL,
      `product_id` int(11) DEFAULT NULL COMMENT '商品id',
      `quantity` int(11) DEFAULT NULL COMMENT '数量',
      `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`),
      KEY `user_id_index` (`user_id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;
    ```

5. tb_pay_info

    ```mysql
    DROP TABLE IF EXISTS `tb_pay_info`;
    CREATE TABLE `tb_pay_info` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) DEFAULT NULL COMMENT '用户id',
      `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
      `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
      `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
      `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
    ```

6. tb_order

    ```mysql
    DROP TABLE IF EXISTS `tb_order`;
    CREATE TABLE `tb_order` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
      `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
      `user_id` int(11) DEFAULT NULL COMMENT '用户id',
      `shipping_id` int(11) DEFAULT NULL,
      `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
      `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
      `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
      `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
      `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
      `send_time` datetime DEFAULT NULL COMMENT '发货时间',
      `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
      `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
    ```

7. tb_order_item

    ```mysql
    DROP TABLE IF EXISTS `tb_order_item`;
    CREATE TABLE `tb_order_item` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
      `user_id` int(11) DEFAULT NULL,
      `order_no` bigint(20) DEFAULT NULL,
      `product_id` int(11) DEFAULT NULL COMMENT '商品id',
      `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
      `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
      `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
      `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
      `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
      `create_time` datetime DEFAULT NULL,
      `update_time` datetime DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `order_no_index` (`order_no`) USING BTREE,
      KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
    ```

8. tb_shipping

    ```mysql
    DROP TABLE IF EXISTS `tb_shipping`;
    CREATE TABLE `tb_shipping` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) DEFAULT NULL COMMENT '用户id',
      `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
      `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
      `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
      `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
      `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
      `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
      `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
      `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
      `create_time` datetime DEFAULT NULL,
      `update_time` datetime DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
    ```

## 三、项目初始化

- pom引入jar包依赖
- 创建目录
- 配置Mybatis逆向工程配置文件
- 生成dao、pojo和mapper文件
- 配置Spring
- 配置日志
- 配置ftp服务器（可以考虑替换成OSS）

## 四、用户模块

### 4.1、登录

1. 接口地址：**/manage/user/login.do**

2. 请求参数：`username`,`password`

3. 返回参数：

    - success

        ```json
        {
            "status": 0,
            "data": {
                "id": 12,
                "username": "aaa",
                "email": "aaa@163.com",
                "phone": null,
                "role": 0,
                "createTime": 1479048325000,
                "updateTime": 1479048325000
            }
        }
        ```

    - fail

        ```json
        {
            "status": 1,
            "msg": "密码错误"
        }
        ```

### 4.2、用户验证

1. 接口地址：**/manage/user/check_vaild.do**

2. 请求参数：`str`,`type`

    str指的是具体参数内容，type指的是参数类型

3. 返回参数：

    - success

        ```json
        {
            "status": 0,
            "msg": "校验成功"
        }
        ```

    - fail

        ```json
        {
        	"status": 1,
            "msg": "用户已存在"
        }
        ```

### 4.3、注册

1. 接口地址：**/manage/user/register.do**

2. 请求参数：`username`,`password`,`email`,`phone`,`question`,`answer`

3. 返回参数：

    - success

        ```json
        {
            "status": 0,
            "msg": "注册成功"
        }
        ```

    - fail

        ```json
        {
            "status": 0,
            "msg": "用户已存在"
        }
        ```

### 4.4、忘记密码

1. 接口地址：**/manage/user/forget_get_question.do**

2. 请求参数：`username`

3. 返回参数：

    - success

        ```json
        {
            "status": 0,
            "data": "问题"
        }
        ```

    - fail

        ```json
        {
        	"status": 1,
            "msg": "该用户未设置找回密码问题"
        }
        ```

### 4.5、提交问题答案

1. 接口地址：**/manage/user/forget_check_answer.do**

2. 请求参数：`username`,`question`,`answer`

3. 返回参数：

    - success

        ```json
        {
            "status": 0,
            "data": "a23741d9-63d3-4a41-b6bc-b014566ada9d"
        }
        ```

    - fail

        ```json
        {
            "status": 1,
            "msg": "答案错误"
        }
        ```

### 4.6、重置密码

- 未登录状态充值密码

    1. 接口地址：**/manage/user/forget_reset_password.do**

    2. 请求参数：username`,`passwordNew`,`forgetToken`

    3. 返回参数：

        - success

            ```json
            {
                "status": 0,
                "msg": "密码修改成功"
            }
            ```

        - fail

            ```json
            {
                "status": 1,
                "msg": "密码修改失败"
            }
            
            {
                "status": 1,
                "msg": "token已经失效"
            }
            ```

- 登录状态重置密码

    1. 接口地址：**/manage/user/list.do**

    2. 请求参数：`passwordOld`,`passwordNew`

    3. 返回参数：

        - success

            ```json
            {
                "status": 0,
                "msg": "密码修改成功"
            }
            ```

        - fail

            ```json
            {
                "status": 1,
                "msg": "旧密码输入错误"
            }
            ```

### 4.7、获取用户信息

1. 接口地址：**/manage/user/list.do**

2. 请求参数：`pageSize`,`pageNum`

3. 返回参数：

    - success

        ```
        {
            "status": 0,
            "data": {
                "pageNum": 1,
                "pageSize": 1,
                "size": 1,
                "orderBy": null,
                "startRow": 1,
                "endRow": 1,
                "total": 16,
                "pages": 6,
                "list": [
                    {
                        "id":1,
                        "username":"admin",
                        "password":"123456",
                        "email":"admin@qq.com",
                        "phone":"15011111111",
                        "question":"问题",
                        "answer":"没有",
                        "role":0,
                        "createTime":1489719093000,
                        "updateTime":1513682138000
                    }
                ],
                "firstPage": 1,
                "prePage": 0,
                "nextPage": 2,
                "lastPage": 6,
                "isFirstPage": true,
                "isLastPage": false,
                "hasPreviousPage": false,
                "hasNextPage": true,
                "navigatePages": 8,
                "navigatepageNums": [
                  1,
                  2,
                  3,
                  4,
                  5,
                  6
                ]
            }
        }
        ```

    - fail

        ```json
        {
          "status": 10,
          "msg": "用户未登录,请登录"
        }
        
        {
          "status": 1,
          "msg": "没有权限"
        }
        ```

### 4.8、更新用户信息

1. 接口地址：**/manage/user/update_information.do**

2. 请求参数：`email`,`phone`,`question`,`answer`

3. 返回参数：

    - success

        ```json
        {
          "status": 0,
          "msg": "更新成功"
        }
        ```

    - fail

        ```json
        {
          "status": 1,
          "msg": "用户未登录"
        }
        ```

### 4.9、退出登录

1. 接口地址：**/manage/user/logout.do**

2. 请求参数：无

3. 返回参数：

    - success

        ```json
        {
          "status": 0,
          "msg": "退出成功"
        }
        ```

    - fail

        ```json
        {
          "status": 1,
          "msg": "服务端异常"
        }
        ```

## 五、分类模块

### 5.1、获取节点

1. 接口地址：**/manage/user/logout.do**
2. 请求参数：无
3. 返回参数：
    - success
    - fail

### 5.2、增加节点

1. 接口地址：**/manage/user/logout.do**
2. 请求参数：无
3. 返回参数：
    - success
    - fail

### 5.3、修改名字

1. 接口地址：**/manage/user/logout.do**
2. 请求参数：无
3. 返回参数：
    - success
    - fail

### 5.4、获取分类ID

1. 接口地址：**/manage/user/logout.do**
2. 请求参数：无
3. 返回参数：
    - success
    - fail

### 5.5、递归子节点ID

1. 接口地址：**/manage/user/logout.do**
2. 请求参数：无
3. 返回参数：
    - success
    - fail

## 六、商品模块

## 购物车模块

## 收货地址模块

## 支付模块

## 订单模块

## 云服务器发布