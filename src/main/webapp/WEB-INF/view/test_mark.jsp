<%--
  Created by IntelliJ IDEA.
  User: MY丶king
  Date: 2020/11/6
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="../../js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    var flag = false;
    var checktestname = function () {
        var testname = $("[name=testname]").val();
        var url = "/test/findByName.do";
        if (testname == "") {
            $("#AAA").css("color", "red");
            $("#AAA").text("考试名称不能为空！");
        } else {
            $.get(url, {testname: testname}, function (result) {
                if (result == "ok") {
                    $("#AAA").html("");
                    $("#AAA").append("√")
                    flag = true;
                } else {
                    $("#AAA").html("");
                    $("#AAA").append("×")
                    $("[name=testname]").focus();
                }
            })
        }
    }

    function fileupload() {
        var formData = new window.FormData();
        formData.append('file', document.querySelector('input[type=file]').files[0]);
        $.ajax({
            url: "/teacher/fileUpload.do?" + random,
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                $("[name=totalscore]").val(data.msg)
                flag = true;
            }
        });

    }

    var random = "";
    for (var i = 0; i < 6; i++) {
        random += Math.floor(Math.random() * 10)
    }


    //发布试题
    var makeTest = function () {
        // var form = $("form").serialize();

        //考卷名称
        var testname = $("[name = testname]").val();
        if (testname == "" || testname == null) {
            $("#name").css("color", "red");
            $("#name").text("考试名称不能为空哦！");
        }
        //总分数
        var totalscore = $("[name = totalscore]").val();
        //通过分数
        var passscore = $("[name = passscore]").val();
        //考试时长
        var testtime = $("[name = testtime]").val();

        //考试对应班级id
        var gids = "";
        var gid = $("[name='gids']:checked");
        for (var i = 0; i < gid.length; i++) {
            gids += gid[i].value + ",";
        }
        gids = gids.substring(0, gids.length - 1);
        //考试开始时间
        var starttimeS = $("[name = starttime]").val();

        //获取当前的一个Date类型 用于讲选择的时间存放
        var statime = new Date();
        statime.setFullYear(parseInt(starttimeS.substring(0, 4)));
        statime.setMonth(parseInt(starttimeS.substring(5, 7)) - 1);
        statime.setDate(parseInt(starttimeS.substring(8, 10)));
        statime.setHours(parseInt(starttimeS.substring(11, 13)));
        statime.setMinutes(parseInt(starttimeS.substring(14, 16)));
        // alert(statime)

        //考试结束时间
        var endtime = $("[name = endtime]").val();
        //获取当前的一个Date类型 用于讲选择的时间存放
        var entime = new Date();
        entime.setFullYear(parseInt(endtime.substring(0, 4)));
        entime.setMonth(parseInt(endtime.substring(5, 7)) - 1);
        entime.setDate(parseInt(endtime.substring(8, 10)));
        entime.setHours(parseInt(endtime.substring(11, 13)));
        entime.setMinutes(parseInt(endtime.substring(14, 16)));

        if (flag == true) {
            //发送ajax请求，保存试题
            $.ajax({
                url: "/teacher/saveTestMark.do",    //请求路径
                type: "post",       //请求方式
                data: {
                    testname: testname,
                    totalscore: totalscore,
                    passscore: passscore,
                    starttime: statime,
                    endtime: entime,
                    testtime: testtime,
                    gids: gids
                },      //返回到后台的数据
                dataType: "json",    //服务器返回的数据类型
                success: function (data) {
                    if (data.msg == "success") {
                        location.href = "/teacher/getDuliTestList.do";
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }

    }
</script>
<body>
<center>
    <form action="/teacher/saveTestMark.do" method="post" enctype="multipart/form-data">
        <table border="1" rules="all">
            <tr align="center">
                <td>
                    考试名称：
                </td>
                <td>
                    <input type="text" name="testname" onblur="checktestname()"/><span id="AAA"></span>
                </td>
            </tr>
            <tr>
                <td>
                    导入试题：
                </td>
                <td>
                    <input type="file" name="filename"/>
                    <input type="button" value="上传" onclick="fileupload()"/>
                </td>
            </tr>
            <tr>
                <td>
                    总分
                </td>
                <td>
                    <input type="text" name="totalscore" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>
                    通关分
                </td>
                <td>
                    <input type="text" name="passscore"/>
                </td>
            </tr>
            <tr>
                <td>
                    开始时间
                </td>
                <td>
                    <input type="datetime-local" name="starttime"/>
                </td>
            </tr>
            <tr>
                <td>
                    结束时间
                </td>
                <td>
                    <input type="datetime-local" name="endtime"/>
                </td>
            </tr>
            <tr>
                <td>
                    考试时长
                </td>
                <td>
                    <input type="number" name="testtime"/>
                </td>
            </tr>
            <tr>
                <td>考试班级</td>
                <td>
                    <c:forEach var="g" items="${list}">
                        <input type="checkbox" name="gids" value="${g.gid}"/>${g.gname}<br>
                    </c:forEach>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="button" value="保存" onclick="makeTest()">
                    <input type="reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
