# 1.环境要求
+ OS：支持Mac/Linux/Windows及一切支持Java运行环境的操作系统
+ Java版本：JDK8及以上
+ 数据库：MySQL数据库（5.7及以上）
-----
# 2.项目信息
## 项目技术
+ `SpringBoot 2（项目骨架，基于此框架开发的）`
+ `Spring Data JPA（负责DDL和一些简单的DML）`
+ `Mybatis（负责一些复杂的关系映射动态查询） `
+ `Hutool 4.1.1（超级强大的工具类）`
+ `Maven 3（项目管理工具和编译工具）`
+ `Lombok（精简代码量）`
+ `Layui 2.2.3（前端UI/JS框架，美观大气UI）`
+ `Vue.js（前端js框架，MVVM模式，负责DOM/数据处理以及组件化html）`
+ `vipspa（前端js路由，单页面体验较好）`
## 功能展示
+ 待完善...

-----
# 3.更新日志
## 说明
+ [^]：更新/修复的功能，[+]：新增的功能，[×]：移除的功能

### v4.0.0(20180707)
+ [^] 更新`SpringBoot`至最新版 2.0
+ [^] 更新`layui`版本至最新 2.3
+ [^] 更新根目录包名更改为 \[*.v4\] 
+ [^] 更新针对SpringBoot2的改动进行优化处理
+ [+] 新增初始化配置页面，方便上手配置
+ [+] 新增后台一些项目的统计
+ [+] 新增页面类型 （我的项目展示）
+ [+] 新增对每个文章的自定义URL的功能
+ [+] 新增对七牛云文件上传的支持（10G免费），也可配置本地上传
+ [+] 新增基本权限控制操作，新系统内部实现了基于AOP注解的一套基础的权限管理，方便网站扩展访客以及管理权限（目前包含网站管理员和网站访客）
+ [+] 新增友情链接功能，同时首页支持友情链接模块面板显示
+ [+] 新增功能中心模块，支持展示自定义功能（如额外的功能、链接都可放到此处展示\[此处有图标展示，可超链接\]）
+ [+] 新增对首页的面板的顺序功能进行配置的功能，同时支持每块面板的显隐操作
+ [+] 新增对首页顶栏右侧的菜单控制功能（显示/隐藏，排序等）
+ [×] 移除对`h2`数据库的支持
+ [×] 移除`template`模块，改用`Spring Data JPA`和`Mybatis`组合
### v3.3.1(20180407)
+ 修复发布笔记页面和关于内容页面的富文本编辑器加载失败的BUG
+ 修复用户管理不能搜索的BUG
+ 修复博文管理中浏览数显示错误的BUG
+ 增加笔记管理中点击标题可以直接访问笔记前端显示内容的功能
+ 加强评论的sql和xss过滤条件
### v3.3.0(20180324)
+ 修复修改文章封面会同时修改管理员头像的BUG
+ 修复某些手机浏览器上页面显示不全的BUG
+ 重新加入mysql，现在可以h2/MySQL二选一啦
### v3.2.0(20180317)
+ 更新相关模块依赖为最新1.11.0.RELEASE版
+ 扩大参数表的值字段的长度
+ 数据库由独立mysql改为Embedded H2 ，方便部署和备份
+ 首页\[网站信息\]面板和\[会员登录\]面板显示可调整顺序设定
+ 调整优化相关代码，解决部分潜在的问题
+ 修复留言的xss漏洞
### v3.1.0(20180226)
+ 新增 留言 功能，右下角的 fixBar 图标点击进入留言板块页面
+ 更新 `template` 依赖至最新快照版本
+ 优化验证评论开放是否的代码，防止恶意评论
+ 修复管理员回复时用户名颜色错误以及认证标识未显示问题
+ 修复评论偶尔不能正确屏蔽的问题
+ 修复后台管理中评论管理内容偶尔显示有误的问题
### v3.0.2(20180224)
+ 优化文章置顶的排序代码
+ 修复文章浏览数统计和点赞统计问题
+ 优化编辑文章时候的Nkeditor的默认字体
### v3.0.1(20180223)
+ 更新相关依赖模块至最新
+ 修改后台编辑器(NKeditor)初始化默认字体样式，以及默认字体大小为14px
+ 修正日志文件大小分割功能
+ 修正页脚在内容不是很多时的位置显示问题
+ 优化部分代码
### v3.0.basic(20180216)
+ 笔记博客v3第一版发布