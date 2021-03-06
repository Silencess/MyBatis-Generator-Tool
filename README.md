#### 背景
之前用了几次MyBatis Generator（MBG），功能确实强大，实体类、mapper文件，Dao层接口都能根据数据库的表逆向生成，但是修改配置文件好麻烦的有木有！

于是想着整一个图形化客户端多方便，上MBG官网看了下。官网提供了MBG的三种使用方式：

第一种就是直接使用jar包，利用java -jar命令，后面写上配置文件地址来生成；

第二种是提供了个demo，里面是个java项目，配置好配置文件，运行main方法来生成；

第三种是安装Eclipse插件来生成，我以为这个插件是图形化的，可以方便的配置各项参数，兴致冲冲的下载安装，结果发现没啥用，你还是得在XML文件中手动配置各项参数，然后针对配置文件运行插件。

很无奈，想自己弄一个，看了下里那个java项目，main方法中有主要运行方法，再看了下后面的详细实现类，发现各项配置参数都提供了public方法，那就很好弄了，于是开干，终于整出这个东西。

#### 运行截图
先看下运行效果图

![image](https://img2018.cnblogs.com/blog/1167340/201907/1167340-20190721180246250-998348553.jpg)

 配置项太多，加上我也没啥审美，暂时就整成这个样子了，不过目前使用还是挺方便的。
在命令提示符窗口执行 javaw -jar Generator.jar 命令，就能打开界面了。
然后填写一下domain和dao中的包名，选择好输出目录。填写好数据库的参数，添加要生成的表明，生成就OK了。
其他的一些参数，如是否去除注释，目标环境之类的，可根据自己的需求调整。
针对表的一些参数，例如更改生成的字段类型等，有些复杂，暂时没做详细的配置，以后有需要在做吧。
欢迎下载使用，提出建议。

#### 使用方法
下载“双击运行.bat”批处理脚本和Generator.jar文件，直接双击运行即可