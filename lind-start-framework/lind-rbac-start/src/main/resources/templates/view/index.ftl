<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>用户列表</span>";section>
    <#if section = "head">
        用户列表
    <#elseif section="head-js">
        <script type="module">
            var home = {
                template: "<home/>"
            }

            var news = {
                template: "<h2>这里是新闻部分</h2>"
            }

            new Vue({
                router: new VueRouter({this.routes}),
                el: '#app',
                data() {
                    return {routes: []};
                },
                created() {
                    this.routes = [
                        {name: "8971410885fd5c6dc5de6a6ca2cfa889", path: "/home", component: home},
                        {name: "9", path: "/role", component: news},
                        {name: "8971410885fd5c6dc5de6a6ca2cfa889", path: "/", component: home},

                    ]
                }
            });
        </script>
    <#elseif section = "form">
        <router-view></router-view>
    </#if>
</@layout.registrationLayout>
