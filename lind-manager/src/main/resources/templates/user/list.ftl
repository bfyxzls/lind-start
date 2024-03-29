<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>修改模板里的变量</span>";section>
    <#if section = "head">
        列表
    <#elseif section = "form">
        <div id="app">
            <p>${list}</p>
            <i-layout>
                <i-content :style="{margin: '20px', minHeight: '220px'}">
                    <div class="search">
                        <Card @keydown.enter.native="handleSearch">
                            <Row>
                                <Form ref="searchForm" inline :label-width="70">

                                    <FormItem label="手机号" prop="mobile">
                                        <Input
                                                type="text"
                                                v-model="searchForm.mobile"
                                                clearable
                                                placeholder="请输入手机号"
                                                style="width: 200px"
                                        />
                                    </FormItem>
                                    <FormItem label="邮箱" prop="email">
                                        <Input
                                                type="text"
                                                v-model="searchForm.email"
                                                clearable
                                                placeholder="请输入邮箱"
                                                style="width: 200px"
                                        />
                                    </FormItem>
                                    <FormItem label="性别" prop="sex">
                                        <Select v-model="searchForm.sex" placeholder="请选择" clearable
                                                style="width: 200px">
                                            <Option v-for="(item, i) in dictSex" :key="i" :value="item.value">
                                                {{item.title}}
                                            </Option>
                                        </Select>
                                    </FormItem>
                                    <FormItem label="登录账号" prop="username">
                                        <Input
                                                type="text"
                                                v-model="searchForm.username"
                                                clearable
                                                placeholder="请输入登录账号"
                                                style="width: 200px"
                                        />
                                    </FormItem>
                                    <FormItem>
                                        <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
                                    </FormItem>
                                </Form>
                            </Row>
                        </Card>
                    </div>

                    <i-table border :columns="columns" :data="data"></i-table>
                </i-content>
            </i-layout>
        </div>

        <script type="text/javascript">
            /*<![CDATA[*/
            var myHome = new Vue({
                el: '#app',
                data() {
                    return {
                        dictSex: [{title: "男", value: "0"}, {title: "女", value: "1"}],
                        searchForm: {
                            username: "",
                            mobile: "",
                            email: "",
                            sex: "0"
                        },
                        columns: [
                            {
                                title: 'Name',
                                key: 'name'
                            },
                            {
                                title: 'Address',
                                key: 'address'
                            },
                            {
                                title: "操作",
                                key: "action",
                                width: 200,
                                align: "center",
                                fixed: "right",
                                render: (h, params) => {
                                    let enableOrDisable = "";
                                    if (params.row.age % 2 == 0) {
                                        enableOrDisable = h(
                                            "Button",
                                            {
                                                props: {
                                                    size: "small"
                                                },
                                                style: {
                                                    marginRight: "5px"
                                                },
                                                on: {
                                                    click: () => {
                                                        this.disable(params.row);
                                                    }
                                                }
                                            },
                                            "禁用"
                                        );
                                    } else {
                                        enableOrDisable = h(
                                            "Button",
                                            {
                                                props: {
                                                    type: "success",
                                                    size: "small"
                                                },
                                                style: {
                                                    marginRight: "5px"
                                                },
                                                on: {
                                                    click: () => {
                                                        this.enable(params.row);
                                                    }
                                                }
                                            },
                                            "启用"
                                        );
                                    }

                                    return h("div", [
                                        h(
                                            "Button",
                                            {
                                                props: {
                                                    type: "primary",
                                                    size: "small"
                                                },
                                                style: {
                                                    marginRight: "5px"
                                                },
                                                on: {
                                                    click: () => {
                                                        this.edit(params.row);
                                                    }
                                                }
                                            },
                                            "编辑"
                                        ),
                                        enableOrDisable,
                                        h(
                                            "Button",
                                            {
                                                props: {
                                                    type: "error",
                                                    size: "small"
                                                },
                                                on: {
                                                    click: () => {
                                                        this.remove(params.row);
                                                    }
                                                }
                                            },
                                            "删除"
                                        )
                                    ]);
                                }
                            }

                        ],
                        data: ${list} // 后端的集合
                    }
                },
                methods: {
                    info(nodesc) {
                        this.$Notice.info({
                            title: '这是标题',
                            desc: nodesc ? '' : '这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容'
                        });
                    },
                    success(nodesc) {
                        this.$Notice.success({
                            title: '这是标题',
                            desc: nodesc ? '' : '这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容'
                        });
                    },
                    warning(nodesc) {
                        this.$Notice.warning({
                            title: '这是标题',
                            desc: nodesc ? '' : '这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容'
                        });
                    },
                    error(nodesc) {
                        this.$Notice.error({
                            title: '这是标题',
                            desc: nodesc ? '' : '这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容'
                        });
                    },
                    edit(v) {
                        this.$Modal.confirm({
                            title: "确认启用",
                            content: "您确认要启用用户 " + v.username + " ?",
                            loading: true,
                            onOk: () => {
                                this.$Modal.remove();
                                this.$Message.success("操作成功");
                            }
                        });
                    },
                    remove(v) {
                        this.$Message.success("操作成功");
                    },
                    enable(v) {
                        this.$Message.success("操作成功");
                    },
                    disable(v) {
                        this.$Message.success("操作成功");
                    },
                    handleSearch() {
                        this.$Message.success("操作成功");
                    }

                }
            })
            /*]]>*/
        </script>
    </#if>
</@layout.registrationLayout>