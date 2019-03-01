# Online-experiment

## 学生在线实验系统  

### 设计目的
1. 为在校学生实验提供便利。
2. 为老师提供自动化，批量化得处理实验。

### 设计思路
1. 客户端采用微信小程序（1.0版）和网页（2.0版）。
2. 服务端主要采用springboot和springcloud。

### 设计计划

 3.1-3.3 初步系统功能设计。  
 3.4-3.7 微信小程序界面设计。  
 3.8-3.15 微信小程序接口。  
 3.15-3.16 搭建springboot模块和服务器环境。  
 3.17-3.18 数据表创建
 3.18-3.20 用户登录和用户信息保存维护。  
 
 ### 系统设计

 #### 主要功能

 学生界面（小程序）：  
 1. 学生登录（v1.0）
 2. 学生微信头像显示。（v1.0）
 3. 课程列表显示。（v1.0）
 4. 实验列表显示。（v1.0）
 5. 讨论页面。（v2.0）
 6. 实验详细（v1.0）

 教师页面：（v2.0）  
 1. 发布实验到指定班级。
 2. 指定班级所有学生实验。
 3. 检索指定班级和指定学生。
 4. 批改实验并打分

 #### 小程序功能实现流程图

 1. 学生登录功能

 ![学生登录功能](https://github.com/whoisje/Online-experiment/blob/master/image/%E7%99%BB%E5%BD%95%E6%B5%81%E7%A8%8B.png)

 2. 学生课程实验列表获取与实验状态显示

 ![课程实验列表获取](https://github.com/whoisje/Online-experiment/blob/master/image/%E5%AE%9E%E9%AA%8C%E5%88%97%E8%A1%A8.png)  
 
 3. 实验内容与学生已经填写的部分获取
 
 ![实验内容获取](https://github.com/whoisje/Online-experiment/blob/master/image/%E5%AE%9E%E9%AA%8C%E5%86%85%E5%AE%B9%E8%8E%B7%E5%8F%96.png)
 
 5. 在线运行代码
 
 ![在线运行代码](https://github.com/whoisje/Online-experiment/blob/master/image/%E5%9C%A8%E7%BA%BF%E6%89%A7%E8%A1%8C%E4%BB%A3%E7%A0%81.png)
 
 6. 保存与提交
 
 ![保存与提交](https://github.com/whoisje/Online-experiment/blob/master/image/%E6%8F%90%E4%BA%A4%E4%BB%A3%E7%A0%81.png)
 
 7. 老师处理结果  
