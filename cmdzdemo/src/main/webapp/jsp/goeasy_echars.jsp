<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>
<!-- 用户男女比例对比统计 -->
<div id="test" style="width: 600px;height:400px;"></div>

<%--统计过去三周的用户注册量变化--%>
<div id="test1" style="width: 600px;height:400px;"></div>

<div id="china" style="width: 600px;height:400px;"></div>

    <script type="text/javascript">
       // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('test'));

        // 指定图表的配置项和数据
        var option1 = {
            // 图形的标题
            title: {
                text: '持明法洲用户性别信息统计'
            },
            // 工具栏
            tooltip: {},
            // 定义图例的相关信息  对象
            legend: {
                data:['用户']
            },
            // x轴显示的坐标值  属性名
            xAxis: {
                data: ["男","女"]
            },
            yAxis: {}

        };

        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option1);
        // 用户男女统计结束


        // 1.基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('test1'));
        // 2.指定图表的配置项和数据
        var option2 = {
            title: {
                text: '过去三周用户注册量变化'
            },
            tooltip: {},
            legend: {
                data:['注册量']
            },
            xAxis: {
                data: ["过去三周","过去两周","过去一周"]
            },
            yAxis: {}

        };
        // 3.使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option2);
        // 用户注册量变化结束


        // 地图展示开始
        // 基于准备好的dom，初始化echarts实例
        var myChina3 = echarts.init(document.getElementById('china'));

        var option3 = {
            title : {
                text: '用户地区分布',
                left: 'center'
            },
            tooltip : {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data:['用户人数']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text:['高','低'],           // 文本，默认为数值文本
                calculable : true
            },
            toolbox: {
                show: true,
                orient : 'vertical',
                left: 'right',
                top: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            }

        };
        myChina3.setOption(option3)
// 发送ajax请求
        $.ajax({
            url: "${pageContext.request.contextPath}/user/getCount1",
            type: "get",
            dataType: "json",
            success: function (data) {
                // 1.
                myChart1.setOption({
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'bar',
                        data: [data.sex.nan, data.sex.nv]
                    }]
                });
                myChart1.setOption(option1);

                // 2.
                myChart2.setOption({
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'line',
                        data: [data.week.weekThree, data.week.weekTwo, data.week.weekOne]
                    }]
                });
                myChart2.setOption(option2);

                // 3.
                myChina3.setOption({
                    series: [{
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.province
                    }]
                });
                myChina3.setOption(option3);
            }
        });

        var goEasy = new GoEasy({
            appkey: "BS-125c5b9532aa42ecafc4572eac7eb412"
        });

        goEasy.subscribe({
            channel: "cmfzChannel",
            onMessage: function (message) {
                alert(message);
                //因为 message 是 json  需要手动处理
                var myData = JSON.parse(message.content);
                // 1.性别
                // myChart1.setOption(option1);
                myChart1.setOption({
                    series : [
                        {
                            name:'注册人数',
                            type:'bar',
                            data:[myData.sex.nan, myData.sex.nv],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                });// 性别结束

                // 每周数据
                // myChart1.setOption(option1);
                myChart2.setOption({
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'line',
                        data: [myData.week.weekThree, myData.week.weekTwo, myData.week.weekOne],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }]
                });// 每周数据结束

                // 地区数据
                //myChina3.setOption(option3);
                myChina3.setOption({
                    series: [{
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: myData.province
                        /*markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }*/
                    }]
                });// 地区数据结束
            }
        });
    </script>

</body>

