<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>修改模板里的变量</span>";section>
    <#if section = "head">
        列表
    <#elseif section = "form">
        <i-layout>
            <i-content>


                <Form ref="searchForm" inline :label-width="70">
                    手机号：
                    <FormItem label="手机号" prop="mobile">
                        <Input
                                type="text"
                                v-model="searchForm.mobile"
                                clearable
                                placeholder="请输入手机号"
                                style="width: 200px"
                        />
                    </FormItem>
                    <br>
                    邮箱：
                    <FormItem label="邮箱" prop="email">
                        <Input
                                type="text"
                                v-model="searchForm.email"
                                clearable
                                placeholder="请输入邮箱"
                                style="width: 200px"
                        />
                    </FormItem>
                    <br>
                    <FormItem>
                        <Button @click="handleSearch" type="primary" icon="ios-search">提交</Button>
                    </FormItem>
                </Form>
            </i-content>
        </i-layout>
    </#if>
</@layout.registrationLayout>