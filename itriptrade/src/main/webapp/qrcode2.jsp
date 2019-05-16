<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>

<head>
    <title>Zero Clipboard Test</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.qrcode.min.js"></script>
    <script type="text/javascript">
        function generateQRCode(rendermethod, picwidth, picheight, url) {
            $("#qrcode").qrcode({
                render: rendermethod, // 渲染方式有table方式（IE兼容）和canvas方式
                width: picwidth, //宽度
                height:picheight, //高度
                text: utf16to8(url), //内容
                typeNumber:-1,//计算模式
                correctLevel:2,//二维码纠错级别
                background:"#ffffff",//背景颜色
                foreground:"#000000"  //二维码颜色

            });
        }
        function init() {
            $.ajax({
                url : "api/wx/createCode/D100000120170627152001858223",
                method : "GET",
                success : function(data) {
                    alert(data.data.code_url);
                    generateQRCode("table",200, 200, data.data.code_url);
                }
            });

        }
        //中文编码格式转换
        function utf16to8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for (i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                }
            }
            return out;
        }

        //查询订单
        function queryOrder() {
            $.ajax({
                url : "api/wx/queryOrderStatus/D100000120170627152001858223",
                method : "GET",
                success : function(result) {
                    if (result.success == 'true') {
                        var orderStatus = result.data.orderStatus;
                        if (orderStatus == 2) { //支付成功后，跳转到百度页面。
                            window.location.href="http://www.baidu.com";
                        }
                    }
                }
            });
        }
        //每隔5秒查看下订单的状态
        setInterval(queryOrder, 5000);

    </script>
</head>
<body onLoad="init()">
<h1>Qrcode</h1>
<div id="qrcode"></div>

</body>
</html>
