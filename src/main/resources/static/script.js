async function loadMessages() {
    const res = await fetch('/get');
    const messages = await res.json();
    const list = document.getElementById('messagesList');
    list.innerHTML = '';

    // Reverse once, before rendering
    messages.reverse().forEach(msg => {
        const li = document.createElement('li');
        li.innerHTML = `
  <div class="msg-content">
    <div class="name">${msg.name}</div>
    <div class="text">${msg.text}</div>
    <div class="actions">
      <button class="edit-btn" onclick="editMessage(${msg.id})">Edit</button>
    </div>
  </div>
`;

        list.appendChild(li);
    });

}


async function sendMessage() {
    const name = document.getElementById('nameInput').value.trim();
    const message = document.getElementById('message').value.trim();
    if (!name || !message) {
        alert("Name and thought required");
        return;
    }

    const res = await fetch("/send", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, message })
    });

    if (res.ok) {
        document.getElementById('message').value = '';
        loadMessages();
    } else {
        alert("Failed to send.");
    }
}

async function editMessage(id) {
    const newText = prompt("Edit your thought:");
    if (newText === null) return;
    await fetch("/edit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, message: newText })
    });
    loadMessages();
}

document.getElementById("sendBtn").addEventListener("click", sendMessage);
loadMessages();