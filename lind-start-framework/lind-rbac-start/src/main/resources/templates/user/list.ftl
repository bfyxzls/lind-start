<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>用户列表</span>";section>
    <#if section = "head">
        用户列表
    <#elseif section="head-js">
        <script type="module">
            import {getRequest} from "/index.js"

            //首页组件
            var chatKfIndex = {
                data: function(){
                    return {
                        visitors: {},
                    }
                },
                methods: {
                },
                created: function () {
                },
                template:$("#chatKfIndex").html()
            };
            //详情组件
            var chatKfBox = {
                data: function(){
                    return {
                        msgList: [],
                        messageContent: "",
                        face: [],
                    }
                },
                methods: {
                    init(){
                        alert(this.$parent.socket);
                        alert(this.$route.params.visitorId);
                    },
                },
                created: function () {
                    this.init();
                },
                template:$("#chatBox").html()
            };
            var routes = [
                { path: '/chatKfIndex',component:chatKfIndex}, // 这个表示会默认渲染  http://localhost:8081/view/user#/
                {path:'/chatKfBox/:visitorId',component:chatKfBox},//http://localhost:8081/view/user#/chatKfBox
            ];
            var router = new VueRouter({
                routes: routes
            })

            new Vue({
                router,
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
                                                    this.deleteItem(params.row.id);
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
                        getRequest('/user', this.searchForm).then(res => {
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
                    this.getList();

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
                <range-date title="from date"></range-date>
                <form-item class="br">
                    <input type="button" @click="handleSearch" class="ivu-btn ivu-btn-default" value="搜 索"/>
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
        <h1>
        <router-view></router-view>
        </h1>
    </#if>
    <template id="chatKfIndex">
        <div>111</div>
    </template>
    <template id="chatBox">
        <div>222</div>
    </template>
</@layout.registrationLayout>