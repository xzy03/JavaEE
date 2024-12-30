<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: linear-gradient(90deg, lightblue, lightgreen);
            background-size: 400%;
            animation: gradientAnimation 10s infinite;
        }
        .register-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container form {
            display: flex;
            flex-direction: column;
        }
        .register-container input, .register-container select {
            margin-bottom: 15px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .register-container button {
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .register-container button:hover {
            background-color: #0056b3;
        }
        .message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
        @keyframes gradientAnimation {
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
        function handleRegister(event) {
            event.preventDefault(); // 阻止表单默认提交

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const email = document.getElementById("email").value;
            const phone = document.getElementById("phone").value;
            const userType = document.getElementById("userType").value; // 获取注册类别

            let requestBody;

            // 根据用户类别选择注册接口
            let registerUrl = '';
            if (userType === '管理员') {
                registerUrl = '/admins/register';
                requestBody = {
                    adUsername: username,
                    adPasswordHash: password,
                    adEmail: email,
                    adPhone: phone
                };
            } else if (userType === '大学生租户') {
                registerUrl = '/tenant/register';
                requestBody = {
                    tAccount: username,
                    tPassword: password,
                    tEmail: email,
                    tPhoneNumber: phone
                };
            } else if (userType === '房东') {
                registerUrl = '/landlords/register';
                requestBody = {
                    lAccount: username,
                    lPassword: password,
                    lEmail: email,
                    lPhoneNumber: phone
                };
            }

            // 发送注册请求
            fetch(registerUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            })
                .then(response => response.json())
                .then(result => {
                    if (result.code === 200) {
                        // 注册成功
                        document.getElementById("message").innerText = "注册成功，请登录！";
                        setTimeout(() => {
                            window.location.href = "/login"; // 跳转到登录页面
                        }, 2000);
                    } else {
                        // 注册失败，显示错误信息
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
<div class="register-container">
    <h1>用户注册</h1>
    <form onsubmit="handleRegister(event)">
        <input id="username" type="text" name="username" placeholder="用户名" required>
        <input id="password" type="password" name="password" placeholder="密码" required>
        <input id="email" type="email" name="email" placeholder="邮箱" required>
        <input id="phone" type="text" name="phone" placeholder="电话" required>
        <select id="userType" name="userType" required>
            <option value="" disabled selected>请选择注册类别</option>
            <option value="管理员">管理员</option>
            <option value="大学生租户">大学生租户</option>
            <option value="房东">房东</option>
        </select>
        <button type="submit">注册</button>
        <div id="message" class="message"></div>
    </form>
</div>
</body>
</html>
