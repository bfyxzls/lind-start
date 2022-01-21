<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>修改模板里的变量</span>";section>
    <#if section = "head">
        列表
    <#elseif section = "form">
        <a href="/f1/add">添加</a>
        <table border="1">
            <tr>
                <td>编号</td>
                <td>名称</td>
                <td>年龄</td>
                <td>操作</td>
            </tr>
        </table>

        <h1>${url!}</h1>
        <h2>${url!'非空'}</h2>
        <#if forgetPasswordAddress?has_content>
            忘记密码
        </#if>

        <i-table id="datatable1"
                 size="small"
                 :columns="columns"
                 :data="cartList"
                 stripe
                 highlight-row>
        </i-table>

        <div style="background:#eee;padding: 20px">
            <Card :bordered="false">
                <p slot="title">No border title</p>
                <p>Content of no border type. Content of no border type. Content of no border type. Content of no border type. </p>
            </Card>
        </div>
        <script>
            var cart = new Vue({
                el: '#app',
                data: function () {
                    return {
                        cartList: [
                            {id: 1, name: 'iPhone X', price: 8300.05, count: 1},
                            {id: 2, name: 'MacBook Pro', price: 18800.75, count: 3},
                            {id: 3, name: 'Mate 10 Porsche', price: 16600.00, count: 8}
                        ],
                        columns: [
                            {
                                title: '名称',
                                key: 'name'
                            },
                            {
                                title: '单价',
                                key: 'price'
                            },
                            {
                                title: '数量',
                                key: 'count'
                            },
                            {
                                title: '操作',
                                render: (h, params) => {
                                    return h('div', [
                                        h('Button', {
                                            props: {
                                                type: 'primary',
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    console.info('减少数量');
                                                    cart.reduceQuantity(params.row.id);
                                                }
                                            }
                                        }, '-'),
                                        h('Button', {
                                            props: {
                                                type: 'primary',
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    console.info('增加数量');
                                                    cart.increaseQuantity(params.row.id);
                                                }
                                            }
                                        }, '+'),
                                        h('Button', {
                                            props: {
                                                type: 'error',
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    console.info('删除当前项');
                                                    cart.deleteItem(params.row.id);
                                                }
                                            }
                                        }, '删除')
                                    ]);
                                }
                            }
                        ]
                    }
                },
                methods: {
                    reduceQuantity: function (id) {
                        for (let i = 0; i < this.cartList.length; i++) {
                            if (this.cartList[i].id === id) {
                                if (this.cartList[i].count > 1) {
                                    this.cartList[i].count--;
                                }
                                break;
                            }
                        }
                    },
                    increaseQuantity: function (id) {
                        for (let i = 0; i < this.cartList.length; i++) {
                            if (this.cartList[i].id === id) {
                                this.cartList[i].count++;
                                break;
                            }
                        }
                    },
                    deleteItem: function (id) {
                        for (let i = 0; i < this.cartList.length; i++) {
                            if (this.cartList[i].id === id) {
                                // 询问是否删除
                                this.$Modal.confirm({
                                    title: '提示',
                                    content: '确定要删除吗？',
                                    onOk: () => {
                                        this.cartList.splice(i, 1);
                                    },
                                    onCancel: () => {
                                        // 什么也不做
                                    }
                                });
                            }
                        }
                    }

                }
            });
        </script>
    </#if>
</@layout.registrationLayout>