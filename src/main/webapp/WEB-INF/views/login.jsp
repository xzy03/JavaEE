<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: linear-gradient(90deg, cyan, purple);
            background-size: 400%;
            animation: myanimation 10s infinite;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .login-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container form {
            display: flex;
            flex-direction: column;
        }
        .login-container input, .login-container select {
            margin-bottom: 15px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .login-container button {
            padding: 10px;
            font-size: 16px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #4cae4c;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
        .success {
            color: green;
            text-align: center;
            margin-bottom: 20px;
        }
        @keyframes myanimation {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
    </style>
    <script>
        function handleLogin(event) {
            event.preventDefault(); // 阻止表单默认提交行为

            const loginName = document.getElementById("loginName").value;
            const password = document.getElementById("password").value;
            const userType = document.getElementById("userType").value; // 获取登录类别

            let requestBody;

            // 根据用户类型选择接口
            let loginUrl = '';
            if (userType === '管理员') {
                loginUrl = '/admins/login';
                requestBody = {
                    adPhone: loginName,
                    adPasswordHash: password
                };
            } else if (userType === '大学生租户') {
                loginUrl = '/tenant/login';
                requestBody = {
                    phoneNum: loginName,
                    password: password
                };
            } else if (userType === '房东') {
                loginUrl = '/landlords/login';
                requestBody = {
                    lPhoneNumber: loginName,
                    lPassword: password
                };
            }

            // 发送登录请求
            fetch(loginUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(result => {
                    if (result.code === 200) {
                        // 登录成功，获取 token
                        const token = result.data.token;

                        // 创建一个隐藏的表单，用于携带 token 和 userType
                        const form = document.createElement("form");
                        form.method = "POST"; // 设置请求方法为 POST
                        form.action = "/userDashboard"; // 设置目标 URL

                        // 添加 token 到隐藏表单
                        const tokenInput = document.createElement("input");
                        tokenInput.type = "hidden";
                        tokenInput.name = "Authorization"; // 后端通过 body 获取 token
                        tokenInput.value = `Bearer `+token;

                        // 添加 userType 到隐藏表单
                        const userTypeInput = document.createElement("input");
                        userTypeInput.type = "hidden";
                        userTypeInput.name = "userType"; // 传递用户类别
                        userTypeInput.value = userType;

                        form.appendChild(tokenInput);
                        form.appendChild(userTypeInput);

                        // 将表单添加到文档中并提交
                        document.body.appendChild(form);
                        form.submit();
                    } else {
                        // 登录失败处理
                        document.getElementById("message").innerText = result.message;
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    document.getElementById("message").innerText = "请求失败，请稍后再试。";
                });
        }
    </script>
</head>
<body>
<div class="login-container">
    <h1>用户登录</h1>
    <form onsubmit="handleLogin(event)">
        <select id="userType" name="userType" required>
            <option value="" disabled selected>请选择登录类别</option>
            <option value="管理员">管理员</option>
            <option value="大学生租户">大学生租户</option>
            <option value="房东">房东</option>
        </select>
        <input id="loginName" type="text" name="loginName" placeholder="登录名" required>
        <input id="password" type="password" name="password" placeholder="密码" required>
        <button type="submit">登录</button>
        <div id="message"></div>
    </form>
</div>
</body>
</html>
