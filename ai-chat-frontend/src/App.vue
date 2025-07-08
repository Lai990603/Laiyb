<template>
  <el-container class="app-container">
    <!-- 侧边栏：对话列表 -->
    <el-aside width="280px" class="conversation-sidebar">
      <div class="sidebar-header">
        <el-button type="primary" icon="Plus" @click="createNewConversation">新建对话</el-button>
      </div>
      <el-scrollbar class="conversation-list">
        <div v-for="conv in conversations" :key="conv.id" class="conversation-item" @click="switchConversation(conv)">
          <div class="conv-title">{{ conv.title }}</div>
          <div class="conv-time">{{ formatTime(conv.updateTime) }}</div>
        </div>
      </el-scrollbar>
    </el-aside>

    <!-- 主内容区：聊天界面 -->
    <el-container direction="vertical">
      <el-header class="chat-header">
        <div class="current-conv-title">{{ currentConversation?.title || '请选择对话' }}</div>
        <el-button type="text" icon="Delete" @click="deleteCurrentConversation">删除对话</el-button>
      </el-header>

      <el-main class="chat-messages">
        <el-scrollbar class="message-list">
          <div v-for="msg in messages" :key="msg.id" class="message-item">
            <div :class="['msg-avatar', msg.role === 'user' ? 'user-avatar' : 'ai-avatar']">
              {{ msg.role === 'user' ? 'U' : 'AI' }}
            </div>
            <div :class="['msg-content', msg.role === 'user' ? 'user-content' : 'ai-content']">
              {{ msg.content }}
            </div>
          </div>
        </el-scrollbar>
      </el-main>

      <el-footer class="chat-input-area">
        <el-input
          v-model="inputContent"
          type="textarea"
          :rows="4"
          placeholder="请输入消息..."
          class="message-input"
        ></el-input>
        <div class="input-actions">
          <el-button type="primary" @click="sendMessage">发送</el-button>
        </div>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ElMessage } from 'element-plus';

// 状态管理
const conversations = ref([]);
const messages = ref([]);
const currentConversation = ref(null);
const inputContent = ref('');

// 模拟数据 - 实际项目中应从API获取
onMounted(() => {
  // 加载对话列表
  loadConversations();
});

// 加载对话列表
const loadConversations = async () => {
  try {
    // 实际项目中替换为真实API调用
    // const res = await $api.get('/conversations/user/1');
    // conversations.value = res.data;
    conversations.value = [
      { id: 1, title: '日常聊天', updateTime: '2023-11-01 14:30' },
      { id: 2, title: '技术问题咨询', updateTime: '2023-11-01 10:15' }
    ];
  } catch (error) {
    ElMessage.error('加载对话列表失败');
  }
};

// 切换对话
const switchConversation = async (conv) => {
  currentConversation.value = conv;
  try {
    // 实际项目中替换为真实API调用
    // const res = await $api.get(`/messages/conversation/${conv.id}`);
    // messages.value = res.data;
    messages.value = [
      { id: 1, conversationId: 1, userId: 1, content: '你好！', role: 'user', sendTime: '14:30' },
      { id: 2, conversationId: 1, userId: 1, content: '您好！有什么可以帮助您的吗？', role: 'assistant', sendTime: '14:31' }
    ];
  } catch (error) {
    ElMessage.error('加载消息历史失败');
  }
};

// 创建新对话
const createNewConversation = async () => {
  try {
    // 实际项目中替换为真实API调用
    // const res = await $api.post('/conversations', { userId: 1, title: '新对话' });
    // conversations.value.unshift(res.data);
    // switchConversation(res.data);
    const newConv = { id: Date.now(), title: '新对话', updateTime: new Date().toLocaleString() };
    conversations.value.unshift(newConv);
    switchConversation(newConv);
    ElMessage.success('创建新对话成功');
  } catch (error) {
    ElMessage.error('创建对话失败');
  }
};

// 发送消息
const sendMessage = async () => {
  if (!currentConversation.value) {
    ElMessage.warning('请先选择或创建对话');
    return;
  }
  if (!inputContent.value.trim()) {
    ElMessage.warning('消息内容不能为空');
    return;
  }

  const userMsg = {
    id: Date.now(),
    conversationId: currentConversation.value.id,
    userId: 1,
    content: inputContent.value,
    role: 'user',
    sendTime: new Date().toLocaleTimeString()
  };
  messages.value.push(userMsg);
  inputContent.value = '';

  // 模拟AI回复
  setTimeout(() => {
    const aiMsg = {
      id: Date.now() + 1,
      conversationId: currentConversation.value.id,
      userId: 1,
      content: '这是AI的回复内容。您刚才说：' + userMsg.content,
      role: 'assistant',
      sendTime: new Date().toLocaleTimeString()
    };
    messages.value.push(aiMsg);
  }, 1000);
};

// 删除当前对话
const deleteCurrentConversation = async () => {
  if (!currentConversation.value) return;

  try {
    // 实际项目中替换为真实API调用
    // await $api.delete(`/conversations/${currentConversation.value.id}`, { params: { userId: 1 } });
    conversations.value = conversations.value.filter(conv => conv.id !== currentConversation.value.id);
    currentConversation.value = null;
    messages.value = [];
    ElMessage.success('删除对话成功');
  } catch (error) {
    ElMessage.error('删除对话失败');
  }
};

// 格式化时间
const formatTime = (timeStr) => {
  return new Date(timeStr).toLocaleString();
};
</script>

<style scoped>
.app-container { height: 100vh; }
.conversation-sidebar { background: #f5f5f5; border-right: 1px solid #e5e5e5; }
.sidebar-header { padding: 15px; border-bottom: 1px solid #e5e5e5; }
.conversation-list { height: calc(100vh - 60px); padding: 10px; }
.conversation-item { padding: 10px; margin-bottom: 5px; background: white; border-radius: 4px; cursor: pointer; }
.conversation-item:hover { background: #f0f0f0; }
.conv-title { font-weight: 500; }
.conv-time { font-size: 12px; color: #999; }

.chat-header { padding: 15px; border-bottom: 1px solid #e5e5e5; display: flex; justify-content: space-between; align-items: center; }
.current-conv-title { font-size: 18px; font-weight: 500; }
.chat-messages { padding: 20px; overflow: hidden; background: #f9f9f9; }
.message-list { height: 100%; }
.message-item { display: flex; margin-bottom: 15px; }
.msg-avatar { width: 40px; height: 40px; border-radius: 50%; display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; margin-right: 10px; }
.user-avatar { background: #409eff; }
.ai-avatar { background: #67c23a; }
.msg-content { max-width: 70%; padding: 10px 15px; border-radius: 10px; line-height: 1.5; }
.user-content { background: #409eff; color: white; margin-left: auto; }
.ai-content { background: white; border: 1px solid #e5e5e5; }
.chat-input-area { padding: 15px; border-top: 1px solid #e5e5e5; background: white; }
.message-input { margin-bottom: 10px; }
.input-actions { display: flex; justify-content: flex-end; }
</style>