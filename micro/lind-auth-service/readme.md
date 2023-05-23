# Spring Security对OAuth2提供了默认可访问端点，即URL

* /oauth/authorize    :申请授权码code，涉及类   AuthorizationEndpoint   
* /oauth/token    :获取令牌token，涉及类   TokenEndpoint   
* /oauth/check_token    :用于资源服务器请求端点来检查令牌是否有效，涉及类   CheckTokenEndpoint   
* /oauth/confirm_access    :用于确认授权提交，涉及类   WhitelabelApprovalEndpoint   
* /oauth/error    :授权错误信息，涉及   WhitelabelErrorEndpoint   
* /oauth/token_key    :提供公有密匙的端点，使用JWT令牌时会使用，涉及类   TokenKeyEndpoint   

# 数据表说明
oauth_client_details ：客户端账号密码、授权、回调地址等重要信息；核心表。
oauth_access_token ：存储access_token。
oauth_refresh_token ：存储refresh_token。
oauth_client_token ：存储从服务端获取的token数据。
oauth_code ：存储授权码。
oauth_approvals ：存储授权成功的客户端信息。

# oauth_client_details表字段说明 
|字段名 | 字段约束 | 详细描述 | 范例 | 
|:---|:---|:---|:---|
|client_id | 主键，必须唯一，不能为空 | 用于唯一标识每一个客户端(client)；注册时必须填写(也可以服务端自动生成)，这个字段是必须的，实际应用也有叫app_key | OaH1heR2E4eGnBr87Br8FHaUFrA2Q0kE8HqZgpdg8Sw |
|resource_ids | 可以为空 | 客户端所能访问的资源id集合，多个资源时用逗号(,)分隔，如：unity-resource,unity-resource2。 | unity-resource |
|client_secret | 不能为空 | 用于指定客户端(client)的访问密匙 | 123456 |
|scope | 可以为空 | 指定客户端申请的权限范围，可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔 | read,write |
|authorized_grant_types | 可以为空，若为空则默认值为"authorization_code,refresh_token" | 指定客户端支持的grant_type，可选值包括authorization_code,password,refresh_token,implicit,client_credentials,若支持多个grant_type用逗号(,)分隔 | authorization_code,password,refresh_token |
|web_server_redirect_uri | 可以为空 | 客户端的重定向URI，可为空，但是如果存在多个URI，必须全部都进行注册 | http://www.baidu.com |
|authorities | 可以为空 | 指定客户端所拥有的Spring Security的权限值，可选值包括read,write，若有多个权限值，用逗号(,)分隔 | read,write |
|access_token_validity | 可以为空，若为空则默认值为12小时 | 设定客户端的access_token的有效时间值(单位:秒)，若不设定值则使用默认值(60*60*12, 12小时) | 3600 |
|refresh_token_validity | 可以为空，若为空则默认值为30天 | 设定客户端的refresh_token的有效时间值(单位:秒)，若不设定值则使用默认值(60*60*24*30, 30天) | 2592000 |
|additional_information | 可以为空 | 在注册时可以附加一些额外的信息，在随后的处理中可以读取使用 | null |
|autoapprove | 可以为空 | true表示需要用户通过授权操作同意授权，false则直接授权成功返回授权码 | true |
