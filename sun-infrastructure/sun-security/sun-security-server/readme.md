令牌获取方式
1. 客户端模式 ：客户端以自己的身份申请令牌
> http://127.0.0.1:9004/security/oauth/token?grant_type=client_credentials&client_id=sun-user&client_secret=sun-user
2. 密码模式 ：第三方应用拿到用户名密码去申请令牌
> http://127.0.0.1:9004/security/oauth/token?grant_type=password&username=CLIENT_USER:sun-web&password=sun-web&client_id=sun-web&client_secret=sun-web


私钥公钥生成


### 生成私钥
`keytool -genkeypair -alias SUN -keyalg RSA -keypass SUN123456 -keystore rsa.jks -validity 365 -storepass SUN123456`

- 查看字段注释 `keytool -genkeypair --help`

私钥放在sun-security项目

### 生成公钥
`keytool -list -rfc --keystore SUN.jks | openssl x509 -inform pem -pubkey`

公钥放在其他微服务项目里

