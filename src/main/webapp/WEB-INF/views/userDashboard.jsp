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

            </c:when>
            <c:when test="${user == '房东'}">
                <li><a href="#landlordProfileManagement" onclick="showLandlordManagement()">个人信息管理</a></li>
                <div id="landlordProfileManagement"></div>
                <li><a href="#landlordHouseManagement" onclick="showLandlordHouseManagement()">我的房屋</a></li>
                <div id="landlordHouseManagement"></div>
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
        console.log('确认操作，租户ID：', Id);

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

        // console.log(requestBody);
        // console.log(url);

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
        console.log('拒绝操作，租户ID：', Id);

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
            tstatus: "未审核", // 只查询未审核的租户
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

                console.log(data);

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
                    console.log(tenant);
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
            tidentityStatus: "未审核", // 只查询未审核的身份证
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

                console.log(data);

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
                    console.log(tenant);
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
            lstatus: "未审核" // 只查询未审核的房东身份证
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

                console.log(data);

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
                    console.log(landlord);
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
            lhouseLicenseState: "未审核" // 只查询未审核的房产证
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

                console.log(data);

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
                    console.log(house);
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
        console.log(token);

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
                console.log(data);
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
            '<li><a onclick="showLandlordHouseAdd()" class="small-text">添加房源</a></li>';
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
        console.log(token);

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
                console.log(data);
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
        console.log(token);

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
                console.log(data);
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
        console.log(token);

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
                console.log(data);
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
                        console.log(img);
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
        <form id="editHouseForm">
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
            <textarea id="hrooms" name="hrooms" placeholder="房间布局" class="wide-textarea" rows="2"></textarea>
            <textarea id="hlocation" name="hlocation" placeholder="房源位置" class="wide-textarea" rows="2"></textarea>
            <textarea id="hfacilities" name="hfacilities" placeholder="配套设施" class="wide-textarea" rows="4"></textarea>
            <textarea id="htenantrequired" name="htenantrequired" placeholder="租客要求" class="wide-textarea" rows="4"></textarea>
            <button type="submit" class="submit-button">提交</button>
        </form>
        <div id="editHouseResult"></div>
    `;
    }

</script>
</body>
</html>
