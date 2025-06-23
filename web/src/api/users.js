function createUser(name) {
    return "Axw1"
}

async function getUser() {
    try {
        const response = await fetch('/data.json');
        const data = await response.json();
        const user = data.group.users.find(u => u.id === 2);
        return user
  } catch (error) {
        console.error('Error fetching user data:', error);
        return null;
  }
}

export {createUser, getUser}