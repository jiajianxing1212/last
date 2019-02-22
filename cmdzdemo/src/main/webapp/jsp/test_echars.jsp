<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
</head>
<body>
<!-- 用户男女比例对比统计 -->
<div id="test" style="width: 600px;height:400px;"></div>

<%--统计过去三周的用户注册量变化--%>
<div id="test1" style="width: 600px;height:400px;"></div>

<div id="china" style="width: 600px;height:400px;"></div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('test'));

        // 指定图表的配置项和数据
        var option = {
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
        myChart.setOption(option);





        // 通过ajax请求后台数据 修改series中data数据  什么样的json
        /*$.ajax({
            url:"/user/getCount",
            type:"get",
            dataType:"json",
            success:function (data) {
                option = {
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'bar',
                        data: [data.nan, data.nv]
                    }]
                }
                myChart.setOption(option);
            }
        })
*/
        // 用户男女统计结束

        // 1.基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('test1'));

        // 2.指定图表的配置项和数据
        var option1 = {
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
        myChart1.setOption(option1);
        // 此处写ajax请求
        /*$.ajax({
            url:"/user/getCountByWeekendOfRegister",
            type:"get",
            dataType:"json",
            success:function (data) {
                option1 = {
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'line',
                        data: [data.weekThree, data.weekTwo,data.weekOne]
                    }]
                }
                myChart1.setOption(option1);
            }
        })*/


        // 用户注册量变化结束

        // 地图展示开始
        // 基于准备好的dom，初始化echarts实例
        var myChina = echarts.init(document.getElementById('china'));

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
        myChina.setOption(option3)

        // 使用ajax请求数据
        /*$.ajax({
            url:"/user/getCountByArea",
            type:"get",
            dataType:"json",
            success:function (data) {
                option3 = {
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
                        data:data
                    }]
                }
                myChina.setOption(option3);
            }
        })*/

        $.ajax({
            url:"${pageContext.request.contextPath}/user/getCount1",
            type:"get",
            dataType:"json",
            success:function (data) {
                // 1.
                option = {
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'bar',
                        data: [data.sex.nan, data.sex.nv]
                    }]
                }
                myChart.setOption(option);
                // 2.
                option1 = {
                    series: [{
                        // 用户图例的数据
                        name: '用户',
                        type: 'line',
                        data: [data.week.weekThree, data.week.weekTwo,data.week.weekOne]
                    }]
                }
                myChart1.setOption(option1);

                // 3.
                option3 = {
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
                        data:data.province
                    }]
                }
                myChina.setOption(option3);
            }
        })
    </script>

</body>

