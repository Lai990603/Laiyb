<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-title">AI聊天系统 - 登录</div>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item class="login-actions">
          <el-button type="primary" @click="handleLogin" class="login-btn">登录</el-button>
          <el-button type="text" @click="toRegister">没有账号？去注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();
const loginForm = ref({
  username: '',
  password: ''
});

const loginRules = ref({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能少于6位', trigger: 'blur' }
  ]
});

const handleLogin = async () => {
  // 实际项目中替换为真实API调用
  if (loginForm.value.username && loginForm.value.password) {
    // 模拟登录成功
    localStorage.setItem('token', 'mock-token-' + Date.now());
    localStorage.setItem('userId', '1');
    ElMessage.success('登录成功');
    router.push('/');
  } else {
    ElMessage.error('请输入用户名和密码');
  }
};

const toRegister = () => {
  router.push('/register');
};
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f5f5;
}
.login-card {
  width: 400px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
.login-title {
  text-align: center;
  font-size: 20px;
  margin-bottom: 20px;
  color: #303133;
}
.login-form {
  padding: 0 20px 20px;
}
.login-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.login-btn {
  width: 60%;
}
</style>