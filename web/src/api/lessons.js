async function removeLesson(lessonId) {
    try {
        const response = await fetch('/data.json');
        const data = await response.json();
        const user = data.group.users.find(u => u.id === 2);
        return user.lessons.find(l => l.id === lessonId) !== null ? true : false
    } catch (error) {
        console.error('Error finding lesson:', error);
        return false;
    }
}

export {removeLesson}