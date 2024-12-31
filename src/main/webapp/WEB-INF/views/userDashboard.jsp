<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cn.edu.zjut.utils.UserInfoUtils" %>
<%@ page import="cn.edu.zjut.entity.dto.UserTokenInfoDto" %>

<%
    // 从工具类中获取当前用户信息
    String token = request.getParameter("Authorization");
    System.out.println("进入userDashboard,当前用户token为：" + token);
    String user=request.getParameter("userType");

    // 将 userTokenInfoDto 放入 request 范围中
    request.setAttribute("user", user);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
        .sidebar a.small-text{
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
            background-image: url('background2.jpeg'); /* 替换为你的图片路径 */
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

        .simple-form{
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
            background-color:#fff8;
            box-shadow:0 5px 40px #0002;
        }
        .header .input-group img {
            width: 20px;
            height: 20px;
        }
        .header .input-group input {
            width: 100%;
            background-color:transparent;
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
        tbody tr:nth-child(even){
            background-color:#0000000b;
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
            background-color:#86e49d;
            color:#006b21;
        }
        tr:nth-child(4n-1) .button {
            background-color:#ebc474;
        }
        tr:nth-child(4n+1) .button {
            background-color:#d893a3;
            color:#b30021;
        }
        tr:nth-child(4n+2) .button {
            background-color:#6fcaea;
        }

        /* 样式表单 */
        form {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        form input {
            flex: 0 1 calc(25% - 10px);  /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form input.three{
            flex: 0 1 calc(33% - 10px);  /*每行三列，间距为10px */
        }

        form input.two{
            flex: 0 1 calc(50% - 10px);  /*每行两列，间距为10px */
        }

        form input.five{
            flex: 0 1 calc(20% - 10px);  /*每行五列，间距为10px */
        }

        form select {
            flex: 0 1 calc(25% - 10px);  /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form select.three{
            flex: 0 1 calc(33% - 10px);  /*每行三列，间距为10px */
        }

        form select.two{
            flex: 0 1 calc(50% - 10px);  /*每行两列，间距为10px */
        }

        form select.five{
            flex: 0 1 calc(20% - 10px);  /*每行五列，间距为10px */
        }

        form option{
            flex: 0 1 calc(25% - 10px);  /*每行四列，间距为10px */
            box-sizing: border-box;
            padding: 10px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form option.three{
            flex: 0 1 calc(33% - 10px);  /*每行三列，间距为10px */
        }

        form option.two{
            flex: 0 1 calc(50% - 10px);  /*每行两列，间距为10px */
        }

        form option.five{
            flex: 0 1 calc(20% - 10px);  /*每行五列，间距为10px */
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
            flex: 0 1 calc(25% - 10px);  /*每行四列，间距为10px */
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
                <li><a href="#studentManagement" onclick="showStudentManagement()">学生信息管理</a></li>
                <div id="studentManagement"></div>
                <li><a href="#teacherManagement" onclick="showTeacherManagement()">教师信息管理</a></li>
                <div id="teacherManagement"></div>
                <li><a href="#deptManagement" onclick="showDeptManagement()">专业信息管理</a></li>
                <div id="deptManagement"></div>
                <li><a href="#classManagement" onclick="showClassManagement()">班级信息管理</a></li>
                <div id="classManagement"></div>
                <li><a href="#courseManagement" onclick="showCourseManagement()">课程信息管理</a></li>
                <div id="courseManagement"></div>
                <li><a href="#teachManagement" onclick="showTeachManagement()">授课信息管理</a></li>
                <div id="teachManagement"></div>
                <li><a href="#scoreManagement" onclick="showScoreManagement()">选课信息管理</a></li>
                <div id="scoreManagement"></div>
            </c:when>
            <c:when test="${user == '大学生租户'}">
                <li><a href="#TenantProfileManagement" onclick="showTenantManagement()">个人信息管理</a></li>
                <div id="TenantProfileManagement"></div>

            </c:when>
            <c:when test="${user == '房东'}">
                <li><a href="#teacherInfo" onclick="showTeacherInfo()">教师个人信息</a></li>
                <li><a href="#searchCourse" onclick="showSearchCourse()"> 课程信息查询</a></li>
                <li><a href="#searchTeachAtTeacher" onclick="showSearchTeachAtTeacher()"> 个人授课查询</a></li>
                <li><a href="#showTeacherStudentScore" onclick="showTeacherStudentScoreInfo()"> 学生成绩总览</a></li>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>

    </ul>
    <div class="logout-btn">
        <button type="button" class="password" onclick="changePassword()">修改密码</button>
    </div>
    <div class="logout-btn">
        <form action="userLogout" method="post">
            <button type="submit">退出登录</button>
        </form>
    </div>
</div>
<div class="content" id="content" >
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
        failMessage.textContent = `操作失败：`+ (failReason || '未知原因'); // 如果没有提供原因，显示"未知原因"
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

    function TenantClean(){
        let content = document.getElementById('TenantProfileManagement');
        content.innerHTML='';
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
                    '学生账号': tenant.taccount || '无',
                    '姓名': tenant.tname || '无',
                    '性别': tenant.tsex || '无',
                    '大学': tenant.tuniversity || '无',
                    '电话': tenant.tphoneNumber || '无',
                    '余额': tenant.tbalance || '无'
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
            <p>当前余额：<span id="currentBalance">`+balance+`</span> 元</p>
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
                    body: JSON.stringify({ amount: Number(amount) })
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
            amount=amount*1.01;
            if (amount && !isNaN(amount) && Number(amount) > 0 && Number(amount) <= balance) {
                fetch('/payment/tenant', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "<%= token %>" // 添加 token
                    },
                    body: JSON.stringify({ amount: Number(-amount) })
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
            <textarea id="tIntroduction" name="tIntroduction" placeholder="个人介绍" class="wide-textarea" class="" rows="4"></textarea>
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
                taccount: formData.get('tAccount'),
                tphoneNumber: formData.get('tPhoneNumber'),
                temail: formData.get('tEmail'),
                tprofilePicture: formData.get('tProfilePicture'),
                tintroduction: formData.get('tIntroduction'),
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


    function showTenantStudentCertification(){

    }

    function showTenantIDCertification(){

    }

    function showStudentManagement(){
        boardClean();
        let content = document.getElementById('studentManagement');
        content.innerHTML='<li><a onclick="showSearchStudent()" class="small-text">学生信息查询</a></li>';
    }

    function showSearchStudent(){

    }




</script>
</body>
</html>
