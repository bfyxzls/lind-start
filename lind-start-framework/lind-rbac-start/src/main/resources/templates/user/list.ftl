<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>用户列表</span>";section>
    <#if section = "head">
        列表
    <#elseif section="head-js">
        <script type="module">

            new Vue({
                el: '#app',
                data() {
                    return {
                        modalDetailVisible: false,//是否显示弹层
                        cartList: [],//列表对象
                        modalTitle: null,//当前条目标题
                        currentRecord: {},//当前条目
                        searchForm: {//检索表单
                            pageNumber: 1,
                            pageSize: 10,
                            fromDate: "",
                            toDate: "",
                        },
                        columns: [
                            {
                                type: "selection",
                                width: 60,
                                align: "center",
                            },
                            {
                                title: '名称',
                                key: 'username'
                            },
                            {
                                title: '电话',
                                key: 'phone'
                            },
                            {
                                title: 'Email',
                                key: 'email'
                            }, {
                                title: '建立时间',
                                key: 'createTime'
                            },
                            {
                                title: '操作',
                                render: (h, params) => {
                                    return h('div', [
                                        h('Button', {
                                            props: {
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    this.detail(params.row);
                                                }
                                            }
                                        }, '查看'),
                                        h('Button', {
                                            props: {
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '5px'
                                            },
                                            on: {
                                                click: () => {
                                                    this.edit(params.row);
                                                }
                                            }
                                        }, '编辑'),
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
                    init() {
                        axios.get('/user').then(res => {
                            this.cartList = res.data.data.records;
                        });
                    },
                    selectDateRange(v) {
                        if (v) {
                            this.searchForm.fromDate = v[0];
                            this.searchForm.toDate = v[1];
                        }
                    },
                    handleSearch() {
                        this.searchForm.pageNumber = 1;
                        this.searchForm.pageSize = 10;
                        this.getList();
                    },
                    getList() {
                        // 多条件搜索用户列表
                        axios.get('/user', {params: this.searchForm}).then(res => {
                            alert("ok");
                            this.cartList = res.data.data.records;
                        });
                    },
                    edit(v) {
                        this.modalTitle = "编辑";
                        this.$refs.searchForm.resetFields();
                        this.detail(v);
                    },
                    detail(v) {
                        this.modalTitle = "查看";
                        for (let attr in v) {
                            if (v[attr] == null) {
                                v[attr] = "";
                            }
                        }
                        let str = JSON.stringify(v);
                        let info = JSON.parse(str);
                        axios.get('/user/' + info.id).then(res => (this.currentRecord = res.data.data));
                        this.modalDetailVisible = true;
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

                },
                mounted() {
                    this.init();
                    // 请注意，在实例化extends组件构造器时，传入属性必须是propsData、而不是props哦
                    new todoItem({
                        propsData: {
                            todoData: [
                                {id: 0, text: '1'},
                                {id: 1, text: '2'},
                                {id: 2, text: '3'}
                            ]
                        }
                    }).$mount('#todoItem')

                }
            });
        </script>
    <#elseif section = "form">
        <div class="search">
            <i-form ref="searchForm" :model="searchForm" inline :label-width="70">
                <form-item label="创建时间">
                    <date-picker
                            type="daterange"
                            format="yyyy-MM-dd"
                            clearable
                            @on-change="selectDateRange"
                            placeholder="选择起始时间"
                            style="width: 200px"
                    ></date-picker>
                </form-item>
                <form-item class="br">
                    <button @click="handleSearch" type="primary" icon="ios-search">搜 索</button>
                </form-item>
            </i-form>
        </div>
        <i-table id="datatable1"
                 size="small"
                 :columns="columns"
                 :data="cartList"
                 stripe
                 highlight-row>
        </i-table>

        <Modal :title="modalTitle" v-model="modalDetailVisible" :mask-closable="false" :width="500">
            <div style="border-bottom:1px dashed #aaa;padding:5px;font-weight:bold">
                <div style="font-weight:bold">姓名：{{currentRecord.realName}}</div>
                <div style="font-weight:bold">电话：{{currentRecord.phone}}</div>
                <div style="font-weight:bold">账号：{{currentRecord.username}}</div>
                <div style="font-weight:bold">Email：{{currentRecord.email}}</div>
                <div style="font-weight:bold">建立时间：{{currentRecord.createTime}}</div>

            </div>
        </Modal>

        <div id="todoItem"></div>
        <my-component></my-component>
        <box-body height="100">
            <h1>ok</h1>
            <h2>ok2</h2>
        </box-body>
    </#if>
</@layout.registrationLayout>