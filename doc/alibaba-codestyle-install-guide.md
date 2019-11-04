# Eeclipse/Idea代码格式化文件——导入指南

> 参考链接: [java代码格式化模板（阿里代码规范）](http://ddrv.cn/a/235259)

## eclipse 格式化设置

1. 格式化模板导入

   - 依次点击：`Window`->`Preferences`->`Java`->`Code Style`->`Formatter`->`Import`
   - 选择`eclipse-codestyle.xml`文件确定
   - 默认在`Active profile`中选择新导入的`P3C-CodeStyle`，如未选择，请手动选择
   - 点击`Apply`完成配置

![eclipse模板文件导入](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/aqINbm.png)

2. 注释模板导入
   - 操作流程同上，`Window`->`Preferences`->`Java`->`Code Style`->`Code Templates`->`Import`
   - 勾选`Automatically add comments for new methods and types`
   - 点击`Apply`完成配置

   ![image.png](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/a2mIj2.png)
   
3. properties文件编码UTF-8

   - `Window`->`Preferences`->`General`->`Content Types`->`Text`->`Java Properties File`
   - 将`Java Properties File`及下属的所有项均设定为 `Default encoding: UTF-8`

   ![image.png](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/yAFfuy.png)

## idea 格式化配置

1. 安装EclipseCodeFormatter插件

   - 因为阿里配置文件是针对eclipse的，所有要导入配置文件需要额外安装EclipseFormatter插件
   - 在线安装，需翻墙。依次点击进入插件界面：`File`->`Settings`->`Plugins`，搜索` eclipse code formatter`，如已有插件则不需安装，如没有，点击`Search in repositories`自动搜索线上插件。

   ![在线安装EclipseCodeFormatter](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/M7vIVb.png)

   - 离线安装：下载插件文件，在`Plugins`界面中选择`Install plugin from disk`，选择已下载好的 `EclipseFormatter.zip`文件，确定并重启idea。
   - 下载地址：[krasa/EclipseCodeFormatter](https://github.com/krasa/EclipseCodeFormatter/releases)

   ![image.png](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/Jz2qaq.png)

2. 导入`eclipse-codestyle.xml`

![导入eclipse-codestyle.xml](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/BNzy2e.png)

3. 设置类的注释格式

   - 进入Settings界面 `Editor`->`File and Code Templates`->`File Header`，右侧输入注释模板

   ![设置类的注释格式](http://ddrvcn.oss-cn-hangzhou.aliyuncs.com/2019/5/bYNRJ3.png)

   - 代码片段

   ```java
   /**
   * @author  ${USER}
   * @since ${DATE} ${TIME} 
   */
   ```