<h1>sse自定义</h1>
<div id="message"></div>
<script>
    let source = null;
    if (window.EventSource) {
        const userId = new Date().getTime();

        // 建立连接
        source = new EventSource('/sse/stream');

        /**
         * 连接一旦建立，就会触发open事件
         * 另一种写法：source.onopen = function (event) {}
         */
        source.addEventListener('open', function (e) {
            setMessageInnerHTML("建立连接。。。");
        }, false);

        source.addEventListener('error', function (e) {
            if (e.readyState === EventSource.CLOSED) {
                setMessageInnerHTML("连接关闭");
            } else {
                console.log(e);
            }
        }, false);
        /**
         * 客户端收到服务器发来的数据
         * 另一种写法：source.onmessage = function (event) {}
         */
        source.addEventListener('message', function (e) {
            setMessageInnerHTML(e.data);
        });
    }

    // 将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
</script>
