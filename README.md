# common-api
常用的API接口调用（Java）

## 发送短信接口：短信宝
package com.xxg.commonapi.smsbao

短信宝官方网站：http://smsbao.com/

接口文档：http://smsbao.com/openapi/

注册即可使用。该接口收费，短信费用0.1元/条。为防止骚扰，默认相同号码一天只能接收4条短信。短信内容中要包含公司前面：【公司签名】，范例：【叉叉网】您的短信验证码是123456。

使用：
```
SmsbaoService smsbaoService = new SmsbaoService();
smsbaoService.setUsername("登录用户名");  // 登录用户名
smsbaoService.setPasswordMd5("登录密码MD5加密（不区分大小写）"); // 登录密码MD5加密（不区分大小写）
boolean success = smsbaoService.send("1871796xxxx", "【叉叉网】您的短信验证码是123456。"); // 手机号、短信内容
if(success) {
	System.out.println("短信发送成功");
} else {
	System.err.println("短信发送失败");
}
```
使用Spring：
```
<bean class="com.xxg.commonapi.smsbao.SmsbaoService">
	<property name="username" value="登录用户名" />
	<property name="passwordMd5" value="登录密码MD5加密（不区分大小写）" />
</bean>
```

## 查询快递跟踪：快递鸟
package com.xxg.commonapi.kdniao

快递鸟官方网站：http://www.kdniao.com/

接口文档：http://www.kdniao.com/YundanChaxunAPI.aspx

免费使用，每天不超过3000次，需要注册后才可以使用。快递公司编码：http://www.kdniao.com/file/ExpressCode.xls

使用：
```
KdniaoService kdniaoService = new KdniaoService();
kdniaoService.setApiId("API ID"); // API ID（商户ID）
kdniaoService.setApiKey("API Key"); // API Key
List<KdniaoTrace> list = kdniaoService.getTrace("YTO", "700192858115"); // 快递公司编码、快递单号
for(KdniaoTrace trace : list) {
	System.out.println(trace.getTime() + " | " + trace.getMessage());
}
```
输出：
```
2016-05-24 19:15:08 | 【广东省广州市白云区罗冲围公司】 取件人: 张定 已收件
2016-05-24 20:15:35 | 【广东省广州市白云区罗冲围公司】 已收件
2016-05-24 23:07:52 | 【广州转运中心】 已收入
2016-05-24 23:13:35 | 【广州转运中心】 已发出 下一站 【上海转运中心】
2016-05-26 02:18:40 | 【上海转运中心】 已收入
2016-05-26 04:33:15 | 【上海转运中心】 已发出 下一站 【上海市闵行区虹桥公司】
2016-05-26 07:37:59 | 【上海市闵行区虹桥公司】 已收入
2016-05-26 07:38:03 | 【上海市闵行区虹桥公司】 派件人: 朱照耀 派件中 派件员电话18321539302
2016-05-26 11:55:09 | 客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务
```
使用Spring：
```
<bean class="com.xxg.commonapi.kdniao.KdniaoService">
	<property name="apiId" value="API ID" />
	<property name="apiKey" value="API Key" />
</bean>
```

## 查询经纬度：高德
package com.xxg.commonapi.amap

接口文档：http://lbs.amap.com/api/webservice/summary/

使用：
```
AmapService amapService = new AmapService();
amapService.setKey("your key");  // Web服务API key
LatitudeAndLongitude latitudeAndLongitude = amapService.getLatitudeAndLongitude("陆家嘴");
System.out.println("陆家嘴纬度: " + latitudeAndLongitude.getLatitude());
System.out.println("陆家嘴经度: " + latitudeAndLongitude.getLongitude());
```
输出：
```
陆家嘴纬度: 31.237015
陆家嘴经度: 121.5025
```
使用Spring：
```
<bean class="com.xxg.commonapi.amap.AmapService">
	<property name="key" value="API key" />
</bean>
```