<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发送</title>
    <script src="https://cas.pkulaw.com/auth/resources/efva6/login/custom/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="app">
    <input type="text" v-model="mess" @keyup.enter="send()">
    <button type="button" @click="send()">发送</button>
</div>
</body>
<script>
    var ff=new Vue({
        el: "#app",
        data() {
            return {
                mess: ''
            }
        },
        methods:{
            send() {
                var t=this;
                axios.get("/sse/push/"+t.mess).then(
                    function(response){
                        console.log(response.data.code)
                        if(response.status()===200){
                            console.log("发送成功！")
                        }else {
                            alert(response.data.msg)
                        }
                        t.mess="";
                    },
                    function(err){
                        console.log(err)
                    }
                )
            },
        }
    })
</script>
</html>
