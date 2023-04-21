<h1>消息推送</h1>
<div id="message"></div>
</body>
<script>
    var obj={
        "id":"chatcmpl-77evbj7aDaPxOVmqDan3uUWXlY5Uz",
        "object":"chat.completion.chunk",
        "created":1682059979,
        "model":"gpt-3.5-turbo-0301",
        "choices":[
            {
                "delta":{
                    "role":"assistant"
                },
                "index":0,
                "finish_reason":null
            }
        ]
    };
    // message为需要发送的消息
    const eventSource = new EventSource("/sse/chat-do?message=java如何生成树结构");
    // 收到消息处理
    eventSource.onmessage = function (event) {
        if (event.data.choices != undefined
            && event.data.choices != null
            && event.data.choices.size()>0
            && event.data.choices[0].delta != undefined
            && event.data.choices[0].delta.content != undefined) {
           // setMessageInnerHTML(event.data.choices[0].delta.content);
alert(event.data.choices[0].delta.content);
        }
    }

    // 将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
</script>
