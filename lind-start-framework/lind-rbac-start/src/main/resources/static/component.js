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
    props: { height:String },//添加最大高度
    data: function() { return this.height ? { dialogStyle:{'max-height':this.height+'px', 'overflow-y':'auto'}} : {dialogStyle:{}}},
    template: '<div class="panel-body" :style="dialogStyle"><slot></slot></div>'
});