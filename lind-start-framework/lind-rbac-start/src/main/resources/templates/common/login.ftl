
<#import "../template_login.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>修改模板里的变量</span>";section>
<#if section = "head">
登录
<#elseif section = "form">
<div id="login" class="flex" style="padding: 10% 10%;">
    <!-- 实例化对象里面可以开始使用iview标签了（注意更改） -->
    <Card style="width:350px">
        <h1 style="color: #2d8cf0;">Login</h1>
        <br>
        <!-- 下面的:model和:rules要修改的话记得修改vue中的data() -->
        <i-form ref="form" :model="form" :rules="ruleInline" inline method="POST">
            <form-item prop="username">
                <poptip trigger="focus" placement="right">
                    <i-input type="text" clearable v-model="form.username" placeholder="Username" size="large">
                        <icon type="ios-person-outline" slot="prepend"></icon>
                    </i-input>
                    <div slot="content">请输入用户名</div>
                </poptip>
            </form-item>
            <br>
            <form-item prop="password">
                <poptip trigger="focus" placement="right">
                    <i-input type="password" password v-model="form.password" placeholder="Password" size="large">
                        <icon type="ios-lock-outline" slot="prepend"></icon>
                    </i-input>
                    <div slot="content">请输入密码</div>
                </poptip>
            </form-item>
            <br>
            <form-item>
                <i-button type="primary" size="large" @click="handleSubmit('form')">登录</i-button>
            </form-item>
        </i-form>
    </card>
</div>
<script>
    new Vue({
        el: '#login',
        data() {
            return {
                form: {
                    username: '',
                    password: ''
                },
                // 下面是验证方法
                ruleInline: {
                    username: [{
                        required: true,
                        message: 'Please fill in the user name',
                        trigger: 'blur'
                    }],
                    password: [{
                        required: true,
                        message: 'Please fill in the password.',
                        trigger: 'blur'
                    }, {
                        type: 'string',
                        min: 6,
                        message: 'The password length cannot be less than 6 bits',
                        trigger: 'blur'
                    }],
                }
            }
        },
        methods: {
            handleSubmit(name) {
                // 表单验证并弹窗提示
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.submit(); //执行提交函数
                        this.$Message.success('Success!');
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
            },
            handleReset(name) {
                // 复位表单
                this.$refs[name].resetFields();
            },
            submit() {
                // 这里使用axios将数据传到后台
                axios.post('/login', {
                    'data': this.$data.form,
                })
                    .then(res => {
                        // 这里的后台返回的是js字符串，用一下方式执行
                        // 当然你也可以换其他方法
                        // 将js字符串代码放入new Function()
                        var js = new Function(res.data);
                        // 调用触发
                        js();
                    }).catch(function(error) {
                    console.log(error);
                });
            }
        }
    })
</script>
</#if>
</@layout.registrationLayout>