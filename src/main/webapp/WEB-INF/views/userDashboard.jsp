<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cn.edu.zjut.utils.UserInfoUtils" %>
<%@ page import="cn.edu.zjut.entity.dto.UserTokenInfoDto" %>

<%
    // 从工具类中获取当前用户信息
    String token = request.getParameter("Authorization");
    System.out.println("进入userDashboard,当前用户token为：" + token);
    String user = request.getParameter("userType");
    UserTokenInfoDto userTokenInfoDto = UserInfoUtils.getCurrentUser();
    request.setAttribute("userTokenInfoDto", userTokenInfoDto);
    // 将 userTokenInfoDto 放入 request 范围中
    request.setAttribute("user", user);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="referrer" content="no-referrer">
    <title>用户操作界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
            height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: lightgrey;
            color: white;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }

        .sidebar h2 {
            text-align: center;
            font-size: 45px;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar li {
            padding: 10px;
            text-align: center;
            font-weight: bold;
        }

        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            font-size: 24px;
        }

        .sidebar a:hover {
            background-color: darkgrey;
        }

        .sidebar a.small-text {
            font-size: 18px;
        }

        .logout-btn {
            margin-top: 20px;
            text-align: center;
        }

        .logout-btn button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #d9534f;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .logout-btn button.password {
            background-color: blue;
        }

        .content {
            flex: 1;
            padding: 20px;
            background-color: #f4f4f4;
            background-image: url('../../static/background2.jpeg'); /* 替换为你的图片路径 */
            background-size: cover; /* 使图片覆盖整个背景 */
            background-position: center; /* 居中显示图片 */
            background-repeat: no-repeat; /* 防止图片重复 */
        }

        .button-group button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 5px;
            cursor: pointer;
        }

        .wide-textarea {
            width: 100%; /* 占满父容器宽度 */
            height: auto; /* 自适应高度 */
            font-size: 16px; /* 字体大小 */
            padding: 10px; /* 内边距 */
            border: 1px solid #ddd; /* 边框颜色 */
            border-radius: 4px; /* 边框圆角 */
            resize: vertical; /* 允许用户垂直调整高度 */
        }

        h1 {
            color: #2c3e50;
            font-size: 28px;
            margin-bottom: 10px;
        }

        .simple-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 20px auto;
            text-align: left;
        }

        .user-info {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 20px auto;
            text-align: center;
        }

        .user-info p {
            font-size: 18px;
            color: #333;
            margin: 10px 0;
        }

        .user-info p span {
            font-weight: bold;
        }

        main.table {
            width: 82vw;
            height: 60vh;
            background-color: #fff5;
            box-shadow: 0 8px 16px #0005;
            border-radius: 16px;
            overflow: hidden;
        }

        .header {
            width: 100%;
            height: 10%;
            background-color: #fff4;
            padding: 0 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header .input-group {
            width: 35%;
            height: 50%;
            background-color: #fff5;
            padding: 0 20px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: .2s;
            margin-right: 100px;
        }

        .header .input-group:hover {
            width: 45%;
            background-color: #fff8;
            box-shadow: 0 5px 40px #0002;
        }

        .header .input-group img {
            width: 20px;
            height: 20px;
        }

        .header .input-group input {
            width: 100%;
            background-color: transparent;
        }

        .header .input-group button {
            width: 120%;
        }

        .shell {
            width: 95%;
            max-height: calc(90% - 25px);
            background-color: #fffb;
            margin: 8px auto;
            border-radius: 10px;
            overflow: auto;
        }

        .shell::-webkit-scrollbar {
            width: 10px;
            height: 10px;
        }

        table {
            width: 100%;
        }

        td img {
            width: 36px;
            height: 36px;
            margin-right: 10px;
            border-radius: 50%;
            vertical-align: middle;
        }

        table,
        th,
        td {
            border-collapse: collapse;
            padding: 20px;
            text-align: left;
        }

        thead th {
            position: sticky;
            top: 0;
            left: 0;
            background-color: #d5d1defe;
            cursor: pointer;
        }

        /*偶数行背景色 */
        tbody tr:nth-child(even) {
            background-color: #0000000b;
        }

        tbody tr:hover {
            background-color: #add8e6 !important;
        }

        .button {
            padding: 5px 5px;
            border-radius: 40px;
            text-align: left;
        }

        tr:nth-child(4n) .button {
            background-color: #86e49d;
            color: #006b21;
        }

        tr:nth-child(4n-1) .button {
            background-color: #ebc474;
        }

        tr:nth-child(4n+1) .button {
            background-color: #d893a3;
            color: #b30021;
        }

        tr:nth-child(4n+2) .button {
            background-color: #6fcaea;
        }

        /* 样式表单 */
        form {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        form input {
            flex: 0 1 calc(25% - 10px); /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form input.three {
            flex: 0 1 calc(33% - 10px); /*每行三列，间距为10px */
        }

        form input.two {
            flex: 0 1 calc(50% - 10px); /*每行两列，间距为10px */
        }

        form input.one {
            flex: 0 1 calc(100% - 10px); /*每行一列，间距为10px */
        }

        form input.five {
            flex: 0 1 calc(20% - 10px); /*每行五列，间距为10px */
        }

        form select {
            flex: 0 1 calc(25% - 10px); /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form select.three {
            flex: 0 1 calc(33% - 10px); /*每行三列，间距为10px */
        }

        form select.two {
            flex: 0 1 calc(50% - 10px); /*每行两列，间距为10px */
        }

        form select.five {
            flex: 0 1 calc(20% - 10px); /*每行五列，间距为10px */
        }

        form option {
            flex: 0 1 calc(25% - 10px); /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form option.three {
            flex: 0 1 calc(33% - 10px); /*每行三列，间距为10px */
        }

        form option.two {
            flex: 0 1 calc(50% - 10px); /*每行两列，间距为10px */
        }

        form option.five {
            flex: 0 1 calc(20% - 10px); /*每行五列，间距为10px */
        }

        form button {
            flex: 0 1 100%; /* 按钮占据一整行 */
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        form button.four {
            flex: 0 1 calc(25% - 10px); /*每行四列，间距为10px */
        }

        button {
            background-color: #4CAF50; /* 绿色背景 */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049; /* 鼠标悬停时的颜色 */
        }

        /* 弹窗背景遮罩 */
        .modal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        /* 弹窗内容 */
        .modal-content {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 300px;
        }

        /* 关闭按钮 */
        .close-button {
            background: #ff4d4d;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        .close-button:hover {
            background: #ff1a1a;
        }

        /* 按钮组 */
        .wallet-actions button {
            background: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            border-radius: 5px;
            cursor: pointer;
        }

        .wallet-actions button:hover {
            background: #0056b3;
        }

    </style>
</head>
<body>
<div class="sidebar">
    <h2>操作列表</h2>
    <ul>
        <c:choose>
            <c:when test="${user == '管理员'}">
                <li><a href="#AdminProfileManagement" onclick="showAdminManagement()">个人信息管理</a></li>
                <div id="AdminProfileManagement"></div>
                <li><a href="#AdminReviewManagement" onclick="showAdminReviewManagement()">审核管理</a></li>
                <div id="AdminReviewManagement"></div>
            </c:when>
            <c:when test="${user == '大学生租户'}">
                <li><a href="#TenantProfileManagement" onclick="showTenantManagement()">个人信息管理</a></li>
                <div id="TenantProfileManagement"></div>
<%--                <li><a href="#TenantRentInfo" onclick="showTenantRentInfo()">查看房租信息</a></li>--%>
<%--                <div id="TenantRentInfo"></div>--%>
                <li><a href="#TenantRentalManagement" onclick="showRentalManagement()">租房板块</a></li>
                <div id="TenantRentalManagement"></div>
                <li><a href="#TenantRentManagement" onclick="showTenantRentManagement()">租房服务管理</a></li>
                <div id="TenantRentManagement"></div>
                <li><a href="#TenantContactsManagement" onclick="showTenantContactsManagement()">合同管理</a></li>
                <div id="TenantContactsManagement"></div>


            </c:when>
            <c:when test="${user == '房东'}">
                <li><a href="#landlordProfileManagement" onclick="showLandlordManagement()">个人信息管理</a></li>
                <div id="landlordProfileManagement"></div>
                <li><a href="#landlordHouseManagement" onclick="showLandlordHouseManagement()">我的房屋</a></li>
                <div id="landlordHouseManagement"></div>
<%--                <li><a href="#showLandlordRentInfo" onclick="showLandlordRentInfo()">查看房租信息</a></li>--%>
<%--                <div id="showLandlordRentInfo"></div>--%>
                <li><a href="#RentManagement" onclick="showRentManagement()">租房服务管理</a></li>
                <div id="RentManagement"></div>
                <li><a href="#LandlordContactsManagement" onclick="showLandlordContactsManagement()">合同管理</a></li>
                <div id="LandlordContactsManagement"></div>

            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>

    </ul>
</div>
<div class="content" id="content">
    <div class="user-info">
        <h1>欢迎!</h1>
    </div>
</div>
<script>

    function handleSuccess(redirectFunctionName) {
        // 禁用按钮
        const buttons = document.querySelectorAll('button');
        buttons.forEach(button => button.disabled = true);

        // 显示成功提示
        const successMessage = document.createElement('div');
        successMessage.textContent = '操作成功';
        successMessage.style.position = 'fixed';
        successMessage.style.top = '50%';
        successMessage.style.left = '50%';
        successMessage.style.transform = 'translate(-50%, -50%)';
        successMessage.style.backgroundColor = 'rgba(0, 128, 0, 0.8)';
        successMessage.style.color = 'white';
        successMessage.style.padding = '10px';
        successMessage.style.borderRadius = '5px';
        document.body.appendChild(successMessage);

        // 显示2秒后移除提示，并刷新页面或更新页面上的显示
        setTimeout(() => {
            document.body.removeChild(successMessage);

            // 重新启用按钮
            buttons.forEach(button => button.disabled = false);

            // 调用传入的跳转函数
            window[redirectFunctionName]();
        }, 2000);
    }

    function handleFail(redirectFunctionName, failReason) {
        // 禁用按钮
        const buttons = document.querySelectorAll('button');
        buttons.forEach(button => button.disabled = true);

        // 显示失败提示
        const failMessage = document.createElement('div');
        failMessage.textContent = `操作失败：` + (failReason || '未知原因'); // 如果没有提供原因，显示"未知原因"
        failMessage.style.position = 'fixed';
        failMessage.style.top = '50%';
        failMessage.style.left = '50%';
        failMessage.style.transform = 'translate(-50%, -50%)';
        failMessage.style.backgroundColor = 'rgba(128, 0, 0, 0.8)';
        failMessage.style.color = 'white';
        failMessage.style.padding = '10px';
        failMessage.style.borderRadius = '5px';
        document.body.appendChild(failMessage);

        // 显示2秒后移除提示，并刷新页面或更新页面上的显示
        setTimeout(() => {
            document.body.removeChild(failMessage);

            // 重新启用按钮
            buttons.forEach(button => button.disabled = false);

            // 调用传入的跳转函数
            if (redirectFunctionName && typeof window[redirectFunctionName] === 'function') {
                window[redirectFunctionName]();
            }
        }, 2000);
    }

    function AdminClean() {
        let content = document.getElementById('AdminReviewManagement');
        content.innerHTML = '';
        content = document.getElementById('AdminProfileManagement');
        content.innerHTML = '';
    }

    function showAdminManagement() {
        AdminClean();
        let content = document.getElementById('AdminProfileManagement');
        content.innerHTML = '<li><a onclick="showAdminEditInfo()" class="small-text">修改个人信息</a></li>';
    }

    function showAdminEditInfo() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>修改管理员信息</h2>
        <form id="editAdminForm" class="simple-form">
            <input type="text" id="adUsername" name="adUsername" placeholder="用户名" class="one" required>
            <input type="text" id="adPhone" name="adPhone" placeholder="手机号" class="one">
            <input type="email" id="adEmail" name="adEmail" placeholder="邮箱" class="one">
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="editAdminResult"></div>
    `;

        // 绑定表单提交事件
        const editAdminForm = document.getElementById('editAdminForm');
        editAdminForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const adminUpdateReq = {
                adUsername: formData.get('adUsername') === "" ? null : formData.get('adUsername'),
                adPhone: formData.get('adPhone') === "" ? null : formData.get('adPhone'),
                adEmail: formData.get('adEmail') === "" ? null : formData.get('adEmail'),
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/admins/changeUserInfo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(adminUpdateReq)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showAdminEditInfo');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showAdminEditInfo', data.message || '修改失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数，传递错误信息
                    handleFail('showAdminEditInfo', '网络错误，请稍后重试');
                });
        });
    }

    function showAdminReviewManagement() {
        AdminClean();
        let content = document.getElementById('AdminReviewManagement');
        content.innerHTML = '<li><a onclick="auditTenantStudentCard()" class="small-text">审核大学生租户学生证</a></li>' +
            '<li><a onclick="auditTenantIDCard()" class="small-text">审核大学生租户身份证</a></li>' +
            '<li><a onclick="auditLandlordIDCard()" class="small-text">审核房东身份证</a></li>' +
            '<li><a onclick="auditLandlordPropertyCertificate()" class="small-text">审核房东房产证</a></li>';
    }

    // 确认操作
    function handleApprove(Id, url) {

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造请求参数
        const requestBody = {
            content: "已审核",
            id: Id
        }

        // 发送 POST 请求
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('审核成功！');
                    // 刷新页面
                    location.reload();
                } else {
                    alert('审核失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('网络错误，请稍后重试。');
            });
    }

    // 拒绝操作
    function handleReject(Id, url) {

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造请求参数
        const requestBody = {
            content: "已拒绝",
            id: Id
        };

        // 发送 POST 请求
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('拒绝成功！');
                    // 刷新页面
                    location.reload();
                } else {
                    alert('拒绝失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('网络错误，请稍后重试。');
            });
    }

    function auditTenantStudentCard() {
        const content = document.getElementById('content');

        // 清空内容并设置标题
        content.innerHTML = `
        <h2>大学生租户学生证审核</h2>
        <div id="tenantStudentCardList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造查询条件
        const queryParams = {
            tstatus: "等待审核", // 只查询等待审核的租户
            tidentityStatus: null,
            tsex: null,
            tmajor: null,
            tuniversity: null,
            tbirthStart: null,
            tbirthEnd: null
        };

        // 发送 POST 请求获取租户列表
        fetch('/tenant/getTenantList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(queryParams)
        })
            .then(response => response.json())
            .then(data => {

                const resultDiv = document.getElementById('tenantStudentCardList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['学生账号', '姓名', '性别', '出生年月', '大学', '专业', '学生证照片', '审核状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.tenantList.forEach(tenant => {
                    const row = document.createElement('tr');

                    // 学生账号
                    const accountCell = document.createElement('td');
                    accountCell.textContent = tenant.taccount || '无';
                    row.appendChild(accountCell);

                    // 姓名
                    const nameCell = document.createElement('td');
                    nameCell.textContent = tenant.tname || '无';
                    row.appendChild(nameCell);

                    // 性别
                    const sexCell = document.createElement('td');
                    sexCell.textContent = tenant.tsex || '无';
                    row.appendChild(sexCell);

                    // 出生年月
                    const birthCell = document.createElement('td');
                    birthCell.textContent = tenant.tbirth ? new Date(tenant.tbirth).toLocaleDateString() : '无';
                    row.appendChild(birthCell);

                    // 大学
                    const universityCell = document.createElement('td');
                    universityCell.textContent = tenant.tuniversity || '无';
                    row.appendChild(universityCell);

                    // 专业
                    const majorCell = document.createElement('td');
                    majorCell.textContent = tenant.tmajor || '无';
                    row.appendChild(majorCell);

                    // 学生证照片
                    const photoCell = document.createElement('td');
                    if (tenant.tprofilePicture) {
                        const img = document.createElement('img');
                        img.src = tenant.tprofilePicture;
                        img.style.width = 'auto'; // 按图片原比例宽度
                        img.style.height = 'auto'; // 按图片原比例高度
                        img.style.maxWidth = '300px'; // 最大宽度限制
                        img.style.maxHeight = '200px'; // 最大高度限制
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0'; // 去除圆角，防止椭圆形
                        img.style.cursor = 'pointer'; // 鼠标悬停效果
                        img.alt = '学生证图片预览';
                        img.addEventListener('click', function () {
                            window.open(tenant.tprofilePicture); // 点击图片新窗口打开原图
                        });
                        photoCell.appendChild(img);
                    } else {
                        photoCell.textContent = '无';
                    }
                    row.appendChild(photoCell);

                    // 审核状态
                    const statusCell = document.createElement('td');
                    statusCell.textContent = tenant.tstatus || '无';
                    row.appendChild(statusCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');
                    const approveButton = document.createElement('button');
                    approveButton.textContent = '确认';
                    approveButton.setAttribute('class', 'button approve-button');
                    approveButton.addEventListener('click', () => handleApprove(tenant.tenantId, '/admins/studentCardCheck'));

                    const rejectButton = document.createElement('button');
                    rejectButton.textContent = '拒绝';
                    rejectButton.setAttribute('class', 'button reject-button');
                    rejectButton.addEventListener('click', () => handleReject(tenant.tenantId, '/admins/studentCardCheck'));

                    actionCell.appendChild(approveButton);
                    actionCell.appendChild(rejectButton);
                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('tenantStudentCardList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function auditTenantIDCard() {
        const content = document.getElementById('content');

        // 清空内容并设置标题
        content.innerHTML = `
        <h2>大学生租户身份证审核</h2>
        <div id="tenantIDCardList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造查询条件
        const queryParams = {
            tidentityStatus: "等待审核", // 只查询等待审核的身份证
            tstatus: null,
            tsex: null,
            tmajor: null,
            tuniversity: null,
            tbirthStart: null,
            tbirthEnd: null
        };

        // 发送 POST 请求获取租户列表
        fetch('/tenant/getTenantList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(queryParams)
        })
            .then(response => response.json())
            .then(data => {

                const resultDiv = document.getElementById('tenantIDCardList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['学生账号', '姓名', '身份证号码', '性别', '出生年月', '身份证正面', '身份证背面', '身份认证状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.tenantList.forEach(tenant => {
                    const row = document.createElement('tr');

                    // 学生账号
                    const accountCell = document.createElement('td');
                    accountCell.textContent = tenant.taccount || '无';
                    row.appendChild(accountCell);

                    // 姓名
                    const nameCell = document.createElement('td');
                    nameCell.textContent = tenant.tname || '无';
                    row.appendChild(nameCell);

                    // 身份证号码
                    const cardNumberCell = document.createElement('td');
                    cardNumberCell.textContent = tenant.tcardNumber || '无';
                    row.appendChild(cardNumberCell);

                    // 性别
                    const sexCell = document.createElement('td');
                    sexCell.textContent = tenant.tsex || '无';
                    row.appendChild(sexCell);

                    // 出生年月
                    const birthCell = document.createElement('td');
                    birthCell.textContent = tenant.tbirth ? new Date(tenant.tbirth).toLocaleDateString() : '无';
                    row.appendChild(birthCell);

                    // 身份证正面
                    const frontImageCell = document.createElement('td');
                    if (tenant.tcardImageFront) {
                        const img = document.createElement('img');
                        img.src = tenant.tcardImageFront;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '身份证正面预览';
                        img.addEventListener('click', function () {
                            window.open(tenant.tcardImageFront);
                        });
                        frontImageCell.appendChild(img);
                    } else {
                        frontImageCell.textContent = '无';
                    }
                    row.appendChild(frontImageCell);

                    // 身份证背面
                    const backImageCell = document.createElement('td');
                    if (tenant.tcardImageBack) {
                        const img = document.createElement('img');
                        img.src = tenant.tcardImageBack;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '身份证背面预览';
                        img.addEventListener('click', function () {
                            window.open(tenant.tcardImageBack);
                        });
                        backImageCell.appendChild(img);
                    } else {
                        backImageCell.textContent = '无';
                    }
                    row.appendChild(backImageCell);

                    // 身份认证状态
                    const identityStatusCell = document.createElement('td');
                    identityStatusCell.textContent = tenant.tidentityStatus || '无';
                    row.appendChild(identityStatusCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');
                    const approveButton = document.createElement('button');
                    approveButton.textContent = '确认';
                    approveButton.setAttribute('class', 'button approve-button');
                    approveButton.addEventListener('click', () => handleApprove(tenant.tenantId, '/admins/idCardCheck'));

                    const rejectButton = document.createElement('button');
                    rejectButton.textContent = '拒绝';
                    rejectButton.setAttribute('class', 'button reject-button');
                    rejectButton.addEventListener('click', () => handleReject(tenant.tenantId, '/admins/idCardCheck'));

                    actionCell.appendChild(approveButton);
                    actionCell.appendChild(rejectButton);
                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('tenantIDCardList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function auditLandlordIDCard() {
        const content = document.getElementById('content');

        // 清空内容并设置标题
        content.innerHTML = `
    <h2>房东身份证审核</h2>
    <div id="landlordIDCardList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造查询条件
        const queryParams = {
            lhouseStatus: null,
            lstatus: "等待审核" // 只查询等待审核的房东身份证
        };

        // 发送 POST 请求获取房东列表
        fetch('/landlords/getLandlordList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(queryParams)
        })
            .then(response => response.json())
            .then(data => {

                const resultDiv = document.getElementById('landlordIDCardList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房东账号', '姓名', '身份证号码', '身份证正面', '身份证背面', '身份认证状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.landlordList.forEach(landlord => {
                    const row = document.createElement('tr');

                    // 房东账号
                    const accountCell = document.createElement('td');
                    accountCell.textContent = landlord.laccount || '无';
                    row.appendChild(accountCell);

                    // 姓名
                    const nameCell = document.createElement('td');
                    nameCell.textContent = landlord.lname || '无';
                    row.appendChild(nameCell);

                    // 身份证号码
                    const cardNumberCell = document.createElement('td');
                    cardNumberCell.textContent = landlord.lcardNumber || '无';
                    row.appendChild(cardNumberCell);

                    // 身份证正面
                    const frontImageCell = document.createElement('td');
                    if (landlord.lcardImageFront) {
                        const img = document.createElement('img');
                        img.src = landlord.lcardImageFront;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '身份证正面预览';
                        img.addEventListener('click', function () {
                            window.open(landlord.lcardImageFront);
                        });
                        frontImageCell.appendChild(img);
                    } else {
                        frontImageCell.textContent = '无';
                    }
                    row.appendChild(frontImageCell);

                    // 身份证背面
                    const backImageCell = document.createElement('td');
                    if (landlord.lcardImageBack) {
                        const img = document.createElement('img');
                        img.src = landlord.lcardImageBack;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '身份证背面预览';
                        img.addEventListener('click', function () {
                            window.open(landlord.lcardImageBack);
                        });
                        backImageCell.appendChild(img);
                    } else {
                        backImageCell.textContent = '无';
                    }
                    row.appendChild(backImageCell);

                    // 身份认证状态
                    const identityStatusCell = document.createElement('td');
                    identityStatusCell.textContent = landlord.lstatus || '无';
                    row.appendChild(identityStatusCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');
                    const approveButton = document.createElement('button');
                    approveButton.textContent = '确认';
                    approveButton.setAttribute('class', 'button approve-button');
                    approveButton.addEventListener('click', () => handleApprove(landlord.landlordId, '/admins/landlordIdCardCheck'));

                    const rejectButton = document.createElement('button');
                    rejectButton.textContent = '拒绝';
                    rejectButton.setAttribute('class', 'button reject-button');
                    rejectButton.addEventListener('click', () => handleReject(landlord.landlordId, '/admins/landlordIdCardCheck'));

                    actionCell.appendChild(approveButton);
                    actionCell.appendChild(rejectButton);
                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('landlordIDCardList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function auditLandlordPropertyCertificate() {
        const content = document.getElementById('content');

        // 清空内容并设置标题
        content.innerHTML = `
    <h2>房东房产证审核</h2>
    <div id="landlordPropertyCertificateList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造查询条件
        const queryParams = {
            lhouseLicenseState: "等待审核" // 只查询等待审核的房产证
        };

        // 发送 POST 请求获取房源列表
        fetch('/house/getHouseList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(queryParams)
        })
            .then(response => response.json())
            .then(data => {

                const resultDiv = document.getElementById('landlordPropertyCertificateList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房源标题', '房源位置', '面积（平方米）', '房间布局', '朝向', '楼层', '房屋图片', '房产证图片', '房产证验证状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.houseList.forEach(house => {
                    const row = document.createElement('tr');

                    // 房源标题
                    const titleCell = document.createElement('td');
                    titleCell.textContent = house.htitle || '无';
                    row.appendChild(titleCell);

                    // 房源位置
                    const locationCell = document.createElement('td');
                    locationCell.textContent = house.hlocation || '无';
                    row.appendChild(locationCell);

                    // 面积（平方米）
                    const areaCell = document.createElement('td');
                    areaCell.textContent = house.harea || '无';
                    row.appendChild(areaCell);

                    // 房间布局
                    const roomsCell = document.createElement('td');
                    roomsCell.textContent = house.hrooms || '无';
                    row.appendChild(roomsCell);

                    // 朝向
                    const directionCell = document.createElement('td');
                    directionCell.textContent = house.hdirection || '无';
                    row.appendChild(directionCell);

                    // 楼层
                    const floorCell = document.createElement('td');
                    floorCell.textContent = house.hfloor || '无';
                    row.appendChild(floorCell);

                    // 房屋图片
                    const housePhotoCell = document.createElement('td');
                    if (house.lhousePhoto) {
                        const img = document.createElement('img');
                        img.src = house.lhousePhoto;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '房屋图片预览';
                        img.addEventListener('click', function () {
                            window.open(house.lhousePhoto);
                        });
                        housePhotoCell.appendChild(img);
                    } else {
                        housePhotoCell.textContent = '无';
                    }
                    row.appendChild(housePhotoCell);

                    // 房产证图片
                    const licensePhotoCell = document.createElement('td');
                    if (house.lhouseLicensePhoto) {
                        const img = document.createElement('img');
                        img.src = house.lhouseLicensePhoto;
                        img.style.width = 'auto';
                        img.style.height = 'auto';
                        img.style.maxWidth = '300px';
                        img.style.maxHeight = '200px';
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0';
                        img.style.cursor = 'pointer';
                        img.alt = '房产证图片预览';
                        img.addEventListener('click', function () {
                            window.open(house.lhouseLicensePhoto);
                        });
                        licensePhotoCell.appendChild(img);
                    } else {
                        licensePhotoCell.textContent = '无';
                    }
                    row.appendChild(licensePhotoCell);

                    // 房产证验证状态
                    const licenseStateCell = document.createElement('td');
                    licenseStateCell.textContent = house.lhouseLicenseState || '无';
                    row.appendChild(licenseStateCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');
                    const approveButton = document.createElement('button');
                    approveButton.textContent = '确认';
                    approveButton.setAttribute('class', 'button approve-button');
                    approveButton.addEventListener('click', () => handleApprove(house.houseId, '/admins/landlordHouseCardCheck'));

                    const rejectButton = document.createElement('button');
                    rejectButton.textContent = '拒绝';
                    rejectButton.setAttribute('class', 'button reject-button');
                    rejectButton.addEventListener('click', () => handleReject(house.houseId, '/admins/landlordHouseCardCheck'));

                    actionCell.appendChild(approveButton);
                    actionCell.appendChild(rejectButton);
                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('landlordPropertyCertificateList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function TenantClean() {
        let content = document.getElementById('TenantProfileManagement');
        content.innerHTML = '';
        content = document.getElementById('TenantRentalManagement');
        content.innerHTML = '';
    }

    function showTenantManagement() {
        TenantClean();
        let content = document.getElementById('TenantProfileManagement');
        content.innerHTML = '<li><a onclick="showTenantSearchInfo()" class="small-text">查看个人信息</a></li>' +
            '<li><a onclick="showTenantEditInfo()" class="small-text">修改个人信息</a></li>' +
            '<li><a onclick="showTenantStudentCertification()" class="small-text">学生证认证</a></li>' +
            '<li><a onclick="showTenantIDCertification()" class="small-text">身份证认证</a></li>';
    }

    function showTenantSearchInfo() {
        let content = document.getElementById('content');

        content.innerHTML = `
        <h2>个人信息查询</h2>
        <div id="searchTenantResult"></div>
    `;

        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }
        // 发送 POST 请求
        fetch('/tenant/tenantInfo', {
            method: 'POST',
            headers: {
                'Authorization': token
            },
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                const resultDiv = document.getElementById('searchTenantResult');
                resultDiv.innerHTML = '';

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('main');
                mainTable.setAttribute('class', 'table');
                const headerSection = document.createElement('section');
                headerSection.setAttribute('class', 'header');
                const headTitle = document.createElement('h1');
                headTitle.textContent = '查询结果';
                headerSection.appendChild(headTitle);
                mainTable.appendChild(headerSection);
                const shellSection = document.createElement('section');
                shellSection.setAttribute('class', 'shell');
                const shellTable = document.createElement('table');
                const shellThead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['属性', '值', '操作'].forEach(headerText => {
                    const shellCell = document.createElement('th');
                    shellCell.textContent = headerText;
                    headRow.appendChild(shellCell);
                    shellThead.appendChild(headRow);
                });
                shellTable.appendChild(shellThead);
                const shellTbody = document.createElement('tbody');

                // 渲染数据
                const tenant = data.data;
                const properties = {
                    '账号': tenant.taccount || '无',
                    '姓名': tenant.tname || '无',
                    '性别': tenant.tsex || '无',
                    '出生年月': tenant.tbirth ? tenant.tbirth.split(' ')[0] : '无',
                    '大学': tenant.tuniversity || '无',
                    '专业': tenant.tmajor || '无',
                    '手机号': tenant.tphoneNumber || '无',
                    '邮箱': tenant.temail || '无',
                    '身份认证状态': tenant.tidentityStatus || '无',
                    '学生认证状态': tenant.tstatus || '无',
                    '余额': tenant.tbalance || '无',
                    '个人介绍': tenant.tintroduction || '无'
                };

                Object.keys(properties).forEach(key => {
                    const row = document.createElement('tr');

                    // 属性列
                    const attrCell = document.createElement('td');
                    attrCell.textContent = key;
                    row.appendChild(attrCell);

                    // 值列
                    const valueCell = document.createElement('td');
                    valueCell.textContent = properties[key];
                    row.appendChild(valueCell);

                    // 操作列
                    const actionCell = document.createElement('td');
                    if (key === '余额') {
                        const walletButton = document.createElement('button');
                        walletButton.setAttribute('class', 'button');
                        walletButton.textContent = '查看详情';
                        walletButton.addEventListener('click', () => showTenantWalletDetails(tenant.tbalance));
                        actionCell.appendChild(walletButton);
                    } else {
                        actionCell.textContent = '-'; // 其他属性无操作
                    }
                    row.appendChild(actionCell);

                    shellTbody.appendChild(row);
                });

                shellTable.appendChild(shellTbody);
                shellSection.appendChild(shellTable);
                mainTable.appendChild(shellSection);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('searchTenantResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
            });
    }

    function showTenantWalletDetails(balance) {
        // 创建弹窗容器
        const modal = document.createElement('div');
        modal.setAttribute('class', 'modal');

        // 设置弹窗内容
        modal.innerHTML = `
        <div class="modal-content">
            <h2>钱包详情</h2>
            <p>当前余额：<span id="currentBalance">` + balance + `</span> 元</p>
            <div class="wallet-actions">
                <button id="rechargeButton">充值</button>
                <button id="withdrawButton">提现</button>
            </div>
            <button id="closeModal" class="close-button">关闭</button>
        </div>
    `;

        // 将弹窗添加到页面
        document.body.appendChild(modal);

        // 获取弹窗元素
        const currentBalanceElement = document.getElementById('currentBalance');
        const closeModalButton = document.getElementById('closeModal');
        const rechargeButton = document.getElementById('rechargeButton');
        const withdrawButton = document.getElementById('withdrawButton');

        // 关闭弹窗
        closeModalButton.addEventListener('click', () => {
            document.body.removeChild(modal);
            showTenantSearchInfo();
        });

        // 充值功能
        rechargeButton.addEventListener('click', () => {
            const amountString = prompt('请输入充值金额：'); // 获取输入的字符串
            const amount = parseFloat(amountString); // 将字符串转换为浮点数
            if (amount && !isNaN(amount) && Number(amount) > 0) {
                fetch('/payment/tenant', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "<%= token %>" // 添加 token
                    },
                    body: JSON.stringify({amount: Number(amount)})
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.code === 200) {
                            alert('充值成功！');
                            currentBalanceElement.textContent = (parseFloat(balance) + amount).toFixed(2);
                            balance = parseFloat(currentBalanceElement.textContent);
                        } else {
                            alert('充值失败：' + data.message);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('充值失败，请稍后重试。');
                    });
            } else {
                alert('请输入有效的充值金额！');
            }
        });

        // 提现功能
        withdrawButton.addEventListener('click', () => {
            const amountString = prompt('请输入提现金额(提现手续费1%)：'); // 获取输入的字符串
            let amount = parseFloat(amountString); // 将字符串转换为浮点数
            amount = amount * 1.01;
            if (amount && !isNaN(amount) && Number(amount) > 0 && Number(amount) <= balance) {
                fetch('/payment/tenant', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "<%= token %>" // 添加 token
                    },
                    body: JSON.stringify({amount: Number(-amount)})
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.code === 200) {
                            alert('提现成功！');
                            currentBalanceElement.textContent = (parseFloat(balance) - amount).toFixed(2);
                            balance = parseFloat(currentBalanceElement.textContent);
                        } else {
                            alert('提现失败：' + data.message);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('提现失败，请稍后重试。');
                    });
            } else if (Number(amount) > balance) {
                alert('提现金额不能大于余额！');
            } else {
                alert('请输入有效的提现金额！');
            }
        });
    }

    function showTenantEditInfo() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>修改个人信息</h2>
        <form id="editTenantForm">
            <input type="text" id="tAccount" name="tAccount" placeholder="用户名" class="two">
            <input type="text" id="tPhoneNumber" name="tPhoneNumber" placeholder="手机号" class="two">
            <input type="email" id="tEmail" name="tEmail" placeholder="邮箱" class="two">
            <input type="text" id="tProfilePicture" name="tProfilePicture" placeholder="头像 URL" class="two">
            <textarea id="tIntroduction" name="tIntroduction" placeholder="个人介绍" class="wide-textarea" rows="4"></textarea>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="editTenantResult"></div>
    `;

        // 绑定表单提交事件
        const editTenantForm = document.getElementById('editTenantForm');
        editTenantForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const tenantUpdateReq = {
                taccount: formData.get('tAccount') === "" ? null : formData.get('tAccount'),
                tphoneNumber: formData.get('tPhoneNumber') === "" ? null : formData.get('tPhoneNumber'),
                temail: formData.get('tEmail') === "" ? null : formData.get('tEmail'),
                tprofilePicture: formData.get('tProfilePicture') === "" ? null : formData.get('tProfilePicture'),
                tintroduction: formData.get('tIntroduction') === "" ? null : formData.get('tIntroduction'),
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/tenant/updateTenantInfo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(tenantUpdateReq)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantSearchInfo');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showTenantEditInfo', data.message || '修改失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数，传递错误信息
                    handleFail('showTenantEditInfo', '网络错误，请稍后重试');
                });
        });
    }

    function showTenantStudentCertification() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>学生证认证</h2>
        <form id="studentCertificationForm" class="simple-form" enctype="multipart/form-data">
            <input type="text" id="tUniversity" name="tUniversity" placeholder="就读大学" class="one" required>
            <input type="text" id="tMajor" name="tMajor" placeholder="专业" class="one" required>
            <input type="file" id="tProfilePicture" name="tProfilePicture" accept="image/*" class="one" required>
            <div id="imagePreview" class="image-preview"></div> <!-- 图片预览区域 -->
            <button type="submit" class="submit-button">提交认证</button>
        </form>
        <div id="studentCertificationResult"></div>
    `;

        // 实时显示上传的图片
        const tProfilePictureInput = document.getElementById('tProfilePicture');
        const imagePreview = document.getElementById('imagePreview');

        tProfilePictureInput.addEventListener('change', function () {
            // 清空之前的图片预览
            imagePreview.innerHTML = '';

            // 获取用户上传的文件
            const file = tProfilePictureInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file); // 创建临时图片 URL
                img.style.width = '100%'; // 自动调整宽度
                img.style.maxWidth = '300px'; // 设置最大宽度为 300px
                img.style.marginTop = '10px';
                img.alt = '学生证照片预览';
                imagePreview.appendChild(img);
            }
        });

        // 绑定表单提交事件
        const studentCertificationForm = document.getElementById('studentCertificationForm');
        studentCertificationForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/tenant/studentInfo', {
                method: 'POST',
                headers: {
                    'Authorization': token // 设置 Authorization 头部
                },
                body: formData // 直接发送 FormData
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('studentCertificationResult');
                    resultDiv.innerHTML = ''; // 清空之前的结果
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantStudentCertification');
                    } else {
                        // 显示错误信息
                        handleFail('showTenantStudentCertification', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showTenantStudentCertification', '网络错误，请稍后重试');
                });
        });
    }

    function showTenantIDCertification() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>身份证认证</h2>
        <form id="idCertificationForm" class="simple-form" enctype="multipart/form-data">
            <select id="tSex" name="tSex" class="one" required>
                <option value="">选择性别</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
            <input type="text" id="tName" name="tName" placeholder="姓名" class="one" required>
            <input type="text" id="tCardNumber" name="tCardNumber" placeholder="身份证号码" class="one" required>
            <label for="tBirth">出生日期：</label>
            <input type="date" id="tBirth" name="tBirth" class="one" required>
            <label for="tCardImageFront">身份证正面照片：</label>
            <input type="file" id="tCardImageFront" name="tCardImageFront" accept="image/*" class="one" required>
            <label for="tCardImageBack">身份证背面照片：</label>
            <input type="file" id="tCardImageBack" name="tCardImageBack" accept="image/*" class="one" required>
            <div id="idImagePreview" class="image-preview"></div> <!-- 图片预览区域 -->
            <button type="submit" class="submit-button">提交认证</button>
        </form>
        <div id="idCertificationResult"></div>
    `;

        // 实时显示上传的图片
        const tCardImageFrontInput = document.getElementById('tCardImageFront');
        const tCardImageBackInput = document.getElementById('tCardImageBack');
        const idImagePreview = document.getElementById('idImagePreview');

        // 实时显示身份证正面照片
        tCardImageFrontInput.addEventListener('change', function () {
            idImagePreview.innerHTML = ''; // 清空之前的图片预览

            const file = tCardImageFrontInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '身份证正面照片预览';
                idImagePreview.appendChild(img);
            }
        });

        // 实时显示身份证背面照片
        tCardImageBackInput.addEventListener('change', function () {
            const file = tCardImageBackInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '身份证背面照片预览';
                idImagePreview.appendChild(img);
            }
        });

        // 绑定表单提交事件
        const idCertificationForm = document.getElementById('idCertificationForm');
        idCertificationForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/tenant/idCardCheck', {
                method: 'POST',
                headers: {
                    'Authorization': token // 设置 Authorization 头部
                },
                body: formData // 直接发送 FormData
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('idCertificationResult');
                    resultDiv.innerHTML = ''; // 清空之前的结果
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantIDCertification');
                    } else {
                        // 显示错误信息
                        handleFail('showTenantIDCertification', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showTenantIDCertification', '网络错误，请稍后重试');
                });
        });
    }

    function showRentalManagement(){
        TenantClean();
        let content = document.getElementById('TenantRentalManagement');
        content.innerHTML = '<li><a onclick="showTenantHouseSearch()" class="small-text">查看在售房屋</a></li>'+
            '<li><a onclick="showTenantAppointment()" class="small-text">我的预约</a></li>';
    }

    function showTenantHouseSearch() {
        let content = document.getElementById('content');

        content.innerHTML = `
    <h2>房源信息查询</h2>
    <div id="searchTenantHouseResult"></div>
    `;

        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        const QueryHouseReq = {
            lhouseLicenseState: '已审核',
        };
        // 发送 POST 请求
        fetch('/house/getHouseList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(QueryHouseReq)
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                const resultDiv = document.getElementById('searchTenantHouseResult');
                resultDiv.innerHTML = '';

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }
                if (data.data.length === 0) {
                    resultDiv.innerHTML = `<p>暂无房源信息</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('main');
                mainTable.setAttribute('class', 'table');
                const headerSection = document.createElement('section');
                headerSection.setAttribute('class', 'header');
                const headTitle = document.createElement('h1');
                headTitle.textContent = '查询结果';
                headerSection.appendChild(headTitle);
                mainTable.appendChild(headerSection);
                const shellSection = document.createElement('section');
                shellSection.setAttribute('class', 'shell');
                const shellTable = document.createElement('table');
                const shellThead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房源名称', '房产证验证状态', '总租户数量', '剩余空闲数量', '操作'].forEach(headerText => {
                    const shellCell = document.createElement('th');
                    shellCell.textContent = headerText;
                    headRow.appendChild(shellCell);
                    shellThead.appendChild(headRow);
                });
                shellTable.appendChild(shellThead);
                const shellTbody = document.createElement('tbody');

                // 渲染数据
                const houseList = data.data.houseList;
                houseList.forEach(item => {
                    const row = document.createElement('tr');
                    ['htitle', 'lhouseLicenseState', 'htotalTenants', 'hremainingVacancies'].forEach(key => {
                        const cell = document.createElement('td');
                        cell.textContent = item[key];
                        row.appendChild(cell);
                    });
                    const actionCell = document.createElement('td');

                    // 查看详情按钮
                    const houseButton = document.createElement('button');
                    houseButton.setAttribute('class', 'button');
                    houseButton.textContent = '查看详情';
                    houseButton.addEventListener('click', () => showHouseDetails(item['houseId']));
                    actionCell.appendChild(houseButton);

                    // 查看房源对应租客按钮
                    const TenantButton = document.createElement('button');
                    TenantButton.setAttribute('class', 'button');
                    TenantButton.textContent = '查看租客';
                    TenantButton.addEventListener('click', () => showHouseTenant(item['houseId']));
                    actionCell.appendChild(TenantButton);

                    // 提交预约按钮
                    const AppointmentButton = document.createElement('button');
                    AppointmentButton.setAttribute('class', 'button');
                    AppointmentButton.textContent = '预约';
                    AppointmentButton.addEventListener('click', () => showHouseAppointment(item['houseId']));
                    actionCell.appendChild(AppointmentButton);

                    // 提交入住申请按钮
                    const RentButton = document.createElement('button');
                    RentButton.setAttribute('class', 'button');
                    RentButton.textContent = '入住申请';
                    RentButton.addEventListener('click', () => showHouseRent(item['houseId']));
                    actionCell.appendChild(RentButton);

                    row.appendChild(actionCell);
                    shellTbody.appendChild(row);

                    shellTable.appendChild(shellTbody);
                    shellSection.appendChild(shellTable);
                    mainTable.appendChild(shellSection);
                    resultDiv.appendChild(mainTable);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('searchTenantHouseResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
            });
    }

    function showHouseTenant(houseId) {
        const content = document.getElementById('content');

        // 清空内容并设置标题
        content.innerHTML = `
    <h2>房屋租户信息</h2>
    <div id="tenantListResult"></div>
    `;

        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造查询条件
        const queryParams = {
            houseId: houseId
        };

        // 发送 POST 请求获取租户列表
        fetch('/tenant/getTenantListByHouseId', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(queryParams)
        })
            .then(response => response.json())
            .then(data => {

                const resultDiv = document.getElementById('tenantListResult');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                if (data.data.tenantList.length === 0) {
                    resultDiv.innerHTML = `<p>暂无租户信息</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['租户姓名', '性别', '学校', '联系方式', '个人介绍'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.tenantList.forEach(tenant => {
                    const row = document.createElement('tr');

                    // 租户姓名
                    const nameCell = document.createElement('td');
                    nameCell.textContent = tenant.tname || '未知';
                    row.appendChild(nameCell);

                    // 性别
                    const sexCell = document.createElement('td');
                    sexCell.textContent = tenant.tsex || '未知';
                    row.appendChild(sexCell);

                    // 学校
                    const universityCell = document.createElement('td');
                    universityCell.textContent = tenant.tuniversity || '未知';
                    row.appendChild(universityCell);

                    // 联系方式（仅展示部分信息）
                    const contactCell = document.createElement('td');
                    const maskedPhoneNumber = tenant.tphoneNumber
                        ? tenant.tphoneNumber.slice(0, 3) + '****' + tenant.tphoneNumber.slice(-4)
                        : '未知';
                    contactCell.textContent = maskedPhoneNumber;
                    row.appendChild(contactCell);

                    // 个人介绍
                    const introductionCell = document.createElement('td');
                    introductionCell.textContent = tenant.tintroduction || '无';
                    row.appendChild(introductionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('tenantListResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function showHouseAppointment(houseId) {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>预约房源</h2>
        <form id="houseAppointmentForm" class="simple-form">
            <label for="appointmentDate">选择预约日期：</label>
            <input type="datetime-local" id="appointmentDate" name="appointmentDate" class="one" required>
            <button type="submit" class="submit-button">提交预约</button>
        </form>
        <div id="appointmentResult"></div>
    `;

        // 绑定表单提交事件
        const houseAppointmentForm = document.getElementById('houseAppointmentForm');
        houseAppointmentForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            let appointmentDate = formData.get('appointmentDate');

            if (!appointmentDate) {
                alert('请选择预约日期');
                return;
            }

            // 不进行时区转换，直接使用用户输入的时间
            // 格式化预约日期为符合要求的格式 (yyyy-MM-dd HH:mm:ss)
            appointmentDate = appointmentDate.replace('T', ' ') + ':00';

            // 构造请求参数
            const requestBody = {
                houseId: houseId,
                appointmentDate: appointmentDate
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/appointments/submit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantHouseSearch');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showTenantHouseSearch', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showTenantHouseSearch', '网络错误，请稍后重试');
                });
        });
    }

    function showHouseRent(houseId) {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>租赁房源</h2>
        <form id="houseRentForm" class="simple-form">
            <p>确认要租赁该房源吗？</p>
            <button type="submit" class="submit-button">提交租赁请求</button>
        </form>
        <div id="rentResult"></div>
    `;

        // 绑定表单提交事件
        const houseRentForm = document.getElementById('houseRentForm');
        houseRentForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 构造请求参数
            const requestBody = {
                houseId: houseId
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/house/rentHouse', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('rentResult');
                    resultDiv.innerHTML = ''; // 清空之前的结果

                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantHouseSearch');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showTenantHouseSearch', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showTenantHouseSearch', '网络错误，请稍后重试');
                });
        });
    }

    function showTenantAppointment() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染标题和结果容器
        content.innerHTML = `
        <h2>我的预约</h2>
        <div id="tenantAppointmentList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 发送 POST 请求获取预约列表
        fetch('/appointments/view/tenant', {
            method: 'POST',
            headers: {
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);

                const resultDiv = document.getElementById('tenantAppointmentList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：`+data.message+`</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房源名称', '房东姓名', '预约日期', '状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.forEach(appointment => {
                    const row = document.createElement('tr');

                    // 房源名称
                    const houseIdCell = document.createElement('td');
                    houseIdCell.textContent = appointment.htitle || '无';
                    row.appendChild(houseIdCell);

                    // 房东姓名
                    const landlordIdCell = document.createElement('td');
                    landlordIdCell.textContent = appointment.lname || '无';
                    row.appendChild(landlordIdCell);

                    // 预约日期
                    const appointmentDateCell = document.createElement('td');
                    appointmentDateCell.textContent = new Date(appointment.appointmentDate).toLocaleString() || '无';
                    row.appendChild(appointmentDateCell);

                    // 状态
                    const statusCell = document.createElement('td');
                    statusCell.textContent = appointment.status || '无';
                    row.appendChild(statusCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');

                    // 修改按钮
                    const modifyButton = document.createElement('button');
                    modifyButton.textContent = '修改';
                    modifyButton.setAttribute('class', 'button modify-button');
                    modifyButton.addEventListener('click', () => handleModifyAppointment(appointment.appointmentId));
                    actionCell.appendChild(modifyButton);

                    // 取消按钮
                    const cancelButton = document.createElement('button');
                    cancelButton.textContent = '取消';
                    cancelButton.setAttribute('class', 'button cancel-button');
                    cancelButton.addEventListener('click', () => handleCancelAppointment(appointment.appointmentId));
                    actionCell.appendChild(cancelButton);

                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('tenantAppointmentList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    // 修改预约
    function handleModifyAppointment(appointmentId) {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>修改预约时间</h2>
        <form id="modifyAppointmentForm" class="simple-form">
            <label for="appointmentDate">选择新的预约日期：</label>
            <input type="datetime-local" id="appointmentDate" name="appointmentDate" class="one" required>
            <button type="submit" class="submit-button">提交修改</button>
        </form>
        <div id="modifyAppointmentResult"></div>
    `;

        // 绑定表单提交事件
        const modifyAppointmentForm = document.getElementById('modifyAppointmentForm');
        modifyAppointmentForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            let appointmentDate = formData.get('appointmentDate');

            if (!appointmentDate) {
                alert('请选择新的预约日期');
                return;
            }

            // 不进行时区转换，直接使用用户输入的时间
            // 格式化预约日期为符合要求的格式 (yyyy-MM-dd HH:mm:ss)
            appointmentDate = appointmentDate.replace('T', ' ') + ':00';

            // 构造请求参数
            const requestBody = {
                appointmentId: appointmentId,
                appointmentDate: appointmentDate
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/appointments/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showTenantAppointment');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showTenantAppointment', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showTenantAppointment', '网络错误，请稍后重试');
                });
        });
    }

    // 取消预约
    function handleCancelAppointment(appointmentId) {
        // 确认取消操作
        if (!confirm('您确定要取消此预约吗？')) {
            return;
        }

        // 构造请求参数
        const requestBody = {
            appointmentId: appointmentId
        };

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 发送 POST 请求
        fetch('/appointments/cancel', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    // 调用成功回调函数
                    handleSuccess('showTenantAppointment');
                } else {
                    // 调用失败回调函数，传递失败原因
                    handleFail('showTenantAppointment', data.message || '取消失败');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                // 调用失败回调函数
                handleFail('showTenantAppointment', '网络错误，请稍后重试');
            });
    }




    function TenantRentClean() {
        let content = document.getElementById('TenantRentManagement');
        content.innerHTML = '';
    }
    function showTenantRentManagement() {
        TenantRentClean();
        let content = document.getElementById('TenantRentManagement');
        content.innerHTML = '' +
            '<li><a onclick="showTenantRentInfo()" class="small-text">查看并支付房租押金</a></li>';
    }

    // 支付租金函数
    function payDeposit(transactionId) {
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return Promise.reject();
        }

        const payload = {
            transactionId: transactionId
        };

        return fetch('/rent/payRent', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(payload)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('支付成功！');
                    showTenantRentInfo(); // 重新加载数据
                    return Promise.resolve(); // 返回一个成功的 Promise
                } else {
                    alert('支付失败：' + data.message);
                    return Promise.reject(); // 返回一个失败的 Promise
                }
            })
            .catch(error => {
                console.error('支付请求异常:', error);
                alert('支付失败，请稍后再试。');
                return Promise.reject(); // 返回一个失败的 Promise
            });
    }


    function TenantContactsClean(){
        let content = document.getElementById('TenantContactsManagement');
        content.innerHTML='';
    }

    function showTenantContactsManagement() {
        TenantContactsClean(); // 如果需要清除其他内容，调用清理函数

        let content = document.getElementById('TenantContactsManagement');
        content.innerHTML = `<li><a onclick="showTenantContractList()" class="small-text">查看合同列表</a></li>

        `;
    }



    // 租户查看合同列表
    function showTenantContractList() {
        const content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
        <h2>租户合同列表</h2>
        <div id="tenantContractsInfoResult"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用租户查看合同列表接口
        fetch('/contacts/viewTenantContracts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('tenantContractsInfoResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    // 如果接口返回错误信息，显示错误
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同列表
                const contractsList = data.data.contractsList;

                if (contractsList.length === 0) {
                    resultDiv.innerHTML = `<p>暂无合同信息</p>`;
                    return;
                }

                // 创建表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['合同ID', '开始时间', '结束时间', '租金金额', '房屋ID', '押金金额', '附加条款', '合同状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractsList.forEach(contract => {
                    const row = document.createElement('tr');

                    // 合同字段映射到表格中
                    [
                        contract.contractId,
                        contract.cstartDate,
                        contract.cendDate,
                        contract.crentAmount.toFixed(2),
                        contract.chouseId,
                        contract.cdepositAmount.toFixed(2),
                        contract.cadditions || '无',
                        contract.cstatus
                    ].forEach(cellData => {
                        const td = document.createElement('td');
                        td.textContent = cellData;
                        td.style.border = '1px solid #ddd';
                        td.style.padding = '8px';
                        row.appendChild(td);
                    });

                    // 添加"查看详情"按钮
                    const tdAction = document.createElement('td');
                    const detailButton = document.createElement('button');
                    detailButton.textContent = '查看详情';
                    detailButton.style.padding = '5px 10px';
                    detailButton.style.margin = '5px';
                    detailButton.style.backgroundColor = '#4CAF50';
                    detailButton.style.color = 'white';
                    detailButton.style.border = 'none';
                    detailButton.style.cursor = 'pointer';

                    // 为每个按钮添加点击事件，传递合同ID
                    detailButton.addEventListener('click', () => {
                        showTenantContractDetails(contract.contractId); // 调用查看详情的函数
                    });

                    tdAction.appendChild(detailButton);

                    // 只有当合同状态为"未生效"，才显示确认和拒绝按钮
                    if (contract.cstatus === '未生效') {
                        // 确认合同按钮
                        const confirmButton = document.createElement('button');
                        confirmButton.textContent = '确认合同';
                        confirmButton.style.padding = '5px 10px';
                        confirmButton.style.margin = '5px';
                        confirmButton.style.backgroundColor = '#007BFF';
                        confirmButton.style.color = 'white';
                        confirmButton.style.border = 'none';
                        confirmButton.style.cursor = 'pointer';

                        // 为每个"确认合同"按钮添加点击事件，传递合同ID
                        confirmButton.addEventListener('click', () => {
                            confirmContract(contract.contractId); // 调用确认合同的函数
                        });

                        tdAction.appendChild(confirmButton);

                        // 拒绝合同按钮
                        const rejectButton = document.createElement('button');
                        rejectButton.textContent = '拒绝合同';
                        rejectButton.style.padding = '5px 10px';
                        rejectButton.style.margin = '5px';
                        rejectButton.style.backgroundColor = '#FF5733';
                        rejectButton.style.color = 'white';
                        rejectButton.style.border = 'none';
                        rejectButton.style.cursor = 'pointer';

                        // 为每个"拒绝合同"按钮添加点击事件，传递合同ID
                        rejectButton.addEventListener('click', () => {
                            rejectContract(contract.contractId); // 调用拒绝合同的函数
                        });

                        tdAction.appendChild(rejectButton);
                    }

                    row.appendChild(tdAction);
                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                const resultDiv = document.getElementById('tenantContractsInfoResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }


    // 租户确认合同
    function confirmContract(contractId) {
        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用确认合同接口
        fetch('/contacts/confirmContract', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('合同确认成功！');
                    showTenantContractList(); // 确认成功后刷新合同列表
                } else {
                    alert('合同确认失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                alert('合同确认失败，请稍后重试。');
            });
    }

    // 租户拒绝合同
    function rejectContract(contractId) {
        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用拒绝合同接口
        fetch('/contacts/rejectContract', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('合同已拒绝！');
                    showTenantContractList(); // 拒绝成功后刷新合同列表
                } else {
                    alert('合同拒绝失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                alert('合同拒绝失败，请稍后重试。');
            });
    }


    // 租户查看合同详情
    // 租户查看合同详情
    function showTenantContractDetails(contractId) {
        let content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
        <h2>合同详细信息</h2>
        <button onclick="goBackToTenantContracts()" class="back-button">返回合同列表</button>
        <div id="tenantContractDetailResult"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用合同详细信息接口
        fetch('/contacts/getContractsDetail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('tenantContractDetailResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    // 如果接口返回错误信息，显示错误
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同详情
                const contract = data.data;

                // 构建合同详情表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 合同详情的键值对
                const contractDetails = [
                    { key: '合同名称', value: contract.tname || '未提供' },
                    { key: '开始时间', value: contract.cstartDate },
                    { key: '结束时间', value: contract.cendDate },
                    { key: '租金金额', value: contract.crentAmount.toFixed(2) },
                    { key: '押金金额', value: contract.cdepositAmount.toFixed(2) },
                    { key: '附加条款', value: contract.cadditions || '无' },
                    { key: '房东名称', value: contract.lname || '未提供' }
                ];

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['字段', '值'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractDetails.forEach(detail => {
                    const row = document.createElement('tr');

                    // 字段名
                    const keyCell = document.createElement('td');
                    keyCell.textContent = detail.key;
                    keyCell.style.border = '1px solid #ddd';
                    keyCell.style.padding = '8px';

                    // 字段值
                    const valueCell = document.createElement('td');
                    valueCell.textContent = detail.value;
                    valueCell.style.border = '1px solid #ddd';
                    valueCell.style.padding = '8px';

                    row.appendChild(keyCell);
                    row.appendChild(valueCell);
                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                const resultDiv = document.getElementById('tenantContractDetailResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }





    // 返回租户合同列表的函数
    function goBackToTenantContracts() {
        // 返回到租户合同列表页面
        showTenantContractList(); // 直接调用查看租户合同列表的函数
    }




    function LandlordClean() {
        let content = document.getElementById('landlordProfileManagement');
        content.innerHTML = '';
        content = document.getElementById('landlordHouseManagement');
        content.innerHTML = '';
    }

    function showLandlordManagement() {
        LandlordClean();
        let content = document.getElementById('landlordProfileManagement');
        content.innerHTML = '<li><a onclick="showLandlordSearchInfo()" class="small-text">查看个人信息</a></li>' +
            '<li><a onclick="showLandlordEditInfo()" class="small-text">修改个人信息</a></li>' +
            '<li><a onclick="showLandlordIDCertification()" class="small-text">身份证认证</a></li>';
    }

    function showLandlordHouseManagement() {
        LandlordClean();
        let content = document.getElementById('landlordHouseManagement');
        content.innerHTML = '<li><a onclick="showLandlordHouseSearch()" class="small-text">个人房源查询</a></li>' +
            '<li><a onclick="showLandlordHouseAdd()" class="small-text">添加房源</a></li>'+
            '<li><a onclick="showLandlordAppointment()" class="small-text">查看预约</a></li>';
    }

    function showLandlordSearchInfo() {
        let content = document.getElementById('content');

        content.innerHTML = `
        <h2>个人信息查询</h2>
        <div id="searchLandlordResult"></div>
    `;

        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 发送 POST 请求
        fetch('/landlords/landlordInfo', {
            method: 'POST',
            headers: {
                'Authorization': token
            },
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                const resultDiv = document.getElementById('searchLandlordResult');
                resultDiv.innerHTML = '';

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('main');
                mainTable.setAttribute('class', 'table');
                const headerSection = document.createElement('section');
                headerSection.setAttribute('class', 'header');
                const headTitle = document.createElement('h1');
                headTitle.textContent = '查询结果';
                headerSection.appendChild(headTitle);
                mainTable.appendChild(headerSection);
                const shellSection = document.createElement('section');
                shellSection.setAttribute('class', 'shell');
                const shellTable = document.createElement('table');
                const shellThead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['属性', '值', '操作'].forEach(headerText => {
                    const shellCell = document.createElement('th');
                    shellCell.textContent = headerText;
                    headRow.appendChild(shellCell);
                    shellThead.appendChild(headRow);
                });
                shellTable.appendChild(shellThead);
                const shellTbody = document.createElement('tbody');

                // 渲染数据
                const landlord = data.data;
                const properties = {
                    '房东账号': landlord.laccount || '无',
                    '姓名': landlord.lname || '无',
                    '电话': landlord.lphoneNumber || '无',
                    '邮箱': landlord.lemail || '无',
                    '余额': landlord.lbalance || '无'
                };

                Object.keys(properties).forEach(key => {
                    const row = document.createElement('tr');

                    // 属性列
                    const attrCell = document.createElement('td');
                    attrCell.textContent = key;
                    row.appendChild(attrCell);

                    // 值列
                    const valueCell = document.createElement('td');
                    valueCell.textContent = properties[key];
                    row.appendChild(valueCell);

                    // 操作列
                    const actionCell = document.createElement('td');
                    if (key === '余额') {
                        const walletButton = document.createElement('button');
                        walletButton.setAttribute('class', 'button');
                        walletButton.textContent = '查看详情';
                        walletButton.addEventListener('click', () => showLandlordWalletDetails(landlord.lbalance));
                        actionCell.appendChild(walletButton);
                    } else {
                        actionCell.textContent = '-'; // 其他属性无操作
                    }
                    row.appendChild(actionCell);

                    shellTbody.appendChild(row);
                });

                shellTable.appendChild(shellTbody);
                shellSection.appendChild(shellTable);
                mainTable.appendChild(shellSection);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('searchLandlordResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
            });
    }

    function showLandlordWalletDetails(balance) {
        // 创建弹窗容器
        const modal = document.createElement('div');
        modal.setAttribute('class', 'modal');

        // 设置弹窗内容
        modal.innerHTML = `
        <div class="modal-content">
            <h2>钱包详情</h2>
            <p>当前余额：<span id="currentBalance">` + balance + `</span> 元</p>
            <div class="wallet-actions">
                <button id="rechargeButton">充值</button>
                <button id="withdrawButton">提现</button>
            </div>
            <button id="closeModal" class="close-button">关闭</button>
        </div>
    `;

        // 将弹窗添加到页面
        document.body.appendChild(modal);

        // 获取弹窗元素
        const currentBalanceElement = document.getElementById('currentBalance');
        const closeModalButton = document.getElementById('closeModal');
        const rechargeButton = document.getElementById('rechargeButton');
        const withdrawButton = document.getElementById('withdrawButton');

        // 关闭弹窗
        closeModalButton.addEventListener('click', () => {
            document.body.removeChild(modal);
            showLandlordSearchInfo();
        });

        // 充值功能
        rechargeButton.addEventListener('click', () => {
            const amountString = prompt('请输入充值金额：'); // 获取输入的字符串
            const amount = parseFloat(amountString); // 将字符串转换为浮点数
            if (amount && !isNaN(amount) && Number(amount) > 0) {
                fetch('/payment/landlord', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "<%= token %>" // 添加 token
                    },
                    body: JSON.stringify({amount: Number(amount)})
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.code === 200) {
                            alert('充值成功！');
                            currentBalanceElement.textContent = (parseFloat(balance) + amount).toFixed(2);
                            balance = parseFloat(currentBalanceElement.textContent);
                        } else {
                            alert('充值失败：' + data.message);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('充值失败，请稍后重试。');
                    });
            } else {
                alert('请输入有效的充值金额！');
            }
        });

        // 提现功能
        withdrawButton.addEventListener('click', () => {
            const amountString = prompt('请输入提现金额(提现手续费1%)：'); // 获取输入的字符串
            let amount = parseFloat(amountString); // 将字符串转换为浮点数
            amount = amount * 1.01;
            if (amount && !isNaN(amount) && Number(amount) > 0 && Number(amount) <= balance) {
                fetch('/payment/landlord', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "<%= token %>" // 添加 token
                    },
                    body: JSON.stringify({amount: Number(-amount)})
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.code === 200) {
                            alert('提现成功！');
                            currentBalanceElement.textContent = (parseFloat(balance) - amount).toFixed(2);
                            balance = parseFloat(currentBalanceElement.textContent);
                        } else {
                            alert('提现失败：' + data.message);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('提现失败，请稍后重试。');
                    });
            } else if (Number(amount) > balance) {
                alert('提现金额不能大于余额！');
            } else {
                alert('请输入有效的提现金额！');
            }
        });
    }

    function showLandlordEditInfo() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>修改个人信息</h2>
        <form id="editLandlordForm">
            <input type="text" id="laccount" name="laccount" placeholder="用户名" class="two">
            <input type="text" id="lphoneNumber" name="lphoneNumber" placeholder="手机号" class="two">
            <input type="email" id="lemail" name="lemail" placeholder="邮箱" class="two">
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="editLandlordResult"></div>
    `;

        // 绑定表单提交事件
        const editLandlordForm = document.getElementById('editLandlordForm');
        editLandlordForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const landlordUpdateReq = {
                laccount: formData.get('laccount') === "" ? null : formData.get('laccount'),
                lphoneNumber: formData.get('lphoneNumber') === "" ? null : formData.get('lphoneNumber'),
                lemail: formData.get('lemail') === "" ? null : formData.get('lemail'),
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/landlords/updateLandlordInfo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(landlordUpdateReq)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showLandlordSearchInfo');
                    } else {
                        // 调用失败回调函数，传递失败原因
                        handleFail('showLandlordEditInfo', data.message || '修改失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数，传递错误信息
                    handleFail('showLandlordEditInfo', '网络错误，请稍后重试');
                });
        });
    }

    function showLandlordIDCertification() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>身份证认证</h2>
        <form id="idCertificationForm" class="simple-form" enctype="multipart/form-data">
            <input type="text" id="tName" name="tName" placeholder="姓名" class="one" required>
            <input type="text" id="tCardNumber" name="tCardNumber" placeholder="身份证号码" class="one" required>
            <label for="tCardImageFront">身份证正面照片：</label>
            <input type="file" id="tCardImageFront" name="tCardImageFront" accept="image/*" class="one" required>
            <label for="tCardImageBack">身份证背面照片：</label>
            <input type="file" id="tCardImageBack" name="tCardImageBack" accept="image/*" class="one" required>
            <div id="idImagePreview" class="image-preview"></div> <!-- 图片预览区域 -->
            <button type="submit" class="submit-button">提交认证</button>
        </form>
        <div id="idCertificationResult"></div>
    `;

        // 实时显示上传的图片
        const tCardImageFrontInput = document.getElementById('tCardImageFront');
        const tCardImageBackInput = document.getElementById('tCardImageBack');
        const idImagePreview = document.getElementById('idImagePreview');

        // 实时显示身份证正面照片
        tCardImageFrontInput.addEventListener('change', function () {
            idImagePreview.innerHTML = ''; // 清空之前的图片预览

            const file = tCardImageFrontInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '身份证正面照片预览';
                idImagePreview.appendChild(img);
            }
        });

        // 实时显示身份证背面照片
        tCardImageBackInput.addEventListener('change', function () {
            const file = tCardImageBackInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '身份证背面照片预览';
                idImagePreview.appendChild(img);
            }
        });

        // 绑定表单提交事件
        const idCertificationForm = document.getElementById('idCertificationForm');
        idCertificationForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/landlords/landlordIdCardCheck', {
                method: 'POST',
                headers: {
                    'Authorization': token // 设置 Authorization 头部
                },
                body: formData // 直接发送 FormData
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('idCertificationResult');
                    resultDiv.innerHTML = ''; // 清空之前的结果
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showLandlordIDCertification');
                    } else {
                        // 显示错误信息
                        handleFail('showLandlordIDCertification', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showLandlordIDCertification', '网络错误，请稍后重试');
                });
        });
    }

    function showLandlordHouseSearch() {
        let content = document.getElementById('content');

        content.innerHTML = `
    <h2>房源信息查询</h2>
    <div id="searchLandlordHouseResult"></div>
    `;

        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        const QueryHouseReq = {
            landlordId: "<%= userTokenInfoDto.getUserId() %>",
        };
        // 发送 POST 请求
        fetch('/house/getHouseList', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(QueryHouseReq)
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                const resultDiv = document.getElementById('searchLandlordHouseResult');
                resultDiv.innerHTML = '';

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }
                if (data.data.length === 0) {
                    resultDiv.innerHTML = `<p>暂无房源信息</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('main');
                mainTable.setAttribute('class', 'table');
                const headerSection = document.createElement('section');
                headerSection.setAttribute('class', 'header');
                const headTitle = document.createElement('h1');
                headTitle.textContent = '查询结果';
                headerSection.appendChild(headTitle);
                mainTable.appendChild(headerSection);
                const shellSection = document.createElement('section');
                shellSection.setAttribute('class', 'shell');
                const shellTable = document.createElement('table');
                const shellThead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房源ID', '房产证验证状态', '总租户数量', '剩余空闲数量', '操作'].forEach(headerText => {
                    const shellCell = document.createElement('th');
                    shellCell.textContent = headerText;
                    headRow.appendChild(shellCell);
                    shellThead.appendChild(headRow);
                });
                shellTable.appendChild(shellThead);
                const shellTbody = document.createElement('tbody');

                // 渲染数据
                const houseList = data.data.houseList;
                houseList.forEach(item => {
                    const row = document.createElement('tr');
                    ['houseId', 'lhouseLicenseState', 'htotalTenants', 'hremainingVacancies'].forEach(key => {
                        const cell = document.createElement('td');
                        cell.textContent = item[key];
                        row.appendChild(cell);
                    });
                    const actionCell = document.createElement('td');

                    // 查看详情按钮
                    const houseButton = document.createElement('button');
                    houseButton.setAttribute('class', 'button');
                    houseButton.textContent = '查看详情';
                    houseButton.addEventListener('click', () => showHouseDetails(item['houseId']));
                    actionCell.appendChild(houseButton);

                    // 编辑房源按钮
                    const editButton = document.createElement('button');
                    editButton.setAttribute('class', 'button');
                    editButton.textContent = '编辑房源';
                    editButton.addEventListener('click', () => showLandlordHouseEdit(item['houseId']));
                    actionCell.appendChild(editButton);

                    // 上传房产证按钮
                    const certificationButton = document.createElement('button');
                    certificationButton.setAttribute('class', 'button');
                    certificationButton.textContent = '上传房产证';
                    certificationButton.addEventListener('click', () => showLandlordHouseCertification(item['houseId']));
                    actionCell.appendChild(certificationButton);

                    row.appendChild(actionCell);
                    shellTbody.appendChild(row);

                    shellTable.appendChild(shellTbody);
                    shellSection.appendChild(shellTable);
                    mainTable.appendChild(shellSection);
                    resultDiv.appendChild(mainTable);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('searchLandlordHouseResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
            });
    }

    function showHouseDetails(houseId) {
        let content = document.getElementById('content');

        content.innerHTML = `
        <h2>房源详细信息</h2>
        <div id="searchHouseDetailResult"></div>
    `;
        // 获取 token
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        const HouseIdReq = {
            houseId: houseId,
        };
        // 发送 POST 请求
        fetch('/house/getHouseDetail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            body: JSON.stringify(HouseIdReq)
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                const resultDiv = document.getElementById('searchHouseDetailResult');
                resultDiv.innerHTML = '';

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('main');
                mainTable.setAttribute('class', 'table');
                const headerSection = document.createElement('section');
                headerSection.setAttribute('class', 'header');
                const headTitle = document.createElement('h1');
                headTitle.textContent = '查询结果';
                headerSection.appendChild(headTitle);
                mainTable.appendChild(headerSection);
                const shellSection = document.createElement('section');
                shellSection.setAttribute('class', 'shell');
                const shellTable = document.createElement('table');
                const shellThead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['属性', '值'].forEach(headerText => {
                    const shellCell = document.createElement('th');
                    shellCell.textContent = headerText;
                    headRow.appendChild(shellCell);
                    shellThead.appendChild(headRow);
                });
                shellTable.appendChild(shellThead);
                const shellTbody = document.createElement('tbody');

                // 渲染数据
                const house = data.data;
                const properties = {
                    '房源标题': house.htitle || '无',
                    '房源位置': house.hlocation || '无',
                    '租金': house.hrent || '无',
                    '面积（平方米）': house.harea || '无',
                    '房间布局': house.hrooms || '无',
                    '可入住时间': house.havailableFrom || '无',
                    '朝向': house.hdirection || '无',
                    '总楼层数': house.htotalFloors || '无',
                    '楼层': house.hfloor || '无',
                    '配套设施': house.hfacilities || '无',
                    '是否允许宠物': house.hpetFriendly || '无',
                    '租客要求': house.htenantrequired || '无',
                    '总租户数量': house.htotalTenants || '无',
                    '剩余空闲数量': house.hremainingVacancies || '无',
                    '房产证图片': house.lhouseLicensePhoto || '无',
                    '房屋图片': house.lhousePhoto || '无',
                };

                Object.keys(properties).forEach(key => {
                    const row = document.createElement('tr');

                    // 属性列
                    const attrCell = document.createElement('td');
                    attrCell.textContent = key;
                    row.appendChild(attrCell);

                    // 值列
                    const valueCell = document.createElement('td');
                    if (key === '房产证图片' || key === '房屋图片') {
                        const img = document.createElement('img');
                        img.src = properties[key];
                        img.style.width = 'auto'; // 按图片原比例宽度
                        img.style.height = 'auto'; // 按图片原比例高度
                        img.style.maxWidth = '300px'; // 最大宽度限制
                        img.style.maxHeight = '200px'; // 最大高度限制
                        img.style.marginTop = '10px';
                        img.style.borderRadius = '0'; // 去除圆角，防止椭圆形
                        img.alt = '图片预览';
                        img.addEventListener('click', function () {
                            window.open(properties[key]);
                        });
                        valueCell.appendChild(img);
                    } else valueCell.textContent = properties[key];
                    row.appendChild(valueCell);

                    shellTbody.appendChild(row);
                });

                shellTable.appendChild(shellTbody);
                shellSection.appendChild(shellTable);
                mainTable.appendChild(shellSection);
                resultDiv.appendChild(mainTable);
            })

    }

    function showLandlordHouseEdit(houseId) {
        const content = document.getElementById('content');

        // 渲染空表单 HTML
        content.innerHTML = `
        <h2>修改房源信息</h2>
        <form id="editHouseForm">
            <input type="text" id="htitle" name="htitle" class="three" placeholder="房源标题">
            <input type="number" id="hfloor" name="hfloor" class="three" placeholder="楼层">
            <input type="number" id="htotalFloors" name="htotalFloors" class="three" placeholder="总楼层数">
            <input type="number" id="hrent" name="hrent" class="three" placeholder="租金">
            <input type="number" id="harea" name="harea" class="three" placeholder="面积（平方米）">
            <input type="number" id="htotalTenants" name="htotalTenants" class="three" placeholder="总租户数量">
            <input type="number" id="hremainingVacancies" name="hremainingVacancies" class="three" placeholder="剩余空闲数量">
            <input type="datetime-local" id="havailableFrom" name="havailableFrom" class="three" placeholder="可入住时间">
            <select id="hpetFriendly" name="hpetFriendly" class="three">
                <option value="">是否同意养宠物</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <textarea id="hrooms" name="hrooms" placeholder="房间布局" class="wide-textarea" rows="2"></textarea>
            <textarea id="hlocation" name="hlocation" placeholder="房源位置" class="wide-textarea" rows="2"></textarea>
            <textarea id="hfacilities" name="hfacilities" placeholder="配套设施" class="wide-textarea" rows="4"></textarea>
            <textarea id="htenantrequired" name="htenantrequired" placeholder="租客要求" class="wide-textarea" rows="4"></textarea>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="editHouseResult"></div>
    `;

        // 绑定表单提交事件
        const editHouseForm = document.getElementById('editHouseForm');
        editHouseForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const houseUpdateReq = {
                houseId: houseId, // 当前房源 ID
                htitle: formData.get('htitle') === "" ? null : formData.get('htitle'),
                hfloor: formData.get('hfloor') === "" ? null : parseInt(formData.get('hfloor')),
                htotalFloors: formData.get('htotalFloors') === "" ? null : parseInt(formData.get('htotalFloors')),
                hrent: formData.get('hrent') === "" ? null : parseFloat(formData.get('hrent')),
                harea: formData.get('harea') === "" ? null : parseFloat(formData.get('harea')),
                htotalTenants: formData.get('htotalTenants') === "" ? null : parseInt(formData.get('htotalTenants')),
                hremainingVacancies: formData.get('hremainingVacancies') === "" ? null : parseInt(formData.get('hremainingVacancies')),
                havailableFrom: formData.get('havailableFrom') === "" ? null : formData.get('havailableFrom'),
                hpetFriendly: formData.get('hpetFriendly') === "" ? null : parseInt(formData.get('hpetFriendly')),
                hrooms: formData.get('hrooms') === "" ? null : formData.get('hrooms'),
                hfacilities: formData.get('hfacilities') === "" ? null : formData.get('hfacilities'),
                htenantrequired: formData.get('htenantrequired') === "" ? null : formData.get('htenantrequired'),
                hlocation: formData.get('hlocation') === "" ? null : formData.get('hlocation'),
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }


            // 发送 POST 请求
            fetch('/house/changeHouseInfo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(houseUpdateReq)
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('editHouseResult');
                    if (data.code === 200) {
                        // 成功提示并刷新页面或调用相关函数
                        resultDiv.innerHTML = `<p>修改成功！</p>`;
                        alert("房源信息修改成功！");
                        showLandlordHouseSearch(); // 刷新房源列表
                    } else {
                        // 显示错误信息
                        resultDiv.innerHTML = `<p>修改失败：` + data.message + `</p>`;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('editHouseResult');
                    resultDiv.innerHTML = `<p>网络错误，请稍后再试。</p>`;
                });
        });
    }

    function showLandlordHouseCertification(houseId) {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
    <h2>上传房产证</h2>
    <form id="houseCertificationForm" class="simple-form" enctype="multipart/form-data">
        <label for="lHousePhoto">房屋照片：</label>
        <input type="file" id="lHousePhoto" name="l_house_photo" accept="image/*" class="one" required>
        <label for="lHouseLicensePhoto">房产证照片：</label>
        <input type="file" id="lHouseLicensePhoto" name="l_house_license_photo" accept="image/*" class="one" required>
        <div id="houseImagePreview" class="image-preview"></div> <!-- 图片预览区域 -->
        <button type="submit" class="submit-button">提交</button>
    </form>
    <div id="houseCertificationResult"></div>
    `;

        // 实时显示上传的图片
        const lHousePhotoInput = document.getElementById('lHousePhoto');
        const lHouseLicensePhotoInput = document.getElementById('lHouseLicensePhoto');
        const houseImagePreview = document.getElementById('houseImagePreview');

        // 实时显示房屋照片
        lHousePhotoInput.addEventListener('change', function () {
            houseImagePreview.innerHTML = ''; // 清空之前的图片预览

            const file = lHousePhotoInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '房屋照片预览';
                houseImagePreview.appendChild(img);
            }
        });

        // 实时显示房产证照片
        lHouseLicensePhotoInput.addEventListener('change', function () {
            const file = lHouseLicensePhotoInput.files[0];
            if (file) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.style.width = '100%';
                img.style.maxWidth = '300px';
                img.style.marginTop = '10px';
                img.alt = '房产证照片预览';
                houseImagePreview.appendChild(img);
            }
        });

        // 绑定表单提交事件
        const houseCertificationForm = document.getElementById('houseCertificationForm');
        houseCertificationForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            formData.append('house_id', houseId); // 添加 houseId 到表单数据中

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/house/addHouseCard', {
                method: 'POST',
                headers: {
                    'Authorization': token // 设置 Authorization 头部
                },
                body: formData // 直接发送 FormData
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('houseCertificationResult');
                    resultDiv.innerHTML = ''; // 清空之前的结果
                    if (data.code === 200) {
                        // 调用成功回调函数
                        handleSuccess('showLandlordHouseCertification');
                    } else {
                        // 显示错误信息
                        handleFail('showLandlordHouseCertification', data.message || '提交失败');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    // 调用失败回调函数
                    handleFail('showLandlordHouseCertification', '网络错误，请稍后重试');
                });
        });
    }

    function showLandlordHouseAdd() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>填写房源信息</h2>
        <form id="publishHouseForm">
            <input type="text" id="htitle" name="htitle" class="three" placeholder="房源标题">
            <input type="number" id="hfloor" name="hfloor" class="three" placeholder="楼层">
            <input type="number" id="htotalFloors" name="htotalFloors" class="three" placeholder="总楼层数">
            <input type="number" id="hrent" name="hrent" class="three" placeholder="租金">
            <input type="number" id="harea" name="harea" class="three" placeholder="面积（平方米）">
            <input type="number" id="htotalTenants" name="htotalTenants" class="three" placeholder="总租户数量">
            <input type="number" id="hremainingVacancies" name="hremainingVacancies" class="three" placeholder="剩余空闲数量">
            <input type="datetime-local" id="havailableFrom" name="havailableFrom" class="three" placeholder="可入住时间" required>
            <select id="hpetFriendly" name="hpetFriendly" class="three" required>
                <option value="">是否同意养宠物</option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <select id="hdirection" name="hdirection" class="three" required>
                <option value="">房屋朝向</option>
                <option value="南">南</option>
                <option value="北">北</option>
            </select>
            <textarea id="hrooms" name="hrooms" placeholder="房间布局" class="wide-textarea" rows="2"></textarea>
            <textarea id="hlocation" name="hlocation" placeholder="房源位置" class="wide-textarea" rows="2"></textarea>
            <textarea id="hfacilities" name="hfacilities" placeholder="配套设施" class="wide-textarea" rows="4"></textarea>
            <textarea id="htenantrequired" name="htenantrequired" placeholder="租客要求" class="wide-textarea" rows="4"></textarea>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="publishHouseResult"></div>
    `;
        // 绑定表单提交事件
        const publishHouseForm = document.getElementById('publishHouseForm');
        publishHouseForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const houseUpdateReq = {
                htitle: formData.get('htitle') === "" ? null : formData.get('htitle'),
                hfloor: formData.get('hfloor') === "" ? null : parseInt(formData.get('hfloor')),
                htotalFloors: formData.get('htotalFloors') === "" ? null : parseInt(formData.get('htotalFloors')),
                hrent: formData.get('hrent') === "" ? null : parseFloat(formData.get('hrent')),
                harea: formData.get('harea') === "" ? null : parseFloat(formData.get('harea')),
                htotalTenants: formData.get('htotalTenants') === "" ? null : parseInt(formData.get('htotalTenants')),
                hremainingVacancies: formData.get('hremainingVacancies') === "" ? null : parseInt(formData.get('hremainingVacancies')),
                havailableFrom: formData.get('havailableFrom') === "" ? null : formData.get('havailableFrom').replace('T', ' ')+':00',
                hpetFriendly: formData.get('hpetFriendly') === "" ? null : parseInt(formData.get('hpetFriendly')),
                hrooms: formData.get('hrooms') === "" ? null : formData.get('hrooms'),
                hfacilities: formData.get('hfacilities') === "" ? null : formData.get('hfacilities'),
                htenantrequired: formData.get('htenantrequired') === "" ? null : formData.get('htenantrequired'),
                hlocation: formData.get('hlocation') === "" ? null : formData.get('hlocation'),
                hdirection: formData.get('hdirection') === "" ? null : formData.get('hdirection'),
            };

            // 获取 token
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }

            // 发送 POST 请求
            fetch('/house/publish', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(houseUpdateReq)
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('publishHouseResult');
                    if (data.code === 200) {
                        // 成功提示并刷新页面或调用相关函数
                        resultDiv.innerHTML = `<p>添加成功！</p>`;
                        alert("房源信息添加成功！");
                        showLandlordHouseSearch(); // 刷新房源列表
                    } else {
                        // 显示错误信息
                        resultDiv.innerHTML = `<p>添加失败：` + data.message + data.data + `</p>`;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('publishHouseResult');
                    resultDiv.innerHTML = `<p>网络错误，请稍后再试。</p>`;
                });
        });
    }

    function showTenantRentInfo(){
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>房租押金信息</h2>
        <form id="searchRentForm">
            <select id="ttransactionType" name="ttransactionType" class="three" required>
                <option value="">交易类型</option>
                <option value="押金支付">押金支付</option>
                <option value="租金支付">租金支付</option>
            </select>
            <select id="tStatus" name="tStatus" class="three" required>
                <option value="">交易状态</option>
                <option value="待支付">待支付</option>
                <option value="已支付">已支付</option>
            </select>
            <input type="datetime-local" id="startTime" name="startTime" class="three" placeholder="开始时间" required>
            <input type="datetime-local" id="endTime" name="endTime" class="three" placeholder="结束时间" required>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="rentInfoSearchResult"></div>
    `;
        // 绑定表单提交事件
        const searchRentForm = document.getElementById('searchRentForm');
        searchRentForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const QueryTransactionReq = {
                ttransactionType: formData.get('ttransactionType') === "" ? null : formData.get('ttransactionType'),
                tstatus: formData.get('tStatus') === "" ? null : formData.get('tStatus'),
                startTime: formData.get('startTime') === "" ? null : formData.get('startTime').replace('T', ' ')+':00',
                endTime: formData.get('endTime') === "" ? null : formData.get('endTime').replace('T', ' ')+':00',
            };
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }
            // 发送 POST 请求
            fetch('/rent/queryRentTenant', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(QueryTransactionReq)
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('rentInfoSearchResult');
                    resultDiv.innerHTML = '';

                    if (data.code !== 200) {
                        resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                        return;
                    }
                    // 创建主表格
                    const mainTable = document.createElement('main');
                    mainTable.setAttribute('class', 'table');
                    const headerSection = document.createElement('section');
                    headerSection.setAttribute('class', 'header');
                    const headTitle = document.createElement('h1');
                    headTitle.textContent = '查询结果';
                    headerSection.appendChild(headTitle);
                    mainTable.appendChild(headerSection);
                    const shellSection = document.createElement('section');
                    shellSection.setAttribute('class', 'shell');
                    const shellTable = document.createElement('table');
                    const shellThead = document.createElement('thead');
                    const headRow = document.createElement('tr');
                    ['房东姓名', '租户姓名', '交易类型', '交易金额','交易状态','支付时间','操作'].forEach(headerText => {
                        const shellCell = document.createElement('th');
                        shellCell.textContent = headerText;
                        headRow.appendChild(shellCell);
                        shellThead.appendChild(headRow);
                    });
                    shellTable.appendChild(shellThead);
                    const shellTbody = document.createElement('tbody');

                    // 渲染数据
                    data.data.forEach(item => {
                        const row = document.createElement('tr');
                        ['lname', 'tname', 'ttransactionType', 'tamount','tstatus','tpaytime'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });
                        const actionCell = document.createElement('td');

                        // 查看详情按钮
                        if(item['tstatus'] === '待支付'){
                            const houseButton = document.createElement('button');
                            houseButton.setAttribute('class', 'button');
                            houseButton.textContent = '支付';
                            houseButton.addEventListener('click', () => payDeposit(item['transactionId']));
                            actionCell.appendChild(houseButton);
                        }
                        else{
                            actionCell.textContent = '-';
                        }

                        row.appendChild(actionCell);
                        shellTbody.appendChild(row);

                        shellTable.appendChild(shellTbody);
                        shellSection.appendChild(shellTable);
                        mainTable.appendChild(shellSection);
                        resultDiv.appendChild(mainTable);
                    });
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('rentInfoSearchResult');
                    resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
                });

        });
    }


    // 支付租金函数
    function payDeposit(transactionId) {
        const token = "<%= token %>";

        if (!token) {
            alert("用户未登录，请先登录！");
            return Promise.reject();
        }

        const payload = {
            transactionId: transactionId
        };

        return fetch('/rent/payRent', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(payload)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('支付成功！');
                    showTenantRentInfo(); // 重新加载数据
                    return Promise.resolve(); // 返回一个成功的 Promise
                } else {
                    alert('支付失败：' + data.message);
                    return Promise.reject(); // 返回一个失败的 Promise
                }
            })
            .catch(error => {
                console.error('支付请求异常:', error);
                alert('支付失败，请稍后再试。');
                return Promise.reject(); // 返回一个失败的 Promise
            });
    }



    //房东查看租户租金状态
    function showLandlordRentInfo(){
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染表单 HTML
        content.innerHTML = `
        <h2>房租押金信息</h2>
        <form id="searchRentForm">
            <select id="ttransactionType" name="ttransactionType" class="three" required>
                <option value="">交易类型</option>
                <option value="押金支付">押金支付</option>
                <option value="租金支付">租金支付</option>
            </select>
            <select id="tStatus" name="tStatus" class="three" required>
                <option value="">交易状态</option>
                <option value="待支付">待支付</option>
                <option value="已支付">已支付</option>
            </select>
            <input type="datetime-local" id="startTime" name="startTime" class="three" placeholder="开始时间" required>
            <input type="datetime-local" id="endTime" name="endTime" class="three" placeholder="结束时间" required>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="rentInfoSearchResult"></div>
    `;
        // 绑定表单提交事件
        const searchRentForm = document.getElementById('searchRentForm');
        searchRentForm.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止默认提交行为

            // 获取表单数据
            const formData = new FormData(this);
            const QueryTransactionReq = {
                ttransactionType: formData.get('ttransactionType') === "" ? null : formData.get('ttransactionType'),
                tstatus: formData.get('tStatus') === "" ? null : formData.get('tStatus'),
                startTime: formData.get('startTime') === "" ? null : formData.get('startTime').replace('T', ' ')+':00',
                endTime: formData.get('endTime') === "" ? null : formData.get('endTime').replace('T', ' ')+':00',
            };
            const token = "<%= token %>";
            if (!token) {
                alert("用户未登录，请先登录！");
                return;
            }
            // 发送 POST 请求
            fetch('/rent/queryRentLandlord', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(QueryTransactionReq)
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('rentInfoSearchResult');
                    resultDiv.innerHTML = '';

                    if (data.code !== 200) {
                        resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                        return;
                    }
                    // 创建主表格
                    const mainTable = document.createElement('main');
                    mainTable.setAttribute('class', 'table');
                    const headerSection = document.createElement('section');
                    headerSection.setAttribute('class', 'header');
                    const headTitle = document.createElement('h1');
                    headTitle.textContent = '查询结果';
                    headerSection.appendChild(headTitle);
                    mainTable.appendChild(headerSection);
                    const shellSection = document.createElement('section');
                    shellSection.setAttribute('class', 'shell');
                    const shellTable = document.createElement('table');
                    const shellThead = document.createElement('thead');
                    const headRow = document.createElement('tr');
                    ['房东姓名', '租户姓名', '交易类型', '交易金额','交易状态','支付时间'].forEach(headerText => {
                        const shellCell = document.createElement('th');
                        shellCell.textContent = headerText;
                        headRow.appendChild(shellCell);
                        shellThead.appendChild(headRow);
                    });
                    shellTable.appendChild(shellThead);
                    const shellTbody = document.createElement('tbody');

                    // 渲染数据
                    data.data.forEach(item => {
                        const row = document.createElement('tr');
                        ['lname', 'tname', 'ttransactionType', 'tamount','tstatus','tpaytime'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });

                        shellTbody.appendChild(row);

                        shellTable.appendChild(shellTbody);
                        shellSection.appendChild(shellTable);
                        mainTable.appendChild(shellSection);
                        resultDiv.appendChild(mainTable);
                    });
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('rentInfoSearchResult');
                    resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`;
                });

        });
    }



    function showLandlordAppointment() {
        // 获取内容容器
        const content = document.getElementById('content');

        // 渲染标题和结果容器
        content.innerHTML = `
    <h2>租户预约列表</h2>
    <div id="landlordAppointmentList"></div>
    `;

        // 获取 token
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 发送 POST 请求获取预约列表
        fetch('/appointments/view/landlord', {
            method: 'POST',
            headers: {
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);

                const resultDiv = document.getElementById('landlordAppointmentList');
                resultDiv.innerHTML = ''; // 清空之前的结果

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 创建主表格
                const mainTable = document.createElement('table');
                mainTable.setAttribute('class', 'table');

                // 创建表头
                const thead = document.createElement('thead');
                const headRow = document.createElement('tr');
                ['房源名称', '租户姓名', '预约日期', '状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headRow.appendChild(th);
                });
                thead.appendChild(headRow);
                mainTable.appendChild(thead);

                // 创建表体
                const tbody = document.createElement('tbody');

                // 渲染数据
                data.data.forEach(appointment => {
                    const row = document.createElement('tr');

                    // 房源名称
                    const houseTitleCell = document.createElement('td');
                    houseTitleCell.textContent = appointment.htitle || '无';
                    row.appendChild(houseTitleCell);

                    // 租户姓名
                    const tenantNameCell = document.createElement('td');
                    tenantNameCell.textContent = appointment.tname || '无';
                    row.appendChild(tenantNameCell);

                    // 预约日期
                    const appointmentDateCell = document.createElement('td');
                    appointmentDateCell.textContent = new Date(appointment.appointmentDate).toLocaleString() || '无';
                    row.appendChild(appointmentDateCell);

                    // 状态
                    const statusCell = document.createElement('td');
                    statusCell.textContent = appointment.status || '无';
                    row.appendChild(statusCell);

                    // 操作按钮
                    const actionCell = document.createElement('td');
                    if (appointment.status === '待确认') {
                        // 接受按钮
                        const acceptButton = document.createElement('button');
                        acceptButton.textContent = '接受';
                        acceptButton.setAttribute('class', 'button accept-button');
                        acceptButton.addEventListener('click', () => handleAcceptAppointment(appointment.appointmentId));
                        actionCell.appendChild(acceptButton);

                        // 拒绝按钮
                        const rejectButton = document.createElement('button');
                        rejectButton.textContent = '拒绝';
                        rejectButton.setAttribute('class', 'button reject-button');
                        rejectButton.addEventListener('click', () => handleRejectAppointment(appointment.appointmentId));
                        actionCell.appendChild(rejectButton);
                    } else {
                        // actionCell.textContent = '无可用操作'; // 如果状态不是“待确认”，显示提示信息
                    }

                    row.appendChild(actionCell);

                    tbody.appendChild(row);
                });

                mainTable.appendChild(tbody);
                resultDiv.appendChild(mainTable);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('landlordAppointmentList');
                resultDiv.innerHTML = `<p>加载失败，请稍后重试。</p>`;
            });
    }

    function handleAcceptAppointment(appointmentId) {
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造请求参数
        const requestBody = {
            appointmentId: appointmentId,
            status: "已接受" // 状态为接受
        };

        // 发送 POST 请求
        fetch('/appointments/confirm', {
            method: 'POST',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    // 操作成功
                    handleSuccess('showLandlordAppointment'); // 刷新页面
                } else {
                    // 操作失败
                    handleFail('showLandlordAppointment', data.message || '操作失败');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                handleFail('showLandlordAppointment', '网络错误，请稍后重试');
            });
    }

    function handleRejectAppointment(appointmentId) {
        const token = "<%= token %>";
        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 构造请求参数
        const requestBody = {
            appointmentId: appointmentId,
            status: "已拒绝" // 状态为拒绝
        };

        // 发送 POST 请求
        fetch('/appointments/confirm', {
            method: 'POST',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    // 操作成功
                    handleSuccess('showLandlordAppointment'); // 刷新页面
                } else {
                    // 操作失败
                    handleFail('showLandlordAppointment', data.message || '操作失败');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                handleFail('showLandlordAppointment', '网络错误，请稍后重试');
            });
    }

    function RentClean() {
        let content = document.getElementById('RentManagement');
        content.innerHTML = '';
    }
    function showRentManagement() {
        RentClean();
        let content = document.getElementById('RentManagement');
        content.innerHTML = '' +
            '<li><a onclick="showLandlordRentInfo()" class="small-text">查看租户房租押金记录</a></li>';
    }



    function LandlordContactsClean(){
        let content = document.getElementById('LandlordContactsManagement');
        content.innerHTML='';
    }

    //房东查看合同管理
    function showLandlordContactsManagement() {
        LandlordContactsClean(); // 如果需要清除其他内容，调用清理函数

        let content = document.getElementById('LandlordContactsManagement');
        content.innerHTML = ``+
            `<li><a onclick="showLandlordContracts()" class="small-text">查看合同列表</a></li>`+`
            <li><a onclick="showPublicLandlordContracts()" class="small-text">发布合同</a></li>`
        ;
    }

    // 房东查看合同列表
    function showLandlordContracts() {
        const content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
        <h2>房东合同列表</h2>
        <div id="contractsInfoResult"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用房东查看合同列表接口
        fetch('/contacts/viewLandlordContracts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    // 如果接口返回错误信息，显示错误
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同列表
                const contractsList = data.data.contractsList;

                if (contractsList.length === 0) {
                    resultDiv.innerHTML = `<p>暂无合同信息</p>`;
                    return;
                }

                // 创建表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['合同ID', '开始时间', '结束时间', '租金金额', '房屋ID', '押金金额', '租户ID', '合同状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractsList.forEach(contract => {
                    const row = document.createElement('tr');

                    // 合同字段映射到表格中
                    [
                        contract.contractId,
                        contract.cstartDate,
                        contract.cendDate,
                        contract.crentAmount.toFixed(2),
                        contract.chouseId,
                        contract.cdepositAmount.toFixed(2),
                        contract.ctenantId,
                        contract.cstatus
                    ].forEach(cellData => {
                        const td = document.createElement('td');
                        td.textContent = cellData;
                        td.style.border = '1px solid #ddd';
                        td.style.padding = '8px';
                        row.appendChild(td);
                    });

                    // 添加"查看详情"按钮
                    const tdAction = document.createElement('td');
                    const detailButton = document.createElement('button');
                    detailButton.textContent = '查看详情';
                    detailButton.style.padding = '5px 10px';
                    detailButton.style.margin = '5px';
                    detailButton.style.backgroundColor = '#4CAF50';
                    detailButton.style.color = 'white';
                    detailButton.style.border = 'none';
                    detailButton.style.cursor = 'pointer';

                    // 为每个按钮添加点击事件，传递合同ID
                    detailButton.addEventListener('click', () => {
                        showLandlordContractDetail(contract.contractId); // 调用查看详情的函数
                    });

                    tdAction.appendChild(detailButton);

                    // 只有当合同状态为"未生效"，才显示确认和拒绝按钮
                    if (contract.cstatus === '已生效' ) {
                        // 违约终止合同按钮
                        const terminateButton = document.createElement('button');
                        terminateButton.textContent = '违约终止';
                        terminateButton.style.padding = '5px 10px';
                        terminateButton.style.margin = '5px';
                        terminateButton.style.backgroundColor = '#FF5733';
                        terminateButton.style.color = 'white';
                        terminateButton.style.border = 'none';
                        terminateButton.style.cursor = 'pointer';

                        // 为每个"违约终止"按钮添加点击事件，传递合同ID
                        terminateButton.addEventListener('click', () => {
                            terminateContract(contract.contractId); // 调用违约终止的函数
                        });

                        tdAction.appendChild(terminateButton);

                        // 正常结束合同按钮
                        const endButton = document.createElement('button');
                        endButton.textContent = '结束合同';
                        endButton.style.padding = '5px 10px';
                        endButton.style.margin = '5px';
                        endButton.style.backgroundColor = '#007BFF';
                        endButton.style.color = 'white';
                        endButton.style.border = 'none';
                        endButton.style.cursor = 'pointer';

                        // 为每个"结束合同"按钮添加点击事件，传递合同ID
                        endButton.addEventListener('click', () => {
                            endContract(contract.contractId); // 调用结束合同的函数
                        });

                        tdAction.appendChild(endButton);
                    }

                    row.appendChild(tdAction);
                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }

    // 违约终止合同
    function terminateContract(contractId) {
        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用违约终止合同接口
        fetch('/contacts/terminateContract', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('合同已违约终止！');
                    showLandlordContracts(); // 终止合同后刷新合同列表
                } else {
                    alert('合同终止失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                alert('合同终止失败，请稍后重试。');
            });
    }

    // 正常结束合同
    function endContract(contractId) {
        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用结束合同接口
        fetch('/contacts/endContract', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert('合同已结束！');
                    showLandlordContracts(); // 结束合同后刷新合同列表
                } else {
                    alert('合同结束失败：' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                alert('合同结束失败，请稍后重试。');
            });
    }


    // 房东查看租户合同详情
    function showLandlordContractDetail(contractId) {
        let content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
        <h2>合同详细信息</h2>
        <button onclick="goBack()" class="back-button">返回合同列表</button>
        <div id="contractDetailResult"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 调用合同详细信息接口
        fetch('/contacts/getContractsDetail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify({ contractsId: contractId }) // 传递合同ID
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('contractDetailResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    // 如果接口返回错误信息，显示错误
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同详情
                const contract = data.data;

                // 构建合同详情表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 合同详情的键值对
                const contractDetails = [
                    { key: '合同名称', value: contract.tname || '未提供' },
                    { key: '开始时间', value: contract.cstartDate },
                    { key: '结束时间', value: contract.cendDate },
                    { key: '租金金额', value: contract.crentAmount.toFixed(2) },
                    { key: '押金金额', value: contract.cdepositAmount.toFixed(2) },
                    { key: '附加条款', value: contract.cadditions || '无' },
                    { key: '房东名称', value: contract.lname || '未提供' }
                ];

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['字段', '值'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractDetails.forEach(detail => {
                    const row = document.createElement('tr');

                    // 字段名
                    const keyCell = document.createElement('td');
                    keyCell.textContent = detail.key;
                    keyCell.style.border = '1px solid #ddd';
                    keyCell.style.padding = '8px';

                    // 字段值
                    const valueCell = document.createElement('td');
                    valueCell.textContent = detail.value;
                    valueCell.style.border = '1px solid #ddd';
                    valueCell.style.padding = '8px';

                    row.appendChild(keyCell);
                    row.appendChild(valueCell);
                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error); // 打印错误信息到控制台
                const resultDiv = document.getElementById('contractDetailResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }

    // 返回合同列表的函数
    function goBack() {
        // 返回到合同列表页面
        showLandlordContracts(); // 直接调用查看合同列表的函数
    }

    // CSS 样式：为返回按钮添加样式
    const style = document.createElement('style');
    style.innerHTML = `
    .back-button {
        background-color: #007BFF;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
    }
    .back-button:hover {
        background-color: #0056b3;
    }
`;
    document.head.appendChild(style);




    function showPublicLandlordContracts() {
        const content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
    <h2>房东合同列表</h2>
    <div id="contractsInfoResult"></div>
`;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 获取房东合同列表
        fetch('/contacts/viewLandlordContracts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同列表
                const contractsList = data.data.contractsList;

                if (contractsList.length === 0) {
                    resultDiv.innerHTML = `<p>暂无合同信息</p>`;
                    return;
                }

                // 创建表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['合同ID', '开始时间', '结束时间', '租金金额', '房屋ID', '押金金额', '租户ID', '合同状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractsList.forEach(contract => {
                    const row = document.createElement('tr');

                    // 合同字段映射到表格中
                    [
                        contract.contractId,
                        contract.cstartDate,
                        contract.cendDate,
                        contract.crentAmount.toFixed(2),
                        contract.chouseId,
                        contract.cdepositAmount.toFixed(2),
                        contract.ctenantId,
                        contract.cstatus
                    ].forEach(cellData => {
                        const td = document.createElement('td');
                        td.textContent = cellData;
                        td.style.border = '1px solid #ddd';
                        td.style.padding = '8px';
                        row.appendChild(td);
                    });

                    // 创建操作列
                    const tdAction = document.createElement('td');

                    // 创建发布合同按钮
                    const publishButton = document.createElement('button');
                    publishButton.textContent = '发布合同';
                    publishButton.style.padding = '5px 10px';
                    publishButton.style.margin = '5px';
                    publishButton.style.backgroundColor = '#4CAF50';
                    publishButton.style.color = 'white';
                    publishButton.style.border = 'none';
                    publishButton.style.cursor = 'pointer';

                    // 使用箭头函数传递合同ID、房屋ID和租户ID
                    publishButton.addEventListener('click', () => handlePublishContract(contract.contractId, contract.chouseId, contract.ctenantId));

                    tdAction.appendChild(publishButton);
                    row.appendChild(tdAction); // 将按钮放置在表格的最右侧

                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }

    function showLandlordPublishContractPage() {
        const content = document.getElementById('content');

        // 动态设置页面结构
        content.innerHTML = `
    <h2>房东合同列表</h2>
    <div id="contractsInfoResult"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 获取房东合同列表
        fetch('/contacts/viewLandlordContracts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            }
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = ''; // 清空容器

                if (data.code !== 200) {
                    resultDiv.innerHTML = `<p>查询失败：` + data.message + `</p>`;
                    return;
                }

                // 获取合同列表
                const contractsList = data.data.contractsList;

                if (contractsList.length === 0) {
                    resultDiv.innerHTML = `<p>暂无合同信息</p>`;
                    return;
                }

                // 创建表格
                const table = document.createElement('table');
                table.style.width = '100%';
                table.style.borderCollapse = 'collapse';

                // 表头
                const thead = document.createElement('thead');
                const headerRow = document.createElement('tr');
                ['合同ID', '开始时间', '结束时间', '租金金额', '房屋ID', '押金金额', '租户ID', '合同状态', '操作'].forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    th.style.border = '1px solid #ddd';
                    th.style.padding = '8px';
                    th.style.textAlign = 'left';
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                // 表体
                const tbody = document.createElement('tbody');
                contractsList.forEach(contract => {
                    const row = document.createElement('tr');

                    // 合同字段映射到表格中
                    [
                        contract.contractId,
                        contract.cstartDate,
                        contract.cendDate,
                        contract.crentAmount.toFixed(2),
                        contract.chouseId,
                        contract.cdepositAmount.toFixed(2),
                        contract.ctenantId,
                        contract.cstatus
                    ].forEach(cellData => {
                        const td = document.createElement('td');
                        td.textContent = cellData;
                        td.style.border = '1px solid #ddd';
                        td.style.padding = '8px';
                        row.appendChild(td);
                    });

                    // 创建操作列
                    const tdAction = document.createElement('td');

                    // 创建发布合同按钮
                    const publishButton = document.createElement('button');
                    publishButton.textContent = '发布合同';
                    publishButton.style.padding = '5px 10px';
                    publishButton.style.margin = '5px';
                    publishButton.style.backgroundColor = '#4CAF50';
                    publishButton.style.color = 'white';
                    publishButton.style.border = 'none';
                    publishButton.style.cursor = 'pointer';

                    // 使用箭头函数传递合同ID、房屋ID和租户ID
                    publishButton.addEventListener('click', () => handlePublishContract(contract.contractId));

                    tdAction.appendChild(publishButton);
                    row.appendChild(tdAction); // 将按钮放置在表格的最右侧

                    tbody.appendChild(row);
                });

                table.appendChild(tbody);
                resultDiv.appendChild(table);
            })
            .catch(error => {
                console.error('Error:', error);
                const resultDiv = document.getElementById('contractsInfoResult');
                resultDiv.innerHTML = `<p>加载失败，请稍后再试。</p>`; // 显示错误信息
            });
    }

    function handlePublishContract(contractId) {
        console.log("发布合同，合同ID:", contractId);

        // 动态创建发布合同页面
        let content = document.getElementById('content');

        // 动态设置页面结构，添加返回按钮
        content.innerHTML = `
    <h2>发布合同</h2>
    <button onclick="goBackToLandlordContracts()" class="back-button">返回合同列表</button>
    <div id="publishContractForm"></div>
    `;

        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        // 创建发布合同表单
        const form = document.createElement('form');
        form.id = 'publishContractForm';
        form.innerHTML = `
    <div class="form-group">
        <label for="contractId">合同ID:</label>
        <input type="text" id="contractId" name="contractId" value="" readonly />
        <br>
    </div>
    <div class="form-group">
        <label for="cStartDate">合同开始日期:</label>
        <input type="date" id="cStartDate" name="cStartDate" required />
        <br>
    </div>
    <div class="form-group">
        <label for="cEndDate">合同结束日期:</label>
        <input type="date" id="cEndDate" name="cEndDate" required />
        <br>
    </div>
    <div class="form-group">
        <label for="cRentAmount">租金金额:</label>
        <input type="number" id="cRentAmount" name="cRentAmount" min="0" max="9999" required />
        <br>
    </div>
    <div class="form-group">
        <label for="cDepositAmount">押金金额:</label>
        <input type="number" id="cDepositAmount" name="cDepositAmount" min="0" max="9999" required />
        <br>
    </div>
    <div class="form-group">
        <label for="cAdditions">附加条款:</label>
        <textarea id="cAdditions" name="cAdditions"></textarea>
        <br>
    </div>
    <div class="form-group">
        <button type="submit" class="publish-button">发布合同</button>
    </div>
    `;

        content.appendChild(form);

        // 设置 contractId 的 value 为传入的 contractId
        document.getElementById('contractId').value = contractId;

        // 提交表单
        form.addEventListener('submit', function (e) {
            e.preventDefault(); // 阻止默认表单提交

            // 获取表单数据
            const contractData = {
                contractId: document.getElementById('contractId').value,
                cStartDate: document.getElementById('cStartDate').value,
                cEndDate: document.getElementById('cEndDate').value,
                cRentAmount: parseFloat(document.getElementById('cRentAmount').value),
                cDepositAmount: parseFloat(document.getElementById('cDepositAmount').value),
                cAdditions: document.getElementById('cAdditions').value || null
            };

            // 调用发布合同接口
            publishContract(contractData);
        });

        // 添加样式
        const style = document.createElement('style');
        style.innerHTML = `
    #publishContractForm {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color: #f9f9f9;
    }
    .form-group {
        margin-bottom: 15px;
        display: flex;
        align-items: center; /* 标签和输入框在垂直方向居中 */
    }
    .form-group label {
        margin-bottom: 0;
        margin-right: 15px; /* 为标签和输入框之间添加间距 */
        width: 150px; /* 标签固定宽度 */
        font-weight: bold;
    }
    .form-group input, .form-group textarea {
        width: calc(100% - 170px); /* 留出标签的宽度 */
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .publish-button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
    }
    .publish-button:hover {
        background-color: #45a049;
    }
    .back-button {
        background-color: #f44336;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-bottom: 20px;
    }
    .back-button:hover {
        background-color: #e53935;
    }
    `;
        document.head.appendChild(style);
    }

    // 返回合同列表的函数
    function goBackToLandlordContracts() {
        showLandlordPublishContractPage();
    }




    // 你可以创建一个函数来处理实际的发布合同请求
    function publishContract(contractData) {
        const token = "<%= token %>"; // 从服务器模板获取 token

        if (!token) {
            alert("用户未登录，请先登录！");
            return;
        }

        fetch('/contacts/publish', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(contractData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    alert("合同发布成功！");
                    // 在这里你可以根据需求刷新页面或跳转到其他页面
                    window.location.href = "/landlord/contract-list"; // 示例：跳转到合同列表页
                } else {
                    alert("发布失败：" + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("发布失败，请稍后重试。");
            });
    }


























</script>
</body>
</html>
