const chatWindow = document.getElementById('chat-window');
const chatForm = document.getElementById('chat-form');
const userInput = document.getElementById('user-input');

function createAvatar(sender) {
    const avatar = document.createElement('div');
    avatar.className = 'avatar ' + sender;
    if (sender === 'user') {
        avatar.textContent = 'U'; // You can use initials or an icon
    } else {
        avatar.textContent = '🤖'; // Bot icon
    }
    return avatar;
}

function appendMessage(text, sender) {
    const row = document.createElement('div');
    row.className = 'message-row ' + sender;
    const msg = document.createElement('div');
    msg.className = 'message ' + sender;
    msg.textContent = text;
    const avatar = createAvatar(sender);
    if (sender === 'user') {
        row.appendChild(msg);
        row.appendChild(avatar);
    } else {
        row.appendChild(avatar);
        row.appendChild(msg);
    }
    chatWindow.appendChild(row);
    chatWindow.scrollTop = chatWindow.scrollHeight;
}

chatForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const text = userInput.value.trim();
    if (!text) return;
    appendMessage(text, 'user');
    userInput.value = '';
    appendMessage('...', 'bot');
    try {
        const res = await fetch('/api/chat', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ message: text })
        });
        const data = await res.json();
        chatWindow.removeChild(chatWindow.lastChild); // remove '...'
        appendMessage(data.response || 'No response', 'bot');
    } catch (err) {
        chatWindow.removeChild(chatWindow.lastChild);
        appendMessage('Error contacting server.', 'bot');
    }
});
