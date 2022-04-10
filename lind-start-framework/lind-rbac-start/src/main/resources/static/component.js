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
    props: {navMenus: Array},
    data: function () {
        return {
            navList: []
        }
    },
    mounted() {
        if (this.navMenus != null) {
            this.navList = this.navMenus;
        } else {
            axios.get('/permission').then(res => (this.navList = res.data.data));
        }
    },
    template: `
     <i-menu theme="light" width="auto">
            <label v-for="navMenu in navList">
                  <menu-item v-if="navMenu.sons==null"
                    :key="navMenu.id" :data="navMenu" :index="navMenu.id" :route="navMenu.path">
                          {{navMenu.title}}
                   </menu-item>
                   <Submenu  v-if="navMenu.sons"
                          :key="navMenu.id" :data="navMenu" :index="navMenu.id">
                    <template slot="title">
                         {{navMenu.title}}
                    </template>
                    <nav-menu :navMenus="navMenu.sons"></nav-menu>
                   </Submenu>
            </label>
    </i-menu>
`
});