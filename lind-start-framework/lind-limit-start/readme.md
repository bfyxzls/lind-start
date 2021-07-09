# 作用
实现服务整体的限流，或者某个接口的限流。
# 依赖引用
```
<dependency>
 <groupId>com.pkulaw</groupId>
 <artifactId>pkulaw-limit-start</artifactId>
 <version>1.0.0</version>
</dependency>
```
# 配置
```
pkulaw.ratelimit.enable: true #开启
pkulaw.ratelimit.limit: 100 #限制个数
pkulaw.ratelimit.timeout: 1000 #超时毫秒
```
# 使用
```
@RateLimiter
@GetMapping("limit")
public CommonResult limit() {
    System.out.println(new PageDto());
    CommonResult commonResult = CommonResult.ok("ok");
    return commonResult;
}
```
