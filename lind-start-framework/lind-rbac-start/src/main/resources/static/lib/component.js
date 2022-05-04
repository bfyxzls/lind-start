import {getRequest} from "/lib/index.js"
// 定义组件
var todoItem = Vue.extend({
    props: {
        todoData: []
    },
    template:
        `
        <ul>
            <li v-for='(d, i) in todoData' :key="i">
                {{ d.text }}
            </li>
        </ul>
       `
});

//注册全局组件
Vue.component('todoItem', todoItem)

Vue.component('my-component', {
    template: '<div>A custom component!</div>'
})

Vue.component('box-body', {
    props: {height: String},//添加最大高度
    data: function () {
        return this.height ? {dialogStyle: {'max-height': this.height + 'px', 'overflow-y': 'auto'}} : {dialogStyle: {}}
    },
    template: `
          <div class="panel-body" :style="dialogStyle">
              <slot></slot>
          </div>
        `
});

Vue.component('range-date', {
    props: {title: String},//标题
    data: function () {
        return {
            name: this.title ? this.title : '开始时间',
            searchForm: {}
        }
    },
    methods: {
        selectDateRange(v) {
            if (v) {
                this.searchForm.fromDate = v[0];
                this.searchForm.toDate = v[1];
            }
        }
    },
    template: `
            <form-item :label="name">
                    <date-picker
                            type="daterange"
                            format="yyyy-MM-dd"
                            clearable
                            @on-change="selectDateRange"
                            placeholder="选择起始时间"
                            style="width: 200px"
                    ></date-picker>
                </form-item>
        `
});

Vue.component('nav-menu', {
    props: {
        navMenus: Array
    },
    data: function () {
        return {
            activeName: '',
            navList: []
        }
    }, methods: {
        menuSelect: function (name) {
            this.activeName = name;
            console.log(name, " is clicked!");
            //调用子组件breadcrumb
            if (this.$route.path != name) {
                this.$router.push(name);//按着路由path完成跳转，menu上绑定name时可以直接使用route.path，注意初需要先把路由字典初始化
            }
        }
    },
    mounted() {
        if (this.navMenus != null) {
            this.navList = this.navMenus;
        } else {
            getRequest('/permission').then(res => (this.navList = res.data.data));
        }

    },
    template: `
         <i-menu  theme="light" width="auto" :active-name="activeName"  @on-select='menuSelect' >
            <div v-for="navMenu in navList">
                  <menu-item v-if="navMenu.sons==null" :name="navMenu.path" >               
                      <Icon type="ios-navigate"/>
                      {{navMenu.title}}
                   </menu-item>
                   <Submenu v-if="navMenu.sons" :name="navMenu.id"  :data="navMenu">
                    <template slot="title"> 
                         {{navMenu.title}}
                    </template>
                    <nav-menu :navMenus="navMenu.sons"></nav-menu>
                   </Submenu>
            </div>
    </i-menu>   
`
});

Vue.component("breadcrumb", {
    data: function () {
        return {
            breadcrumbList: []
        }
    },
    watch: {
        $route: {
            handler(newName, oldName) {
                getRequest('/permission/father?path=' + newName.path).then(res => (this.breadcrumbList = res.data.data));
            },
            deep: true,
            immediate: true
        }
    },
    template: `
     <div class="layout-breadcrumb">
        <Breadcrumb :style="{margin: '24px 0'}" separator=">">
            <Breadcrumb-item v-for="(item,index) in breadcrumbList">{{item.title}}</Breadcrumb-item>
        </Breadcrumb>
     </div>
`
});

Vue.component('home', {
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
            getRequest('/user/list', this.searchForm).then(res => {
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
    },
    template: `<div>
                    <div class="search">
                        <i-form ref="searchForm" :model="searchForm" inline :label-width="70">
                              <form-item :label="name">
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
                </div>
                `
});

Vue.component('role-list', {
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
                    key: 'name'
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
            getRequest('/role/list', this.searchForm).then(res => {
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
    },
    template: `<div>
                    <div class="search">
                        <i-form ref="searchForm" :model="searchForm" inline :label-width="70">
                              <form-item :label="name">
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
                </div>
                `
});

export const userList = {
    template: "<home/>"
}
export const roleList = {
    template: "<role-list/>"
}
export const news = {
    template: "<h2>这里是新闻部分</h2>"
}

var routes = [];
$.ajax({
    url: "/permission/list?pageSize=10000",
    async: false,//这块需要是同步请求，将同步的结果赋值常量routerConfig
    success: function (res) {
        var item = res.data.records;
        for (var i in item) {
            if (item[i].path != null && item[i].path != '' && item[i].type == 0) {
                routes.push({path: item[i].path, component: {template: item[i].filePath}})
            }
        }
        return routes;
    }
})
export const routerConfig = routes;



