### leferAPI
> 个人网站的对外api后台，实现一些简单的功能。
#### 一. 面向微信小程序的note api
##### 1. 实现微信帐号绑定note用户
+ /note/onLogin
```
IN:     wx.code
OUT:    token
DESC:   根据code获取openid。查询openid有没有对应的用户名，如有登录并获取note的token并返回给微信端；如没有，返回标志，促使小程序打开登录页面。
```
+ /note/login
```
IN:     wx.code,note.username,note.password
OUT:    token
DESC:   根据code获取openid。使用note<username,password>登录，并返回token
```