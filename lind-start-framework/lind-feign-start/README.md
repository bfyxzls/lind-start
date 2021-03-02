# 说明
1. 主要对feign的请求进行拦截，在请求头添加token信息
2. 自定义feign的隔离策略，实现了feign请求头的转发token,否则你的隔离级别只能是信号量SEMAPHORE